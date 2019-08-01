package restAssuredUsingGit.test;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.json.simple.JSONObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class Test3PS {
	
	ExtentHtmlReporter reporter;
	ExtentReports extents;
	
	@BeforeSuite
	public void extentReportBeforeTestSetUp()
	{
        System.out.println("Generating extent report3");
		
		reporter = new ExtentHtmlReporter("extentReport3.html");
		extents= new ExtentReports();
		extents.attachReporter(reporter);	
	}
	
	
    //GETCall   //To get all employees detail
	@Test(priority=3)
	public void  testAllEmployees()
	{
		ExtentTest test = extents.createTest("testAllEmployees","This test methode takes the detail of all the employees");
		
		RequestSpecification resp = RestAssured.given();
	    Response getData=resp.get("http://localhost:7200/topics");
		
		String AllEmployeedata =getData.asString();
		System.out.println("Data is"+AllEmployeedata);
		
		int statuscode=getData.statusCode();
		System.out.println("Status code is "+statuscode);

		//for extent report generation
	
		if(statuscode==200)
		{
			test.pass("Test has passed");
			test.log(Status.INFO, getData.asString());
		}
		else
		{
			test.fail("Test has failed");
			Exception t = new Exception();
			test.log(Status.INFO,t.getMessage());
			
		}
		
		//for testNG to know testcase status
		Assert.assertEquals(200, statuscode);	
	
	}


	//GetCall //To get specific employee detail
	@Test(priority=4) 
	public void testSpecificEmployee()
	{
		ExtentTest test = extents.createTest("testSpecificEmployee","This test methode retrievs the detail of single employee");
		
		RequestSpecification resp =RestAssured.given();
        Response getData=resp.get("http://localhost:7200/topics/01");
		
		String SpecificEmployeedata =getData.asString();
		System.out.println("Data is"+SpecificEmployeedata);
		
		int statuscode=getData.statusCode();
		System.out.println("Status code is "+statuscode); 
		
		
		//for extent report generation
		
			if(statuscode==200)
			{
				test.pass("Test has passed");
				test.log(Status.INFO, getData.asString());
			}
			else
			{
				test.fail("Test has failed");
				Exception t = new Exception();
				test.log(Status.INFO,t.getMessage());
				
			}
			
			//for testNG to know testcase status
			Assert.assertEquals(200, statuscode);	
	
	}
	
	//PostCall   //Creating an employee
	@Test(priority=1)
	public void testCreatingNewEmployee()
	{
		ExtentTest test = extents.createTest("testCreatingNewEmployee","This test method creates an employee");
		
		RequestSpecification resp =RestAssured.given();
        resp.header("Content-Type","application/json");
        
        JSONObject json = new JSONObject();
        json.put("id", "04");
        json.put("name", "venkatesh04");
        
        resp.body(json.toJSONString());
        Response getData=resp.post("http://localhost:7200/topic");
        int statuscode =getData.statusCode();
        System.out.println("Status code is "+statuscode); 
        
      //for extent report generation
    	
    		if(statuscode==200)
    		{
    			test.pass("Test has passed");
    			test.log(Status.INFO, getData.asString());
    		}
    		else
    		{
    			test.fail("Test has failed");
    			Exception t = new Exception();
    			test.log(Status.INFO,t.getMessage());
    			
    		}
    		
    		//for testNG to know testcase status
    		Assert.assertEquals(200, statuscode);	
	}
	
	
	
	//PutCall   //Updating an employee
	@Test(priority=2)
	public void testUpdatingAnEmployee()
	{
		
		ExtentTest test = extents.createTest("testUpdatingAnEmployee","This test method updates an employee");
		
		RequestSpecification resp =RestAssured.given();
        resp.header("Content-Type","application/json");
        
        JSONObject json = new JSONObject();
        json.put("id", "03");
        json.put("name", "venkatesh03modified");
        
        resp.body(json.toJSONString());
        Response getData=resp.put("http://localhost:7200/topic/03");
        int statuscode =getData.statusCode();
        System.out.println("Status code is "+statuscode); 
        
      //for extent report generation
    	
    		if(statuscode==200)
    		{
    			test.pass("Test has passed");
    			test.log(Status.INFO, getData.asString());
    		}
    		else
    		{
    			test.fail("Test has failed");
    			Exception t = new Exception();
    			test.log(Status.INFO,t.getMessage());
    			
    		}
    		
    		//for testNG to know testcase status
    		Assert.assertEquals(200, statuscode);	
        
        
	}
	
//	    //DeleteCall   //Deleting an employee
//		@Test(priority=5)
//		public void testDeletingAnEmployee()
//		{
//			RequestSpecification resp =RestAssured.given();
//	        Response getData=resp.delete("http://localhost:7000/topic/01");
//	        int statusCode =getData.statusCode();
//	        System.out.println("Status code is "+statusCode);  
//		}
	
	
	@AfterSuite
	public void extentReportAfterTestSetUp()
	{
		extents.flush();
		System.out.println("Execution completed and reports generated");
	}
	
	
	

}
