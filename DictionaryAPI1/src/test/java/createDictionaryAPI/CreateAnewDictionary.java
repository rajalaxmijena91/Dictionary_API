package createDictionaryAPI;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateAnewDictionary {

	RequestSpecification httpRequest;
	Response response;
	String responseBody;

	@BeforeMethod
	public void InitialSetup()
	{
		RestAssured.baseURI="https://dictionary.iachieved.it/dictionary";
		httpRequest=RestAssured.given();
		httpRequest.header("Content-Type","application/json");
	}

	@Test
	public void CreateDictionary()
	{
		httpRequest.headers("Authorization","Basic cWFjYW5kaWRhdGU6c29mdEtpdHR5TGl0dGxlQmFsbG9mRnVy");

		response=httpRequest.request(Method.POST);	

		//Print Response in console window
		responseBody=response.getBody().asString();
		System.out.println("Response body is :" +responseBody);	

		//StatusCode validation 
		int statusCode=response.getStatusCode();
		System.out.println("Status code is :" +statusCode);
		Assert.assertEquals(statusCode, 201);
		String contentType=response.contentType();
		System.out.println("ContentType is: " + contentType);
		Assert.assertEquals(contentType,"application/json");

	}
	@Test
	public void CreateDictionarywithNoAuthorization()
	{

		response=httpRequest.request(Method.POST);	

		//Print Response in console window
		responseBody=response.getBody().asString();
		System.out.println("Response body is :" +responseBody);	

		//StatusCode validation 
		int statusCode=response.getStatusCode();
		System.out.println("Status code is :" +statusCode);
		Assert.assertEquals(statusCode, 401);
	}	
}
