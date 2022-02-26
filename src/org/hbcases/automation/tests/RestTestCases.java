package org.hbcases.automation.tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.restassured.*;
import io.restassured.response.Response;

import com.google.gson.*;
import com.google.gson.JsonParser;

public class RestTestCases {

	List<JsonObject> jsonList;

	public RestTestCases() {

		JsonObject jsonObj1 = new JsonObject();
		jsonObj1.addProperty("id", 1);
		jsonObj1.addProperty("name", "apple");
		jsonObj1.addProperty("price", 3);
		jsonObj1.addProperty("stock", 100);

		JsonObject jsonObj2 = new JsonObject();
		jsonObj2.addProperty("id", 2);
		jsonObj2.addProperty("name", "grapes");
		jsonObj2.addProperty("price", 5);
		jsonObj2.addProperty("stock", 50);

		jsonList = new ArrayList<JsonObject>();

		jsonList.add(jsonObj1);
		jsonList.add(jsonObj2);

	}

	@Parameters({ "url" })
	@Test
	public void showAllgrocery(String url) {
		// TODO Auto-generated method stub

		Response respond = RestAssured.get(url + "/allGrocery");

		Assert.assertEquals(respond.getStatusCode(), 200, respond.getStatusCode() + "  " + respond.getStatusLine());

		JsonObject rootObj = new JsonParser().parse(respond.asString()).getAsJsonObject();
		JsonArray groceryArray = rootObj.getAsJsonArray("data");

		// Dönen sonuç ile başlangıçta verilmiş olan json objesini karşılaştırması 
		//Başlangıçta yer alan json objsi elle örnek veri seri kontol edilerek eklenmiştir.

		boolean initialObjFounded = true;

		
		for(JsonObject obj : jsonList)
		{
			if(!groceryArray.contains(obj))
				initialObjFounded = false;
		}

		Assert.assertEquals(initialObjFounded, true, "Actual json objects are not equals expected objects");

	}

	@Parameters({ "grocery-name", "url" })
	@Test
	public void getwithName(String name, String url) {
		// TODO Auto-generated method stub

		Response respond = RestAssured.get(url + "/allGrocery/" + name);

		if (respond.getStatusCode() == 200)
			Assert.assertTrue(true);
		else if (respond.getStatusCode() == 404)
			Assert.fail("Hata Kodu:" + respond.getStatusCode() + "  Hata:" + respond.getStatusLine() + "\n"
					+ "Sayfa Bulunamdı");
		else if (respond.getStatusCode() == 400)
			Assert.fail("Hata Kodu:" + respond.getStatusCode() + "  Hata:" + respond.getStatusLine() + "\n" + "Bad Request");
		else
			Assert.fail("Hata Kodu:" + respond.getStatusCode() + "  Hata:" + respond.getStatusLine());

	}

	@Parameters({ "url" })
	@Test
	public void addGrocery(String url) {

		JsonObject jsonObj = new JsonObject();
		String name= "cherry";

		jsonObj.addProperty("id",3);
		jsonObj.addProperty("name", name);
		jsonObj.addProperty("price",12.8);
		jsonObj.addProperty("stock",25);

		Response addResponse = RestAssured.given().port(80) // port number
				.contentType("application/json") // another way to specify content type
				.body(jsonObj.toString()) // use jsonObj toString method
				.when().post(url + "/add");

		Assert.assertEquals(addResponse.getStatusCode(), 200,
				addResponse.getStatusCode() + "   " + addResponse.getStatusLine());

		Response respond = RestAssured.get(url + "/allGrocery/"+name);
		JsonObject rootObj = new JsonParser().parse(respond.asString()).getAsJsonObject();
		Assert.assertEquals(rootObj,jsonObj,"Obje eklenememistir.");
			

	}

}
