package deleteDictinaryAPI;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteAdictionary {

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


	@Test(priority=0)
	public void DeleteDictionary(){


		httpRequest.headers("Authorization","Basic cWFjYW5kaWRhdGU6c29mdEtpdHR5TGl0dGxlQmFsbG9mRnVy");

		Response response=httpRequest.request(Method.DELETE,"/4e43f6ea-10fc-4834-b8e2-7b1e458a6d57");	

		//Print Response in console window
		String responseBody=response.getBody().asString();
		System.out.println("Response body is :" +responseBody);	

		//StatusCode validation 
		int statusCode=response.getStatusCode();
		System.out.println("Status code is :" +statusCode);
		Assert.assertEquals(statusCode, 200);
		String contentType=response.contentType();
		System.out.println("ContentType is: " + contentType);
		Assert.assertEquals(contentType,"application/json");

	}
	
	@Test(priority=1)
	public void ValidateDeletedKeyUsingGet() {
		
		httpRequest.headers("Authorization","Basic cWFjYW5kaWRhdGU6c29mdEtpdHR5TGl0dGxlQmFsbG9mRnVy");
	    response=httpRequest.request(Method.GET,"/4e43f6ea-10fc-4834-b8e2-7b1e458a6d57/keys");
	    System.out.println("get response body is: "+ response.getBody().asString());
	    responseBody=response.getBody().asString();
		Assert.assertEquals(responseBody.contains("4e43f6ea-10fc-4834-b8e2-7b1e458a6d57"),false);
	}
	

	@Test(priority=2)
	public void DeleteDictionaryWithOutAuthorization(){


		Response response=httpRequest.request(Method.DELETE,"/31e2db92-2e2a-4e15-b2de-4c69a8760d5b");	

		//Print Response in console window
		String responseBody=response.getBody().asString();
		System.out.println("Response body is :" +responseBody);	

		//StatusCode validation 
		int statusCode=response.getStatusCode();
		System.out.println("Status code is :" +statusCode);
		Assert.assertEquals(statusCode, 401);
		String contentType=response.contentType();
		System.out.println("ContentType is: " + contentType);
		Assert.assertEquals(contentType,"text/plain");


	}

	@Test(priority=3)
	public void DeleteDictionaryWithInvalidID(){

		httpRequest.headers("Authorization","Basic cWFjYW5kaWRhdGU6c29mdEtpdHR5TGl0dGxlQmFsbG9mRnVy");
		Response response=httpRequest.request(Method.DELETE,"/31e2db92-2e2a-4e15-b2de-4c69a8760d");	

		//Print Response in console window
		String responseBody=response.getBody().asString();
		System.out.println("Response body is :" +responseBody);	

		//StatusCode validation 
		int statusCode=response.getStatusCode();
		System.out.println("Status code is :" +statusCode);
		Assert.assertEquals(statusCode, 404);
		String contentType=response.contentType();
		System.out.println("ContentType is: " + contentType);
		Assert.assertEquals(contentType,"application/json");


	}


}
