package createDictionaryAPI;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateOrModifyAkeyValuePair {

	RequestSpecification httpRequest;
	Response response;
	String responseBody;
	SoftAssert softAssert = new SoftAssert();

	@BeforeMethod
	public void InitialSetup()
	{
		RestAssured.baseURI="https://dictionary.iachieved.it/dictionary";
		httpRequest=RestAssured.given();
		httpRequest.header("Content-Type","application/json");
		
	}

	@Test
	public void CreateAkeyValuePair()
	{

		//Response Object
		JSONObject requestParams=new JSONObject();
		httpRequest.headers("Authorization","Basic cWFjYW5kaWRhdGU6c29mdEtpdHR5TGl0dGxlQmFsbG9mRnVy");
		requestParams.put("value",12);
		httpRequest.body(requestParams.toJSONString());
	
		response=httpRequest.request(Method.POST,"/4e43f6ea-10fc-4834-b8e2-7b1e458a6d57/keys/key6");	
		
		//Print Response in console window
		responseBody=response.getBody().asString();
		System.out.println("Response body is :" +responseBody);	

		//StatusCode validation 
		int statusCode=response.getStatusCode();
		System.out.println("Status code is :" +statusCode);
		
		softAssert.assertEquals(statusCode, 201);
	
			
		//Content_type Validation
		String contentType=response.contentType();
		System.out.println("ContentType is: " + contentType);
		Assert.assertEquals(contentType,"application/json");

	}
	@Test
	public void CreateAkeyValuePairWithOutAuthorization()
	{
		//Response Object
		JSONObject requestParams=new JSONObject();
		requestParams.put("value", 3);
		response=httpRequest.request(Method.POST,"/4e43f6ea-10fc-4834-b8e2-7b1e458a6d57/keys/key3");	

		//Print Response in console window
		responseBody=response.getBody().asString();
		System.out.println("Response body is :" +responseBody);	

		//StatusCode validation 
		int statusCode=response.getStatusCode();
		System.out.println("Status code is :" +statusCode);
		Assert.assertEquals(statusCode, 401);
		String contentType=response.contentType();
		System.out.println("ContentType is: " + contentType);
		Assert.assertEquals(contentType,"text/plain");
	}
	
	@Test
	public void ModifyAkeyValuePair()
	{
		//Response Object
		JSONObject requestParams=new JSONObject();
		requestParams.put("value",13);
		httpRequest.body(requestParams.toJSONString());
		httpRequest.headers("Authorization","Basic cWFjYW5kaWRhdGU6c29mdEtpdHR5TGl0dGxlQmFsbG9mRnVy");
		response=httpRequest.request(Method.POST,"/4e43f6ea-10fc-4834-b8e2-7b1e458a6d57/keys/key7");	

		//Print Response in console window
		responseBody=response.getBody().asString();
		System.out.println("Response body is :" +responseBody);	

		//StatusCode validation 
		int statusCode=response.getStatusCode();
		System.out.println("Status code is :" +statusCode);
		Assert.assertEquals(statusCode,201);
		String contentType=response.contentType();
		System.out.println("ContentType is: " + contentType);
		Assert.assertEquals(contentType,"application/json");


	}

}
