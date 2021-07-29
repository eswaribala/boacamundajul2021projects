package com.boa.loanapprovalapi;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
@Component("fundTransferDelegate")
public class FundTransferDelegate implements JavaDelegate{

	 private final Logger LOGGER = Logger.getLogger(LoggerDelegate.class.getName());
	  
	  public void execute(DelegateExecution execution) throws Exception {
	    
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

}
