package com.qa.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class T1_GetUserTest extends BaseTest{
	
	int FirstID =0;
	
	@BeforeMethod
	public void createRestClientObject() {
		restClient = new RestClient(prop, baseURI);
	}
	
	@Test(enabled=true, priority=3)
	public void getUser() {
		System.out.println("------------------------>");
		 Response response = restClient.get("/public/v2/users",true, true);
		 JsonPath js = response.jsonPath();
		 js.prettyPrint();
		 FirstID =js.getInt("[0].id");
	}
	
	@Test(enabled=true, priority=2)
	public void getUserByID() {
		System.out.println("------------------------>");
		restClient.get("/public/v2/users/"+FirstID,true, true)
		.then().log().all().assertThat().statusCode(200)
			.body("id", Matchers.equalTo(FirstID));
	}
	
	@Test(enabled=true, priority=1)
	public void getUserWithQuery() {
		System.out.println("------------------------>");
		Map<String,String> Query = new HashMap<>();
		Query.put("name", "Sravan");
		restClient.get("/public/v2/users", null, Query,true, true)
		.then().log().all().assertThat().statusCode(200)
			.body("name", Matchers.everyItem(Matchers.equalTo("Sravan")));
	}
			

}
