package com.boa.customerapi.delegates;

import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.boa.customerapi.models.Customer;
import com.github.wnameless.json.flattener.JsonFlattener;

import jline.internal.Log;
import spinjar.com.minidev.json.JSONObject;
import spinjar.com.minidev.json.parser.JSONParser;
@Component("readData")
public class ReadData implements JavaDelegate{
	@Autowired
	private RestTemplate restTemplate;
    @Value("${customerurl}")
    private String url; 
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				long customerId=Long.parseLong(execution.getVariable("customerId")
						.toString());
				HttpHeaders headers = new HttpHeaders();
			       headers.setContentType(MediaType.APPLICATION_JSON);
			  
			 	
				HttpEntity request = new HttpEntity<String>(null,headers);

				ResponseEntity<?> response = restTemplate.
				          exchange(url+"/fetch/"+customerId,HttpMethod.GET, request,
						  String.class); 
				Map<String,Object> data=parseString(response.getBody().toString());
				for(String key:data.keySet()) {
					System.out.println(data.get(key));
				}
				
				execution.setVariable("customerId", data.get("customerId").toString());
				execution.setVariable("name", data.get("name").toString());
				execution.setVariable("mobileNo", data.get("mobileNo").toString());
				Log.info("Customer Data received....");
	}
	private Map<String,Object> parseString(String response)
	{
		JSONParser parser = new JSONParser(); 
		Map<String, Object> flattenedJsonMap=null;
		String token="";
	  	try {
	  		 
			// Put above JSON content to crunchify.txt file and change path location
			Object obj = parser.parse(response);
			JSONObject jsonObject = (JSONObject) obj;
 
			// JsonFlattener: A Java utility used to FLATTEN nested JSON objects
			String flattenedJson = JsonFlattener.flatten(jsonObject.toString());
			//log("\n=====Simple Flatten===== \n" + flattenedJson);
 
		flattenedJsonMap = JsonFlattener.flattenAsMap(jsonObject.toString());
		 	
		} catch (Exception e) {
			e.printStackTrace();
		}
	 return flattenedJsonMap;

	}
}
