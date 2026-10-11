package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.lombokpojo.User;
import com.qa.gorest.utils.StringUtils;

public class T2_CreateUserTest extends BaseTest{
	
	@BeforeMethod
	public void createRestClientObject() {
		restClient = new RestClient(prop,baseURI);
	}
	@Test
	public void creteAndGetUser() {
		User user = new User("Sravan",StringUtils.getRandomMail(), "male", "active");
		Integer id = restClient.post("/public/v2/users", "json", user, true,true).then().log().all()
				.assertThat().statusCode(201)
				.extract().path("id");
		System.out.println("userid==== " + id);
		
//		RestClient rest2 = new RestClient();
//		System.out.println("SpecBuilder2 hashcode: " +rest2.hashCode());
		restClient.get("/public/v2/users/"+id, true , true).then().log().all();
	}

}
