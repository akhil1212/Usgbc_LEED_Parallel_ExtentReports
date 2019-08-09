package common
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatterBuilder.InstantPrinterParser

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.configuration.RunConfiguration
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
import com.relevantcodes.extentreports.ExtentReports
import com.relevantcodes.extentreports.ExtentTest
import com.relevantcodes.extentreports.LogStatus

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
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
import common.entities
import common.commonReports
import org.testng.Assert;

class Common_StepDef {

	int rowNum
	String NameOfForm

	static String scoreCardFormName
	String	prjName
	ExtentTest logger;
	ExtentReports extent = commonReports.extent;
	String browserName = ""

		@Given("User clicks on project (.*)")
		public void user_clicks_on_Project(String project) {
	
			try{
				WebUI.callTestCase(findTestCase("Test Cases/TC_Login"), null)
	
				//TestObject projectName  = new TestObject().addProperty('xpath', ConditionType.EQUALS, '//div[@title="BDD9 TestProject"]', true)
				if (project == "O+M:EB"){
					prjName = GlobalVariable.O_M_EB
	
				}
				TestObject projectName = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.projectName+prjName+"']")
				WebUI.click(projectName)
	
				logger = extent.startTest(GlobalVariable.currentTestCaseId);
				Assert.assertTrue(true);
	
				browserName = new LeedOnlineKeywords().getBrowserName()
				
				logger.assignCategory(GlobalVariable.scenarioName+" - "+browserName);
				
				logger.log(LogStatus.PASS, "User clicks on project",browserName);
			}catch(Exception e){
				logger.log(LogStatus.FAIL, "User clicks on project is failed");
				new LeedOnlineKeywords ().takeScreenshot(logger)
	
			}
		}
	
	
		@And("User navigates to the credits page of the project")
		public void user_navigates_to_the_credits_page_of_the_project() {
			try{
				TestObject creditsBtn = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.creditsBtn)
	
				//WebUI.click(findTestObject('Object Repository/Login/a_Credits'))
	
				WebUI.click(creditsBtn)
				logger.log(LogStatus.PASS, "User navigates to the credits page of the project",browserName);
			}catch(Exception e){
				logger.log(LogStatus.FAIL, "User navigates to the credits page of the project is failed");
				new LeedOnlineKeywords ().takeScreenshot(logger)
	
			}
		}
	
	
	
		@And("User should be on form (.*)")
		public void user_should_be_on_FRM_form(String formname) {
	
			//	WebUI.callTestCase(findTestCase("Test Cases/TC_Login"), null)
			try{
				println formname
	
				int formRow = new LeedOnlineKeywords().get_rowNumber_of_form(formname)
				rowNum = formRow
	
				println rowNum
	
				String formName = findTestData("Data Files/TD_FormElements").getValue("Form Name", rowNum)
	
				NameOfForm =formName
	
				String formStatus = entities.scoreCardStatusPrefix+formName+entities.scoreCardStatusSuffix
	
				//	String formStatus = entities.scoreCard+formName+"']"+entities.scorecardFormSuffix
	
				TestObject formStatusObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,formStatus,true)
	
				String scoreCardStatus = WebUI.getText(formStatusObj)
	
				println "The form Status is "+scoreCardStatus
	
				if(scoreCardStatus == "READY FOR REVIEW"){
	
					new LeedOnlineKeywords().click_mark_as_complete(formName)
				}
	
				WebUI.delay(3)
	
				scoreCardFormName = new LeedOnlineKeywords().navigate_to_form(formName)
	
	
				WebUI.delay(5)
	
	
	
				String stringToSplit = findTestData("Data Files/TD_FormElements").getValue("Form Name with option", rowNum)
	
	
				/*if(stringToSplit.contains("!R")){
				 String checkBoxVal = findTestData("Data Files/TD_FormElements").getValue("Checkbox Value", rowNum)
				 String optionCheckbox_xpath =  new LeedOnlineKeywords().checkbox_getXpath(checkBoxVal,"option")+"[1]"
				 println "Checkbox to be unchecked "+optionCheckbox_xpath
				 new LeedOnlineKeywords().IsChecked(optionCheckbox_xpath)
				 }*/
				logger.log(LogStatus.PASS, "User should be on form",browserName);
			}catch(Exception e){
				logger.log(LogStatus.FAIL, "User should be on form is failed");
				new LeedOnlineKeywords ().takeScreenshot(logger)
	
			}
	
		}



	@And("User verifies form version,credit name matches in scorecard and form page")
	public void user_verifies_form_version_credit_name_matches_in_scorecard_and_form_page(){

		try{
			TestObject formTitleObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.projectTitle,true)

			String formTitle = WebUI.getText(formTitleObj)

			println "The form Title is "+formTitle

			WebUI.verifyMatch(scoreCardFormName, formTitle, false)

			boolean isVersion = WebUI.verifyTextPresent("(V01)", false)

			println "Is version v01 "+isVersion

//			logger = extent.startTest(GlobalVariable.currentTestCaseId);
//			Assert.assertTrue(true);
//
//			browserName = new LeedOnlineKeywords().getBrowserName()
//
//			logger.assignCategory(GlobalVariable.scenarioName+" - Positive Flow -"+browserName);
			logger.log(LogStatus.PASS, "User verifies form version,credit name matches in scorecard and form page",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User verifies form version,credit name matches in scorecard and form page is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}



	@When("User uploads files to Add file button")
	public void user_uploads_files_to_Add_file_button() {
		try{
			println rowNum
			//WebUI.click(findTestObject("Object Repository/Page_LEED Online/label_Comprehensive Phase-Out Plan"))

			String addfileNum = findTestData("Data Files/TD_FormElements").getValue("Add File", rowNum)

			println "Number of Add files "+addfileNum
			int length = Integer.parseInt(addfileNum)

			println "Addfile num "+length

			for(int i=1;i<=length;i++){

				//String xPath = entities.addFile+'['+i+']'
				//String xPath = '(//input[@type="file"])['+i+']'


				new LeedOnlineKeywords().UploadFiles_to_Addfile(i)
				//println "The path is ###################"+xPath
				WebUI.delay(3)
			}

			logger.log(LogStatus.PASS, "User uploads files to Add file button",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User uploads files to Add file button is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	@And("User Enters data to Narrative field and verifies")
	public void user_enters_data_to_Narrative_field(){
		try{
			println rowNum

			String random = new LeedOnlineKeywords().generateRandomString(5)
			//String data = "Describe the circumstances limiting the project team's ability to provide the submittals required in this form. Be sure to reference what additional documentation has been provided, if any. Non-standard documentation will be considered upon its merits. (Optional)"+new LeedOnlineKeywords().generateRandomString(5)
			String Narrative = findTestData("Data Files/TD_TestDataPositiveFlow").getValue("Narrative", 1)
			String data = Narrative+random
			String narrativeNum = findTestData("Data Files/TD_FormElements").getValue("Narrative", rowNum)
			println "Number of Narrative "+narrativeNum
			int length = Integer.parseInt(narrativeNum)
			for(int i=1;i<=length;i++){
				String path_narrative = entities.narrative+'['+i+']'
				new LeedOnlineKeywords().fill_narrative(path_narrative,data)

			}

			logger.log(LogStatus.PASS, "User Enters data to Narrative field and verifies",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User Enters data to Narrative field and verifies is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}



	@And("User will select the Radio Button")
	public void User_will_select_the_Radio_Button(){

		try{
			println rowNum

			String optionValue
			String stringToSplit = findTestData("Data Files/TD_FormElements").getValue("Form Name with option", rowNum)

			if(stringToSplit.contains("!R")){

				String checkBoxVal = findTestData("Data Files/TD_FormElements").getValue("Checkbox Value", rowNum)

				String optionCheckbox_xpath =  new LeedOnlineKeywords().checkbox_getXpath(checkBoxVal,"option")+"[1]"

				println "Checkbox to be unchecked "+optionCheckbox_xpath

				new LeedOnlineKeywords().IsChecked(optionCheckbox_xpath)

			}

			if(stringToSplit.contains("!R")){
				String[] form_name = stringToSplit.split("!R")


				optionValue = form_name[0].substring(form_name[0].lastIndexOf("-")+1)

			}

			else{

				optionValue = stringToSplit.substring(stringToSplit.lastIndexOf("-")+1)

			}

			println "The option Value"+optionValue

			//String options = findTestData("Data Files/TD_FormElements").getValue("RadioButton Value", rowNum)

			String option_xpath = entities.radioButton+"[@value='"+optionValue+"'] | "+entities.radioButton1+"@value='"+optionValue+"']"
			//String option_xpath = "(//input[@name='selectedOption'])[@type='radio'][@value='"+optionValue+"']"

			TestObject radio_button = new TestObject().addProperty('xpath',ConditionType.EQUALS,option_xpath,true)
			println "The xpath is "+option_xpath

			new LeedOnlineKeywords().select_radio_button(radio_button)


			String radioCount = findTestData("Data Files/TD_FormElements").getValue("Radio Button", rowNum)

			int length = Integer.parseInt(radioCount)

			if(length>0){

				for(int i=1;i<=length;i++){
					String radio_xpath = entities.radioOptions+"["+i+"]"

					TestObject radiobtn = new TestObject().addProperty('xpath',ConditionType.EQUALS,radio_xpath,true)
					new LeedOnlineKeywords().select_radio_button(radiobtn)
				}

			}

			logger.log(LogStatus.PASS, "User will select the Radio Button",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User will select the Radio Button is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	@And("User enters data to text box and verifies")
	public void user_enters_data_to_text_box(){

		try{
			String textBoxNum = findTestData("Data Files/TD_FormElements").getValue("Text Box", rowNum)

			println "Text box number "+textBoxNum
			int length = Integer.parseInt(textBoxNum)
			String textValue = findTestData("Data Files/TD_TestDataPositiveFlow").getValue("Text Box", 1)
			for(int i=1;i<=length;i++){
				TestObject text_box =  new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.textMedium+'['+i+']',true)
				new LeedOnlineKeywords().enter_TextBox_Data(text_box,textValue)
			}

			logger.log(LogStatus.PASS, "User enters data to text box and verifies",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User enters data to text box and verifies is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	@And("User checks the checkbox option")
	public void user_checks_the_checkbox_option(){

		try{
			String checkBoxVal = findTestData("Data Files/TD_FormElements").getValue("Checkbox Value", rowNum)

			println "The form Name "+checkBoxVal

			String stringToSplit = findTestData("Data Files/TD_FormElements").getValue("Form Name with option", rowNum)

			//To check checkboxex apart from the option checkbox

			if(stringToSplit.contains("!R")){
				String checkBoxCount = findTestData("Data Files/TD_FormElements").getValue("Checkbox", rowNum)

				int length = Integer.parseInt(checkBoxCount)

				if(length>0){

					for(int i=1;i<=length;i++){
						String fieldCheckBoxPath = new LeedOnlineKeywords().checkbox_getXpath(checkBoxVal,"")+"["+i+"]"

						TestObject fieldCheckBoxObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,fieldCheckBoxPath,true)
						new LeedOnlineKeywords().select_checkBox(fieldCheckBoxObj)
					}

				}

			}


			else if(checkBoxVal!='' && stringToSplit.contains("!C")){

				String checkbox_xpath =  new LeedOnlineKeywords().checkbox_getXpath(checkBoxVal,"option")
				TestObject checkboxObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,checkbox_xpath,true)
				new LeedOnlineKeywords().select_checkBox(checkboxObj)
				String checkBoxCount = findTestData("Data Files/TD_FormElements").getValue("Checkbox", rowNum)
				int length = Integer.parseInt(checkBoxCount)

				if(length>0){

					for(int i=1;i<=length;i++){
						String fieldCheckBoxPath = new LeedOnlineKeywords().checkbox_getXpath(checkBoxVal,"")+"["+i+"]"

						TestObject fieldCheckBoxObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,fieldCheckBoxPath,true)
						new LeedOnlineKeywords().select_checkBox(fieldCheckBoxObj)
					}

				}
			}

			else{

				String checkBoxCount = findTestData("Data Files/TD_FormElements").getValue("Checkbox", rowNum)
				int length = Integer.parseInt(checkBoxCount)
				String checkBoxValue = ""

				if(length>0){

					for(int i=1;i<=length;i++){
						String fieldCheckBoxPath = new LeedOnlineKeywords().checkbox_getXpath(checkBoxValue,"")+"["+i+"]"
						TestObject fieldCheckBoxObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,fieldCheckBoxPath,true)
						new LeedOnlineKeywords().select_checkBox(fieldCheckBoxObj)
					}

				}

			}

			logger.log(LogStatus.PASS, "User checks the checkbox option",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User checks the checkbox option is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	@And("User enters data to text box with number and verifies")
	public void user_enters_data_to_text_box_with_number(){

		try{
			String numberTextBoxNum = findTestData("Data Files/TD_FormElements").getValue("Text Box for Numbers", rowNum)
			String numberToEnter = findTestData("Data Files/TD_TestDataPositiveFlow").getValue("Number Text Box", 1)
			int length=Integer.parseInt(numberTextBoxNum)
			for(int i=1;i<=length;i++){
				TestObject rtl_textBox = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.textRtl+'['+i+']',true)
				new LeedOnlineKeywords().enter_data_to_textbox_rtl(rtl_textBox,numberToEnter)
			}

			logger.log(LogStatus.PASS, "User enters data to text box with number and verifies",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User enters data to text box with number and verifies is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	@And("User fills the table (.*) with data (.*) isAddRow (.*) and verifies")
	public void user_fills_the_table(String table, String data, String isAddRow){

		try{
			new LeedOnlineKeywords().enter_TableData(table, data, isAddRow)

			logger.log(LogStatus.PASS, "User fills the table with data and verifies",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User fills the table with data and verifies is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	@And("User clicks on Save button")
	public void User_clicks_on_Save_button() {

		try{
			new LeedOnlineKeywords().click_save_button()
			WebUI.delay(4)

			logger.log(LogStatus.PASS, "User clicks on Save button",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User clicks on Save button is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	@Then("User can able to see the form got saved")
	public void user_can_able_to_see_the_form_got_saved(){

		try{
			WebUI.verifyTextPresent("Form data saved successfully.", false)

			WebUI.delay(6)

			logger.log(LogStatus.PASS, "User can able to see the form got saved",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User can able to see the form got saved is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	@And("User downloads the uploaded files and verifies")
	public void user_downloads_the_uploaded_files(){
		try{
			String addfileNum = findTestData("Data Files/TD_FormElements").getValue("Add File", rowNum)

			int length = Integer.parseInt(addfileNum)


			for(int i=1;i<=length;i++){

				new LeedOnlineKeywords().download_the_uploaded_files(i)

			}

			logger.log(LogStatus.PASS, "User downloads the uploaded files and verifies",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User downloads the uploaded files and verifies is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	@And("User deletes the uploaded files and verifies the files are deleted")
	public void user_deletes_the_uploaded_files(){
		try{
			String addfileNum = findTestData("Data Files/TD_FormElements").getValue("Add File", rowNum)

			int length = Integer.parseInt(addfileNum)

			for(int i=length;i>=1;i--){



				new LeedOnlineKeywords().delete_uploaded_files(i)

			}

			logger.log(LogStatus.PASS, "User deletes the uploaded files and verifies the files are deleted",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User deletes the uploaded files and verifies the files are deleted is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
		extent.endTest(logger)
	}

	@And("User clicks the Complete icon to set status to Ready for Review")
	public void User_clicks_the_Complete_icon_to_set_status_to_Ready_for_Review(){
		try{
			WebUI.back()
			WebUI.delay(2)
			new LeedOnlineKeywords().click_mark_as_complete(NameOfForm)

			WebUI.delay(2)

			WebUI.forward()

			logger.log(LogStatus.PASS, "User clicks the Complete icon to set status to Ready for Review",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User clicks the Complete icon to set status to Ready for Review is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	@And("User sets the date field and verifies")
	public void User_sets_the_date_field(){

		try{
			new LeedOnlineKeywords().fill_date_field()

			logger.log(LogStatus.PASS, "User sets the date field and verifies",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User sets the date field and verifies is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	@And("User verifies the uploaded files")
	public void user_verifies_the_uploaded_files(){

		try{
			String addfileNum = findTestData("Data Files/TD_FormElements").getValue("Add File", rowNum)

			println "Number of Add files "+addfileNum
			int length = Integer.parseInt(addfileNum)

			println "Addfile num "+length

			for(int i=1;i<=length;i++){
				boolean statusMatch = new LeedOnlineKeywords().verify_uploaded_files(i)
				println "Is files matched " +statusMatch

			}

			logger.log(LogStatus.PASS, "User verifies the uploaded files",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User verifies the uploaded files is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}


	@And("User validates the date format")
	public void User_check_the_date_field_is_validated(){
		try{
			WebDriver driver = DriverFactory.getWebDriver()

			String iframePath = entities.formIframe
			TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,iframePath,true)
			WebUI.switchToFrame(iFrame, 1)

			TestObject obj1 = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.dateText,true)
			String strDate=WebUI.getAttribute(obj1, "value")
			println "******strDate*********"+strDate

			SimpleDateFormat sdfrmt = new SimpleDateFormat("dd MMM yyyy");


			Date javaDate = sdfrmt.parse(strDate);
			//System.out.println(strDate+" is valid date format");


			WebUI.switchToDefaultContent()

			logger.log(LogStatus.PASS, "User validates the date format",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User validates the date format is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	@And("User dowloads the generated pdf form")
	public void user_verifies_the_PDF(){
		try{
			new LeedOnlineKeywords().click_pdf_Icon()

			logger.log(LogStatus.PASS, "User dowloads the generated pdf form",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User dowloads the generated pdf form is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	@And("User verifies the revision icon present after saving the form")
	public void user_verifies_the_revision_icon_present_after_saving_the_form(){
		try{
			WebUI.delay(2)
			new LeedOnlineKeywords().check_revisionIcon()

			logger.log(LogStatus.PASS, "User verifies the revision icon present after saving the form",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User verifies the revision icon present after saving the form is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	@And("User verifies the uploaded files from pdf and form page")
	public void user_verifies_the_uploaded_files_from_pdf_and_form_page(){
		try{
			String addfileNum = findTestData("Data Files/TD_FormElements").getValue("Add File", rowNum)

			println "Number of Add files "+addfileNum
			int length = Integer.parseInt(addfileNum)

			println "Addfile num "+length

			for(int i=1;i<=length;i++){
				String compare = new LeedOnlineKeywords().verify_uploaded_files_pdf(i)

				WebUI.verifyMatch(compare, "Found", false, FailureHandling.CONTINUE_ON_FAILURE)
			}

			logger.log(LogStatus.PASS, "User verifies the uploaded files from pdf and form page",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User verifies the uploaded files from pdf and form page is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}


	// Verify text from pdf

	@And("User verifies text with pdf and form page")
	public void user_verifies_text_with_pdf(){
		try{
			String textBoxNum = findTestData("Data Files/TD_FormElements").getValue("Text Box", rowNum)

			println "Text box number "+textBoxNum
			int length_text = Integer.parseInt(textBoxNum)
			String numberTextBoxNum = findTestData("Data Files/TD_FormElements").getValue("Text Box for Numbers", rowNum)

			int length_textWithNum=Integer.parseInt(numberTextBoxNum)


			if(length_text>0){

				for(int i=1;i<=length_text;i++){
					TestObject textBoxWithNum = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.textMedium+"["+i+"]",true)
					String compare = new LeedOnlineKeywords().verify_text_value_fromPdf(textBoxWithNum)

					WebUI.verifyMatch(compare, "Found", false)
				}
			}

			if(length_textWithNum>0){

				for(int i=1;i<=length_textWithNum;i++){
					TestObject textBoxWithNum = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.textRtl+"["+i+"]",true)
					String compare = new LeedOnlineKeywords().verify_text_value_fromPdf(textBoxWithNum)
					WebUI.verifyMatch(compare, "Found", false)
				}
			}

			logger.log(LogStatus.PASS, "User verifies text with pdf and form page",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User verifies text with pdf and form page is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	//Table verification with pdf

	@And("User verifies the tables with pdf and form page")
	public void user_verifies_tables(){
		try{
			String tableNum = findTestData("Data Files/TD_FormElements").getValue("Table", rowNum)

			int length=Integer.parseInt(tableNum)
			String compare = new LeedOnlineKeywords().verify_pdf_table(length)
			WebUI.verifyMatch(compare, "Found", false)

			logger.log(LogStatus.PASS, "User verifies the tables with pdf and form page",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User verifies the tables with pdf and form page is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}


	@And("User verifies the date with pdf and form page")
	public void user_verifies_the_date_with_pdf_and_form_page(){
		try{
			TestObject obj1 = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.dateText,true)

			String compare = new LeedOnlineKeywords().verify_date_from_pdf(obj1)
			WebUI.verifyMatch(compare, "Found", false)

			logger.log(LogStatus.PASS, "User verifies the date with pdf and form page",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User verifies the date with pdf and form page is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	//Narrative verification with pdf

	@And("User verifies the narrative from pdf and form page")
	public void user_verifies_the_narraive(){
		try{
			String narrativeNum = findTestData("Data Files/TD_FormElements").getValue("Narrative", rowNum)
			println "Number of Narrative "+narrativeNum
			int length = Integer.parseInt(narrativeNum)
			for(int i=1;i<=length;i++){
				String compare = new LeedOnlineKeywords().verify_narrative_text_fromPdf(i)
				WebUI.verifyMatch(compare, "Found", false)
			}

			logger.log(LogStatus.PASS, "User verifies the narrative from pdf and form page",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User verifies the narrative from pdf and form page is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}


	/* Auto Save Functionality Scripts  ----- Started  */

	String DateTime

	@And("User verifies form draft is saved automatically after 10 sec complete by filling the Narrative")
	public void user_verifies_Auto_save_after_entering_Narrative(){
		try{
			TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)
			String random = new LeedOnlineKeywords().generateRandomString(5)
			//String data = "Describe the circumstances limiting the project team's ability to provide the submittals required in this form. Be sure to reference what additional documentation has been provided, if any. Non-standard documentation will be considered upon its merits. (Optional)"+new LeedOnlineKeywords().generateRandomString(5)
			String Narrative = findTestData("Data Files/TD_TestDataAutoSave").getValue("Narrative", 1)
			String data = Narrative+random
			String narrativeNum = findTestData("Data Files/TD_FormElements").getValue("Narrative", rowNum)
			println "Number of Narrative "+narrativeNum
			int length = Integer.parseInt(narrativeNum)
			for(int i=1;i<=length;i++){
				String path_narrative = entities.narrative+"["+i+"]"
				new LeedOnlineKeywords().fill_narrative(path_narrative,data)
			}
			WebUI.delay(10)
			TestObject draftDateObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.draftDate,true)
			WebUI.switchToFrame(iFrame, 1)
			String dateTimeNarrative = WebUI.getText(draftDateObj)

			println "dateTimeNarrative "+dateTimeNarrative
			println "Initially "+DateTime
			if(DateTime == ""){
				WebUI.verifyElementPresent(draftDateObj, 3)

			}

			else{
				WebUI.verifyNotMatch(DateTime, dateTimeNarrative, false)
			}

			DateTime = dateTimeNarrative
			WebUI.switchToDefaultContent()

			logger.log(LogStatus.PASS, "User verifies form draft is saved automatically after 10 sec complete by filling the Narrative",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User verifies form draft is saved automatically after 10 sec complete by filling the Narrative is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	@And("User verifies Auto save after entering text box")
	public void user_verifies_Auto_save_after_entering_text_box(){
		try{
			TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)

			TestObject draftDateObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.draftDate,true)

			String textBoxNum = findTestData("Data Files/TD_FormElements").getValue("Text Box", rowNum)
			String TextValue = "Test filling text box"
			println "Text box number "+textBoxNum
			int length = Integer.parseInt(textBoxNum)
			String textValue = findTestData("Data Files/TD_TestDataAutoSave").getValue("Text Box", 1)

			for(int i=1;i<=length;i++){
				TestObject textObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.textMedium+"["+i+"]",true)
				new LeedOnlineKeywords().enter_TextBox_Data(textObj, textValue)
			}
			WebUI.delay(10)
			WebUI.switchToFrame(iFrame, 1)
			String dateTimetext = WebUI.getText(draftDateObj)

			println "dateTimetext "+dateTimetext
			WebUI.verifyNotMatch(DateTime, dateTimetext, false)

			DateTime = dateTimetext
			WebUI.switchToDefaultContent()

			logger.log(LogStatus.PASS, "User verifies Auto save after entering text box",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User verifies Auto save after entering text box is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	@And("User verifies Auto save after entering text box with number")
	public void user_verifies_Auto_save_after_entering_text(){
		try{
			TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)

			TestObject draftDateObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.draftDate,true)

			String numberTextBoxNum = findTestData("Data Files/TD_FormElements").getValue("Text Box for Numbers", rowNum)
			String numberToEnter = findTestData("Data Files/TD_TestDataAutoSave").getValue("Number Text Box", 1)

			//String numVal = "767"
			int length=Integer.parseInt(numberTextBoxNum)
			for(int i=1;i<=length;i++){
				TestObject NumbertextObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.textRtl+"["+i+"]",true)
				new LeedOnlineKeywords().enter_data_to_textbox_rtl(NumbertextObj,numberToEnter)

			}
			WebUI.delay(10)
			WebUI.switchToFrame(iFrame, 1)
			String dateTimeNumbertext = WebUI.getText(draftDateObj)
			println "dateTimeNumbertext "+dateTimeNumbertext
			WebUI.verifyNotMatch(DateTime, dateTimeNumbertext, false)
			DateTime = dateTimeNumbertext
			WebUI.switchToDefaultContent()

			logger.log(LogStatus.PASS, "User verifies Auto save after entering text box with number",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User verifies Auto save after entering text box with number is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	@And("User verifies form draft is saved automatically after 10 sec by entering the date field")
	public void user_verifies_Auto_save_after_entering_the_date_field(){
		try{
			TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)

			TestObject draftDateObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.draftDate,true)
			new LeedOnlineKeywords().fill_date_field_autosave()
			WebUI.delay(10)
			WebUI.switchToFrame(iFrame, 1)
			String dateTimeDate = WebUI.getText(draftDateObj)
			println "dateTimeDate "+dateTimeDate
			WebUI.verifyNotMatch(DateTime,dateTimeDate,false)

			DateTime = dateTimeDate
			WebUI.switchToDefaultContent()

			logger.log(LogStatus.PASS, "User verifies form draft is saved automatically after 10 sec by entering the date field",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User verifies form draft is saved automatically after 10 sec by entering the date field is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	/*@And("User verifies Auto save after checking the check box")
	 public void user_verifies_Auto_save_after_checking_the_check_box(){
	 }*/

	@And("User will not make any changes in the form and verifies form is not saved as draft")
	public void user_will_not_make_any_changes_in_the_form_and_verifies_form_not_saved_as_draft(){
		try{
			WebUI.delay(10)
			TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)
			WebUI.switchToFrame(iFrame, 1)
			TestObject draftDateObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.draftDate,true)
			String dateTimeNoChange = WebUI.getText(draftDateObj)
			println "dateTimeNoChange "+ dateTimeNoChange
			WebUI.verifyMatch(DateTime, dateTimeNoChange, false)

			DateTime = dateTimeNoChange
			WebUI.switchToDefaultContent()

			logger.log(LogStatus.PASS, "User will not make any changes in the form and verifies form is not saved as draft",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User will not make any changes in the form and verifies form is not saved as draft is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	@And("User uploads files to Add file button and verifies form is not saved as draft")
	public void user_uploads_files_to_Add_file_button_and_verifies_form_not_saved_as_draft(){
		try{
			String addfileNum = findTestData("Data Files/TD_FormElements").getValue("Add File", rowNum)
			int length = Integer.parseInt(addfileNum)


			for(int i=1;i<=length;i++){
				new LeedOnlineKeywords().UploadFiles_to_Addfile(i)

				WebUI.delay(3)
			}

			WebUI.delay(10)
			TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)
			WebUI.switchToFrame(iFrame, 1)
			TestObject draftDateObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.draftDate,true)

			String dateTimeAddfile = WebUI.getText(draftDateObj)
			println "dateTimeAddfile "+dateTimeAddfile
			WebUI.verifyMatch(DateTime, dateTimeAddfile, false)

			DateTime = dateTimeAddfile
			WebUI.switchToDefaultContent()

			logger.log(LogStatus.PASS, "User uploads files to Add file button and verifies form is not saved as draft",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User uploads files to Add file button and verifies form is not saved as draft is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	@And("User checks the checkbox option and verifies form draft is saved automatically after 10 sec complete")
	public void user_checks_the_checkbox_option_and_verifies_autosave(){
		try{
			String checkBoxVal = findTestData("Data Files/TD_FormElements").getValue("Checkbox Value", rowNum)

			println "The form Name "+checkBoxVal

			String stringToSplit = findTestData("Data Files/TD_FormElements").getValue("Form Name with option", rowNum)

			//To check checkbox apart from the option checkbox

			if(stringToSplit.contains("!R")){
				String checkBoxCount = findTestData("Data Files/TD_FormElements").getValue("Checkbox", rowNum)

				int length = Integer.parseInt(checkBoxCount)

				if(length>0){

					for(int i=1;i<=length;i++){
						String fieldCheckBoxPath = new LeedOnlineKeywords().checkbox_getXpath(checkBoxVal,"")+"["+i+"]"

						TestObject fieldCheckBoxObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,fieldCheckBoxPath,true)


						new LeedOnlineKeywords().ChangeCheckbox(fieldCheckBoxPath)
						WebUI.delay(3)

					}

				}

			}


			else if(checkBoxVal!='' && stringToSplit.contains("!C")){

				String checkbox_xpath =  new LeedOnlineKeywords().checkbox_getXpath(checkBoxVal,"option")
				TestObject checkboxObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,checkbox_xpath,true)
				new LeedOnlineKeywords().select_checkBox(checkboxObj)
				String checkBoxCount = findTestData("Data Files/TD_FormElements").getValue("Checkbox", rowNum)
				int length = Integer.parseInt(checkBoxCount)

				if(length>0){

					for(int i=1;i<=length;i++){
						String fieldCheckBoxPath = new LeedOnlineKeywords().checkbox_getXpath(checkBoxVal,"")+"["+i+"]"

						TestObject fieldCheckBoxObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,fieldCheckBoxPath,true)
						new LeedOnlineKeywords().ChangeCheckbox(fieldCheckBoxPath)
						WebUI.delay(3)

					}

				}
			}

			else{

				String checkBoxCount = findTestData("Data Files/TD_FormElements").getValue("Checkbox", rowNum)
				int length = Integer.parseInt(checkBoxCount)
				String checkBoxValue = ""

				if(length>0){

					for(int i=1;i<=length;i++){
						String fieldCheckBoxPath = new LeedOnlineKeywords().checkbox_getXpath(checkBoxValue,"")+"["+i+"]"
						TestObject fieldCheckBoxObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,fieldCheckBoxPath,true)

						new LeedOnlineKeywords().ChangeCheckbox(fieldCheckBoxPath)
					}

				}
			}

			WebUI.delay(10)

			TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)
			WebUI.switchToFrame(iFrame, 1)
			TestObject draftDateObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.draftDate,true)

			String dateTimeCheckbox = WebUI.getText(draftDateObj)
			println "dateTimeCheckbox "+dateTimeCheckbox
			WebUI.verifyNotMatch(DateTime, dateTimeCheckbox, false)

			DateTime = dateTimeCheckbox
			WebUI.switchToDefaultContent()

			logger.log(LogStatus.PASS, "User checks the checkbox option and verifies form draft is saved automatically after 10 sec complete",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User checks the checkbox option and verifies form draft is saved automatically after 10 sec complete is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	@And("User verifies form draft is saved automatically after 10 sec complete by selecting radio button")
	public void user_verifies_auto_save_after_selecting_radio_button(){
		try{
			WebUI.delay(10)

			TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)
			WebUI.switchToFrame(iFrame, 1)
			TestObject draftDateObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.draftDate,true)
			WebUI.verifyElementPresent(draftDateObj, 2)
			String dateTimeRadioButton = WebUI.getText(draftDateObj)
			println "dateTimeRadioButton "+dateTimeRadioButton


			DateTime = dateTimeRadioButton
			WebUI.switchToDefaultContent()

			logger.log(LogStatus.PASS, "User verifies form draft is saved automatically after 10 sec complete by selecting radio button",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User verifies form draft is saved automatically after 10 sec complete by selecting radio button is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	@And("User verifies warning message of form saved as draft is present and draft not changed")
	public void user_verifies_warning_message_of_form_saved_as_draft(){
		try{
			WebUI.delay(4)

			String warn = "//div[contains(text(),'Form drafted and not yet saved!')]"

			TestObject warnObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,warn,true)


			TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)
			WebUI.switchToFrame(iFrame, 1)

			WebUI.verifyElementPresent(warnObj, 2)

			TestObject draftDateObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.draftDate,true)

			String dateTimeAfterLogin = WebUI.getText(draftDateObj)
			println "dateTimeAfterLogin "+dateTimeAfterLogin

			WebUI.verifyMatch(DateTime, dateTimeAfterLogin, false)

			DateTime = dateTimeAfterLogin

			WebUI.switchToDefaultContent()

			logger.log(LogStatus.PASS, "User verifies warning message of form saved as draft is present and draft not changed",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User verifies warning message of form saved as draft is present and draft not changed is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	@And("User verifies clear draft,warning message is not present")
	public void user_verifies_clear_draft_warning_message_are_not_present(){
		try{
			TestObject clearDraft = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.clearDraft,true)
			WebUI.verifyElementNotPresent(clearDraft, 3)

			String warn = "//div[contains(text(),'Form drafted and not yet saved!')]"

			TestObject warnObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,warn,true)


			TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)
			WebUI.switchToFrame(iFrame, 1)

			WebUI.verifyElementNotPresent(warnObj, 2)
			WebUI.switchToDefaultContent()

			logger.log(LogStatus.PASS, "User verifies clear draft,warning message is not present",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User verifies clear draft,warning message is not present is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}


	@And("User clicks on clear draft and verifies the draft is not present")
	public void user_clears_draft(){
		try{
			new LeedOnlineKeywords().clear_draft()
			WebUI.delay(4)

			logger.log(LogStatus.PASS, "User clicks on clear draft and verifies the draft is not present",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User clicks on clear draft and verifies the draft is not present is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}

		extent.endTest(logger)
	}



	@Then("User clicks on Save button and deletes the uploaded files")
	public void User_clicks_on_Save_button_and_deletes_the_uploaded_files() {
		try{
			new LeedOnlineKeywords().click_save_button()
			WebUI.delay(4)

			String addfileNum = findTestData("Data Files/TD_FormElements").getValue("Add File", rowNum)

			int length = Integer.parseInt(addfileNum)

			for(int i=length;i>=1;i--){



				new LeedOnlineKeywords().delete_uploaded_files(i)

			}

			logger.log(LogStatus.PASS, "User clicks on Save button and deletes the uploaded files",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User clicks on Save button and deletes the uploaded files is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}


	@And("User verifies draft saved automatically after filling the table")
	public void user_verifies_draft_saved_automatically_after_filling_the_table(){
		try{
			WebUI.delay(10)

			TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)
			WebUI.switchToFrame(iFrame, 1)
			TestObject draftDateObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.draftDate,true)
			//WebUI.verifyElementPresent(draftDateObj, 2)
			String dateTimeTable = WebUI.getText(draftDateObj)
			println "dateTimeTable "+dateTimeTable

			WebUI.verifyNotMatch(DateTime, dateTimeTable, false)

			DateTime = dateTimeTable
			WebUI.switchToDefaultContent()

			logger.log(LogStatus.PASS, "User verifies draft saved automatically after filling the table",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User verifies draft saved automatically after filling the table is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}


	@And("User clicks on cancel while deleting the uploaded file and verifies the files are retained")
	public void user_cancels_file_deleting_the_uploaded_file(){
		try{
			new LeedOnlineKeywords().cancel_file_delete()

			logger.log(LogStatus.PASS, "User clicks on cancel while deleting the uploaded file and verifies the files are retained",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User clicks on cancel while deleting the uploaded file and verifies the files are retained is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	@And("User deletes the table rows table (.*) with data (.*)")
	public void user_deletes_the_table_rows(String table, String data){
		try{
			new LeedOnlineKeywords().delete_tableRows(table, data)
			WebUI.delay(3)
			new LeedOnlineKeywords().click_save_button()

			logger.log(LogStatus.PASS, "User deletes the table rows table with data",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User deletes the table rows table with data is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	/* Login and Logout for auto save functionality */

	@And("User logout and login to the form (.*) and rating(.*)")
	public void user_logout_and_login_to_the_form(String formName, String rating){
		try{
			TestObject account=new TestObject().addProperty('xpath',ConditionType.EQUALS,"//li[@id='my-account']/div",true)

			println "################################ Before Clicking Account"
			WebUI.click(account)
			println "################################ After Clicking Account"

			TestObject signout=new TestObject().addProperty('xpath',ConditionType.EQUALS,"//li[@id='my-account']/div//li[5]",true)
			println "################################ Before Clicking Signout"
			WebUI.click(signout)
			println "################################ After Clicking Signout"
			WebUI.delay(2)

			WebUI.callTestCase(findTestCase("Test Cases/TC_Login"), null)

			if (rating == "O+M:EB"){
				prjName = GlobalVariable.O_M_EB

			}
			TestObject projectName = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.projectName+prjName+"']")
			//	Added
			WebUI.delay(2)
			println "################################ Before Clicking Project"
			WebUI.click(projectName)
			println "################################ After Clicking Project"


			WebUI.delay(2)
			TestObject creditsBtn = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.creditsBtn)
			println "################################ After Clicking Credits"
			WebUI.click(creditsBtn)
			println "################################ Before Clicking Credits"

			int formRow = new LeedOnlineKeywords().get_rowNumber_of_form(formName)
			rowNum = formRow

			println rowNum

			formName = findTestData("Data Files/TD_FormElements").getValue("Form Name", rowNum)

			NameOfForm =formName

			new LeedOnlineKeywords().navigate_to_form(formName)

			logger.log(LogStatus.PASS, "User logout and login to the form and rating",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User logout and login to the form and rating is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	/* Login and Logout for auto save functionality */

	/* Auto Save Functionality Scripts  ----- Ended*/

	/* IP and SI   
	 * 
	 */
	Map<Integer,ArrayList<String>> map = new HashMap<>()
	Map<Integer,ArrayList<String>> map2 = new HashMap<>()
	@And("User checks the units of table")
	public void user_check_the_units_of_table_with_data(){
		try{
			String tableNum = findTestData("Data Files/TD_FormElements").getValue("Table", rowNum)
			int length=Integer.parseInt(tableNum)
			println "length******* : "+length
			LeedOnlineKeywords leedOnline = new LeedOnlineKeywords();

			map = leedOnline.tablecolumn(length);
			for (Map.Entry<Integer,ArrayList<String>> entry : map.entrySet())
				println "Key = " + entry.getKey() +	", Value = " + entry.getValue();

			logger.log(LogStatus.PASS, "User checks the units of table",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User checks the units of table is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	Map<Integer,ArrayList<String>> map_textbox = new HashMap<>()
	Map<Integer,ArrayList<String>> map_tb = new HashMap<>()
	@And("User checks the units of textbox")
	public void user_check_the_units_of_textbox_with_data(){

		try{
			String txtbox_Num = findTestData("Data Files/TD_FormElements").getValue("Text Box for Numbers", rowNum)
			int length=Integer.parseInt(txtbox_Num)
			println "length******* : "+length
			LeedOnlineKeywords leedOnline = new LeedOnlineKeywords();

			map_textbox = leedOnline.textboxcolumn(length);
			for (Map.Entry<Integer,ArrayList<String>> entry : map_textbox.entrySet())
				println "Key = " + entry.getKey() +	", Value = " + entry.getValue();

			logger.log(LogStatus.PASS, "User checks the units of textbox",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User checks the units of textbox is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	@And("User changes the unit type (.*) rating (.*)")
	public void User_changes_the_unit_type(String formName,String rating){
		try{
			String unitType='SI'
			new LeedOnlineKeywords().changeunits(unitType,formName,rating)

			logger.log(LogStatus.PASS, "User changes the unit type,rating",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User changes the unit type,rating is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}


	@And("User checks the units are not same in table")
	public void User_checks_the_units_are_not_same_in_table(){

		try{
			String tableNum = findTestData("Data Files/TD_FormElements").getValue("Table", rowNum)
			int length=Integer.parseInt(tableNum)
			println "length******* : "+length
			//		new LeedOnlineKeywords().tablecolumn(length)
			LeedOnlineKeywords leedOnline = new LeedOnlineKeywords();
			map2 = leedOnline.tablecolumn(length);

			for (Map.Entry<Integer,ArrayList<String>> entry : map2.entrySet())
				println "Key = " + entry.getKey() +	", Value = " + entry.getValue();

			List <String> list1 = new ArrayList<>();
			List <String> list2 = new ArrayList<>();
			for(int i =1; i <=length; i++){
				list1 = map.get(i);
				list2 = map2.get(i);

				println "Assertaion value of two tables key:"+i+"->"+ list1.equals(list2)
				/*println "list1 is " + list1
				 println "list2 is " + list2
				 WebUI.verifyNotMatch(list1, list2, false)*/

			}

			logger.log(LogStatus.PASS, "User checks the units are not same in table",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User checks the units are not same in table is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	@And("User checks the units are not same in textbox")
	public void User_checks_the_units_are_not_same_in_textbox(){
		try{
			String txtbox_Num = findTestData("Data Files/TD_FormElements").getValue("Text Box for Numbers", rowNum)
			int length=Integer.parseInt(txtbox_Num)
			println "length******* : "+length
			LeedOnlineKeywords leedOnline = new LeedOnlineKeywords();

			map_tb = leedOnline.textboxcolumn(length);

			for (Map.Entry<Integer,ArrayList<String>> entry : map_tb.entrySet())
				println "Key = " + entry.getKey() +	", Value = " + entry.getValue();

			List <String> list1 = new ArrayList<>();
			List <String> list2 = new ArrayList<>();
			for(int i =1; i <=length; i++){
				list1 = map_textbox.get(i);
				list2 = map_tb.get(i);
				println "Assertaion value of two textboxs key:"+i+"->"+ list1.equals(list2)
			}

			logger.log(LogStatus.PASS, "User checks the units are not same in textbox",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User checks the units are not same in textbox is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}

	@And("User revert the unit type (.*) rating (.*)")
	public void User_revert_the_unit_type(String formName,rating){
		try{
			String unitType='IP'
			new LeedOnlineKeywords().changeunits(unitType,formName,rating)

			logger.log(LogStatus.PASS, "User revert the unit type,rating",browserName);
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "User revert the unit type,rating is failed");
			new LeedOnlineKeywords ().takeScreenshot(logger)

		}
	}


}
