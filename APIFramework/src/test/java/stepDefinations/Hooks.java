package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		StepDefination sd = new StepDefination();
		if(StepDefination.place_Id==null)
		{
		sd.add_place_payload("Nitin", "English", "Marvel");	
		sd.user_calls_with_http_request("addPlaceAPI", "Post");
		sd.the_api_call_got_success_with_status_code(200);
		sd.verify_place_id_created_maps_to_using("Nitin", "getPlaceAPI");	
		}
	}
	
	

}
