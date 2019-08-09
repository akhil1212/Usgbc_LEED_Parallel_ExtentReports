package common
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.eclipse.persistence.internal.jpa.parsing.jpql.antlr.JPQLParser.aggregateExpression_scope
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import projectKeywords.LeedOnlineKeywords

import com.kms.katalon.core.configuration.RunConfiguration
import groovy.io.FileType
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testobject.SelectorMethod
import org.openqa.selenium.remote.HttpCommandExecutor
import org.openqa.selenium.remote.SessionId
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.Capabilities

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.openqa.selenium.TakesScreenshot;

class LoginStepDefinition {

	//	String browserName,extent,logger
	ExtentTest logger;
	ExtentReports extent = commonReports.extent;
	String browserName = ""
	@Given("User has to navigate to leed online home page")
	public void user_has_to_navigate_to_leed_online_home_page() {


		try{
			WebUI.openBrowser("")
			WebUI.maximizeWindow()

			RemoteWebDriver driver = DriverFactory.getWebDriver()
			String DownloadPath = "H:\\USGBC_NewApproach1\\USGBC_LEED\\Include\\TestData\\DownloadedFiles"+"\\"+GlobalVariable.currentTestCaseId

			new	LeedOnlineKeywords().sendCommandForDownloadChromeHeadLess((HttpCommandExecutor)driver.getCommandExecutor(),driver.getSessionId(), DownloadPath )

			WebUI.navigateToUrl("https://leedonline-stg.usgbc.org")

			logger = extent.startTest("Login");


			browserName = new LeedOnlineKeywords().getBrowserName()
			logger.assignCategory(GlobalVariable.currentTestCaseId+"-Login"+" - "+browserName);
			//browserName = new LeedOnlineKeywords().getBrowserName()
			Assert.assertTrue(true);
			//			logger.log(LogStatus.PASS, "User has to navigate to leed online home page",browserName);
			logger.log(LogStatus.PASS, "User has to navigate to leed online home page",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User has to navigate to leed online home page is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	@And("User enters the username and password")
	public void user_enters_the_username_automationbdd_gmail_com_and_password_initpass() {

		try{

			TestObject userNameObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.userName)
			TestObject passwordObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.password)

			WebUI.setText(userNameObj, GlobalVariable.UserName)
			WebUI.setText(passwordObj, GlobalVariable.Password)
			//logger.log(LogStatus.PASS, "User enters the username and password",browserName);
			logger.log(LogStatus.PASS, "User enters the username and password",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User enters the username and password is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	@And("User has to accept end user license agreement")
	public void user_has_to_accept_end_user_license_agreement() {

		try{
			WebUI.delay(3)
			TestObject agreementObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.agreement)
			WebUI.scrollToElement(agreementObj,30)
			WebUI.check(agreementObj)
			//logger.log(LogStatus.PASS, "User has to accept end user license agreement",browserName);
			logger.log(LogStatus.PASS, "User has to accept end user license agreement",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "Test Case is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}


	@When("User clicks on LOG IN button")
	public void user_clicks_on_LOG_IN_button() {

		try{

			WebUI.delay(4)
			TestObject btnObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.loginButton)
			WebUI.submit(btnObj)
			//logger.log(LogStatus.PASS, "User clicks on LOG IN button",browserName);
			logger.log(LogStatus.PASS, "User clicks on LOG IN button",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "Test Case is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)
		}

	}

	@Then("User will be logged in successfully")
	public void user_will_be_logged_in_successfully(){

		//		String title=WebUI.getWindowTitle()
		//		String title_equal=WebUI.verifyEqual("LEED Online",title)
		//		WebUI.comment("########checking the title equal########"+title_equal)

		//		WebUI.delay(15)
		//		String Index=WebUI.getWindowIndex()
		//		TestObject leed = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.leed)
		//		String I= WebUI.getText(leed)
		//		String title_equal=WebUI.verifyEqual("LEED ONLINE",I)
		//		String I=WebUI.verifyElementPresent(leed, 0)

		//		TestObject leed = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.leed)
		//		boolean I=WebUI.verifyElementPresent(leed, 30)

		try{

			try{

				WebUI.delay(5)
				TestObject leed = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.leed)
				boolean I=WebUI.verifyElementPresent(leed, 30)

				println "####################Index######################"+I

			}
			catch(Exception e){

				println "######Error########" +e.stackTrace
				println "####################Inside catch######################"

				WebUI.delay(60)
				println "########--Completed waiting 60 sec's---########"
				TestObject btnObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.loginButton)
				WebUI.submit(btnObj)
				println "########--Clicking the btn---########"

			}

			//logger.log(LogStatus.PASS, "User will be logged in successfully",browserName);
			logger.log(LogStatus.PASS, "User will be logged in successfully",browserName);

		}catch(Exception e){
			logger.log(LogStatus.FAIL, "Test Case is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
		extent.endTest(logger)
	}

	///////////////////////////////////////////////////////////////////
	//	int rowNum
	//	String NameOfForm
	//
	//	static String scoreCardFormName
	//	String	prjName
	//	@Given("User clicks on project (.*)")
	//	public void user_clicks_on_Project(String project) {
	//
	//		WebUI.callTestCase(findTestCase("Test Cases/TC_Login"), null)
	//		try{
	//
	//
	//			//				WebUI.callTestCase(findTestCase("Test Cases/TC_Login"), null)
	//
	//			//TestObject projectName  = new TestObject().addProperty('xpath', ConditionType.EQUALS, '//div[@title="BDD9 TestProject"]', true)
	//			if (project == "O+M:EB"){
	//				prjName = GlobalVariable.O_M_EB
	//			}
	//			TestObject projectName = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.projectName+prjName+"']")
	//			WebUI.click(projectName)
	//			WebUI.delay(10)
	//			browserName = new LeedOnlineKeywords().getBrowserName()
	//
	//			logger.log(LogStatus.PASS, "User clicks on project",browserName);
	//		}catch(Exception e){
	//			logger.log(LogStatus.FAIL, "User clicks on project is failed");
	//			new LeedOnlineKeywords ().takeScreenshot(logger)
	//
	//		}
	//	}
	//
	//	@And("User navigates to the credits page of the project")
	//	public void user_navigates_to_the_credits_page_of_the_project() {
	//		try{
	//			TestObject creditsBtn = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.creditsBtn)
	//
	//			//WebUI.click(findTestObject('Object Repository/Login/a_Credits'))
	//
	//			WebUI.click(creditsBtn)
	//			logger.log(LogStatus.PASS, "User navigates to the credits page of the project",browserName);
	//		}catch(Exception e){
	//			logger.log(LogStatus.FAIL, "User navigates to the credits page of the project is failed");
	//			new LeedOnlineKeywords ().takeScreenshot(logger)
	//
	//		}
	//	}
	//
	//
	//
	//	@And("User should be on form (.*)")
	//	public void user_should_be_on_FRM_form(String formname) {
	//
	//		//	WebUI.callTestCase(findTestCase("Test Cases/TC_Login"), null)
	//		try{
	//			println formname
	//
	//			int formRow = new LeedOnlineKeywords().get_rowNumber_of_form(formname)
	//			rowNum = formRow
	//
	//			println rowNum
	//
	//			String formName = findTestData("Data Files/TD_FormElements").getValue("Form Name", rowNum)
	//
	//			NameOfForm =formName
	//
	//			String formStatus = entities.scoreCardStatusPrefix+formName+entities.scoreCardStatusSuffix
	//
	//			//	String formStatus = entities.scoreCard+formName+"']"+entities.scorecardFormSuffix
	//
	//			TestObject formStatusObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,formStatus,true)
	//
	//			String scoreCardStatus = WebUI.getText(formStatusObj)
	//
	//			println "The form Status is "+scoreCardStatus
	//
	//			if(scoreCardStatus == "READY FOR REVIEW"){
	//
	//				new LeedOnlineKeywords().click_mark_as_complete(formName)
	//			}
	//
	//			WebUI.delay(3)
	//
	//			scoreCardFormName = new LeedOnlineKeywords().navigate_to_form(formName)
	//
	//
	//			WebUI.delay(5)
	//
	//
	//
	//			String stringToSplit = findTestData("Data Files/TD_FormElements").getValue("Form Name with option", rowNum)
	//
	//
	//			/*if(stringToSplit.contains("!R")){
	//			 String checkBoxVal = findTestData("Data Files/TD_FormElements").getValue("Checkbox Value", rowNum)
	//			 String optionCheckbox_xpath =  new LeedOnlineKeywords().checkbox_getXpath(checkBoxVal,"option")+"[1]"
	//			 println "Checkbox to be unchecked "+optionCheckbox_xpath
	//			 new LeedOnlineKeywords().IsChecked(optionCheckbox_xpath)
	//			 }*/
	//			logger.log(LogStatus.PASS, "User should be on form",browserName);
	//		}catch(Exception e){
	//			logger.log(LogStatus.FAIL, "User should be on form is failed");
	//			new LeedOnlineKeywords ().takeScreenshot(logger)
	//
	//		}
	//		extent.endTest(logger)
	//	}
}