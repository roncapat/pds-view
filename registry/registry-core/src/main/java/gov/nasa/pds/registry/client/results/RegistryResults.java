//	Copyright 2013, by the California Institute of Technology.
//	ALL RIGHTS RESERVED. United States Government Sponsorship acknowledged.
//	Any commercial use must be negotiated with the Office of Technology 
//	Transfer at the California Institute of Technology.
//	
//	This software is subject to U. S. export control laws and regulations 
//	(22 C.F.R. 120-130 and 15 C.F.R. 730-774). To the extent that the software 
//	is subject to U.S. export control laws and regulations, the recipient has 
//	the responsibility to obtain export licenses or other export authority as 
//	may be required before exporting such information to foreign countries or 
//	providing access to foreign nationals.
//	
//	$Id: SearchCoreLauncher.java 12098 2013-09-18 15:53:49Z jpadams $
//
package gov.nasa.pds.registry.client.results;

import gov.nasa.pds.registry.client.RegistryClient;
import gov.nasa.pds.registry.exception.RegistryClientException;
import gov.nasa.pds.registry.exception.RegistryServiceException;
import gov.nasa.pds.registry.model.Association;
import gov.nasa.pds.registry.model.ExtrinsicObject;
import gov.nasa.pds.registry.model.PagedResponse;
import gov.nasa.pds.registry.model.wrapper.ExtendedExtrinsicObject;
import gov.nasa.pds.registry.query.AssociationFilter;
import gov.nasa.pds.registry.query.ExtrinsicFilter;
import gov.nasa.pds.registry.query.RegistryQuery;
import gov.nasa.pds.registry.util.Debugger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Wrapper for Registry Service PagedResponse object.  A simple utility to handles paging through the 
 * registry results to minimize memory load when a large number of products are being queries
 * 
 * @author jpadams
 *
 */
public class RegistryResults {

	/** Output logger **/
	private static Logger log = Logger.getLogger(RegistryResults.class.getName());
	
	/** In order to scale for very large registry products, need to page the registry output **/
	public static final int MAX_PAGE_LENGTH = 100;
	
	private int pageLength;
	
	private int queryMax;
	
	private int registryIndex;
	
	private int start;
	
	//private String version;
	
	private String currentRegistryUrl;
	
	private List<String> registryUrlList;
	
	private RegistryClient client;
	
	private List<Object> resultObjects;
	
	private RegistryQuery<?> query;
	
	private List<ResultsFilter> resultsFilterList;
	
	/** Maintain list of returned lids so we can just throw out any other versions **/
	private List<String> lidList;
	

	/**
	 * Constructor for a single registryUrl.  Creates list with registryUrl and calls main constructor.
	 * 
	 * @param registryUrl
	 * @param query
	 * @param version
	 * @param queryMax
	 * @throws RegistryClientException
	 */
	public RegistryResults(String registryUrl, RegistryQuery<?> query, int queryMax) throws RegistryClientException {
		this(Arrays.asList(registryUrl), query, queryMax, null);
	}
	
	/**
	 * Constructor for a single registryUrl.  Creates list with registryUrl and calls main constructor.
	 * 
	 * @param registryUrl
	 * @param query
	 * @param version
	 * @param queryMax
	 * @throws RegistryClientException
	 */
	public RegistryResults(String registryUrl, RegistryQuery<?> query, int queryMax, List<ResultsFilter> resultsFilterList) throws RegistryClientException {
		this(Arrays.asList(registryUrl), query, queryMax, resultsFilterList);
	}
	
	/**
	 * Constructor for list of registry urls. Call main constructor with null for filter list
	 * @param registryUrlList
	 * @param query
	 * @param version
	 * @param queryMax
	 * @throws RegistryClientException
	 */
	public RegistryResults(List<String> registryUrlList, RegistryQuery<?> query, int queryMax) throws RegistryClientException {
		this(registryUrlList, query, queryMax, null);
	}
	
	/**
	 * Main constructor that initializes globals as needed and increments to the first registry in the list.
	 * 
	 * @param registryUrlList
	 * @param query
	 * @param version
	 * @param queryMax
	 * @throws RegistryClientException
	 */
	public RegistryResults(List<String> registryUrlList, RegistryQuery<?> query, 
			int queryMax, List<ResultsFilter> resultsFilterList) throws RegistryClientException {
		this.registryIndex = -1;
		this.start = 1;
		this.registryUrlList = registryUrlList;
		this.query = query;
		//this.version = version;
		this.queryMax = queryMax;
		
		if (resultsFilterList != null) {
			this.resultsFilterList = resultsFilterList;
		} else {
			this.resultsFilterList = new ArrayList<ResultsFilter>();
		}
		
		setPageLength(-1);
		
		this.currentRegistryUrl = null;
		
		this.resultObjects = new ArrayList<Object>();
		this.lidList = new ArrayList<String>();
		
		nextRegistry();
	}
	
	/**
	 * Handles paging through results
	 * 
	 * @return	list of ExtrinsicObjects objects from query
	 * @throws RegistryServiceException
	 * @throws RegistryClientException
	 */
	public boolean nextPage() throws RegistryServiceException, RegistryClientException {
		if (this.start+this.pageLength > this.queryMax) {
			this.pageLength = this.queryMax - this.start + 1;
		}
		
		performRegistryQuery(this.start, this.pageLength);
		
		this.start += this.pageLength;
		
		return !this.resultObjects.isEmpty();
	}
	
	/**
	 * Queries Registry with page beginning at start with specified page length.
	 * 
	 * TODO Current implementation requires this method to be called an extra time after it runs out of results
	 * 		For instance, if we query from start=1 with pageLength=100, but there are only 90 results, it will still query
	 * 		the Registry again even though we should know it is already out of results 
	 * 
	 * @param start
	 * @param pageLength
	 * @throws RegistryServiceException
	 * @throws RegistryClientException
	 * @throws SearchCoreFatalException 
	 */
	@SuppressWarnings("unchecked")
	public void performRegistryQuery(int start, int pageLength) throws RegistryServiceException, RegistryClientException {
		// Get the PagedResponse and SearchCoreExtrinsic objects ready
		PagedResponse<?> pr = null;
		
		this.resultObjects.clear();		// Clear out the results list
		try {
		this.client = new RegistryClient(this.currentRegistryUrl);	// Initialize the client
		if (this.query.getFilter() instanceof ExtrinsicFilter) {
			Debugger.debug(start + " - " + pageLength);
			pr = this.client.getExtrinsics((RegistryQuery<ExtrinsicFilter>)this.query, start, pageLength);	// Get PagedResponse with pageLength
		} else if (this.query.getFilter() instanceof AssociationFilter) {
			pr = this.client.getAssociations((RegistryQuery<AssociationFilter>)this.query, start, pageLength);	// Get PagedResponse with pageLength
		} else {
			throw new RegistryClientException("Unknown Registry Filter.");
		}
		} catch (RegistryServiceException e) {
			e.printStackTrace();
		}
		
		if (pr.getResults().size() != 0 ) {
			for (Object object : pr.getResults()) {		// Loop through PagedResponse
				if (object instanceof ExtrinsicObject) {
					filterExtrinsic((ExtrinsicObject)object);
				} else if (object instanceof Association) {
					this.resultObjects.add((Association)object);
					Debugger.debug(((Association)object).getTargetObject());
				}
			}
		} else if (nextRegistry()) {
			this.start = 1;
			nextPage();
		} else {
			//System.out.println("\n\n No More Results Found On This Page\n\n");	
			this.resultObjects.addAll(pr.getResults());
		}
	}
	
	/**
	 * Apply the ResultsFilters to each resulting object
	 * 
	 * @param extrinsic
	 * @throws RegistryServiceException
	 */
	private void filterExtrinsic(ExtrinsicObject extrinsic) throws RegistryServiceException {
		ExtendedExtrinsicObject searchExtrinsic = new ExtendedExtrinsicObject(extrinsic);
		
		String lid = searchExtrinsic.getLid();
		//String lidvid = searchExtrinsic.getLidvid();

		// Check if we already grabbed this product
		if (!this.lidList.contains(lid) && lid != null) {
			
			// 2014-08-08 - jpadams - Commented out this code because I don't think we need
			// to care about the version anymore in this class. If something cares about the
			// lidvid, it is handled in the filters. Here we just want to apply the remaining
			// filters on the data
			//if (lidvid == null) {	// TODO need to refactor the version into a SlotFilter
			//	applyResultsFilters(this.resultObjects, this.client.getLatestObject(lid,
			//			ExtrinsicObject.class), true);
			//} else {
			
			applyResultsFilters(this.resultObjects, extrinsic);
		}
	}
	
	/**
	 * Apply the ResultsFilters to the ExtrinsicObject when applicable
	 * 
	 * @param resultObjects
	 * @param extObj
	 * @throws RegistryServiceException 
	 */
	private void applyResultsFilters(List<Object> resultObjects, ExtrinsicObject extObj) throws RegistryServiceException {
		boolean accept = true;
		//Debugger.debug(extObj.getLid());
		if (this.resultsFilterList.size() > 0) {
			// Loop through the results filters
			for (ResultsFilter filter : this.resultsFilterList) {
				// If the filter is a slot filter, we are in business
				if (filter instanceof SlotFilter) {
					//if (!ignoreVersion || !((SlotFilter)filter).getName().equals("version_id")) {
						//Debugger.debug(filter.toString());
					
					// Apply the filter to the object, if it returns null then it fails.

						extObj = (ExtrinsicObject) filter.applyFilter(this.client, extObj);
						if (extObj == null) {
							accept = false;
						}
					//}
				}
			}
			if (accept) {
				Debugger.debug("+++ product accepted: " + extObj.getLid());
				resultObjects.add(extObj);
				this.lidList.add(extObj.getLid());
			}
		} else {
			resultObjects.add(extObj);
		}
	} 
	
	/**
	 * Method to iterate to next registry
	 * @return	boolean describing whether or not another registry exists
	 * @throws 	RegistryClientException
	 */
	private boolean nextRegistry() throws RegistryClientException {
		if (++this.registryIndex < this.registryUrlList.size()) {
			this.currentRegistryUrl = this.registryUrlList.get(this.registryIndex);
			return true;
		} else {
			return false;
		}
	}
	
	

	/**
	 * @return the resultObjects
	 */
	public List<Object> getResultObjects() {
		return this.resultObjects;
	}

	/**
	 * @return the pageLength
	 */
	public int getPageLength() {
		return this.pageLength;
	}

	/**
	 * @param pageLength the pageLength to set
	 */
	public void setPageLength(int pageLength) {
		if (pageLength == -1) {
			this.pageLength = MAX_PAGE_LENGTH;
		} else {
			this.pageLength = Math.min(pageLength, MAX_PAGE_LENGTH);
		}
	}

}
