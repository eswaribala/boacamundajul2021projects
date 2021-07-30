package com.boa.loanapprovalapi.delegates;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.history.HistoricTaskInstanceQuery;
import org.camunda.bpm.engine.task.TaskQuery;
import org.camunda.bpm.model.bpmn.instance.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jline.internal.Log;
import lombok.extern.slf4j.Slf4j;
@Component("fundTransferDelegate")
@Slf4j
public class FundTransferDelegate implements JavaDelegate,TaskListener{
    @Autowired
	private HistoryService historyService;
    @Autowired
    private TaskService taskService;
	 private final Logger LOGGER = Logger.getLogger(LoggerDelegate.class.getName());
	  
	  public void execute(DelegateExecution execution) throws Exception {
	    
		 Map<String,Object> taskMap=new HashMap<String,Object>(); 
		// TaskQuery currentTask=taskService.createTaskQuery().taskId(execution.getCurrentActivityId());
		 HistoricTaskInstance previousTask=findpreviousTask(execution.getProcessInstanceId());
	     Log.info(previousTask.getName());
		 LOGGER.info("\n\n  ... LoggerDelegate invoked by "
	            + "activtyName='" + execution.getCurrentActivityName() + "'"
	            + ", activtyId=" + execution.getCurrentActivityId()
	            + ", processDefinitionId=" + execution.getProcessDefinitionId()
	            + ", processInstanceId=" + execution.getProcessInstanceId()
	            + ", businessKey=" + execution.getProcessBusinessKey()
	            + ", executionId=" + execution.getId()
	            + ", variables=" + execution.getVariables()
	            + " \n\n");
	   
	  }
	  
	  public HistoricTaskInstance findpreviousTask(String processInstanceId) {
		  return historyService.createHistoricTaskInstanceQuery()
				  .processInstanceId(processInstanceId).orderByHistoricTaskInstanceEndTime()
				  .desc().list().get(0);
	  }

	@Override
	public void notify(DelegateTask task) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		log.info("\n\n  ... LoggerDelegate invoked by "
		        + "activtyName='" + task.getExecution().getCurrentActivityName() + "'"
		        + ", activtyId=" + task.getExecution().getCurrentActivityId()
		        + ", processDefinitionId=" + task.getProcessDefinitionId()
		        + ", processInstanceId=" + task.getProcessInstanceId()
		        + ", businessKey=" + task.getExecution().getProcessBusinessKey()
		        + ", executionId=" + task.getId()
		        + ", variables=" + task.getVariables()
		        + " \n\n");
	}

}
