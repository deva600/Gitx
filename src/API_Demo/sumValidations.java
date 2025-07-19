package API_Demo;

import org.testng.Assert;
import org.testng.annotations.Test;

import File.payload;
import io.restassured.path.json.JsonPath;

public class sumValidations {
@Test
public void sumOfCourse()
{
//*************TC006 Verify if Sum of all Course prices matches with Purchase Amount******//
	int sum=0;
	JsonPath js=new JsonPath(payload.coursePrice());
	int count=js.getInt("courses.size()");
	for(int i=0;i<count;i++)
	{
		int price=js.getInt("courses["+i+"].price");
		int copies=js.getInt("courses["+i+"].copies");
		int amount=price * copies;
		System.out.println(amount);
		sum=sum+amount;
	}
	System.out.println(sum);
	int purchaseAmount= js.getInt("dashboard.purchaseAmount");
	Assert.assertEquals(sum,purchaseAmount);
	}
}
