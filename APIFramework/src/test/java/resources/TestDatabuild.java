package resources;

import java.util.ArrayList;

import pojo.Location;
import pojo.SendPlace;

public class TestDatabuild {
	
	
	public SendPlace addPlacePayload(String name, String language, String address)
	{
		SendPlace p = new SendPlace();
		p.setAccuracy(50);
		p.setName(name);
		p.setPhone_number("(+91) 983 893 3937");
		p.setAddress(address);
		p.setWebsite("http://google.com");
		p.setLanguage(language);
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		ArrayList<String> t = new ArrayList<String>();
		t.add(0,"shoe park");
		t.add(1, "shop");
		p.setTypes(t);
		return p;
	}
	
	public String deletePlacePayload(String place_Id)
	{
		return "{\r\n"
				+ "    \"place_id\":\"" + place_Id + "\"\r\n"
				+ "}";
	}

}
