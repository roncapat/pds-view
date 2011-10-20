//Copyright (c) 2009, California Institute of Technology.
//ALL RIGHTS RESERVED. U.S. Government sponsorship acknowledged.
//
// $Id$ 
//

package gov.nasa.pds.search.core.index;

import gov.nasa.pds.search.core.parse.ProfileParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;

/**
 * This class creates a lucene index for text search.
 * 
 * @author pramirez
 * @version $Revision$
 * 
 */
public class Indexer {
	public static void main(String[] args) throws IOException {
		String usage = "java " + Indexer.class
				+ " <index_directory> <crawl_directory> [weights_file]";
		if (args.length > 2) {
			System.err.println("Usage: " + usage);
			System.exit(1);
		}

		Date start = new Date();
		try {
			IndexWriter writer = new IndexWriter(new File(args[0],
					"catalog_index"), new StandardAnalyzer(), true);
			Properties weights = new Properties();

			if (args.length == 3)
				weights.load(new FileInputStream(new File(args[2])));

			indexDocs(writer, new File(args[1]), weights);

			writer.optimize();
			writer.close();

			Date end = new Date();

			System.out.print(end.getTime() - start.getTime());
			System.out.println(" total milliseconds");

		} catch (IOException e) {
			System.out.println(" caught a " + e.getClass()
					+ "\n with message: " + e.getMessage());
		}
	}

	/**
	 * Creates an lucene indexed DOM document
	 * 
	 * @param writer
	 *            - IndexWriter object for indexing of data using lucene
	 *            algorithm
	 * @param file
	 *            - File object that will be used for index
	 * @param weights
	 *            - properties for weighting the indexed values
	 * @throws IOException
	 */
	public static void indexDocs(IndexWriter writer, File file,
			Properties weights) throws IOException {
		// do not try to index files that cannot be read
		if (file.canRead()) {
			if (file.isDirectory()) {
				String[] files = file.list();
				// an IO error could occur
				if (files != null) {
					for (int i = 0; i < files.length; i++) {
						indexDocs(writer, new File(file, files[i]), weights);
					}
				}
			} else {
				System.out.println("adding " + file);
				Document doc = ProfileParser.parse(file);

				// Set document weight
				Field resClass = doc.getField("resClass");
				doc.setBoost(Float.parseFloat(weights.getProperty("document."
						+ resClass.stringValue(), "1.0")));

				// Set field weights
				for (Enumeration e = doc.fields(); e.hasMoreElements();) {
					Field field = (Field) e.nextElement();
					float weight = Float.parseFloat(weights.getProperty(
							"field." + field.name(), "1.0"));
					field.setBoost(weight);
				}

				writer.addDocument(doc);
			}
		}
	}
}
