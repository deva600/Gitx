package API_Demo;

import File.payload;
import io.restassured.path.json.JsonPath;
import static io.restassured.path.json.JsonPath.*;

public class complexJsonParse {
	public static void main(String[] arg)
	{
	
	JsonPath js=new JsonPath(payload.coursePrice());
	
	//******TC001. Print No of courses returned by API**********//
	System.out.println("******TC001. Print No of courses returned by API**********");
	int count=js.getInt("courses.size()");
	System.out.println(count);
	
	//******TC002. Print Purchase Amount**********//
	    System.out.println("******TC002. Print Purchase Amount**********");
		int purchaseAmt=js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmt);
	
   //*******TC003  Print Title of the first course*********//
		System.out.println("*******TC003  Print Title of the first course*********");
		String TitleFirst=js.get("courses[0].title");
		System.out.println(TitleFirst);
		
//*******TC004  Print All Title of the courses and its respective Prices*********//	
		System.out.println("TC004  Print All Title of the courses and its respective Prices");
		for(int i=0;i<count;i++)
		{
			String titles=js.get("courses["+i+"].title");
		    System.out.println(titles);
		    System.out.println(js.getInt("courses["+i+"].price"));
			}

//******TC005 Print no of copies sold by RPA Course	******//	
		
		System.out.println("TC005 Print no of copies sold by RPA Course");
		for(int i=0;i<count;i++)
		{
			String titles=js.get("courses["+i+"].title");
		    if(titles.equalsIgnoreCase("RPA"))
		    {
		    System.out.println(js.getInt("courses["+i+"].copies"));
		    break;
			}
		}
	
		
}	
	
}
