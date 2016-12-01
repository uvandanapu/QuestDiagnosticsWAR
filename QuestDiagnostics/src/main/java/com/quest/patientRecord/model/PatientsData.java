package com.quest.patientRecord.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;


@XmlRootElement(name = "PatientsData")
@XmlSeeAlso(PatientDetails.class)
public class PatientsData {

	private List patientsList;

	public void setPatientInfo(List patientsList) {

		this.patientsList = patientsList;
	}

	@XmlElementWrapper(name = "patientsList")
	@XmlElement(name = "patientInfo")
	public List getPatientInfo() {

		return patientsList;
	}
}
