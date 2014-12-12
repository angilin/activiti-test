package com.angilin.bpmn.service;

import java.util.List;

import org.activiti.engine.task.Task;

public interface TaskInstService {

	
	public List<Task> getTaskList(String processInstanceId);
}
