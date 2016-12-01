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
public class RetrieveInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(RetrieveInfoController.class);
	
	@Resource
	private PatientDetailsProcessor patientDetailsProcessor;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/retrieveInfo", method = RequestMethod.GET)
	public ModelAndView retrievePatientsInfo() {
		
		
	
		
		logger.info("Retrieve 1 :");
		
		 patientDetailsProcessor.retrieveInfoByEmailId();
		
		return new ModelAndView("success");
	}
	
}
