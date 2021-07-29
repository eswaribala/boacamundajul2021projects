package com.boa.loanapprovalapi.delegates;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component("fundTransferTaskListener")
public class FundTransferTaskListener implements TaskListener {

	@Override
	public void notify(DelegateTask task) {
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
		task.getExecution().getProcessInstance().setVariable("status", true);
		task.setVariable("status", true);
	}

}
