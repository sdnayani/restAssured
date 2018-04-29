package restAssured;
import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

public class GetStatusCodeTest {
	//BaseUri is https://maps.googleapis.com
	//https://maps.googleapis.com/maps/api/place/textsearch/json?query=restaurants+in+Sydney&key=YOUR_API_KEY

	/*  Rest Assured follows the BDD ( Behaviour Driven Development ) approach for writing tests i.e Given- When - 
	 *  Then structure. In Given, we will mention the parameters while making the API call.When, is used for making the API call.
	    Then, is where we perform the assertion or check if the response is as expected.*/
 
@BeforeClass
  public void setBaseUri () {

    RestAssured.baseURI = "https://maps.googleapis.com";
  }

  @Test
  public void testStatusCode () {
    
    Response res = 
    given ()
    .param ("query", "restaurants in mumbai")
    .param ("key", "Xyz")
    .when()
    .get ("/maps/api/place/textsearch/json");

    Assert.assertEquals (res.statusCode (), 200);
  }

@Test
public void testStatusCodeRestAssured () {

given ().param ("query", "restaurants in mumbai")
        .param ("key", "Xyz")
        .when()
        .get ("/maps/api/place/textsearch/json")
        .then ()
        .assertThat ().statusCode (200);

   }


}