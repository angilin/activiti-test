package com.angilin.bpmn;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ActivitiTestService {

	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	
	public void test() {
		
		//报错
		//ProcessEngine processEngine = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration().buildProcessEngine();
		
		/*ProcessEngine processEngine = null;
		
		processEngine = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResource(
						"activiti.cfg.xml").buildProcessEngine();*/
		
		/*processEngine = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResource(
						"conf\\spring-activiti.xml").buildProcessEngine();*/
		
		/*try {
			//processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromInputStream(new FileInputStream("E:\\信审系统\\test\\src\\main\\resources\\activiti.cfg.xml")).buildProcessEngine();
			//processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromInputStream(new FileInputStream("E:\\信审系统\\test\\src\\main\\resources\\conf\\spring-mybatis2.xml")).buildProcessEngine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}*/
		
		
		
		
		//ProcessEngine processEngine = Context.getProcessEngineConfiguration().buildProcessEngine();
		
		/* 如果不用spring配置，可以用这种方式生成processEngine
		 * ProcessEngine processEngine = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration()
				  .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE)
				  .setJdbcUrl("jdbc:h2:mem:my-own-db;DB_CLOSE_DELAY=1000")
				  .setJobExecutorActivate(true)
				  .buildProcessEngine();*/
		
		
		//配置文件名字需要是activiti-context.xml（spring方式）或activiti.cfg.xml（文件输入流方式）
		//ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		
		
		
		//流程引擎的API和服务
		//有服务都是无状态的。这意味着可以在多节点集群环境下运行Activiti，每个节点都指向同一个数据库， 不用担心哪个机器实际执行前端的调用。 无论在哪里执行服务都没有问题。\
	
		//RepositoryService repositoryService = processEngine.getRepositoryService();
		
		repositoryService.createDeployment().addClasspathResource("bpmn/my-process.bpmn").deploy();
		System.out.println("Number of process definitions: " + repositoryService.createProcessDefinitionQuery().count());
		
		/*RuntimeService runtimeService = processEngine.getRuntimeService();*/
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("my-process");
	
		/*TaskService taskService = processEngine.getTaskService();*/
		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
		System.out.println(task.getName());
		
	}
	
	
	
	public static void main(String[] args){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"conf/spring.xml","conf/spring-mybatis.xml","conf/spring-activiti.xml"});//,"activiti.cfg.xml"
        /*context.start();
        context.registerShutdownHook();*/
        ActivitiTestService activitiService = (ActivitiTestService)context.getBean("activitiTestService");
        activitiService.test();
        
        java.lang.System.getProperty("global.config.path");
	}
}
