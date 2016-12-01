package com.quest.patientRecord.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.annotation.Resource;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.quest.patientRecord.dao.PatientRecordsRetrieverDAO;
import com.quest.patientRecord.model.PatientDetails;

@Service
public class PatientDetailsProcessor {

	private static final Logger logger = LoggerFactory.getLogger(PatientDetailsProcessor.class);

	@Resource
	private PatientRecordsRetrieverDAO patientRecordsRetrieverDAO;

	@Resource
	private Java2PdfConnectorService java2PdfConnectorService;

	@SuppressWarnings("unchecked")
	public void sendMessage(PatientDetails patientDetails) {

		logger.info(" patient details  " + patientDetails);

		try { // Create and start connection

			Hashtable env = new Hashtable();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			env.put(Context.PROVIDER_URL, "t3://localhost:7001");
			env.put(Context.SECURITY_PRINCIPAL, "uday");
			env.put(Context.SECURITY_CREDENTIALS, "Welcome2Java");

			InitialContext ctx = new InitialContext(env);
			QueueConnectionFactory f = (QueueConnectionFactory) ctx.lookup("jms/TestConnectionFactory");
			QueueConnection con = f.createQueueConnection();
			con.start();
			// 2) create queue session
			QueueSession ses = con.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			// 3) get the Queue object
			Queue t = (Queue) ctx.lookup("jms/TestJMSQueue");
			// 4)create QueueSender object
			QueueSender sender = ses.createSender(t);
			// 5) create TextMessage object
			TextMessage msg = ses.createTextMessage();

			// 6) write message

			msg.setText(patientDetails.toString());
			// 7) send message
			sender.send(msg);
			logger.info("Message successfully sent.");
			// 8) connection close
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void retrieveInfoByEmailId() {
		// TODO Auto-generated method stub

		logger.info("Retrieve patientDetails 2" );

		try {

			ArrayList<PatientDetails> detailedInfo = patientRecordsRetrieverDAO.getPatientInfo();
			logger.info("PatientsList is transferred for Java2PDF conversion " );
			
			for(PatientDetails p : detailedInfo){
				System.out.println(p.getDob());
			}

			
			java2PdfConnectorService.connectPDFHandler(detailedInfo);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
