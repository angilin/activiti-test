package com.angilin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.angilin.bpmn.service.ProcessService;

@Controller
@RequestMapping(value = "/proc")
public class ProcessDefineController {
	
	@Autowired
	private ProcessService processDefineService;

	@RequestMapping(value = "/procList")
	public String list(HttpServletRequest request, ModelMap modelMap){
		
		request.setAttribute("list", processDefineService.getProcessDefinitionList());
		return "proc/procList";
	}
	
}
