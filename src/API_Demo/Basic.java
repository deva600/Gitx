package API_Demo;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import File.ReusableMethods;
import File.payload;

public class Basic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Validate the Post method 
		//3 concepts in Rest Assured
		//1)Given-All input details 
		//2)When-> Resource and Http Method to submit the API
		//3)Then ->Validate the Response
		
//*******Add the place->Update the place with new address->Get the Place/Validate the New address is updated-Test case condition//*
	
      RestAssured.baseURI="https://rahulshettyacademy.com";
      String response=given().log().all().queryParam("key", "qaclick123").header("content-type","application/json").body(payload.Addplace()).
      when().post("maps/api/place/add/json").
      then().assertThat().statusCode(200).body("scope",equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract().asString();
      System.out.println(response);
      
//Get the Pace id to update and get the response
      
     JsonPath js=new JsonPath(response); //->Jsan class in restAssured used to convert the Json to String
     String placeId= js.getString("place_id");
     System.out.println(placeId);
     
 //Update the Address is updated with new address//
     
  String Newaddress="1525 T.P.Lane,Southrampart";
  System.out.println("Updated Address " +Newaddress);
  
  given().log().all().queryParam("key","qaclick").header("content-type","application/json").body("{\r\n"
  		+ "\"place_id\":\""+placeId+"\",\r\n"
  		+ "\"address\":\""+Newaddress+"\",\r\n"
  		+ "\"key\":\"qaclick123\"\r\n"
  		+ "}\r\n"
  		+ "").
  when().put("maps/api/place/update/json").
  then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));
  
  
  //Get method to validate the new address updated correctly
 
  String getResponse=given().log().all().queryParam("place_id",placeId).queryParam("key","qaclick123").
  when().get("maps/api/place/get/json").
  then().log().all().assertThat().statusCode(200).extract().asString();
  System.out.println(getResponse);
  //JsonPath js1=new JsonPath(validate); -> We do this also but we created one ReUsable class as below
  JsonPath js1=ReusableMethods.rawJson(getResponse);
  String getAddress=js1.getString("address");
  System.out.println("Address int the get call " +getAddress);
  Assert.assertEquals(getAddress, Newaddress);
  
}

}
