package com.angilin.bpmn;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.angilin.bpmn.service.ProcessService;
import com.angilin.bpmn.service.TaskInstService;

public class ProcessTest{

	private static ClassPathXmlApplicationContext context;
	
	private static ProcessService processService;
	
	private static TaskInstService taskInstService;
	
	@Test
	public void test() throws IOException{
		
		Properties prop = new Properties();//属性集合对象    
		FileInputStream fis = new FileInputStream("E:\\github\\activiti-test\\src\\main\\resources\\conf\\jdbc.properties");//属性文件流    
		prop.load(fis);//将属性文件流装载到Properties对象中
		assertEquals(prop.get("jdbc.driverClassName"),"oracle.jdbc.driver.OracleDriver");
		
		
		List<Deployment> list = processService.getProcessDefinitionList();
		//加载流程文件，classpath下相对路径
		//processService.deploy("bpmn/my-process.bpmn");
		//加载流程文件，绝对路径
		processService.deploy("my-process", new FileInputStream("E:\\github\\activiti-test\\src\\main\\resources\\bpmn\\my-process.bpmn"));
		List<Deployment> list2 = processService.getProcessDefinitionList();
		assertEquals(list.size(), list2.size()-1);
		
		ProcessInstance pi = processService.startProcessInstanceByKey("my-process");
				
		List<Task> taskList = taskInstService.getTaskList(pi.getProcessInstanceId());
		assertEquals(taskList.size(), 1);
		assertEquals(taskList.get(0).getName(), "Activiti is awesome!");
		
	}
	
	
	@BeforeClass
	public static void beforeClass(){
		context = new ClassPathXmlApplicationContext(new String[] {"conf/spring.xml","conf/spring-mybatis.xml","conf/spring-activiti.xml"});
        context.start();
        context.registerShutdownHook();
        
        processService = (ProcessService)context.getBean("processServiceImpl");
        taskInstService = (TaskInstService)context.getBean("taskInstServiceImpl");
	}
}
