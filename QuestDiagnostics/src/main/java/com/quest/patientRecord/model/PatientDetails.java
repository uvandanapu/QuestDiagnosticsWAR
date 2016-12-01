package com.quest.patientRecord.model;

import java.io.Serializable;
import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

public class PatientDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fName;
	private String lName;
	private String eMail;
	private Long pNum;
	private Date dob;

	/**
	 * @return the eMail
	 */
	public String geteMail() {
		return eMail;
	}

	/**
	 * @param eMail
	 *            the eMail to set
	 */
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	/**
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * @return the pNum
	 */
	public Long getpNum() {
		return pNum;
	}

	/**
	 * @param pNum
	 *            the pNum to set
	 */
	public void setpNum(Long pNum) {
		this.pNum = pNum;
	}

	/**
	 * @param dob
	 *            the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	private PatientAddress patientAddress;

	/**
	 * @return the fName
	 */
	public String getfName() {
		return fName;
	}

	/**
	 * @return the patientAddress
	 */
	public PatientAddress getPatientAddress() {
		return patientAddress;
	}

	/**
	 * @param patientAddress
	 *            the patientAddress to set
	 */
	public void setPatientAddress(PatientAddress patientAddress) {
		this.patientAddress = patientAddress;
	}

	/**
	 * @param fName
	 *            the fName to set
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}

	/**
	 * @return the lName
	 */
	public String getlName() {
		return lName;
	}

	/**
	 * @param lName
	 *            the lName to set
	 */
	public void setlName(String lName) {
		this.lName = lName;
	}

	public String toString(){
		return fName+":"+lName+":"+eMail+":"+pNum+":"+dob;
		
		
	}
}
