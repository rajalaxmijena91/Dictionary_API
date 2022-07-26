package createOrModifyKeyValuePairAPI;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAllKeys {

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
	public void GetAllKey()
	{


		//JSONObject requestParams=new JSONObject();

		httpRequest.headers("Authorization","Basic cWFjYW5kaWRhdGU6c29mdEtpdHR5TGl0dGxlQmFsbG9mRnVy");
		response=httpRequest.request(Method.GET,"4e43f6ea-10fc-4834-b8e2-7b1e458a6d57/keys");

		//print response in console window
		responseBody=response.getBody().asPrettyString();
		System.out.println("response Body is:"+responseBody);

		//Status code validation
		int StatusCode=response.getStatusCode();
		System.out.println("status code is:"+StatusCode);
		Assert.assertEquals(StatusCode, 200);
		String contentType=response.contentType();
		System.out.println("ContentType is: " + contentType);
		Assert.assertEquals(contentType,"application/json");
	}

	@Test(priority=1)
	public void GetAllKeyWithOutAuth()
	{
		response=httpRequest.request(Method.GET,"/4e43f6ea-10fc-4834-b8e2-7b1e458a6d57/keys");

		//print response in console window
		responseBody=response.getBody().asPrettyString();
		System.out.println("response Body is:"+responseBody);

		//Status code validation
		int StatusCode1=response.getStatusCode();
		System.out.println("status code is:"+StatusCode1);
		Assert.assertEquals(StatusCode1, 401);
		String contentType=response.contentType();
		System.out.println("ContentType is: " + contentType);
		Assert.assertEquals(contentType,"text/plain");

	}
	@Test(priority=2)
	public void GetAllKeyInvalidID()
	{

		httpRequest.headers("Authorization","Basic cWFjYW5kaWRhdGU6c29mdEtpdHR5TGl0dGxlQmFsbG9mRnVy");
		response=httpRequest.request(Method.GET,"/4e43f6ea-10fc-4834-b8e2-7b1e458a6d5/keys");


		//print response in console window
		responseBody=response.getBody().asPrettyString();
		System.out.println("response Body is:"+responseBody);

		//Status code validation
		int StatusCode=response.getStatusCode();
		System.out.println("status code is:"+StatusCode);
		Assert.assertEquals(StatusCode, 404);
		String contentType=response.contentType();
		System.out.println("ContentType is: " + contentType);
		Assert.assertEquals(contentType,"application/json");

	}
}




