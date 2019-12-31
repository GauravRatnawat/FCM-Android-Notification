package com.firstgroup.FCM.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServletContextInjector implements InitializingBean {
	
	@Autowired
	ServletContext servletContext;
	
	
    public void afterPropertiesSet(){    		
    	
    	servletContext.setAttribute("DATA", getData());      
    }
    
    public Map<String, String>  getData() {
 		String csvFile = "app_id_data.csv";        
 	    String line = "";
 	    String cvsSplitBy = ",";
 	    Map<String, String> data = new HashMap<>();
 	    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
 	    	int skipRowHeader =0;
 	        while ((line = br.readLine()) != null) {
 	        	if(skipRowHeader == 0) {
 	        		skipRowHeader++;
 	        		continue;
 	        	}
 	            // use comma as separator
 		            String[] carParksData = line.split(cvsSplitBy);
 		       data.put(carParksData[0], carParksData[1]);
 		            	            
 	        	
 	        }
 	        return data;

 	    } catch (IOException e) {
 	        e.printStackTrace();
 	    }
 		return null;
 	}
}