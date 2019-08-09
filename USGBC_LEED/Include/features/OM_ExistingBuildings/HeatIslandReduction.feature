#Feature: Entering data to Heat Island Reduction - LEED v4.1 O+M: EB
#---------------------------------------------------------------------------------------------------------------------------------
# Authors :                                                                                                                                                          Reviewer    :
# Date   :                                                                                                                                                                   Reviewed On :
# Last Updated By:
# Last Updated On:
# Feature Name:
# Feature Description:
#---------------------------------------------------------------------------------------------------------------------------------
Feature: Entering data to Heat Island Reduction - LEED v4.1 O+M: EB

  @SmokeTest
  Scenario Outline: Filling Heat Island Reduction form for Contributing nonroof measures
    Given User clicks on project <ratingSystem>
    And User navigates to the credits page of the project
    And User should be on form <formname>
    And User verifies form version,credit name matches in scorecard and form page
    When User uploads files to Add file button
    And User verifies the uploaded files
    And User fills the table <tableName> with data <testData> isAddRow <isAddRow> and verifies
    And User fills the table <tableName1> with data <testData1> isAddRow <isAddRow1> and verifies
    And User fills the table <tableName2> with data <testData2> isAddRow <isAddRow2> and verifies
    And User Enters data to Narrative field and verifies
    And User enters data to text box with number and verifies
    And User clicks on Save button
    Then User can able to see the form got saved
    And User verifies the revision icon present after saving the form
    And User dowloads the generated pdf form
    And User verifies the narrative from pdf and form page
    And User verifies the uploaded files from pdf and form page
    And User verifies the tables with pdf and form page
    And User checks the units of table
    And User checks the units of textbox
    And User changes the unit type <formname> rating <ratingSystem>
    And User checks the units are not same in table
    And User checks the units are not same in textbox
    And User revert the unit type <formname> rating <ratingSystem>
    And User deletes the table rows table <tableName> with data <testData>
    And User clicks the Complete icon to set status to Ready for Review
    And User downloads the uploaded files and verifies
    And User clicks on cancel while deleting the uploaded file and verifies the files are retained
    And User deletes the uploaded files and verifies the files are deleted

    Examples: 
      | formname              | ratingSystem | tableName                            | testData                         | isAddRow | tableName1                                | testData1                             | isAddRow1 | tableName2                         | testData2                      | isAddRow2 |
      | Heat Island Reduction | O+M:EB       | Table: Contributing nonroof measures | TD_Contributing nonroof measures | No       | Table: Contributing high-reflectance roof | TD_Contributing high-reflectance roof | Yes       | Table: Summary of nonroof and roof | TD_Summary of nonroof and roof | No        |

  @RegressionTest
  Scenario Outline: Checking Auto Save functionality in Heat Island Reduction form for Contributing nonroof measures
    Given User clicks on project <ratingSystem>
    And User navigates to the credits page of the project
    And User should be on form <formname>
    And User verifies form draft is saved automatically after 10 sec complete by filling the Narrative
    And User will not make any changes in the form and verifies form is not saved as draft
    And User uploads files to Add file button and verifies form is not saved as draft
    And User fills the table <tableName> with data <testData> isAddRow <isAddRow> and verifies
    And User fills the table <tableName1> with data <testData1> isAddRow <isAddRow1> and verifies
    And User fills the table <tableName2> with data <testData2> isAddRow <isAddRow2> and verifies
    And User verifies draft saved automatically after filling the table
    And User logout and login to the form <formname> and rating <ratingSystem>
    And User verifies warning message of form saved as draft is present and draft not changed
    Then User clicks on Save button and deletes the uploaded files
    And User verifies clear draft,warning message is not present
    And User verifies form draft is saved automatically after 10 sec complete by filling the Narrative
    And User clicks on clear draft and verifies the draft is not present

    Examples: 
      | formname              | ratingSystem | tableName                            | testData                         | isAddRow | tableName1                                | testData1                             | isAddRow1 | tableName2                         | testData2                      | isAddRow2 |
      | Heat Island Reduction | O+M:EB       | Table: Contributing nonroof measures | TD_Contributing nonroof measures | No       | Table: Contributing high-reflectance roof | TD_Contributing high-reflectance roof | Yes       | Table: Summary of nonroof and roof | TD_Summary of nonroof and roof | No        |
