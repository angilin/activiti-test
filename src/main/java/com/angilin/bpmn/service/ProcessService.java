package com.angilin.bpmn.service;

import java.io.InputStream;
import java.util.List;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;

public interface ProcessService {
	
	public void deploy(String classpathResource);

	public void deploy(String resourceName, InputStream inputStream);
	
	public List<Deployment> getProcessDefinitionList();
	
	public ProcessInstance startProcessInstanceByKey(String processKey);
}
