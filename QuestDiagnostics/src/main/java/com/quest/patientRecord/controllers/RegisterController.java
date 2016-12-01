package com.quest.patientRecord.controllers;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.quest.patientRecord.model.PatientDetails;
import com.quest.patientRecord.service.PatientDetailsProcessor;

/**
 * Handles requests for the application home page.
 */
@Controller
public class RegisterController {
	
	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
	
	@Resource
	private PatientDetailsProcessor patientDetailsProcessor;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/formRegister", method = RequestMethod.POST)
	public ModelAndView addPatient(PatientDetails patientDetails) {
		
		
	
		
		logger.info(patientDetails.geteMail());
		
		patientDetailsProcessor.sendMessage(patientDetails);
		
		return new ModelAndView("success","savedInfo",patientDetails);
	}
	
}
