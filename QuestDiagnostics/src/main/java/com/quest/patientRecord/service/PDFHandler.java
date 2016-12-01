package com.quest.patientRecord.service;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

import com.quest.patientRecord.model.PatientDetails;
import com.quest.patientRecord.model.PatientsData;

@Service
public class PDFHandler {

	public static final String EXTENSION = ".pdf";
	public String PRESCRIPTION_URL = "template.xsl";

	private static final Logger logger = LoggerFactory.getLogger(PDFHandler.class);

	public String generatePDF(ByteArrayOutputStream xmlSource, String templateFilePath) throws IOException {

		File file = File.createTempFile("" + System.currentTimeMillis(), EXTENSION);

		URL url = new File(templateFilePath + PRESCRIPTION_URL).toURI().toURL();

		// creation of transform source
		StreamSource transformSource = new StreamSource(url.openStream());

		// create an instance of fop factory
		FopFactory fopFactory = FopFactory.newInstance();

		// a user agent is needed for transformation
		FOUserAgent foUserAgent = fopFactory.newFOUserAgent();

		// to store output
		ByteArrayOutputStream pdfoutStream = new ByteArrayOutputStream();

		StreamSource source = new StreamSource(new ByteArrayInputStream(xmlSource.toByteArray()));

		Transformer xslfoTransformer;
		try {
			TransformerFactory transfact = TransformerFactory.newInstance();

			xslfoTransformer = transfact.newTransformer(transformSource);

			// Construct fop with desired output format
			Fop fop;

			try {
				fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, pdfoutStream);

				// Resulting SAX events (the generated FO)

				// must be piped through to FOP
				Result res = new SAXResult(fop.getDefaultHandler());

				// Start XSLT transformation and FOP processing
				try {
					// everything will happen here..
					xslfoTransformer.transform(source, res);

					// if you want to save PDF file use the following code
					OutputStream out = new java.io.FileOutputStream(file);

					out = new java.io.BufferedOutputStream(out);

					FileOutputStream str = new FileOutputStream(file);

					str.write(pdfoutStream.toByteArray());

					str.close();
					out.close();

				} catch (TransformerException e) {
					e.printStackTrace();
				}
			} catch (FOPException e) {
				e.printStackTrace();
			}
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}
		return file.getPath();
	}

	public ByteArrayOutputStream getXMLSource(PatientsData data) throws Exception {
		JAXBContext context;

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();

		try {
			context = JAXBContext.newInstance(PatientsData.class);

			Marshaller m = context.createMarshaller();

			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			m.marshal(data, outStream);

		} catch (JAXBException e) {

			e.printStackTrace();
		}
		return outStream;

	}

}
