$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("Include/features/OM_ExistingBuildings/SiteManagement.feature");
formatter.feature({
  "name": "Entering data to Site Management form - LEED v4.1 O+M: EB",
  "description": "",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "Filling Project Information form",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@SmokeTest"
    }
  ]
});
formatter.step({
  "name": "User clicks on project \u003cratingSystem\u003e",
  "keyword": "Given "
});
formatter.step({
  "name": "User navigates to the credits page of the project",
  "keyword": "And "
});
formatter.step({
  "name": "User should be on form \u003cformname\u003e",
  "keyword": "And "
});
formatter.step({
  "name": "User uploads files to Add file button",
  "keyword": "When "
});
formatter.step({
  "name": "User verifies the uploaded files",
  "keyword": "And "
});
formatter.step({
  "name": "User Enters data to Narrative field and verifies",
  "keyword": "And "
});
formatter.step({
  "name": "User clicks on Save button",
  "keyword": "And "
});
formatter.step({
  "name": "User can able to see the form got saved",
  "keyword": "Then "
});
formatter.step({
  "name": "User verifies the revision icon present after saving the form",
  "keyword": "And "
});
formatter.step({
  "name": "User dowloads the generated pdf form",
  "keyword": "And "
});
formatter.step({
  "name": "User verifies the narrative from pdf and form page",
  "keyword": "And "
});
formatter.step({
  "name": "User verifies the uploaded files from pdf and form page",
  "keyword": "And "
});
formatter.step({
  "name": "User clicks the Complete icon to set status to Ready for Review",
  "keyword": "And "
});
formatter.step({
  "name": "User downloads the uploaded files and verifies",
  "keyword": "And "
});
formatter.step({
  "name": "User clicks on cancel while deleting the uploaded file and verifies the files are retained",
  "keyword": "And "
});
formatter.step({
  "name": "User deletes the uploaded files and verifies the files are deleted",
  "keyword": "And "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "formname",
        "ratingSystem"
      ]
    },
    {
      "cells": [
        "Site Management",
        "O+M:EB"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Filling Project Information form",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@SmokeTest"
    }
  ]
});
formatter.step({
  "name": "User clicks on project O+M:EB",
  "keyword": "Given "
});
formatter.match({
  "location": "Common_StepDef.user_clicks_on_Project(String)"
});
formatter.result({
  "error_message": "java.lang.NullPointerException: Cannot invoke method log() on null object\r\n\tat org.codehaus.groovy.runtime.NullObject.invokeMethod(NullObject.java:91)\r\n\tat org.codehaus.groovy.runtime.callsite.PogoMetaClassSite.call(PogoMetaClassSite.java:48)\r\n\tat org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCall(CallSiteArray.java:48)\r\n\tat org.codehaus.groovy.runtime.callsite.NullCallSite.call(NullCallSite.java:35)\r\n\tat org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCall(CallSiteArray.java:48)\r\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:113)\r\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:133)\r\n\tat Common_StepDef.user_clicks_on_Project(Common_StepDef.groovy:86)\r\n\tat ✽.User clicks on project O+M:EB(Include/features/OM_ExistingBuildings/SiteManagement.feature:14)\r\n",
  "status": "failed"
});
formatter.step({
  "name": "User navigates to the credits page of the project",
  "keyword": "And "
});
formatter.match({
  "location": "Common_StepDef.user_navigates_to_the_credits_page_of_the_project()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "User should be on form Site Management",
  "keyword": "And "
});
formatter.match({
  "location": "Common_StepDef.user_should_be_on_FRM_form(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "User uploads files to Add file button",
  "keyword": "When "
});
formatter.match({
  "location": "Common_StepDef.user_uploads_files_to_Add_file_button()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "User verifies the uploaded files",
  "keyword": "And "
});
formatter.match({
  "location": "Common_StepDef.user_verifies_the_uploaded_files()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "User Enters data to Narrative field and verifies",
  "keyword": "And "
});
formatter.match({
  "location": "Common_StepDef.user_enters_data_to_Narrative_field()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "User clicks on Save button",
  "keyword": "And "
});
formatter.match({
  "location": "Common_StepDef.User_clicks_on_Save_button()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "User can able to see the form got saved",
  "keyword": "Then "
});
formatter.match({
  "location": "Common_StepDef.user_can_able_to_see_the_form_got_saved()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "User verifies the revision icon present after saving the form",
  "keyword": "And "
});
formatter.match({
  "location": "Common_StepDef.user_verifies_the_revision_icon_present_after_saving_the_form()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "User dowloads the generated pdf form",
  "keyword": "And "
});
formatter.match({
  "location": "Common_StepDef.user_verifies_the_PDF()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "User verifies the narrative from pdf and form page",
  "keyword": "And "
});
formatter.match({
  "location": "Common_StepDef.user_verifies_the_narraive()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "User verifies the uploaded files from pdf and form page",
  "keyword": "And "
});
formatter.match({
  "location": "Common_StepDef.user_verifies_the_uploaded_files_from_pdf_and_form_page()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "User clicks the Complete icon to set status to Ready for Review",
  "keyword": "And "
});
formatter.match({
  "location": "Common_StepDef.User_clicks_the_Complete_icon_to_set_status_to_Ready_for_Review()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "User downloads the uploaded files and verifies",
  "keyword": "And "
});
formatter.match({
  "location": "Common_StepDef.user_downloads_the_uploaded_files()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "User clicks on cancel while deleting the uploaded file and verifies the files are retained",
  "keyword": "And "
});
formatter.match({
  "location": "Common_StepDef.user_cancels_file_deleting_the_uploaded_file()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "User deletes the uploaded files and verifies the files are deleted",
  "keyword": "And "
});
formatter.match({
  "location": "Common_StepDef.user_deletes_the_uploaded_files()"
});
formatter.result({
  "status": "skipped"
});
formatter.scenarioOutline({
  "name": "Checking Auto Save functionality in Site Management form",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@RegressionTest"
    }
  ]
});
formatter.step({
  "name": "User clicks on project \u003cratingSystem\u003e",
  "keyword": "Given "
});
formatter.step({
  "name": "User navigates to the credits page of the project",
  "keyword": "And "
});
formatter.step({
  "name": "User should be on form \u003cformname\u003e",
  "keyword": "And "
});
formatter.step({
  "name": "User verifies form draft is saved automatically after 10 sec complete by filling the Narrative",
  "keyword": "And "
});
formatter.step({
  "name": "User will not make any changes in the form and verifies form is not saved as draft",
  "keyword": "And "
});
formatter.step({
  "name": "User uploads files to Add file button and verifies form is not saved as draft",
  "keyword": "And "
});
formatter.step({
  "name": "User logout and login to the form \u003cformname\u003e and rating \u003cratingSystem\u003e",
  "keyword": "And "
});
formatter.step({
  "name": "User verifies warning message of form saved as draft is present and draft not changed",
  "keyword": "And "
});
formatter.step({
  "name": "User clicks on Save button and deletes the uploaded files",
  "keyword": "Then "
});
formatter.step({
  "name": "User verifies clear draft,warning message is not present",
  "keyword": "And "
});
formatter.step({
  "name": "User verifies form draft is saved automatically after 10 sec complete by filling the Narrative",
  "keyword": "And "
});
formatter.step({
  "name": "User clicks on clear draft and verifies the draft is not present",
  "keyword": "And "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "formname",
        "ratingSystem"
      ]
    },
    {
      "cells": [
        "Site Management",
        "O+M:EB"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Checking Auto Save functionality in Site Management form",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@RegressionTest"
    }
  ]
});
formatter.step({
  "name": "User clicks on project O+M:EB",
  "keyword": "Given "
});
formatter.match({
  "location": "Common_StepDef.user_clicks_on_Project(String)"
});
formatter.result({
  "error_message": "java.lang.NullPointerException: Cannot invoke method log() on null object\r\n\tat org.codehaus.groovy.runtime.NullObject.invokeMethod(NullObject.java:91)\r\n\tat org.codehaus.groovy.runtime.callsite.PogoMetaClassSite.call(PogoMetaClassSite.java:48)\r\n\tat org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCall(CallSiteArray.java:48)\r\n\tat org.codehaus.groovy.runtime.callsite.NullCallSite.call(NullCallSite.java:35)\r\n\tat org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCall(CallSiteArray.java:48)\r\n\tat org.codehaus.groovy.runtime.callsite.PogoMetaClassSite.call(PogoMetaClassSite.java:57)\r\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:133)\r\n\tat Common_StepDef.user_clicks_on_Project(Common_StepDef.groovy:86)\r\n\tat ✽.User clicks on project O+M:EB(Include/features/OM_ExistingBuildings/SiteManagement.feature:37)\r\n",
  "status": "failed"
});
formatter.step({
  "name": "User navigates to the credits page of the project",
  "keyword": "And "
});
formatter.match({
  "location": "Common_StepDef.user_navigates_to_the_credits_page_of_the_project()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "User should be on form Site Management",
  "keyword": "And "
});
formatter.match({
  "location": "Common_StepDef.user_should_be_on_FRM_form(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "User verifies form draft is saved automatically after 10 sec complete by filling the Narrative",
  "keyword": "And "
});
formatter.match({
  "location": "Common_StepDef.user_verifies_Auto_save_after_entering_Narrative()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "User will not make any changes in the form and verifies form is not saved as draft",
  "keyword": "And "
});
formatter.match({
  "location": "Common_StepDef.user_will_not_make_any_changes_in_the_form_and_verifies_form_not_saved_as_draft()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "User uploads files to Add file button and verifies form is not saved as draft",
  "keyword": "And "
});
formatter.match({
  "location": "Common_StepDef.user_uploads_files_to_Add_file_button_and_verifies_form_not_saved_as_draft()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "User logout and login to the form Site Management and rating O+M:EB",
  "keyword": "And "
});
formatter.match({
  "location": "Common_StepDef.user_logout_and_login_to_the_form(String,String)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "User verifies warning message of form saved as draft is present and draft not changed",
  "keyword": "And "
});
formatter.match({
  "location": "Common_StepDef.user_verifies_warning_message_of_form_saved_as_draft()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "User clicks on Save button and deletes the uploaded files",
  "keyword": "Then "
});
formatter.match({
  "location": "Common_StepDef.User_clicks_on_Save_button_and_deletes_the_uploaded_files()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "User verifies clear draft,warning message is not present",
  "keyword": "And "
});
formatter.match({
  "location": "Common_StepDef.user_verifies_clear_draft_warning_message_are_not_present()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "User verifies form draft is saved automatically after 10 sec complete by filling the Narrative",
  "keyword": "And "
});
formatter.match({
  "location": "Common_StepDef.user_verifies_Auto_save_after_entering_Narrative()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "User clicks on clear draft and verifies the draft is not present",
  "keyword": "And "
});
formatter.match({
  "location": "Common_StepDef.user_clears_draft()"
});
formatter.result({
  "status": "skipped"
});
});