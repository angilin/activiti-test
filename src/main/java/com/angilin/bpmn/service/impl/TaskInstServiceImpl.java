package com.angilin.bpmn.service.impl;

import java.util.List;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.angilin.bpmn.service.TaskInstService;

@Service
public class TaskInstServiceImpl implements TaskInstService{

	@Autowired
	private TaskService taskService;
	
	public List<Task> getTaskList(String processInstanceId){
		return taskService.createTaskQuery().processInstanceId(processInstanceId).list();
	}
}
