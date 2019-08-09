import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.text.SimpleDateFormat

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import common.commonReports
import gherkin.ast.Scenario

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import internal.GlobalVariable as GlobalVariable

import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.relevantcodes.extentreports.ExtentReports

class NewTestListener {
	
	@BeforeTestSuite
	def sampleBeforeTestSuite(TestSuiteContext testSuiteContext) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_hhmmss_a");
		String TestReport ="Test Report" + dateFormat.format(date).toString()
		println"***************************"+ TestReport
		commonReports.extent=new ExtentReports(System.getProperty("user.dir") +'\\ExtentReports\\'+TestReport+'.html', true)

	}

	
	@BeforeTestCase
	def sampleBeforeTestCase(TestCaseContext testCaseContext) {
//		GlobalVariable.currentTestCaseId = testCaseContext.getTestCaseId()
		String a=testCaseContext.getTestCaseId()
		
		String lastWord = a.substring(a.lastIndexOf("/")+1);
		GlobalVariable.currentTestCaseId=lastWord
		println "GlobalVariable.currentTestCaseId#############"+ lastWord 
		
	}
	
	@AfterTestSuite
	def sampleAfterTestSuite(TestSuiteContext testSuiteContext){
		commonReports.extent.flush()
	}
	
//	@BeforeTestCase
//	public void before(Scenario scenario) {
//		
//		String sName = scenario.getName(); //getKeyword()  //getDescription()//getName();
//		GlobalVariable.scenarioName=sName
//		println "##############"+sName  //GlobalVariable.scenarioName
//	}
	
	
	
}