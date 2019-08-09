$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("Include/features/OM_ExistingBuildings/EnergyEfficiencyBestManagementPractices.feature");
formatter.feature({
  "name": "Energy Efficiency Best Management Practices - LEED v4.1 O+M: EB",
  "description": "",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "Filling Energy Efficiency Best Management Practices",
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
  "name": "User verifies form version,credit name matches in scorecard and form page",
  "keyword": "And "
});
formatter.step({
  "name": "User uploads files to Add file button",
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
        "Energy Efficiency Best Management Practices",
        "O+M:EB"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Filling Energy Efficiency Best Management Practices",
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
  "status": "passed"
});
formatter.step({
  "name": "User navigates to the credits page of the project",
  "keyword": "And "
});
formatter.match({
  "location": "Common_StepDef.user_navigates_to_the_credits_page_of_the_project()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User should be on form Energy Efficiency Best Management Practices",
  "keyword": "And "
});
formatter.match({
  "location": "Common_StepDef.user_should_be_on_FRM_form(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User verifies form version,credit name matches in scorecard and form page",
  "keyword": "And "
});
formatter.match({
  "location": "Common_StepDef.user_verifies_form_version_credit_name_matches_in_scorecard_and_form_page()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User uploads files to Add file button",
  "keyword": "And "
});
formatter.match({
  "location": "Common_StepDef.user_uploads_files_to_Add_file_button()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User deletes the uploaded files and verifies the files are deleted",
  "keyword": "And "
});
formatter.match({
  "location": "Common_StepDef.user_deletes_the_uploaded_files()"
});
formatter.result({
  "status": "passed"
});
});