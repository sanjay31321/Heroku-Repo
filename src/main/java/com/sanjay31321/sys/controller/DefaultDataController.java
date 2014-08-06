package com.sanjay31321.sys.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sanjay31321.sys.preset.DefaultDataInstall;
import com.sanjay31321.sys.preset.model.Install;

@Controller
public class DefaultDataController {
	private static final Logger logger = LoggerFactory.getLogger(DefaultDataController.class);
	
	@Autowired 
	private DefaultDataInstall defaultDataInstall;   
	
	
	@RequestMapping(value = "/install", method = RequestMethod.GET)
	public String getinstall(Locale locale, Install install) {
		logger.info("Welcome to Install Default Settings page ! GET METHOD : The client locale is {}.", locale);
		return "install";
	}
	
	@RequestMapping(value="/install", method=RequestMethod.POST)
	public String postinstall(@Valid  Install install, BindingResult result, Locale locale, final RedirectAttributes redirectAttributes) {
		logger.info("Welcome to Install Default Settings page ! POST METHOD : The client locale is {}.", locale);
		
		defaultDataInstall.install();
		redirectAttributes.addFlashAttribute("Msg","Default Settings installed successfully.");
		return "install";
	}
}
