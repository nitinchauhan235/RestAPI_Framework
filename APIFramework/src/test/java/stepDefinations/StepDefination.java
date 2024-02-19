package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Location;
import pojo.SendPlace;
import resources.APIResources;
import resources.TestDatabuild;
import resources.Utils;


public class StepDefination extends Utils{	
	

	ResponseSpecification res;
	Response response;
	RequestSpecification sepreq;
	TestDatabuild data = new TestDatabuild();
	static String place_Id;

@Given("add place payload {string} {string} {string}")
public void add_place_payload(String name, String language, String address) throws IOException{
    // Write code here that turns the phrase above into concrete actions
	
	sepreq = given().spec(requestSpecification()).body(data.addPlacePayload(name,language,address));
	
    }
@When("user calls {string} with {string} http request")
public void user_calls_with_http_request(String resource, String method) {
    // Write code here that turns the phrase above into concrete actions
	
	//Constructor will be called with the value of resource you pass
	APIResources resourceAPI = APIResources.valueOf(resource);

	res = new ResponseSpecBuilder().expectStatusCode(200).build();
	if(method.equalsIgnoreCase("Post"))
	{
		response = sepreq.when().post(resourceAPI.getResource());
	}
	else if (method.equalsIgnoreCase("Get"))
	{
		response = sepreq.when().get(resourceAPI.getResource());
	}
	else if (method.equalsIgnoreCase("Delete"))
	{
		response = sepreq.when().post(resourceAPI.getResource());
	}
	
	//.then().log().all().spec(res).extract().response();

}
@Then("the API call got success with status code {int}")
public void the_api_call_got_success_with_status_code(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
	
	assertEquals(response.getStatusCode(), 200);
	 
}
@Then("{string} in response body is {string}")
public void in_response_body_is(String Keyvalue, String Expectedvalue ) {
    // Write code here that turns the phrase above into concrete actions
	//JsonPath js = new JsonPath(response.asString());
	String responseValue = getJson(response.asString(), Keyvalue);
	assertEquals(responseValue, Expectedvalue); 
}

@Then("verify place_Id created maps to {string} using {string}")
public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
    // Write code here that turns the phrase above into concrete actions
    place_Id = getJson(response.asString(), "place_id");
    sepreq = given().spec(requestSpecification()).queryParam("place_id", place_Id);
	user_calls_with_http_request(resource, "GET");
	String actualName = getJson(response.asString(), "name");
	assertEquals(actualName, expectedName);
}

@Given("DeletePlace Payload")
public void delete_place_payload()throws IOException {
    // Write code here that turns the phrase above into concrete actions
	 sepreq = given().spec(requestSpecification()).body(data.deletePlacePayload(place_Id));
	
}


}
