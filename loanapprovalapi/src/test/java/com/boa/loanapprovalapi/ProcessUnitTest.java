package com.boa.loanapprovalapi;

import javax.annotation.PostConstruct;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;


/**
 * Test case starting an in-memory database-backed Process Engine.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessUnitTest {

  @Autowired
  private ProcessEngine processEngine;

  static {
    LogFactory.useSlf4jLogging(); // MyBatis
  }

  @ClassRule
  @Rule
  public static ProcessEngineRule rule;

  @PostConstruct
  void initRule() {
    rule = TestCoverageProcessEngineRuleBuilder.create(processEngine).build();
  }

  @Before
  public void setup() {
    init(processEngine);
  }

  @Test
  @Deployment(resources = "loanprocess.bpmn") // only required for process test coverage
  public void testHappyPath() {
    // Drive the process by API and assert correct behavior by camunda-bpm-assert
/*
    ProcessInstance processInstance = processEngine().getRuntimeService()
        .startProcessInstanceByKey(ProcessConstants.PROCESS_DEFINITION_KEY);

    assertThat(processInstance).isEnded();
*/
	  
	  // Given we create a new process instance
	    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey("loanapprovalapi");
	    // Then it should be active
	    assertThat(processInstance).isActive();
	    // And it should be the only instance
	    assertThat(processInstanceQuery().count()).isEqualTo(1);
	    // And there should exist just a single task within that process instance
	    assertThat(task(processInstance)).isNotNull();

	    // When we complete that task
	    complete(task(processInstance));
	    // Then the process instance should be ended
	    assertThat(processInstance).isEnded();
  }

}
