package File;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {
	public static JsonPath rawJson(String Response)
	{
		JsonPath Js1=new JsonPath(Response);
		return Js1;
	}
}
