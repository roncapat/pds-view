// Copyright 2006-2011, by the California Institute of Technology.
// ALL RIGHTS RESERVED. United States Government Sponsorship acknowledged.
// Any commercial use must be negotiated with the Office of Technology Transfer
// at the California Institute of Technology.
//
// This software is subject to U. S. export control laws and regulations
// (22 C.F.R. 120-130 and 15 C.F.R. 730-774). To the extent that the software
// is subject to U.S. export control laws and regulations, the recipient has
// the responsibility to obtain export licenses or other export authority as
// may be required before exporting such information to foreign countries or
// providing access to foreign nationals.
//
// $Id$

package gov.nasa.pds.harvest.crawler.metadata.extractor;

import gov.nasa.jpl.oodt.cas.metadata.MetExtractor;
import gov.nasa.jpl.oodt.cas.metadata.MetExtractorConfig;
import gov.nasa.jpl.oodt.cas.metadata.Metadata;
import gov.nasa.jpl.oodt.cas.metadata.exceptions.MetExtractionException;
import gov.nasa.pds.harvest.constants.Constants;
import gov.nasa.pds.harvest.logging.ToolsLevel;
import gov.nasa.pds.harvest.logging.ToolsLogRecord;
import gov.nasa.pds.harvest.policy.ElementName;
import gov.nasa.pds.harvest.policy.LidContents;
import gov.nasa.pds.harvest.policy.Slot;
import gov.nasa.pds.harvest.policy.TitleContents;
import gov.nasa.pds.tools.LabelParserException;
import gov.nasa.pds.tools.label.Label;
import gov.nasa.pds.tools.label.ManualPathResolver;
import gov.nasa.pds.tools.label.Sequence;
import gov.nasa.pds.tools.label.Set;
import gov.nasa.pds.tools.label.Value;
import gov.nasa.pds.tools.label.parser.DefaultLabelParser;
import gov.nasa.pds.tools.util.MessageUtils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.io.FilenameUtils;

/**
 * A class to extract metadata from a PDS3 data product label.
 *
 * @author mcayanan
 *
 */
public class Pds3MetExtractor implements MetExtractor {
  /** Logger object. */
  private static Logger log = Logger.getLogger(
          Pds3MetExtractor.class.getName());

  /** Holds the metadata extractor configuration. */
  private Pds3MetExtractorConfig config;

  /** Label parser. */
  private DefaultLabelParser parser;

  /**
   * Constructor.
   *
   * @param config A configuration object for the metadata extractor.
   */
  public Pds3MetExtractor(Pds3MetExtractorConfig config) {
    this.config = config;
    ManualPathResolver resolver = new ManualPathResolver();
    parser = new DefaultLabelParser(false, true, resolver);
  }

  /**
   * Extract the metadata from the given file.
   *
   * @param product The PDS3 label file.
   *
   * @return A metadata object containing the extracted metadata.
   */
  public Metadata extractMetadata(File product)
  throws MetExtractionException {
    Metadata metadata = new Metadata();
    Label label = null;
    try {
      label = parser.parseLabel(product.toURI().toURL());
    } catch (LabelParserException lp) {
      throw new MetExtractionException(MessageUtils.getProblemMessage(lp));
    } catch (Exception e) {
      throw new MetExtractionException(e.getMessage());
    }
    metadata.addMetadata(Constants.OBJECT_TYPE, "Product_Proxy_PDS3");

    String lid = createLid(product, label, config.getLidContents());
    metadata.addMetadata(Constants.LOGICAL_ID, lid);
    //Get the value of PRODUCT_VERSION or default to 1.0
    try {
      String productVersion =
        label.getAttribute("PRODUCT_VERSION").getValue().toString();
      metadata.addMetadata(Constants.PRODUCT_VERSION, productVersion);
    } catch (NullPointerException n) {
      metadata.addMetadata(Constants.PRODUCT_VERSION, "1.0");
    }
    //Create a title
    String title = createTitle(product, label, config.getTitleContents());

    //This is a default title.
    if (title.trim().isEmpty()) {
      title = "PDS3 Data Product";
    }
    metadata.addMetadata(Constants.TITLE, title);

    // Capture the include paths for file object processing.
    metadata.addMetadata(Constants.INCLUDE_PATHS, config.getIncludePaths());

    // Register any static metadata that is specified in the policy config
    if (!config.getStaticMetadata().isEmpty()) {
      for (Slot slot : config.getStaticMetadata()) {
        metadata.addMetadata(slot.getName(), slot.getValue());
      }
    }

    // Register additional metadata (if specified)
    if (!config.getAncillaryMetadata().isEmpty()) {
      for (ElementName element : config.getAncillaryMetadata()) {
        try {
          String value = label.getAttribute(element.getValue().trim())
          .getValue().toString();
          if (element.getSlotName() != null) {
            metadata.addMetadata(element.getSlotName(), value);
          } else {
            metadata.addMetadata(element.getValue().toLowerCase(), value);
          }
        } catch (NullPointerException n) {
          // Ignore. Element was not found in the label.
        }
      }
    }
    return metadata;
  }

  /**
   * Creates the logical identifier for the PDS3 product.
   *
   * @param product The PDS3 file being registered.
   * @param label The object representation of the PDS3 label.
   * @param lidContents The user-specified lid contents.
   * @return A logical identifier.
   *
   * @throws MetExtractionException
   */
  private String createLid(File product, Label label,
      LidContents lidContents) throws MetExtractionException {
    log.log(new ToolsLogRecord(ToolsLevel.INFO,
        "Creating logical identifier.", product));
    String lid ="";
    List<String> elementValues = new ArrayList<String>();
    for (ElementName name : lidContents.getElementName()) {
      try {
        Value value = label.getAttribute(name.getValue().trim())
        .getValue();
        String val = "";
        // Get only the first value if multiple values are specified for
        // an element.
        if (value instanceof Sequence || value instanceof Set) {
          Collection collection = (Collection) value;
          val = collection.iterator().next().toString();
        } else {
          val = value.toString();
        }
        elementValues.add(val);
      } catch (NullPointerException n) {
        log.log(new ToolsLogRecord(ToolsLevel.WARNING,
            name.getValue() + " not found.", product));
      }
    }
    lid = lidContents.getPrefix();
    for (String elementValue : elementValues) {
      lid += ":" + elementValue;
    }
    if (lidContents.isAppendFilename()) {
      lid += ":" + FilenameUtils.getBaseName(product.toString());
    }
    log.log(new ToolsLogRecord(ToolsLevel.INFO,
        "Created the following logical identifier: " + lid, product));
    //Product ID or Product Version values may have slash characters
    //Replace it with a dash character
    String conformingLid = lid.replaceAll(Constants.URN_ILLEGAL_CHARACTERS, "-");
    if (!conformingLid.equals(lid)) {
      log.log(new ToolsLogRecord(ToolsLevel.WARNING, "Element values used "
          + "in creating the logical identifier contain URN reserved "
          + "and/or excluded characters. Replacing with '-' characters: "
          + conformingLid, product));
    }
    return conformingLid;
  }

  /**
   * Creates the title of the PDS3 Proxy Product.
   *
   * @param product The product.
   * @param label The product label.
   * @param titleContents The title contents.
   *
   * @return The resulting value.
   */
  private String createTitle(File product, Label label,
      TitleContents titleContents) {
    log.log(new ToolsLogRecord(ToolsLevel.INFO,
        "Creating title.", product));
    List<String> elementValues = new ArrayList<String>();
    for (ElementName name : titleContents.getElementName()) {
      try {
        Value value = label.getAttribute(name.getValue().trim())
        .getValue();
        String val = "";
        // Get only the first value if multiple values are specified for
        // an element.
        if (value instanceof Sequence || value instanceof Set) {
          Collection collection = (Collection) value;
          val = collection.iterator().next().toString();
        } else {
          val = value.toString();
        }
        elementValues.add(val);
      } catch (NullPointerException n) {
        log.log(new ToolsLogRecord(ToolsLevel.WARNING,
            name.getValue() + " not found.", product));
      }
    }
    String title = "";
    for (String elementValue : elementValues) {
        title += " " + elementValue;
    }
    if (titleContents.isAppendFilename()) {
      title += " " + FilenameUtils.getBaseName(product.toString());
    }
    return title.trim();
  }

  /**
   * Extract the metadata.
   *
   * @param product A PDS4 xml file.
   * @return a class representation of the extracted metadata.
   *
   */
  public Metadata extractMetadata(String product)
  throws MetExtractionException {
      return extractMetadata(new File(product));
  }

  /**
   * Extract the metadata.
   *
   * @param product A PDS4 xml file.
   * @return a class representation of the extracted metadata.
   *
   */
  public Metadata extractMetadata(URL product)
  throws MetExtractionException {
      return extractMetadata(product.toExternalForm());
  }

  /**
   * No need to be implemented.
   *
   */
  public Metadata extractMetadata(File product, File configFile)
          throws MetExtractionException {
      // No need to implement at this point
      return null;
  }

  /**
   * No need to be implemented.
   *
   */
  public Metadata extractMetadata(File product, String configFile)
          throws MetExtractionException {
      // No need to implement at this point
      return null;
  }

  /**
   * No need to be implemented.
   *
   */
  public Metadata extractMetadata(File product, MetExtractorConfig config)
          throws MetExtractionException {
      setConfigFile(config);
      return extractMetadata(product);
  }

  /**
   * No need to be implemented.
   *
   */
  public Metadata extractMetadata(URL product, MetExtractorConfig config)
          throws MetExtractionException {
      setConfigFile(config);
      return extractMetadata(product);
  }

  /**
   * No need to be implemented.
   *
   */
  public void setConfigFile(File configFile)
  throws MetExtractionException {
      // No need to implement at this point
  }

  /**
   * No need to be implemented.
   *
   */
  public void setConfigFile(String configFile)
  throws MetExtractionException {
      // No need to implement at this point
  }

  public void setConfigFile(MetExtractorConfig config) {
      this.config = (Pds3MetExtractorConfig) config;
  }
}
