package com.quest.patientRecord.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.quest.patientRecord.model.PatientDetails;
import com.quest.patientRecord.utility.Connector;

@Repository
public class PatientRecordsRetrieverDAO {

	private static final Logger logger = LoggerFactory.getLogger(PatientRecordsRetrieverDAO.class);
	
	public  ArrayList<PatientDetails> getPatientInfo() throws ClassNotFoundException, SQLException {
		
		
		
		PatientDetails patientDetails = null;
		
		
		Connection connection = null;
		
		ArrayList<PatientDetails> patientsList = new ArrayList<PatientDetails>();
		
		try {
		
		
			connection = Connector.getConnectionInfo();
		
			// call the stored procedure
		
		
			// Creating Callable statement
			CallableStatement statement = (CallableStatement) connection.prepareCall("{call patientInfo()}");
			
			
			ResultSet rs = statement.executeQuery();
		
			
			while (rs.next()) {
				
				patientDetails = new PatientDetails();
				
				patientDetails.setfName(rs.getString(1));
				patientDetails.setlName(rs.getString(2));
				patientDetails.seteMail(rs.getString(3));
				patientDetails.setpNum(rs.getLong(4));
				patientDetails.setDob(rs.getDate(5));
				
				patientsList.add(patientDetails);
				
			}
			
			
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			// finally block used to close resources
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
		return patientsList;
		
	}
	
}

		
