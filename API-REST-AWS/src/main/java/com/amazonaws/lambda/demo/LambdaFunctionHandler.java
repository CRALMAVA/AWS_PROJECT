package com.amazonaws.lambda.demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.json.simple.JSONObject;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

public class LambdaFunctionHandler implements RequestStreamHandler {

    @SuppressWarnings("unchecked")
	@Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {

        int letter = 0;
        OutputStream outputString = new ByteArrayOutputStream();
        while((letter = input.read()) >= 0) {
        	outputString.write(letter);
        }
        
        String sampleOutputString = outputString.toString();
        JSONObject responseJson = new JSONObject();         
		responseJson.put("sizes", sampleOutputString);

        OutputStreamWriter writer = new OutputStreamWriter(output, "UTF-8");
        writer.write(responseJson.toString());
        writer.close();
        
        
        
        
        
    
    }

}
