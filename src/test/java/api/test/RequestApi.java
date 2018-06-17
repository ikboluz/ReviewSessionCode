package api.test;

import static org.junit.Assert.*;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class RequestApi {

	/*
	 * Send a get request to https://reqres.in/api/users Including query param ->
	 * page=2 Accept type is json Verify status code 200, verify response body
	 * 
	 */
	@Test
	public void getUsersTest() {
		// given().accept(ContentType.JSON)
		// .and().params("page", 2)
		// .when().get("https://reqres.in/api/users")
		// .then().assertThat().statusCode(200);

		Response response = given().accept(ContentType.JSON).and().params("page", 2).when()
				.get("https://reqres.in/api/users");

		System.out.println(response.getStatusLine());
		System.out.println(response.getContentType());
		System.out.println(response.headers());
		System.out.println(response.body().asString());

		// add assertions for parts of response.
		assertEquals(200, response.statusCode());
		assertTrue(response.contentType().contains("application/json"));

		Header header = new Header("X-Powered-By", "Express");
		assertTrue(response.headers().asList().contains(header));

		JsonPath json = response.jsonPath();

		assertEquals(12, json.getInt("total"));
		assertEquals(4, json.getInt("total_pages"));

		assertEquals(4, json.getInt("data.id[0]"));
		// Verify that Charles's id is 5
		assertEquals(5, json.getInt("data.find{it.first_name == 'Charles'}.id"));

		// Assert using JsonPath that person with id 6, first name is Tracey
		// and lastname is Ramos
		assertEquals("Ramos", json.getString("data.find{it.id==6}.last_name"));
		assertEquals("Tracey", json.getString("data.find{it.id==6}.first_name"));

		given().header("Authorization", "Bearer 10960~07alELbC11w2DpnwzQ1rwfJb8M5u9wqDpoRfG57DWSxnjJAVmB1evcyfvZARz4EP")
				.when().get("https://learn.cybertekschool.com/api/v1/accounts/self/users").thenReturn().body()
				.prettyPrint();

		// {
		// "page": 2,
		// "per_page": 3,
		// "total": 12,
		// "total_pages": 4,
		// "data": [
		// {
		// "id": 4,
		// "first_name": "Eve",
		// "last_name": "Holt",
		// "avatar":
		// "https://s3.amazonaws.com/uifaces/faces/twitter/marcoramires/128.jpg"
		// },
		// {
		// "id": 5,
		// "first_name": "Charles",
		// "last_name": "Morris",
		// "avatar":
		// "https://s3.amazonaws.com/uifaces/faces/twitter/stephenmoon/128.jpg"
		// },
		// {
		// "id": 6,
		// "first_name": "Tracey",
		// "last_name": "Ramos",
		// "avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/bigmancho/128.jpg"
		// }
		// ]
		// }

	}

}
