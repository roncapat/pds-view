//	Copyright 2009-2010, by the California Institute of Technology.
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
//	$Id$
//

package gov.nasa.pds.registry.service;

import gov.nasa.pds.registry.exception.ExceptionType;
import gov.nasa.pds.registry.exception.RegistryServiceException;
import gov.nasa.pds.registry.model.Association;
import gov.nasa.pds.registry.model.AuditableEvent;
import gov.nasa.pds.registry.model.ClassificationNode;
import gov.nasa.pds.registry.model.ClassificationScheme;
import gov.nasa.pds.registry.model.EventType;
import gov.nasa.pds.registry.model.ObjectAction;
import gov.nasa.pds.registry.model.ObjectStatus;
import gov.nasa.pds.registry.model.RegistryPackage;
import gov.nasa.pds.registry.model.RegistryResponse;
import gov.nasa.pds.registry.model.ExtrinsicObject;
import gov.nasa.pds.registry.model.RegistryObject;
import gov.nasa.pds.registry.model.Service;
import gov.nasa.pds.registry.model.ServiceBinding;
import gov.nasa.pds.registry.model.SpecificationLink;
import gov.nasa.pds.registry.model.Report;
import gov.nasa.pds.registry.model.naming.IdentifierGenerator;
import gov.nasa.pds.registry.model.naming.Versioner;
import gov.nasa.pds.registry.query.AssociationFilter;
import gov.nasa.pds.registry.query.AssociationQuery;
import gov.nasa.pds.registry.query.ObjectQuery;
import gov.nasa.pds.registry.query.ExtrinsicQuery;
import gov.nasa.pds.registry.query.QueryOperator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * This class contains all the logic for publishing, versioning, updating, and
 * deleting registry objects. The registry aims to support the registry portion
 * of the CCSDS regrep specification at the same time it leverages the much of
 * the ebXML information model.
 * 
 * @author pramirez
 * 
 */
@org.springframework.stereotype.Service("registryService")
public class RegistryServiceImpl implements RegistryService {

  @Autowired
  MetadataStore metadataStore;

  @Autowired
  @Qualifier("versioner")
  Versioner versioner;

  @Autowired
  @Qualifier("idGenerator")
  IdentifierGenerator idGenerator;

  @Autowired
  @Qualifier("report")
  Report report;

  private static List<String> CORE_OBJECT_TYPES = Arrays.asList("Association",
      "AuditableEvent", "Classification", "ClassificationNode",
      "ClassificationScheme", "ExtrinsicObject", "Service", "ServiceBinding",
      "SpecificationLink", "RegistryPackage", "ExternalIdentifier");

  private static String OBJECT_TYPE_SCHEME = "urn:registry:classificationScheme:ObjectType";

  /*
   * (non-Javadoc)
   * 
   * @see
   * gov.nasa.pds.registry.service.RegistryService#setMetadataStore(gov.nasa
   * .pds.registry.service.MetadataStore)
   */
  public void setMetadataStore(MetadataStore metadataStore) {
    this.metadataStore = metadataStore;
  }

  /*
   * (non-Javadoc)
   * 
   * @see gov.nasa.pds.registry.service.RegistryService#getMetadataStore()
   */
  public MetadataStore getMetadataStore() {
    return metadataStore;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * gov.nasa.pds.registry.service.RegistryService#setVersioner(gov.nasa.pds
   * .registry.model.naming.Versioner)
   */
  public void setVersioner(Versioner versioner) {
    this.versioner = versioner;
  }

  /*
   * (non-Javadoc)
   * 
   * @see gov.nasa.pds.registry.service.RegistryService#getVersioner()
   */
  public Versioner getVersioner() {
    return this.versioner;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * gov.nasa.pds.registry.service.RegistryService#setIdentifierGenerator(gov
   * .nasa.pds.registry.model.naming.IdentifierGenerator)
   */
  public void setIdentifierGenerator(IdentifierGenerator idGenerator) {
    this.idGenerator = idGenerator;
  }

  /*
   * (non-Javadoc)
   * 
   * @see gov.nasa.pds.registry.service.RegistryService#getIdentifierGenerator()
   */
  public IdentifierGenerator getIdentifierGenerator() {
    return this.idGenerator;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * gov.nasa.pds.registry.service.RegistryService#getExtrinsics(java.lang.Integer
   * , java.lang.Integer)
   */
  @SuppressWarnings("unchecked")
  public RegistryResponse<ExtrinsicObject> getExtrinsics(Integer start,
      Integer rows) {
    RegistryResponse<ExtrinsicObject> page = new RegistryResponse<ExtrinsicObject>(
        start, metadataStore.getNumRegistryObjects(ExtrinsicObject.class),
        (List<ExtrinsicObject>) metadataStore.getRegistryObjects(start, rows,
            ExtrinsicObject.class));
    return page;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * gov.nasa.pds.registry.service.RegistryService#getExtrinsics(gov.nasa.pds
   * .registry.query.ExtrinsicQuery)
   */
  public RegistryResponse<ExtrinsicObject> getExtrinsics(ExtrinsicQuery query) {
    return this.getExtrinsics(query, 1, 20);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * gov.nasa.pds.registry.service.RegistryService#getExtrinsics(gov.nasa.pds
   * .registry.query.ExtrinsicQuery, java.lang.Integer, java.lang.Integer)
   */
  public RegistryResponse<ExtrinsicObject> getExtrinsics(ExtrinsicQuery query,
      Integer start, Integer rows) {
    if (start <= 0) {
      start = 1;
    }
    return metadataStore.getExtrinsics(query, start, rows);
  }

  /*
   * (non-Javadoc)
   * 
   * @see gov.nasa.pds.registry.service.RegistryService#getReport()
   */
  public Report getReport() {
    Report report = new Report(this.report);
    report.setAssociations(metadataStore
        .getNumRegistryObjects(Association.class));
    report.setExtrinsics(metadataStore
        .getNumRegistryObjects(ExtrinsicObject.class));
    report.setServices(metadataStore.getNumRegistryObjects(Service.class));
    report.setClassificationNodes(metadataStore
        .getNumRegistryObjects(ClassificationNode.class));
    report.setClassificationSchemes(metadataStore
        .getNumRegistryObjects(ClassificationScheme.class));
    return report;
  }

  /*
   * (non-Javadoc)
   * 
   * @see gov.nasa.pds.registry.service.RegistryService#versionObject(java
   * .lang.String, java.lang.String, gov.nasa.pds.registry.model.RegistryObject,
   * boolean)
   */
  public String versionObject(String user, String lid, RegistryObject object,
      boolean major) throws RegistryServiceException {
    RegistryObject referencedObject = this.getLatestObject(lid, object
        .getClass());
    if (object.getGuid() == null) {
      object.setGuid(idGenerator.getGuid());
    }
    if (object.getHome() == null) {
      object.setHome(idGenerator.getHome());
    }
    object.setLid(referencedObject.getLid());
    object.setVersionName(versioner.getNextVersion(referencedObject
        .getVersionName(), major));
    object.setStatus(referencedObject.getStatus());
    this.validateObject(object);
    metadataStore.saveRegistryObject(object);
    AuditableEvent createEvent = new AuditableEvent(EventType.Created, Arrays
        .asList(object.getGuid()), user);
    createEvent.setGuid(idGenerator.getGuid());
    createEvent.setHome(idGenerator.getHome());
    metadataStore.saveRegistryObject(createEvent);
    AuditableEvent event = new AuditableEvent(EventType.Versioned, Arrays
        .asList(referencedObject.getGuid()), user);
    event.setGuid(idGenerator.getGuid());
    event.setHome(idGenerator.getHome());
    metadataStore.saveRegistryObject(event);
    return object.getGuid();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * gov.nasa.pds.registry.service.RegistryService#getLatestObject(java.lang
   * .String, java.lang.Class)
   */
  public RegistryObject getLatestObject(String lid,
      Class<? extends RegistryObject> objectClass) {
    // TODO: Make this throw an exception if the lid does not exist
    List<? extends RegistryObject> objects = metadataStore.getRegistryObjectVersions(lid,
        objectClass);
    Collections.sort(objects, versioner.getComparator());
    if (objects.size() > 0) {
      return objects.get(objects.size() - 1);
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * gov.nasa.pds.registry.service.RegistryService#getEarliestObject(java.lang
   * .String, java.lang.Class)
   */
  public RegistryObject getEarliestObject(String lid,
      Class<? extends RegistryObject> objectClass) {
    // TODO: Make this throw an exception if the lid does not exist
    List<? extends RegistryObject> objects = metadataStore.getRegistryObjectVersions(lid,
        objectClass);
    Collections.sort(objects, versioner.getComparator());
    if (objects.size() > 0) {
      return (ExtrinsicObject) objects.get(0);
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * gov.nasa.pds.registry.service.RegistryService#getNextObject(java.lang.String
   * , java.lang.String, java.lang.Class)
   */
  public RegistryObject getNextObject(String lid, String versionId,
      Class<? extends RegistryObject> objectClass) {
    // TODO: Make this method throw an exception if the lid or version is not
    // found
    List<? extends RegistryObject> objects = metadataStore.getRegistryObjectVersions(lid,
        objectClass);
    Collections.sort(objects, versioner.getComparator());
    for (int i = 0; i < objects.size(); i++) {
      RegistryObject object = objects.get(i);
      if (versionId.equals(object.getVersionId())) {
        if (i < objects.size() - 1) {
          return (ExtrinsicObject) objects.get(i + 1);
        } else {
          return null;
        }
      }
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * gov.nasa.pds.registry.service.RegistryService#getPreviousObject(java.lang
   * .String, java.lang.String, java.lang.Class)
   */
  public RegistryObject getPreviousObject(String lid, String versionId,
      Class<? extends RegistryObject> objectClass) {
    // TODO: Make this method throw an exception if the lid or version is not
    // found
    List<? extends RegistryObject> objects = metadataStore.getRegistryObjectVersions(lid,
        objectClass);
    Collections.sort(objects, versioner.getComparator());
    for (int i = 0; i < objects.size(); i++) {
      RegistryObject object = objects.get(i);
      if (versionId.equals(object.getVersionId())) {
        if (i > 0) {
          return objects.get(i - 1);
        } else {
          return null;
        }
      }
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * gov.nasa.pds.registry.service.RegistryService#getObjectVersions(java.lang
   * .String, java.lang.Class)
   */
  public List<RegistryObject> getObjectVersions(String lid,
      Class<? extends RegistryObject> objectClass) {
    return new ArrayList<RegistryObject>(metadataStore
        .getRegistryObjectVersions(lid, objectClass));
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * gov.nasa.pds.registry.service.RegistryService#getObject(java.lang.String,
   * java.lang.String, java.lang.Class)
   */
  public RegistryObject getObject(String lid, String versionId,
      Class<? extends RegistryObject> objectClass) {
    return metadataStore.getRegistryObject(lid, versionId, objectClass);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * gov.nasa.pds.registry.service.RegistryService#getClassificationNodes(java
   * .lang.String)
   */
  public List<ClassificationNode> getClassificationNodes(String scheme) {
    ArrayList<ClassificationNode> nodes = new ArrayList<ClassificationNode>();
    nodes.addAll(metadataStore.getClassificationNodes(scheme));
    return nodes;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * gov.nasa.pds.registry.service.RegistryService#changeObjectStatus(java.lang
   * .String , java.lang.String, java.lang.String,
   * gov.nasa.pds.registry.model.ObjectAction, java.lang.Class)
   */
  public void changeObjectStatus(String user, String lid, String versionId,
      ObjectAction action, Class<? extends RegistryObject> objectClass) {
    RegistryObject object = metadataStore.getRegistryObject(lid, versionId,
        objectClass);
    this.changeObjectStatus(user, object, action);
  }

  /*
   * (non-Javadoc)
   * 
   * @see gov.nasa.pds.registry.service.RegistryService#changeObjectStatus
   * (java.lang.String, java.lang.String,
   * gov.nasa.pds.registry.model.ObjectAction, java.lang.Class)
   */
  public void changeObjectStatus(String user, String guid, ObjectAction action,
      Class<? extends RegistryObject> objectClass) {
    RegistryObject registryObject = metadataStore.getRegistryObject(guid,
        objectClass);
    this.changeObjectStatus(user, registryObject, action);
  }

  private void changeObjectStatus(String user, RegistryObject registryObject,
      ObjectAction action) {
    registryObject.setStatus(action.getObjectStatus());
    metadataStore.updateRegistryObject(registryObject);
    AuditableEvent event = new AuditableEvent(action.getEventType(), Arrays
        .asList(registryObject.getGuid()), user);
    event.setGuid(idGenerator.getGuid());
    event.setHome(idGenerator.getHome());
    metadataStore.saveRegistryObject(event);
  }

  /*
   * (non-Javadoc)
   * 
   * @see gov.nasa.pds.registry.service.RegistryService#updateObject(java
   * .lang.String, gov.nasa.pds.registry.model.RegistryObject)
   */
  public void updateObject(String user, RegistryObject registryObject) {
    metadataStore.updateRegistryObject(registryObject);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * gov.nasa.pds.registry.service.RegistryService#getAssociations(gov.nasa.
   * pds.registry.query.AssociationQuery, java.lang.Integer, java.lang.Integer)
   */
  public RegistryResponse<Association> getAssociations(AssociationQuery query,
      Integer start, Integer rows) {
    if (start <= 0) {
      start = 1;
    }
    return metadataStore.getAssociations(query, start, rows);
  }

  /*
   * (non-Javadoc)
   * 
   * @see gov.nasa.pds.registry.service.RegistryService#getObjects(gov.nasa
   * .pds.registry.query.ObjectQuery, java.lang.Integer, java.lang.Integer,
   * java.lang.Class)
   */
  @SuppressWarnings("unchecked")
  public RegistryResponse<Association> getObjects(ObjectQuery query,
      Integer start, Integer rows, Class<? extends RegistryObject> objectClass) {
    if (start <= 0) {
      start = 1;
    }
    return (RegistryResponse<Association>) metadataStore.getRegistryObjects(
        query, start, rows, objectClass);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * gov.nasa.pds.registry.service.RegistryService#getAuditableEvents(java.lang
   * .String)
   */
  public RegistryResponse<AuditableEvent> getAuditableEvents(String affectedObject) {
    RegistryResponse<AuditableEvent> response = new RegistryResponse<AuditableEvent>();
    response.setResults(metadataStore.getAuditableEvents(affectedObject));
    return response;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * gov.nasa.pds.registry.service.RegistryService#publishObject(java.lang.String
   * , gov.nasa.pds.registry.model.RegistryObject, java.lang.String)
   */
  @Override
  public String publishObject(String user, RegistryObject registryObject,
      String packageId) throws RegistryServiceException {
    if (registryObject.getGuid() == null) {
      registryObject.setGuid(idGenerator.getGuid());
    }
    if (registryObject.getHome() == null) {
      registryObject.setHome(idGenerator.getHome());
    }
    registryObject.setVersionName(versioner.getInitialVersion());
    if (registryObject.getLid() == null) {
      registryObject.setLid(idGenerator.getGuid());
    }
    if (registryObject.getVersionId() == null) {
      registryObject.setVersionId(registryObject.getVersionName());
    }
    // Check to see if object with same lid already exists
    if (metadataStore.hasRegistryObjectVersions(registryObject.getLid(),
        registryObject.getClass())) {
      throw new RegistryServiceException("Registry object with logical id "
          + registryObject.getLid() + registryObject.getVersionId()
          + " already exists.", ExceptionType.EXISTING_OBJECT);
    }
    registryObject.setStatus(ObjectStatus.Submitted);
    this.validateObject(registryObject);
    metadataStore.saveRegistryObject(registryObject);

    if (packageId == null) {
      this.createAuditableEvents(user, registryObject, EventType.Created);
    } else {
      Association hasMember = new Association();
      hasMember.setGuid(idGenerator.getGuid());
      hasMember.setHome(idGenerator.getHome());
      hasMember.setSourceObject(packageId);
      hasMember.setTargetObject(registryObject.getGuid());
      hasMember.setAssociationType("urn:registry:AssociationType:HasMember");
      metadataStore.saveRegistryObject(hasMember);
      this.createAuditableEvents(user, registryObject, EventType.Created,
          Arrays.asList(new String[] { packageId }));
    }
    return registryObject.getGuid();
  }

  /*
   * (non-Javadoc)
   * 
   * @see gov.nasa.pds.registry.service.RegistryService#publishObject(java
   * .lang.String, gov.nasa.pds.registry.model.RegistryObject)
   */
  public String publishObject(String user, RegistryObject registryObject)
      throws RegistryServiceException {
    return this.publishObject(user, registryObject, null);
  }

  private void createAuditableEvents(String user,
      RegistryObject registryObject, EventType eventType) {
    this.createAuditableEvents(user, registryObject, eventType,
        new ArrayList<String>());
  }

  private void createAuditableEvents(String user,
      RegistryObject registryObject, EventType eventType,
      List<String> alsoAffected) {
    List<String> affectedObjects = new ArrayList<String>(alsoAffected);
    affectedObjects.add(registryObject.getGuid());
    // If this is a service we have other affected objects
    if (registryObject instanceof Service) {
      Service service = (Service) registryObject;
      for (ServiceBinding binding : service.getServiceBindings()) {
        affectedObjects.add(binding.getGuid());
        for (SpecificationLink link : binding.getSpecificationLinks()) {
          affectedObjects.add(link.getGuid());
        }
      }
    }
    AuditableEvent event = new AuditableEvent(eventType, affectedObjects, user);
    event.setGuid(idGenerator.getGuid());
    event.setHome(idGenerator.getHome());
    metadataStore.saveRegistryObject(event);
  }

  private void validateObject(RegistryObject registryObject)
      throws RegistryServiceException {
    // Check to see if object already exists
    if (metadataStore.hasRegistryObject(registryObject.getLid(), registryObject
        .getVersionId(), registryObject.getClass())) {
      throw new RegistryServiceException("Registry object with logical id "
          + registryObject.getLid() + " and version id "
          + registryObject.getVersionId() + " already exists.",
          ExceptionType.EXISTING_OBJECT);
    }
    // Check object type
    if (!metadataStore.hasClassificationNode(OBJECT_TYPE_SCHEME, registryObject
        .getObjectType())
        && !CORE_OBJECT_TYPES.contains(registryObject.getObjectType())) {
      throw new RegistryServiceException("Not a valid object type \""
          + registryObject.getObjectType() + "\"",
          ExceptionType.INVALID_REQUEST);
    }
    // Run type specific validation
    if (registryObject instanceof ExtrinsicObject) {
      this.validateExtrinsic((ExtrinsicObject) registryObject);
    } else if (registryObject instanceof Association) {
      this.validateAssociation((Association) registryObject);
    } else if (registryObject instanceof ClassificationNode) {
      this.validateNode((ClassificationNode) registryObject);
    } else if (registryObject instanceof Service) {
      this.validateService((Service) registryObject);
    }
  }

  private void validateExtrinsic(ExtrinsicObject object) {
    // Nothing to validate at this point
  }

  private void validateAssociation(Association association) {
    // TODO: Validate that the associationType exist within the classification
    // scheme for associations
  }

  private void validateNode(ClassificationNode node) {
    StringBuffer path = new StringBuffer(node.getCode());
    path.insert(0, "/");
    boolean done = false;
    ClassificationNode currentNode = node;
    while (!done) {
      boolean nodeParentFound = false;
      try {
        currentNode = (ClassificationNode) this.getObject(currentNode
            .getParent(), ClassificationNode.class);
        path.insert(0, currentNode.getCode());
        path.insert(0, "/");
        nodeParentFound = true;
      } catch (NoResultException nre) {
        // Suppress as it could be the parent is a classification scheme
      }
      if (!nodeParentFound) {
        ClassificationScheme parent = (ClassificationScheme) this.getObject(
            currentNode.getParent(), ClassificationScheme.class);
        path.insert(0, parent.getGuid());
        path.insert(0, "/");
        done = true;
      }
    }
    node.setPath(path.toString());
  }

  private void validateService(Service service) {
    for (ServiceBinding binding : service.getServiceBindings()) {
      if (binding.getGuid() == null) {
        binding.setGuid(idGenerator.getGuid());
      }
      if (binding.getGuid() == null) {
        binding.setHome(idGenerator.getHome());
      }
      if (binding.getService() == null) {
        binding.setService(service.getGuid());
      }
      for (SpecificationLink link : binding.getSpecificationLinks()) {
        if (link.getGuid() == null) {
          link.setGuid(idGenerator.getGuid());
        }
        if (link.getHome() == null) {
          link.setHome(idGenerator.getHome());
        }
        if (link.getServiceBinding() == null) {
          link.setServiceBinding(binding.getGuid());
        }
      }
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see gov.nasa.pds.registry.service.RegistryService#deleteObject(java
   * .lang.String, java.lang.String, java.lang.String, java.lang.Class)
   */
  public void deleteObject(String user, String lid, String versionId,
      Class<? extends RegistryObject> objectClass) {
    RegistryObject registryObject = metadataStore.getRegistryObject(lid,
        versionId, objectClass);
    this.deleteObject(user, registryObject);
  }

  /*
   * (non-Javadoc)
   * 
   * @see gov.nasa.pds.registry.service.RegistryService#deleteObject(java
   * .lang.String, java.lang.String, java.lang.Class)
   */
  public void deleteObject(String user, String guid,
      Class<? extends RegistryObject> objectClass) {
    RegistryObject registryObject = metadataStore.getRegistryObject(guid,
        objectClass);
    this.deleteObject(user, registryObject);
  }

  private void deleteObject(String user, RegistryObject registryObject) {
    metadataStore.deleteRegistryObject(registryObject.getGuid(), registryObject
        .getClass());
    AssociationFilter filter = new AssociationFilter.Builder().targetObject(
        registryObject.getGuid()).sourceObject(registryObject.getGuid())
        .build();
    AssociationQuery.Builder queryBuilder = new AssociationQuery.Builder()
        .filter(filter).operator(QueryOperator.OR);
    RegistryResponse<Association> response = metadataStore.getAssociations(queryBuilder
        .build(), 1, -1);
    List<String> associationIds = new ArrayList<String>();
    for (RegistryObject object : response.getResults()) {
      associationIds.add(object.getGuid());
      metadataStore.deleteRegistryObject(object.getGuid(), object.getClass());
    }
    this.createAuditableEvents(user, registryObject, EventType.Deleted,
        associationIds);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * gov.nasa.pds.registry.service.RegistryService#getAssocation(java.lang.String
   * )
   */
  public Association getAssocation(String guid) {
    return (Association) this.getObject(guid, Association.class);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * gov.nasa.pds.registry.service.RegistryService#getExtrinsic(java.lang.String
   * )
   */
  public ExtrinsicObject getExtrinsic(String guid) {
    return (ExtrinsicObject) this.getObject(guid, ExtrinsicObject.class);
  }

  /*
   * (non-Javadoc)
   * 
   * @see gov.nasa.pds.registry.service.RegistryService#getObject(java.lang
   * .String, java.lang.Class)
   */
  public RegistryObject getObject(String guid,
      Class<? extends RegistryObject> objectClass) {
    return metadataStore.getRegistryObject(guid, objectClass);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * gov.nasa.pds.registry.service.RegistryService#configure(java.lang.String,
   * gov.nasa.pds.registry.model.RegistryPackage, java.util.List)
   */
  public String configure(String user, RegistryPackage registryPackage,
      List<? extends RegistryObject> registryObjects)
      throws RegistryServiceException {
    RegistryServiceException exception = null;
    String packageId = null;
    Map<String, Class<? extends RegistryObject>> registered = new HashMap<String, Class<? extends RegistryObject>>();
    try {
      packageId = this.publishObject(user, registryPackage);
    } catch (RegistryServiceException e) {
      throw e;
    }
    // For now we will simply call publish on every item in the list that is a
    // classification scheme of classification node
    // TODO: Make this a transaction to rollback if all don't work
    for (RegistryObject object : registryObjects) {
      try {
        if (object instanceof ClassificationScheme
            || object instanceof ClassificationNode) {
          String id = this.publishObject(user, object, packageId);
          System.out.println("Published: " + id);
          registered.put(id, object.getClass());
        }
      } catch (RegistryServiceException e) {
        exception = e;
        break;
      }
    }

    // TODO: Make this method transactional and just rollback anything added
    // here. Instead of deleting one by one
    if (exception != null) {
      for (String id : registered.keySet()) {
        this.deleteObject(user, id, registered.get(id));
      }
      // TODO: Remove package and associations?
      throw exception;
    }

    return packageId;
  }

}
