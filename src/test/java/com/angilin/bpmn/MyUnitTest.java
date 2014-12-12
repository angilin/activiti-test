package com.angilin.bpmn;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class MyUnitTest {
	
	@Rule
	public ActivitiRule activitiRule = new ActivitiRule("test/no-spring-activiti.xml");
	
	@Test
	@Deployment(resources = {"bpmn/my-process.bpmn"})
	public void test() {
		ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("my-process");
		assertNotNull(processInstance);
		
		Task task = activitiRule.getTaskService().createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult();
		assertEquals("Activiti is awesome!", task.getName());
	}
	

}
