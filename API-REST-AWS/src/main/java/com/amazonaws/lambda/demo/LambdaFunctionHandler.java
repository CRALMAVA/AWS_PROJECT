package com.amazonaws.lambda.demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

public class LambdaFunctionHandler implements RequestStreamHandler {

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {

    	OutputStream outputString = new ByteArrayOutputStream();
    	JSONObject responseJson = new JSONObject();   
    	List list = new ArrayList();
        int letter = 0;
        int totalPeople = 0;
       
        while((letter = input.read()) >= 0) {
        	outputString.write(letter);
        }        
        String groups = outputString.toString().replace("\\\"", "").replace("\"","").trim(); 
		String[] peopleXGroup = groups.split(",");		
		
		for (int x = 0; x < peopleXGroup.length; x++) {
			int value = Integer.parseInt(peopleXGroup[x]);
			totalPeople = totalPeople + value;
			}	
			
			for(int x=0;x<=totalPeople;x++){
				int a = 0;
				int count = 1;
				for(String element:peopleXGroup){					
					a += Integer.parseInt(element);
				
				if(count>=2){	
					if(x==a && (totalPeople%a)==0){	
						a = 0;
						list.add(x);
						
					}
					
				}
				count++;
				}				

			}		
	     
              
		responseJson.put("sizes", list.stream().distinct().collect(Collectors.toList()).toString().replace("[","").replace("]", "").replace(" ", ""));

        OutputStreamWriter writer = new OutputStreamWriter(output, "UTF-8");
        writer.write(responseJson.toString());
        writer.close();
        
        
        
        
        
    
    }

}
