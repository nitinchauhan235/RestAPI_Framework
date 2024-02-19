package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonParseException;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	static RequestSpecification req ;
	public RequestSpecification requestSpecification() throws IOException
	{
		if(req==null)
		{
	// Logging into a new test file using request/response log filters
	PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
	req = new RequestSpecBuilder().addQueryParam("key","qaclick123")
			.addFilter(RequestLoggingFilter.logRequestTo(log))
			.addFilter(ResponseLoggingFilter.logResponseTo(log))
			.setBaseUri(getGlobalValue("baseUrl")).build();
		}
		return req;
	}
	
	public String getGlobalValue(String key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream("C:\\Users\\nitin\\OneDrive\\Desktop\\Softwares\\API Automation\\APIFramework\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(file);
		return (String) prop.get(key);
	}
	
	public String getJson(String response,String key)
	{
		JsonPath js = new JsonPath(response);
		System.out.println(js.get(key).toString());
		return js.get(key).toString();
		
	}
}
