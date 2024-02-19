package resources;

//Enum classes are the collection of methods or constants
public enum APIResources {
	
	addPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	private String resource;

	//Constructor
	APIResources(String resource) {
		// TODO Auto-generated constructor stub
		
		this.resource=resource;
		
	}
	
	public String getResource()
	{
		return resource;
	}
	

}
