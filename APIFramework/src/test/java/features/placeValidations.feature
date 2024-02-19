Feature: Validating Place API's

@AddPlace
Scenario Outline: Verify if Place is successfully added using AddPlaceAPI
	Given add place payload "<name>" "<language>" "<address>"
	When user calls "addPlaceAPI" with "Post" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	Then verify place_Id created maps to "<name>" using "getPlaceAPI"
	
Examples:
	|name             | language  | address                   |
	|Marvel House     | French-IN | 29, side layout, cohen 10 |
	#|Frontline house1 | French-IN | 29, side layout, cohen 11 |
	#|Frontline house2 | French-IN | 29, side layout, cohen 12 |

@DeletePlace	
Scenario: Verify if Delete Place functionality is working
	Given DeletePlace Payload
	When user calls "deletePlaceAPI" with "Post" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	


