package restAssured;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
//import com.jayway.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class SimpleGetTest {
	
	@Test
	public void GetWeatherDetails()
	{  
	RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
	// Get the RequestSpecification of the request that you want to sent
	// to the server. The server is specified by the BaseURI that we have
	// specified in the above step.
	RequestSpecification httpRequest = RestAssured.given(); 
	// Make a request to the server by specifying the method Type and the method URL.
			// This will return the Response from the server. Store the response in a variable.
    Response response = httpRequest.request(Method.GET, "/Hyderabad");
			// Now let us print the body of the message to see what response
			// we have recieved from the server
	String responseBody = response.getBody().asString();
	System.out.println("Response Body is =>  " + responseBody);
	}

	@Test
	public void GetWeatherDetailsStatusCode()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Hyderabad");
 
		// Get the status code from the Response. In case of 
		// a successfull interaction with the web service, we
		// should get a status code of 200.
		int statusCode = response.getStatusCode();
 
		// Assert that correct status code is returned.
		Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
	}
	
	@Test
	public void GetWeatherDetailsInvalidCity()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/chennai");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
	}
	
	@Test
	public void GetWeatherStatusLine()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Hyderabad");
		
		// Get the status line from the Response and store it in a variable called statusLine
		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine /*actual value*/, "HTTP/1.1 200 OK" /*expected value*/, "Correct status code returned");
	}

	@Test
	public void GetWeatherHeaders()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Hyderabad");
	 
		// Reader header of a give name. In this line we will get
		// Header named Content-Type
		String contentType = response.header("Content-Type");
		System.out.println("Content-Type value: " + contentType);
	 
		// Reader header of a give name. In this line we will get
		// Header named Server
		String serverType =  response.header("Server");
		System.out.println("Server value: " + serverType);
	 
		// Reader header of a give name. In this line we will get
		// Header named Content-Encoding
		String acceptLanguage = response.header("Content-Encoding");
		System.out.println("Content-Encoding: " + acceptLanguage);
	}

	@Test
	public void IteratingOverHeaders()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Hyderabad");
	 
		// Get all the headers. Return value is of type Headers.
		// Headers class implements Iterable interface, hence we
		// can apply an advance for loop to go through all Headers
		// as shown in the code below
		Headers allHeaders = response.headers();
	 
		   System.out.println(" Printing all headers");
		// Iterate over all the Headers
		for(Header header : allHeaders)
		{
			System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
		}
	}

	@Test
	public void testWeatherHeaders()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Hyderabad");
	 
		// Reader header of a give name. In this line we will get
		// Header named Content-Type
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType /* actual value */, "application/json" /* expected value */);
	 
		// Reader header of a give name. In this line we will get
		// Header named Server
		String serverType =  response.header("Server");
		Assert.assertEquals(serverType /* actual value */, "nginx/1.12.2" /* expected value */);
	 
		// Reader header of a give name. In this line we will get
		// Header named Content-Encoding
		String contentEncoding = response.header("Content-Encoding");
		Assert.assertEquals(contentEncoding /* actual value */, "gzip" /* expected value */);
	}
	
	@Test
	public void WeatherMessageBody()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Hyderabad");
	 
		// Retrieve the body of the Response
		ResponseBody body = response.getBody();
	 
		// To check for sub string presence get the Response body as a String.
		// Do a String.contains
		String bodyAsString = body.asString();
	    System.out.println(" The bodyAsString "+bodyAsString);
		// convert the body into lower case and then do a comparison to ignore casing.
		Assert.assertEquals(bodyAsString.toLowerCase().contains("hyderabad") /*Expected value*/, true /*Actual Value*/, "Response body contains Hyderabad");
	}

	@Test
	public void VerifyCityInJsonResponse()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Hyderabad");
	 
		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();
	 
		// Then simply query the JsonPath object to get a String value of the node
		// specified by JsonPath: City (Note: You should not put $. in the Java code)
		String city = jsonPathEvaluator.get("Humidity");
	 
		// Let us print the city variable to see what we got
		System.out.println("City received from Response " + city);
	 
		// Validate the response
		Assert.assertEquals(city, "Hyderabad", "Correct city name received in the Response");
	 
	}
	
	@Test
	public void DisplayAllNodesInWeatherAPI()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Hyderabad");
	 
		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();
	 
		// Let us print the city variable to see what we got
		System.out.println("City received from Response " + jsonPathEvaluator.get("City"));
	 
		// Print the temperature node
		System.out.println("Temperature received from Response " + jsonPathEvaluator.get("Temperature"));
	 
		// Print the humidity node
		System.out.println("Humidity received from Response " + jsonPathEvaluator.get("Humidity"));
	 
		// Print weather description
		System.out.println("Weather description received from Response " + jsonPathEvaluator.get("Weather"));
	 
		// Print Wind Speed
		System.out.println("City received from Response " + jsonPathEvaluator.get("WindSpeed"));
	 
		// Print Wind Direction Degree
		System.out.println("City received from Response " + jsonPathEvaluator.get("WindDirectionDegree"));
	}
}
