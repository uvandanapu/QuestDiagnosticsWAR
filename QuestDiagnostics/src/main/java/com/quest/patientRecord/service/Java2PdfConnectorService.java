package com.quest.patientRecord.service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.quest.patientRecord.model.PatientDetails;
import com.quest.patientRecord.model.PatientsData;

@Service

public class Java2PdfConnectorService {

	@Resource
	private PDFHandler pdfHandler;
	
	

	private static final Logger logger = LoggerFactory.getLogger(Java2PdfConnectorService.class);
			
	
	
	
	public void connectPDFHandler(List patientsInfoList) {
		// TODO Auto-generated method stub
		
		
		String templateFilePath ="C:/BCJMay16/Develop/Work Space/Spring/QuestDiagnostics/src/main/webapp/resources/";
		
		PatientsData patientsData = new PatientsData();
		
		patientsData.setPatientInfo(patientsInfoList);
		
		try {
			ByteArrayOutputStream streamSource = pdfHandler.getXMLSource(patientsData);

			pdfHandler.generatePDF(streamSource,templateFilePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	
}
