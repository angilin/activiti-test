package com.angilin.bpmn.service.impl;

import java.io.InputStream;
import java.util.List;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.angilin.bpmn.service.ProcessService;

@Service
public class ProcessServiceImpl implements ProcessService{

	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	public void deploy(String classpathResource){
		repositoryService.createDeployment().addClasspathResource(classpathResource).deploy();
	}
		
	public void deploy(String resourceName, InputStream inputStream){
		repositoryService.createDeployment().addInputStream(resourceName, inputStream).deploy();
	}
	
	public List<Deployment> getProcessDefinitionList(){
		return repositoryService.createDeploymentQuery().orderByDeploymenTime().desc().list();
	}
	
	public ProcessInstance startProcessInstanceByKey(String processKey){
		return runtimeService.startProcessInstanceByKey(processKey);
	}
}
