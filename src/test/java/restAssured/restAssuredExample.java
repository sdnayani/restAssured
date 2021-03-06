package restAssured;

import static org.testng.Assert.assertEquals;
import static org.hamcrest.Matchers.equalTo;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

public class restAssuredExample {
	
	@BeforeClass
	  public void setBaseUri () {

	    RestAssured.baseURI = "http://localhost:8080/rest-assured-example";
	  }
	
	/**I have added some examples for different scenarios to test .. headers, status codes, cookies, file uploads etc ..
	 Verify JSON GET Request

	 We’re testing a simple response containing some JSON data here ..

	     Request URL: /service/single-user
	     Request Method: GET
	     Response Content-Type: application/json
	     Response Body:

	     {
	       "email":"test@hascode.com",
	       "firstName":"Tim",
	       "id":"1",
	       "lastName":"Testerman"
	     }**/


	@Test
	public void testGetSingleUser() {
	  expect().
	    statusCode(200).
	    body(
	      "email", equalTo("test1@hascode.com"),
	      "firstName", equalTo("Tim"),
	      "lastName", equalTo("Testerman"),
	      "id", equalTo("1")).
	    when().
	    get("/service/single-user");
	}

	
	/**
	 * 
    Request URL: /service/single-user
    Request Method: GET
    Response Content-Type: application/json
    Response Body

    {
      "email":"test@hascode.com",
      "firstName":"Tim",
      "id":"1",
      "lastName":"Testerman"
    }


	 */
	
	@Test
	public void testGetSingleUserProgrammatic() {
	  Response res = get("/service/single-user");
	  assertEquals(200, res.getStatusCode());
	  String json = res.asString();
	  JsonPath jp = new JsonPath(json);
	  assertEquals("test@hascode.com", jp.get("email"));
	  assertEquals("Tim", jp.get("firstName"));
	  assertEquals("Testerman", jp.get("lastName"));
	  assertEquals("1", jp.get("id"));
	}
	/**
	 * Verifying XML

Now we’re going to validate returned XML

    Request URL: /service/single-user/xml
    Request Method: GET
    Response Content-Type: application/xml
    Response Body

    <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
    <user>
      <email>test@hascode.com</email>
      <firstName>Tim</firstName>
      <id>1</id>
      <lastName>Testerman</lastName>
    </user>

And this is our test: 
	 */
	
	@Test
	public void testGetSingleUserAsXml() {
	  expect().
	    statusCode(200).
	    body(
	      "user.email", equalTo("test@hascode.com"),
	      "user.firstName", equalTo("Tim"),
	      "user.lastName", equalTo("Testerman"),
	      "user.id", equalTo("1")).
	    when().
	    get("/service/single-user/xml");
	}
	/**
	 * Handling Request Parameters

This is a simple example how to add some request parameters

    Request URL: /service/user/create
    Request Method: GET
    Response Content-Type: application/json
    Response Body

    {
      "email":"test@hascode.com",
      "firstName":"Tim",
      "id":"1",
      "lastName":"Testerman"
    }

And this is our test:
	 * 
	 */
	
	@Test
	public void testCreateuser() {
	  final String email = "test@hascode.com";
	  final String firstName = "Tim";
	  final String lastName = "Tester";
	 
	  given().
	    parameters(
	      "email", email,
	      "firstName", firstName,
	      "lastName", lastName).
	  expect().
	    body("email", equalTo(email)).
	    body("firstName", equalTo(firstName)).
	    body("lastName", equalTo(lastName)).
	  when().
	  get("/service/user/create");
	}
/**
 * HTTP Status Code

Now an example how to verify HTTP headers – in the following example, a 404 Page Not Found is returned ..

    Request URL: /service/status/notfound
    Request Method: GET
    Response Content-Type: text/plain
    Response Status: 404 / Page Not Found

And this is our test:**/

@Test
public void testStatusNotFound() {
  expect().
    statusCode(404).
  when().
  get("/service/status/notfound");
}
 
	
}
