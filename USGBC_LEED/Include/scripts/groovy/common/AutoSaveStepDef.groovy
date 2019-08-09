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
import common.entities
import projectKeywords.LeedOnlineKeywords

class AutoSaveStepDef {



	/*	int rowNum
	 String NameOfForm
	 static String dateTime
	 static String narrativeText
	 @When("User uploads files to Add file button to check auto save on form (.*) rating (.*)")
	 public void user_uploads_files_to_Add_file_button(String formname, String rating) {
	 int formRow = new LeedOnlineKeywords().get_rowNumber_of_form(formname,rating)
	 rowNum = formRow
	 println rowNum
	 String formName = findTestData("Data Files/TD_FormElements").getValue("Form Name", rowNum)
	 NameOfForm =formName
	 //WebUI.click(findTestObject("Object Repository/Page_LEED Online/label_Comprehensive Phase-Out Plan"))
	 TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)
	 WebUI.switchToFrame(iFrame, 1)
	 TestObject draftDateObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.draftDate,true)
	 WebUI.delay(8)
	 dateTime = WebUI.getText(draftDateObj)
	 WebUI.switchToDefaultContent()
	 String addfileNum = findTestData("Data Files/TD_FormElements").getValue("Add File", rowNum)
	 //println "Number of Add files "+addfileNum
	 int length = Integer.parseInt(addfileNum)
	 //println "Addfile num "+length
	 WebUI.delay(10)
	 for(int i=1;i<=length;i++){
	 //String xPath = entities.addFile+'['+i+']'
	 //String xPath = '(//input[@type="file"])['+i+']'
	 new LeedOnlineKeywords().UploadFiles_to_Addfile(i)
	 //println "The path is ###################"+xPath
	 WebUI.delay(3)
	 }
	 }
	 @And("User verifies form not saved as draft")
	 public void user_verifies_form_not_saved_as_draft(){
	 WebUI.delay(10)
	 TestObject draftDateObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.draftDate,true)
	 TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)
	 WebUI.switchToFrame(iFrame, 1)
	 String qq = WebUI.getText(draftDateObj)
	 println "Date and Time before narrative "+dateTime
	 println "Date and Time after narrative "+qq
	 WebUI.verifyMatch(dateTime, qq, false)
	 WebUI.switchToDefaultContent()
	 }
	 @And("User Enters data to Narrative field to check auto save on form (.*) rating (.*)")
	 public void user_enters_data_to_Narrative_field(String formname, String rating){
	 int formRow = new LeedOnlineKeywords().get_rowNumber_of_form(formname,rating)
	 rowNum = formRow
	 println rowNum
	 String formName = findTestData("Data Files/TD_FormElements").getValue("Form Name", rowNum)
	 NameOfForm =formName
	 WebUI.delay(3)
	 TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)
	 WebUI.switchToFrame(iFrame, 1)
	 //dateTime = WebUI.getText(draftDateObj)
	 //println dateTime
	 WebUI.switchToDefaultContent()
	 String narrativeNum = findTestData("Data Files/TD_FormElements").getValue("Narrative", rowNum)
	 println "Number of Narrative "+narrativeNum
	 int length = Integer.parseInt(narrativeNum)
	 for(int i=1;i<=length;i++){
	 String path_narrative = entities.narrative+"["+i+"]"
	 new LeedOnlineKeywords().fill_narrative(path_narrative)
	 }
	 WebUI.delay(10)
	 WebUI.switchToFrame(iFrame, 1)
	 TestObject draftDateObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.draftDate,true)
	 WebUI.verifyElementPresent(draftDateObj, 3)
	 //dateTime = WebUI.getText(draftDateObj)
	 WebUI.switchToDefaultContent()
	 //println dateTime
	 narrativeText = new LeedOnlineKeywords().get_narrative_text()
	 }
	 @And("User verifies warning message of form saved as draft")
	 public void user_verifies_warning_message_of_form_saved_as_draft(){
	 WebUI.delay(10)
	 WebUI.back()
	 WebUI.delay(3)
	 WebUI.forward()
	 WebUI.delay(4)
	 String warn = "//div[contains(text(),'Form drafted and not yet saved!')]"
	 TestObject warnObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,warn,true)
	 println "Text before going back "+narrativeText
	 String narrativeAfter = new LeedOnlineKeywords().get_narrative_text()
	 //WebUI.verifyMatch(narrativeText, narrativeAfter, false)
	 println "Text after "+narrativeAfter
	 //WebUI.verifyTextPresent("Form drafted and not yet saved!", false)
	 TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)
	 WebUI.switchToFrame(iFrame, 1)
	 WebUI.verifyElementPresent(warnObj, 2)
	 WebUI.switchToDefaultContent()
	 }
	 @And("User checks form saved as draft")
	 public void user_checks_autosave(){
	 WebUI.delay(10)
	 TestObject draftDateObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.draftDate,true)
	 TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)
	 WebUI.switchToFrame(iFrame, 1)
	 String qq = WebUI.getText(draftDateObj)
	 println "Date and Time before narrative "+dateTime
	 println "Date and Time after narrative "+qq
	 WebUI.verifyNotMatch(dateTime, qq, false, FailureHandling.STOP_ON_FAILURE)
	 WebUI.switchToDefaultContent()
	 }
	 @And("User clears the draft")
	 public void user_clears_draft(){
	 new LeedOnlineKeywords().clear_draft()
	 WebUI.delay(4)
	 }
	 @And("User Enters data to Narrative field after clear draft")
	 public void user_enters_data_to_Narrative_field_after_clear_draft(){
	 String path_narrative = entities.narrative+"[1]"
	 new LeedOnlineKeywords().fill_narrative(path_narrative)
	 }
	 @And("User deletes the downloaded pdf file")
	 public void user_deletes_the_downloaded_files(){
	 WebUI.delay(5)
	 new LeedOnlineKeywords().deletePdfFile()
	 }
	 @And("User deletes the table rows table (.*) with data (.*)")
	 public void user_deletes_the_table_rows(String table, String data){
	 new LeedOnlineKeywords().delete_tableRows(table, data)
	 WebUI.delay(3)
	 }
	 @And("User verifies clear draft,warning message are not present")
	 public void user_verifies_clear_draft_warning_message_are_not_present(){
	 TestObject clearDraft = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.clearDraft,true)
	 WebUI.verifyElementNotPresent(clearDraft, 3)
	 String warn = "//div[contains(text(),'Form drafted and not yet saved!')]"
	 TestObject warnObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,warn,true)
	 TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)
	 WebUI.switchToFrame(iFrame, 1)
	 WebUI.verifyElementNotPresent(warnObj, 2)
	 WebUI.switchToDefaultContent()
	 }
	 @And("User will not make any changes in the form")
	 public void user_will_not_make_any_changes_in_the_form(){
	 WebUI.delay(10)
	 }
	 @And("User cancels deleting the uploaded file")
	 public void user_cancels_file_deleting_the_uploaded_file(){
	 new LeedOnlineKeywords().cancel_file_delete()
	 }*/
}