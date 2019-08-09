package projectKeywords
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

//import java.util.concurrent.atomic.Striped64.Cell

//import javax.swing.DefaultRowSorter.Row

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
import com.relevantcodes.extentreports.ExtentTest
import com.relevantcodes.extentreports.LogStatus

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.apache.poi.ss.usermodel.DataFormatter
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import common.entities
import gherkin.ast.Scenario
import groovy.io.FileType

import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.hssf.util.CellReference
//import org.apache.poi.ss.usermodel

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Row



import org.apache.http.entity.StringEntity
import org.openqa.selenium.remote.HttpCommandExecutor
import org.openqa.selenium.remote.SessionId

import org.apache.http.client.HttpClient
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpPost

import org.apache.http.io.SessionInputBuffer
import org.apache.http.entity.ContentType
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.Capabilities

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeDriverService
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.json.Json


import org.apache.http.client.utils.URIBuilder
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients

import java.lang.reflect.Field
import com.testautomationguru.utility.PDFUtil

class LeedOnlineKeywords {

	//Navigation to form

	@Keyword
	def navigate_to_form(String formName){

		//String formNamePath = entities.scorecardFormName+"[contains(text(),'"+formName+"')]"

		String formNamePath = entities.scoreCard+formName+"'])"

		TestObject formNameObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,formNamePath,true)

		//TestObject formNameObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,"(//div[@class='col2 credit-name simpleTip'])[contains(text(),'Fundamental')]",true)

		////div[@class='col2 credit-name simpleTip'][contains(text(),"Fundamental"

		//WebUI.scrollToElement(formNameObj, 2)


		WebUI.delay(2)

		WebUI.click(formNameObj)

		String formname = WebUI.getText(formNameObj)

		println "The form name in scorecard is "+ formname
		WebUI.delay(3)

		//String formVPath = entities.scorecardFormPrefix+formName+entities.scorecardFormSuffix

		String formVPath = entities.scoreCard+formName+entities.scorecardFormSuffix
		//String formNamePath = entities.scoreCard+formName+"']"


		TestObject formV1 = new TestObject().addProperty('xpath',ConditionType.EQUALS,formVPath,true)

		WebUI.click(formV1)

		return formname



	}



	/**
	 * Upload files to Add File field
	 */
	@Keyword
	def UploadFiles_to_Addfile(int addFileIndex) {

		String ElemntPath = entities.addFile+'['+addFileIndex+']'

		println "Path inside Keyword "+ElemntPath
		String iframePath = entities.formIframe

		TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,iframePath,true)

		WebUI.switchToFrame(iFrame, 1)
		def list = []
		//def dir = new File("/home/group10/TestFiles")
		String path = RunConfiguration.getProjectDir() + '/Include/TestData/FilesToUpload'
		//println path
		def dir = new File(path)
		dir.eachFile (FileType.FILES) { file -> list << file}
		for (item in list) {
			//println item
			String Filepath = item

			println "File path "+Filepath
			//String Filepath = "/home/promantusinc/Desktop/desktop_files/COO_1.pdf"

			TestObject inputField  = new TestObject().addProperty('xpath', ConditionType.EQUALS, ElemntPath, true)
			println "tiny ui button "+ entities.AddFileEnabled+addFileIndex+"]"
			TestObject waitEnabled = new TestObject().addProperty('xpath', ConditionType.EQUALS, entities.AddFileEnabled+addFileIndex+"]", true)
			WebUI.waitForElementAttributeValue(waitEnabled, 'class', 'tiny ui button', 20)
			WebUI.delay(7)
			WebUI.uploadFile(inputField, Filepath)

		}

		WebUI.switchToDefaultContent()
	}







	//######################Writing into Narrative Field###############

	@Keyword

	def fill_narrative(String narrative_framexpath, String data){

		//String data = "Describe the circumstances limiting the project team's ability to provide the submittals required in this form. Be sure to reference what additional documentation has been provided, if any. Non-standard documentation will be considered upon its merits. (Optional)"+generateRandomString(5)

		TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)

		TestObject editor_frame = new TestObject().addProperty('xpath',ConditionType.EQUALS,narrative_framexpath,true)

		TestObject editor_body = new TestObject().addProperty('xpath',ConditionType.EQUALS,"//body",true)

		WebUI.switchToFrame(iFrame, 2)

		WebUI.switchToFrame(editor_frame, 2)

		WebUI.setText(editor_body, data)

		WebUI.delay(2)

		String textAdded = WebUI.getText(editor_body)

		println textAdded

		boolean isNarrative = WebUI.verifyMatch(data, textAdded, false)

		println "Narrative verification is "+isNarrative

		WebUI.switchToDefaultContent()



	}


	//Selecting Radio Button

	@Keyword

	def select_radio_button(TestObject radio_xpath){

		TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)
		WebUI.switchToFrame(iFrame, 2)
		WebUI.click(radio_xpath)

		WebUI.switchToDefaultContent()
	}




	//################Filling the Text Box (Medium Text)#############################
	@Keyword

	def enter_TextBox_Data(TestObject textbox_object,String textVal){
		TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)
		WebUI.switchToFrame(iFrame, 2)

		WebUI.setText(textbox_object, textVal)

		WebUI.delay(2)

		String textFilled = WebUI.getAttribute(textbox_object, "value")

		boolean textVerify = WebUI.verifyMatch(textFilled, textVal, false)
		println "Text verification is "+textVerify

		WebUI.switchToDefaultContent()


	}


	//########################Filling Textt Box (RTL), with numbers################
	@Keyword
	def enter_data_to_textbox_rtl(TestObject rtl_text, String number){
		TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)
		WebUI.switchToFrame(iFrame, 2)
		WebUI.setText(rtl_text, number)

		WebUI.delay(2)

		String numVal = WebUI.getAttribute(rtl_text, "value")

		boolean isNumber = WebUI.verifyMatch(numVal, number, false)

		println "Number verified "+isNumber
		WebUI.switchToDefaultContent()

	}

	//################################CheckBox######################

	@Keyword
	def select_checkBox(TestObject checkbox_object){
		TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)
		WebUI.switchToFrame(iFrame, 2)
		WebUI.check(checkbox_object)
		WebUI.switchToDefaultContent()
	}


	//Get the row number of the form name from excel


	@Keyword

	def get_rowNumber_of_form(String form_name){
		WebUI.delay(2)
		String fileName = RunConfiguration.getProjectDir()+'/Include/TestData/FormBase/USGBC-FormList.xlsx'
		String cellContent = form_name//"Fundamental Refrigerant Management-comprehensivePhaseOut"
		///int rownr=0, colnr = 1

		InputStream input = new FileInputStream(fileName);

		XSSFWorkbook wb = new XSSFWorkbook(fileName)
		// = new XSSFWorkbook(input);
		XSSFSheet sheet = wb.getSheetAt(0);
		for (Row row : sheet) {
			for (Cell cell : row) {
				if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					if (cell.getRichStringCellValue().getString().trim().equals(cellContent)) {
						println row.getRowNum();

						return row.getRowNum();
					}
				}
			}
		}


		return 0
	}

	/*
	 * Entering data to tables
	 *
	 */
	@Keyword

	def enter_TableData(String table_name, String testData, String isAddRow) {


		String iframePath = entities.formIframe
		TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,iframePath,true)
		WebUI.switchToFrame(iFrame, 1)



		//String table_name = "Table: Mechanical Cooling and Refrigeration Equipment "
		println table_name
		int columnCount = findTestData("Data Files/"+testData).getColumnNumbers()
		println "Column Number"+columnCount

		int[] tableColums = new int[columnCount]
		//For Loop to get column names which has select fields "_Select will suffixed in columns names which has select option"
		for (int i=0;i<columnCount;i++){
			String ColuName = findTestData("Data Files/"+testData).getColumnNames()[i]
			if(ColuName.contains("SelectField")){
				tableColums[i] = 1
				println ColuName
			}
			else{
				tableColums[i] = 0
			}

			println "i value #######"+i
		}
		int cellIndex =1
		int rowCount = findTestData("Data Files/"+testData).getRowNumbers()
		//For Loop to enter details in the Table
		for(int row=1;row<=rowCount;row++){

			println "Row index value "+row
			for(int col=1;col<=columnCount;col++)
			{
				//println "Cell Index"+cellIndex
				//	println "Column index number##############"+col+"Value of column "+findTestData("Data Files/"+testData).getValue(col, row)
				if(tableColums[col-1] == 1 && findTestData("Data Files/"+testData).getValue(col, row) != ""){
					//	println "Inside If Column index number##############"+col+"Value of column "+findTestData("Data Files/"+testData).getValue(col, row).value

					//String xpath_select = entities.tableSelect+'['+cellIndex+']'
					String xpath_select = entities.tableSelectPrefix + table_name +entities.tableSelectSuffix+ "[" + row + "]" + "//td//select)" + '['+cellIndex+']'
					println "Inside if ###############"+xpath_select
					TestObject tablePath_select = new TestObject().addProperty('xpath',ConditionType.EQUALS, xpath_select,true)
					WebUI.selectOptionByValue(tablePath_select,findTestData("Data Files/"+testData).getValue(col, row),true)

					WebUI.delay(2)
					String cellValueSelect = WebUI.getAttribute(tablePath_select, 'value')

					//println "Cell value  get"+cellValueSelect

					WebUI.verifyMatch(cellValueSelect, findTestData("Data Files/"+testData).getValue(col, row), false)
					cellIndex++
				}

			}
			//Reinitizailise cellindex =0 to handle input text to start from 1
			cellIndex=1
			for(int col=1;col<=columnCount;col++)
			{
				println "Cell Index"+cellIndex
				println "Column index number##############"+col
				if(tableColums[col-1] == 0 && findTestData("Data Files/"+testData).getValue(col, row) != ""){
					//String xpath_select = entities.tableSelect+'['+cellIndex+']'
					String xpath_text = entities.tableTextPrefix+table_name+entities.tableTextSuffix+"[" + row + "]" + "//td//input[@type = 'text'])" + '['+cellIndex+']'
					//println "Inside else 3333333333333333333 "+xpath_text
					TestObject tablePath_text = new TestObject().addProperty('xpath',ConditionType.EQUALS,xpath_text,true)
					WebUI.clearText(tablePath_text)
					WebUI.setText(tablePath_text, findTestData("Data Files/"+testData).getValue(col, row))

					WebUI.delay(2)

					String cellValueText = WebUI.getAttribute(tablePath_text, 'value')
					//println "Cell text value get "+cellValueText
					WebUI.verifyMatch(cellValueText, findTestData("Data Files/"+testData).getValue(col, row), false)
					cellIndex++


				}

			}



			//Boolean isPresent
			String Xpath_Add = "(//label[contains(text(),'"+table_name+"')]/following::table[1]//td//i)"+[row]
			TestObject ADD_ICON = new TestObject().addProperty('xpath',ConditionType.EQUALS,Xpath_Add,true)

			//There are few rows which doesn't have add icon at last column ,We consider that as a static rows,
			//So to avoid failure we have to skip clicking on add icon
			if(row<rowCount && isAddRow == "Yes"){
				//To Add new row in the Table,Click on the ADD Button ICON
				//String Xpath_Add = "(//label[contains(text(),'"+table_name+"')]/following::table[1]//td//i)"+[row]
				//TestObject ADD_ICON = new TestObject().addProperty('xpath',ConditionType.EQUALS,Xpath_Add,true)
				WebUI.click(ADD_ICON)
				cellIndex=1
			}

			else{
				cellIndex =1
			}

		}

		WebUI.switchToDefaultContent()
		WebUI.delay(4)
	}

	//##########################Click Save Button#######################

	@Keyword

	def click_save_button(){
		TestObject saveButton = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.saveButton,true)
		TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)
		WebUI.switchToFrame(iFrame, 2)
		WebUI.click(saveButton)
		WebUI.switchToDefaultContent()
	}

	//######################Get number of rows of uploaded files' table

	@Keyword
	def countRowsPerPage(String xpath){

		//println "xpath is " + xpath
		WebDriver driver = DriverFactory.getWebDriver()

		//Find the table element on the page

		//WebUI.switchToFrame(findTestObject('Object Repository/PurchasingPolicy/iframe'), 10)

		TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)
		WebUI.switchToFrame(iFrame, 2)

		WebElement Webtable=driver.findElement(By.xpath(xpath))
		//println Webtable

		List TotalRowCount=Webtable.findElements(By.xpath(xpath))

		//Get the size of the List, this is the number of rows
		int totalNumberOfRows=TotalRowCount.size()
		println('Total Number of rows on the page:= ' + totalNumberOfRows)

		WebUI.switchToDefaultContent()

		return totalNumberOfRows

	}

	//#####################Set Download Location################################

	@Keyword

	def setDownloadPath() {

		//ChromeOptions options = new ChromeOptions();
		//options.setHeadless(true)

		HashMap<Object, String> chromePrefs = new HashMap<Object, String>();

		chromePrefs.put("download.default_directory", RunConfiguration.getProjectDir() + "/Include/TestData/DownloadedFiles")

		RunConfiguration.setWebDriverPreferencesProperty("prefs", chromePrefs)





	}

	//------------------Check whether File gets Downloaded-----------------------

	@Keyword


	def isFiledownloaded(String downloadPath, String fileName) {
		File dir = new File(downloadPath);

		File[] dirContents = dir.listFiles();

		String lastAttempt = '';
		String fileNameInExcel ='';
		WebUI.delay(1)

		println "######################## dowloadPath" + downloadPath
		println "######################## dirContents.length"+ dirContents.length
		if (dirContents.length > 0) {

			for (int i = 0; i < dirContents.length; i++) {

				if (dirContents[i].getName().equals(fileName)) {

					// File has been found, it can now be deleted:

					dirContents[i].delete();

					KeywordUtil.markPassed(fileName + ' exist in ' + downloadPath + ' and was deleted')
					KeywordUtil.markPassed("")
					println "Fname from folder "+dirContents[i].getName() + "equals to" + fileName
					fileNameInExcel = dirContents[i].getName()
					println "File name is = "+ fileNameInExcel
					return fileNameInExcel;
					//return true;

				}

				//	lastAttempt = dirContents[i].getName().equals(fileName);
				lastAttempt = dirContents[i].getName()
				println "lastAttempt is = "+ lastAttempt
			}

			if (lastAttempt != fileName) {
				//println "Fname from folder "+dirContents[i].getName() + "not equals to" + fileName
				//fileNameInExcel = dirContents[i].getName()
				KeywordUtil.markFailed(fileName + ' does not exist in ' + downloadPath)
				println "lastAttempt is = "+ lastAttempt
				return lastAttempt
				//return false;
			}
		}

		return false;
	}

	//##################Mark As complete

	@Keyword

	def click_mark_as_complete(String formName){

		String completeIcon = entities.markCompletePrefix+formName+entities.markCompleteSuffix

		TestObject markAsComplete = new TestObject().addProperty('xpath',ConditionType.EQUALS,completeIcon,true)

		WebUI.click(markAsComplete)

		WebUI.delay(5)
	}


	///###################Downloading Files############

	@Keyword

	def delete_uploaded_files(int fileIndex){
		String tablePath = entities.uploadedTablePrefix+fileIndex+entities.uploadedTableSuffix

		int NumOfRows = new LeedOnlineKeywords().countRowsPerPage(tablePath)
		println "Number of rows "+NumOfRows


		if (NumOfRows > 0){
			for (int k = NumOfRows; k>=1; k--) {

				//      //println(k)
				TestObject  delete = new TestObject().addProperty('xpath', ConditionType.EQUALS, entities.deleteIconPrefix+fileIndex+entities.deleteIconSuffix+"["+k+"]", true)
				////println download
				WebUI.delay(2)
				//WebUI.mouseOver(findTestObject('PurchasingPolicy/Upload_Five_most/p_Upload the five most purchas'))
				//WebUI.switchToFrame(findTestObject("Object Repository/PurchasingPolicy/iframe"), 5)

				TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)
				WebUI.switchToFrame(iFrame, 2)

				WebUI.delay(5)
				WebUI.click(delete)
				WebUI.delay(2)
				TestObject yesbutton = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.yesButton,true)
				WebUI.click(yesbutton)
				WebUI.switchToDefaultContent()
				//WebUI.click(findTestObject('Object Repository/PurchasingPolicy/Upload_Five_most/button_Yes'))
				WebUI.delay(2)
			}

			WebUI.delay(5)
			String uploadedtable = entities.uploadedFileTable+"["+fileIndex+"]"

			TestObject uploadedFileObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,uploadedtable,true)

			boolean isNotFound = WebUI.verifyElementNotPresent(uploadedFileObj, 2)

			println "Is table Not found "+isNotFound
		}
	}


	//##########################Download the uploded files

	@Keyword

	def download_the_uploaded_files(int fileIndex){

		String tablePath = entities.uploadedTablePrefix+fileIndex+entities.uploadedTableSuffix

		int NumOfRows = new LeedOnlineKeywords().countRowsPerPage(tablePath)
		println "Number of rows "+NumOfRows


		if (NumOfRows > 0){

			//WebUI.scrollToElement(findTestObject('PurchasingPolicy/Upload_Five_most/p_Upload the five most purchas'), 2)

			for (int k = NumOfRows; k>=1; k--) {


				TestObject  download = new TestObject().addProperty('xpath', ConditionType.EQUALS, entities.downloadIconPrefix+fileIndex+entities.downloadIconSuffix+"["+k+"]", true)

				WebUI.delay(2)

				TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)
				WebUI.switchToFrame(iFrame, 2)
				TestObject fileName =  new TestObject().addProperty('xpath', ConditionType.EQUALS, entities.uploadedFileNamePrefix+fileIndex+entities.uploadedFileNameSuffix+"["+k+"]", true)

				WebUI.delay(5)
				WebUI.click(download)
				IsFileExist(fileName)
				WebUI.mouseOver(iFrame)
				WebUI.switchToDefaultContent()
				WebUI.delay(2)
			}

			WebUI.delay(5)

		}
		String path = RunConfiguration.getProjectDir() + '/Include/TestData/DownloadedFiles'+"/"+GlobalVariable.currentTestCaseId

		for(int j=1;j<=findTestData("Data Files/TD_FileNames").getRowNumbers();j++){
			WebUI.delay(2)

			String fname = findTestData("Data Files/TD_FileNames").getValue("File Name", j)
			println fname
			String isDownloaded = new LeedOnlineKeywords().isFiledownloaded(path,fname )
			Boolean isFilePresent =  WebUI.verifyMatch(isDownloaded, fname, false)
			println isFilePresent
			println isDownloaded

			WebUI.delay(2)
		}

		deleteDownloadedFiles(path)


	}

	//####################################Delete All the Files From Downloaded Location###################

	@Keyword

	def deleteDownloadedFiles(String downloadPath){
		File dir = new File(downloadPath);
		File[] dirContents = dir.listFiles();

		println "#################"+downloadPath
		println dirContents.length
		if (dirContents.length > 0) {

			for (int i = 0; i < dirContents.length; i++) {

				dirContents[i].delete();
				//return true;

			}

		}

		return false
	}


	//####################To fill date field

	@Keyword

	def fill_date_field(){

		String iframePath = entities.formIframe

		TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,iframePath,true)
		WebUI.switchToFrame(iFrame, 1)

		TestObject obj1 = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.dateText,true)
		WebUI.click(obj1)

		TestObject obj = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.dateSelect,true)
		WebUI.click(obj)

		WebUI.delay(2)

		String dateFilled = WebUI.getAttribute(obj1, "value")

		println "Date is "+dateFilled

		WebUI.verifyNotMatch(dateFilled, "", false)

		WebUI.switchToDefaultContent()

	}



	//Enter date for Auto save
	@Keyword

	def fill_date_field_autosave(){

		String iframePath = entities.formIframe

		TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,iframePath,true)
		WebUI.switchToFrame(iFrame, 1)

		TestObject obj1 = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.dateText,true)
		WebUI.click(obj1)

		TestObject obj = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.dateSelectAutosave,true)
		WebUI.click(obj)

		WebUI.delay(2)

		String dateFilled = WebUI.getAttribute(obj1, "value")

		println "Date is "+dateFilled

		WebUI.verifyNotMatch(dateFilled, "", false)

		WebUI.switchToDefaultContent()

	}

	//########################selecting checkbox option

	@Keyword

	def String checkbox_getXpath(String nameAttr,String checkBoxType){
		String checkBoxPath

		if(checkBoxType == "option"){
			checkBoxPath = "(//div[@class='ui checkbox leed-checkbox']//input[@type='checkbox' and (@name='"+nameAttr+"')])"

		}

		else{
			checkBoxPath = "(//div[@class='ui checkbox leed-checkbox']//input[@type='checkbox' and not(@name='"+nameAttr+"')])"

		}

		return checkBoxPath
	}

	@Keyword
	def public String generateRandomString(int strLen) {

		String aToZ = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"
		Random rand=new Random();
		StringBuilder res=new StringBuilder();
		for (int i = 0; i <strLen ; i++) {
			int randIndex=rand.nextInt(aToZ.length());
			res.append(aToZ.charAt(randIndex));
		}
		return res.toString();
	}



	@Keyword
	static void sendCommandForDownloadChromeHeadLess(HttpCommandExecutor driverCommandExecutor,SessionId sessionId,String downloadPath) {
		println "Entered now"
		println ""+driverCommandExecutor
		println ""+sessionId
		println "" +downloadPath
		Json json = new Json()

		Map<String, Object> paramsMap = new HashMap<>();
		paramsMap.put("cmd", "Page.setDownloadBehavior");

		//	println "######################" + paramsMap
		Map<String,String> cmdParamsMap = new HashMap<>();
		cmdParamsMap.put("behavior", "allow");
		//	println "######################" + cmdParamsMap
		cmdParamsMap.put("downloadPath", downloadPath);
		//	println "######################" + cmdParamsMap
		paramsMap.put("params", cmdParamsMap);
		//	println "######################" + paramsMap

		String content = json.toJson(paramsMap)
		//	println "################# Content = "+content
		//log.debug("The request content is :: {}" ,content);
		URL remoteServerUri = null;
		try {

			Field field = HttpCommandExecutor.class.getDeclaredField("remoteServer")

			//		println "################# Field = "+field
			field.setAccessible(true);
			remoteServerUri = (URL)field.get(driverCommandExecutor);
			//		println "################# remoteServerUri = "+remoteServerUri
		}catch (Exception e) {
			println "############# ERROR"+e
			println "This will cause all the file validations to fail"
			//log.debug("The HttpCommandExecutor has been modified please check with the framework team",e);
			//log.error("This will cause all the file validations to fail");
			return;
		}

		CloseableHttpClient httpclient = null;

		try {


			httpclient = HttpClients.createDefault()
			//	println "############# httpclient"+ httpclient
			URIBuilder builder = new URIBuilder(remoteServerUri.toURI())
			//	println "############# builder"+ builder
			builder.setPath("/session/"+sessionId.toString()+"/chromium/send_command");
			//builder.setPath("session/"+sessionId.toString()+"/chromium/send_command");
			//	println "############# builder.setPath"+ builder
			HttpPost sendCommandPost = new HttpPost(builder.build())
			//	println "############# sendCommandPost"+ sendCommandPost
			sendCommandPost.setHeader("Content-Type", ContentType.APPLICATION_JSON.getMimeType())
			//sendCommandPost.setHeaders("Content-Type","")

			//StringEntity entity = new StringEntity(content, );
			StringEntity entity = new StringEntity(content, ContentType.APPLICATION_JSON)
			//		println "############# entity"+ entity
			sendCommandPost.setEntity(entity);
			//		println "############# sendCommandPost"+ sendCommandPost
			CloseableHttpResponse response = httpclient.execute(sendCommandPost);
			//		println "############# response"+ response
			int statusCode = response.getStatusLine().getStatusCode();
			//log.debug("The Response Status code is {}",statusCode);
			println"The Response Status code is {}" + statusCode;
			if(statusCode <= 200 && statusCode >= 300) {
				///log.debug("Un-Successfull status code received");
				println "Failure"
			}
		}catch (IOException e) {
			//log.error("Error Occured while enabling download file setting for chrome headless mode");
			//log.error("This will cause all the file validations to fail",e);
			println "This will cause all the file validations to fail"+e

		} catch (URISyntaxException e) {
			//log.debug("this should never ever occur");
			println "this should never ever occur"

		}
		finally {
			if(httpclient != null) {
				try {
					//httpclient.close()
				} catch (IOException e) {
					//log.warn("Error Occured while closing the http client",e);
					println "Error Occured while closing the http client" + e
				}
			}
		}

	}


	@Keyword

	def IsFileExist(TestObject fileNameObject){

		String  fname_txt = WebUI.getText(fileNameObject)
		String path = RunConfiguration.getProjectDir() + '/Include/TestData/DownloadedFiles/'+GlobalVariable.currentTestCaseId+"/"+fname_txt
		println "############################## Path = "+path
		File file = new File(path);
		println "############################## file = "+file
		while (!file.exists()) {
			println "Waiting for file to download"
			WebUI.delay(5)
		}
	}

	//To uncheck optional checkbox

	@Keyword
	def public IsChecked(String xpath){
		WebDriver driver = DriverFactory.getWebDriver()

		String iframePath = entities.formIframe

		TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,iframePath,true)
		WebUI.switchToFrame(iFrame, 1)
		WebElement checkbox =driver.findElement(By.xpath(xpath))
		if(checkbox.isSelected()){
			println"Checked ##########"
			checkbox.click()
		}
		WebUI.switchToDefaultContent()




	}


	//Clear Draft

	@Keyword

	def clear_draft(){
		TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)

		TestObject clearDraft = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.clearDraft,true)
		TestObject yesButton = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.yesButton,true)
		WebUI.delay(4)
		WebUI.switchToFrame(iFrame, 2)
		WebUI.click(clearDraft)
		WebUI.delay(2)
		WebUI.click(yesButton)
		WebUI.delay(5)
		WebUI.verifyElementNotPresent(clearDraft, 2)
		WebUI.switchToDefaultContent()

	}

	//get Narrative Text

	@Keyword
	def String get_narrative_text(){
		TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)
		TestObject editor_frame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.narrative+"[1]",true)

		TestObject editor_body = new TestObject().addProperty('xpath',ConditionType.EQUALS,"//body",true)



		WebUI.switchToFrame(iFrame, 2)
		WebUI.switchToFrame(editor_frame, 2)
		//TestObject narrativeText = new TestObject().addProperty('xpath',ConditionType.EQUALS,editor_body,true)
		String textValue = WebUI.getText(editor_body)
		WebUI.switchToDefaultContent()
		return textValue
	}


	@Keyword

	def public click_pdf_Icon(){
		TestObject pdfButton = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.verifypdf,true)
		TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)
		WebUI.switchToFrame(iFrame, 2)
		WebUI.click(pdfButton)
		WebUI.delay(10)
		WebUI.switchToDefaultContent()
		//isFiledownloaded("", "")
		WebUI.delay(6)
	}


	@Keyword

	def public String readPdf(){

		String pdf = RunConfiguration.getProjectDir()+"/Include/TestData/DownloadedFiles/"+GlobalVariable.currentTestCaseId+"/creditForm.pdf"
		PDFUtil pdfUtil1 = new PDFUtil()
		String file = pdfUtil1.getText(pdf)


		return file
	}

	@Keyword

	def public verify_narrative_text_fromPdf(int index){

		TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)
		String flag = "Found"
		String file = readPdf()

		for(int i=1;i<=index;i++){
			WebUI.switchToFrame(iFrame, 4)

			TestObject narrativeFrameObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.narrative+"["+i+"]",true)
			WebUI.switchToFrame(narrativeFrameObj, 2)
			TestObject editor_body = new TestObject().addProperty('xpath',ConditionType.EQUALS,"//body",true)
			String narrative_value = WebUI.getText(editor_body)

			println narrative_value
			if (file.contains(narrative_value)){
				println('Found Text')
				if(flag != "NotFound"){
					flag = "Found"
				}
			}
			else{
				println "Not found"
				flag = "NotFound"
			}
			WebUI.switchToDefaultContent()
		}
		return flag

	}


	@Keyword

	def public verify_text_value_fromPdf(TestObject textObj){


		TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)
		String flag = "Found"
		String file = readPdf()
		WebUI.switchToFrame(iFrame, 2)
		String rtlVal = WebUI.getText(textObj)
		println rtlVal

		if (file.contains(rtlVal)){
			println('Found Text')

			if(flag != "NotFound"){
				flag = "Found"
			}
		}
		else{
			println "Not found"
			flag = "NotFound"
		}

		WebUI.switchToDefaultContent()
		return flag

	}

	/*
	 * 
	 * Verify Date with pdf and form page
	 */

	@Keyword

	def public verify_date_from_pdf(TestObject dateObj){
		TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)
		String flag = "Found"
		String file = readPdf()
		WebUI.switchToFrame(iFrame, 2)
		String dateVal = WebUI.getAttribute(dateObj,'value')
		println dateVal
		if (file.contains(dateVal)){
			println('Found Text')

			if(flag != "NotFound"){
				flag = "Found"
			}
		}
		else{
			println "Not found"
			flag = "NotFound"
		}

		WebUI.switchToDefaultContent()
		return flag

	}

	@Keyword
	def verify_pdf_table(int index){

		String flag= "Found"
		TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)

		for(int i=1;i<=index;i++){
			String pdf = RunConfiguration.getProjectDir()+"/Include/TestData/DownloadedFiles/"+GlobalVariable.currentTestCaseId+"/creditForm.pdf"
			//String tablepath = "(//label[contains(text(),'Table: Site lighting measurements')]/following::table[@class='ui celled table calculator unstackable'])[1]"
			//String tablepath = "(//table[@class='ui celled table calculator unstackable'])"+"["+i+"]"
			String tablepath = "(//table[not(@class='ui collapsing celled table unstackable')])"+"["+i+"]"
			PDFUtil pdfUtil1 = new PDFUtil()
			String file = pdfUtil1.getText(pdf)
			WebUI.delay(8)
			println(file)
			WebDriver webDriver = DriverFactory.getWebDriver()
			WebUI.switchToFrame(iFrame, 4)
			//WebUI.scrollToElement(findTestObject(scrollpath1),15)
			WebElement Table = webDriver.findElement(By.xpath(tablepath))
			List<WebElement> row_table = Table.findElements(By.tagName('tr'))
			int rows_count = row_table.size()
			println "Number of rows: "+rows_count
			for (int row =2 ;row < rows_count;row++){
				List<WebElement> column_row = row_table.get(row).findElements(By.tagName('td'))
				List<WebElement> get_text = row_table.get(row).findElements(By.tagName('input'))
				int column_count = get_text.size()

				println "Number of columns : "+column_count
				println('Number of cells in row\t'+ row + '\tare\t'+ column_count)
				for (int column = 0; column <column_count; column++){
					String gettext = get_text.get(column).getAttribute("value")
					println(gettext)
					if (file.contains(gettext)){
						println('Found Text')

						if(flag != "Not Found"){
							flag= "Found"
						}
					}

					else{

						flag = "NotFound"
					}
				}
				/*for (int j=3;j<column_count;j++){
				 String celltext = column_row.get(j).getText()
				 println(celltext)
				 if (file.contains(celltext)){
				 println('Found Text')
				 }
				 }*/
			}
			WebUI.switchToDefaultContent()
		}
		return flag
	}
	//####################################Delete pdf From Downloaded Location###################

	@Keyword

	def deletePdfFile(){
		File dir = new File(RunConfiguration.getProjectDir()+'/Include/TestData/DownloadedFiles');
		File[] dirContents = dir.listFiles();


		println dirContents.length
		if (dirContents.length > 0) {

			for (int i = 0; i < dirContents.length; i++) {

				dirContents[i].delete();
				//return true;

			}

		}

		return false
	}

	//To check whether revision icon is present

	@Keyword

	def check_revisionIcon(){
		TestObject revisionIconObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.rivisionIcon,true)
		TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)
		WebUI.switchToFrame(iFrame, 4)
		boolean isIconPresent = WebUI.verifyElementPresent(revisionIconObj, 3)
		println "Presence of rivision Icon: "+isIconPresent
		WebUI.switchToDefaultContent()

	}

	//Keyword to delete Table rows

	@Keyword

	def delete_tableRows(String table_name,String testData){

		String iframePath = entities.formIframe
		TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,iframePath,true)
		WebDriver webDriver = DriverFactory.getWebDriver()
		WebUI.switchToFrame(iFrame, 1)

		int rowCount = findTestData("Data Files/"+testData).getRowNumbers()
		String Xpath_icon = "(//label[contains(text(),'"+table_name+"')]/following::table[1]//td//i[@class='minus icon'])"

		String tableXpath = "(//label[contains(text(),'"+table_name+"')]/following::table[1])"

		WebElement Table = webDriver.findElement(By.xpath(tableXpath))
		//List<WebElement> row_table = Table.findElements(By.tagName('tr'))

		List<WebElement> row_table = Table.findElements(By.xpath(Xpath_icon))
		int rows_count = row_table.size()
		println "Number of Delete icon rows "+rows_count
		for(int row=rows_count; row>=1;row--){
			println row
			String Xpath_delete = "(//label[contains(text(),'"+table_name+"')]/following::table[1]//td//i[@class='minus icon'])"+[row]
			TestObject Delete_ICON = new TestObject().addProperty('xpath',ConditionType.EQUALS,Xpath_delete,true)



			//To Add new row in the Table,Click on the ADD Button ICON
			//String Xpath_Add = "(//label[contains(text(),'"+table_name+"')]/following::table[1]//td//i)"+[row]
			//TestObject ADD_ICON = new TestObject().addProperty('xpath',ConditionType.EQUALS,Xpath_Add,true)
			WebUI.click(Delete_ICON)
			WebUI.delay(2)



		}
		WebUI.switchToDefaultContent()
	}

	//Keyword to cancel deletion of uploaded file

	@Keyword

	def cancel_file_delete(){
		TestObject  delete = new TestObject().addProperty('xpath', ConditionType.EQUALS, entities.deleteIconPrefix+"1"+entities.deleteIconSuffix+"[1]", true)
		String iframePath = entities.formIframe
		TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,iframePath,true)
		TestObject cancleBtn = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.cancelButton,true)
		WebUI.switchToFrame(iFrame, 2)
		WebUI.click(delete)
		WebUI.delay(2)
		WebUI.click(cancleBtn)
		WebUI.delay(3)

		TestObject fileNameCol = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.fileNameColumn,true)
		boolean isNotDeleted =  WebUI.verifyElementPresent(fileNameCol, 2)

		println "Status whether file Not deleted "+ isNotDeleted

		WebUI.switchToDefaultContent()
	}

	//Keyword to verify the file uploaded

	@Keyword

	def verify_uploaded_files(int fileIndex){
		String iframePath = entities.formIframe
		TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,iframePath,true)

		println "Inside Iframe"
		String tablePath = entities.uploadedTablePrefix+fileIndex+entities.uploadedTableSuffix
		String statusMatch
		int NumOfRows = new LeedOnlineKeywords().countRowsPerPage(tablePath)
		println "Number of rows "+NumOfRows

		WebUI.switchToFrame(iFrame, 2)
		if (NumOfRows > 0){

			//WebUI.scrollToElement(findTestObject('PurchasingPolicy/Upload_Five_most/p_Upload the five most purchas'), 2)
			//ArrayList<String> fileNameList = get_filenames_from_excel()

			//println fileNameList
			for (int k=1;k <= NumOfRows; k++) {
				TestObject fileNameObj =  new TestObject().addProperty('xpath', ConditionType.EQUALS, entities.uploadedFileNamePrefix+fileIndex+entities.uploadedFileNameSuffix+"["+k+"]", true)
				String  fname_txt = WebUI.getText(fileNameObj)
				println "Inside table "+fname_txt
				String isFileMatched = get_filenames_from_excel(fname_txt)
				println "After File matched " + isFileMatched
				//WebUI.verifyMatch(fname_txt, isFileMatched, false)
				WebUI.verifyMatch(fname_txt, isFileMatched, false, FailureHandling.CONTINUE_ON_FAILURE)

			}
			WebUI.switchToDefaultContent()


		}


	}

	//Get file name from the Excel

	@Keyword

	def get_filenames_from_excel(String fileName){
		String fname
		for(int j=1;j<=findTestData("Data Files/TD_FileNames").getRowNumbers();j++){
			WebUI.delay(2)

			fname = findTestData("Data Files/TD_FileNames").getValue("File Name", j)
			println fname

			if(fname.equals(fileName)){
				println fname +" is equal to "+fileName

				KeywordUtil.markPassed(fname+ ' is equal to '+fileName)

				return fname


			}


		}

		if(fname!=fileName){
			KeywordUtil.markFailed(fname + " is not equal to "+ fileName)

			return fname
		}


	}


	//Verify the uploaded files from the generated pdf
	@Keyword

	def verify_uploaded_files_pdf(int fileIndex){
		String flag= "Found"
		TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.formIframe,true)
		WebUI.switchToFrame(iFrame, 2)

		String pdf = RunConfiguration.getProjectDir()+"/Include/TestData/DownloadedFiles/"+GlobalVariable.currentTestCaseId+"/creditForm.pdf"
		String tablepath = entities.uploadedFileTable+"["+fileIndex+"]"

		PDFUtil pdfUtil1 = new PDFUtil()

		String file = pdfUtil1.getText(pdf)
		println(file)
		WebDriver webDriver = DriverFactory.getWebDriver()
		WebElement Table = webDriver.findElement(By.xpath(tablepath))
		List<WebElement> row_table = Table.findElements(By.tagName('tr'))
		int rows_count = row_table.size()
		println "Number of rows: "+rows_count
		for (int row =2 ;row < rows_count;row++){
			List<WebElement> column_row = row_table.get(row).findElements(By.tagName('td'))

			int column_count = column_row.size()

			println "Number of columns : "+column_count
			println('Number of cells in row\t'+ row + '\tare\t'+ column_count)
			for (int column = 0; column <2; column++){
				String gettext = column_row.get(column).getText()
				println(gettext)
				if (file.contains(gettext)){
					println('Found Text')

					if(flag != "NotFound"){

						flag = "Found"
					}

				}

				else{
					flag = "NotFound"
				}
			}

		}

		WebUI.switchToDefaultContent()
		return flag

	}


	//To Check/Uncheck optional checkbox

	@Keyword
	def public ChangeCheckbox(String xpath){
		WebDriver driver = DriverFactory.getWebDriver()

		String iframePath = entities.formIframe

		TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,iframePath,true)
		WebUI.switchToFrame(iFrame, 1)
		WebElement checkbox =driver.findElement(By.xpath(xpath))
		if(checkbox.isSelected()){
			println"Checked ##########"
			checkbox.click()
		}

		else{
			checkbox.click()
		}
		WebUI.switchToDefaultContent()




	}

	/*
	 * 
	 * IP and SI
	 */

	String NameOfForm
	int rowNum
	@Keyword
	def changeunits(String unitType, formName,rating){ //, table_name, testData

		WebUI.back()
		WebUI.delay(2)

		TestObject details = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.details,true)
		println "##################### Before Details clicked "
		WebUI.click(details)
		println "##################### After Details clicked "

		WebUI.delay(2)
		TestObject EditBtn = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.editBtn,true)
		println "##################### Before EditBtn clicked "
		WebUI.click(EditBtn)
		println "##################### After EditBtn clicked "

		TestObject popup = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.popup,true)
		println "##################### Before Verify Popup "
		WebUI.verifyElementPresent(popup, 30)
		println "##################### After Verify Popup "

		WebUI.delay(7)
		if(unitType=="IP"){
			TestObject Units_IP = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.Units_IP,true)
			println "##################### Before Units_IP clicked "
			WebUI.click(Units_IP)
			println "##################### After Units_IP clicked "
		}
		else if(unitType=="SI"){
			TestObject Units_SI = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.Units_SI,true)
			println "##################### Before Units_SI clicked "
			WebUI.click(Units_SI)
			println "##################### After Units_SI clicked "
		}
		else{
			println "Use Unit types SI or IP"
		}
		WebUI.delay(2)
		TestObject UpdateBtn = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.Update,true)
		println "##################### Before UpdateBtn clicked "
		WebUI.delay(2)
		WebUI.submit(UpdateBtn)
		println "##################### After UpdateBtn clicked "
		WebUI.delay(5)
		TestObject creditsBtn = new TestObject().addProperty('xpath',ConditionType.EQUALS,entities.creditsBtn,true)
		println "##################### Before creditsBtn clicked "
		WebUI.delay(2)
		WebUI.click(creditsBtn)
		println "##################### After creditsBtn clicked "
		WebUI.delay(3)

		println "formName***"+formName

		int formRow = new LeedOnlineKeywords().get_rowNumber_of_form(formName)
		rowNum = formRow

		println "rowNum**********"+ rowNum

		String getformname = findTestData("Data Files/TD_FormElements").getValue("Form Name", rowNum)

		NameOfForm =getformname
		println "NameOfForm*******:"+NameOfForm
		String formNamePath = entities.scoreCard+NameOfForm+"'])"
		TestObject formNameObj = new TestObject().addProperty('xpath',ConditionType.EQUALS,formNamePath,true)
		WebUI.click(formNameObj)
		WebUI.delay(3)

		String formVPath = entities.scoreCard+NameOfForm+entities.scorecardFormSuffix
		TestObject formV1 = new TestObject().addProperty('xpath',ConditionType.EQUALS,formVPath,true)
		WebUI.click(formV1)
		WebUI.delay(3)

		//		println table_name
		//		int columnCount = findTestData("Data Files/"+testData).getColumnNumbers()
		//		println "Column Number"+columnCount

	}


	@Keyword
	def tablecolumn(int index){

		Map<Integer,ArrayList<String>> map = new HashMap<>()
		Map<Integer,ArrayList<String>> map1 = new HashMap<>()


		for(int i=1;i<=index;i++){
			println "inside if and value of i ***:" +i
			String tablepath = "(//table[not(@class='ui collapsing celled table unstackable')])"+"["+i+"]"
			println "tablepath*******:"+tablepath

			String iframePath = entities.formIframe
			TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,iframePath,true)
			WebUI.switchToFrame(iFrame, 1)

			WebDriver driver = DriverFactory.getWebDriver()
			WebElement Table = driver.findElement(By.xpath(tablepath))
			//			List<WebElement> column_row = Table.findElements(By.tagName('th'))
			List<WebElement> get_text = Table.findElements(By.tagName('th'))
			List<WebElement> rows_table = Table.findElements(By.tagName('tr'))
			List<WebElement> row_text = Table.findElements(By.tagName('td'))

			int column_count = get_text.size()
			int row_count = row_text.size()

			println "column_count******* :" +column_count
			println "row_count******* :" +row_count

			List<String> allist = new ArrayList<>()
			for (int column = 0; column <column_count; column++){
				String gettext = get_text.get(column).getText()

				if(gettext.contains("(")){
					allist.add(gettext);
					println "Col list******* :" +allist
				}
			}
			map.put(i,allist);

			for (int row = 0; row <row_count; row++){
				String rowtext = row_text.get(row).getText()

				if(rowtext.contains("(")){
					allist.add(rowtext);
				}
			}
			map.put(i,allist);
			WebUI.switchToDefaultContent()
		}
		return map;
	}


	@Keyword
	def textboxcolumn(int index){

		Map<Integer,ArrayList<String>> map = new HashMap<>()
		List<String> txtlist = new ArrayList<>()

		for(int i=1;i<=index;i++){

			String textboxpath = "(//div[@class='leed-single-row']//div[1])"+"["+i+"]"

			String iframePath = entities.formIframe
			TestObject iFrame = new TestObject().addProperty('xpath',ConditionType.EQUALS,iframePath,true)
			WebUI.switchToFrame(iFrame, 1)

			WebDriver driver = DriverFactory.getWebDriver()
			WebElement textbox = driver.findElement(By.xpath(textboxpath))
			String txt_value=textbox.getText();

			println "textbox********** : "+txt_value

			if(txt_value.contains("(")){
				txtlist.add(txt_value)
			}
			map.put(i,txtlist);
			WebUI.switchToDefaultContent()
		}
		return map
	}

	@Keyword
	def getBrowserName(){

		RemoteWebDriver driver = DriverFactory.getWebDriver()
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = cap.getBrowserName().toLowerCase();
		println "The browserName is ########### " + browserName
		return browserName

	}

	@Keyword
	public String takeScreenshot(ExtentTest logger) {
		//WebDriver driver = DriverFactory.getWebDriver()
		Date d = new Date();
		String screenshotFile = d.toString().replace(":", "_").replace(" ", "_")+ ".png";
		WebUI.takeScreenshot(System.getProperty("user.dir") + "//screenshots//" + screenshotFile)
		// test.log(LogStatus.FAIL)
		//		logger.log(LogStatus.FAIL, "Test Failed")
		logger.log(LogStatus.FAIL, "Screenshot-> "
				+ logger.addScreenCapture(System.getProperty("user.dir") + "//screenshots//" + screenshotFile));

	}

	@Keyword
	public void before(Scenario scenario) {
		
		String sName = scenario.getName(); //getKeyword()  //getDescription()//getName();
		GlobalVariable.scenarioName=sName
		println "##############"+sName  //GlobalVariable.scenarioName
	}
			
}
