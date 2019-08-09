#Feature: Entering data to Fundamental Refrigerant Management form
#---------------------------------------------------------------------------------------------------------------------------------
# Authors :                                                                                                                                                          Reviewer    :
# Date   :                                                                                                                                                                   Reviewed On :
# Last Updated By:
# Last Updated On:
# Feature Name:
# Feature Description:
#---------------------------------------------------------------------------------------------------------------------------------
Feature: Fundamental Refrigerant Management form - LEED v4.1 O+M: EB

  #Scenario-1
  @SmokeTest
  Scenario Outline: Filling Fundamental Refrigerant Management form for noCfcRefrigerants
    Given User clicks on project <ratingSystem>
    And User navigates to the credits page of the project
    And User should be on form <formname>
    And User verifies form version,credit name matches in scorecard and form page
    And User checks the checkbox option
    And User uploads files to Add file button
    And User verifies the uploaded files
    And User Enters data to Narrative field and verifies
    And User clicks on Save button
    Then User can able to see the form got saved
    And User verifies the revision icon present after saving the form
    And User dowloads the generated pdf form
    And User verifies the uploaded files from pdf and form page
    And User verifies the narrative from pdf and form page
    And User clicks the Complete icon to set status to Ready for Review
    And User downloads the uploaded files and verifies
    And User clicks on cancel while deleting the uploaded file and verifies the files are retained
    And User deletes the uploaded files and verifies the files are deleted

    Examples: 
      | formname                                               | ratingSystem |
      | Fundamental Refrigerant Management-noCfcRefrigerants!C | O+M:EB       |

  #Scenario-2
  @SmokeTest
  Scenario Outline: Filling Fundamental Refrigerant Management form for Comprehensive Phase-Out Plan
    Given User clicks on project <ratingSystem>
    And User navigates to the credits page of the project
    And User should be on form <formname>
    And User verifies form version,credit name matches in scorecard and form page
    When User will select the Radio Button
    And User uploads files to Add file button
    And User verifies the uploaded files
    And User Enters data to Narrative field and verifies
    And User fills the table <tableName> with data <testData> isAddRow <isAddRow> and verifies
    And User checks the checkbox option
    And User clicks on Save button
    Then User can able to see the form got saved
    And User verifies the revision icon present after saving the form
    And User dowloads the generated pdf form
    And User verifies the narrative from pdf and form page
    And User verifies the uploaded files from pdf and form page
    And User verifies the tables with pdf and form page
    And User deletes the table rows table <tableName> with data <testData>
    And User clicks the Complete icon to set status to Ready for Review
    And User downloads the uploaded files and verifies
    And User clicks on cancel while deleting the uploaded file and verifies the files are retained
    And User deletes the uploaded files and verifies the files are deleted

    Examples: 
      | formname                                                   | ratingSystem | tableName                                             | testData                      | isAddRow |
      | Fundamental Refrigerant Management-comprehensivePhaseOut!R | O+M:EB       | Table: Mechanical Cooling and Refrigeration Equipment | TD_FundamentalRefrigerantMgmt | Yes      |

  #Scenario-3
  @SmokeTest
  Scenario Outline: Filling Fundamental Refrigerant Management form for Economic Analysis
    Given User clicks on project <ratingSystem>
    And User navigates to the credits page of the project
    And User should be on form <formname>
    And User verifies form version,credit name matches in scorecard and form page
    When User will select the Radio Button
    And User uploads files to Add file button
    And User verifies the uploaded files
    And User Enters data to Narrative field and verifies
    And User enters data to text box and verifies
    And User fills the table <tableName> with data <testData> isAddRow <isAddRow> and verifies
    And User fills the table <tableName1> with data <testData1> isAddRow <isAddRow1> and verifies
    And User checks the checkbox option
    And User clicks on Save button
    Then User can able to see the form got saved
    And User verifies the revision icon present after saving the form
    And User dowloads the generated pdf form
    And User verifies the narrative from pdf and form page
    And User verifies the uploaded files from pdf and form page
    And User verifies text with pdf and form page
    And User verifies the tables with pdf and form page
    And User deletes the table rows table <tableName> with data <testData>
    And User clicks the Complete icon to set status to Ready for Review
    And User downloads the uploaded files and verifies
    And User clicks on cancel while deleting the uploaded file and verifies the files are retained
    And User deletes the uploaded files and verifies the files are deleted

    Examples: 
      | formname                                              | ratingSystem | tableName                                             | testData                      | isAddRow | tableName1            | testData1        | isAddRow1 |
      | Fundamental Refrigerant Management-economicAnalysis!R | O+M:EB       | Table: Mechanical Cooling and Refrigeration Equipment | TD_FundamentalRefrigerantMgmt | Yes      | Table: Simple Payback | TD_SimplePayback | No        |

  #Scenario-4
  @SmokeTest
  Scenario Outline: Checking Auto Save functionality in Fundamental Refrigerant Management form for noCfcRefrigerants
    Given User clicks on project <ratingSystem>
    And User navigates to the credits page of the project
    And User should be on form <formname>
    And User checks the checkbox option
    And User verifies form draft is saved automatically after 10 sec complete by filling the Narrative
    And User will not make any changes in the form and verifies form is not saved as draft
    And User uploads files to Add file button and verifies form is not saved as draft
    And User logout and login to the form <formname> and rating <ratingSystem>
    And User verifies warning message of form saved as draft is present and draft not changed
    Then User clicks on Save button and deletes the uploaded files
    And User verifies clear draft,warning message is not present
    And User verifies form draft is saved automatically after 10 sec complete by filling the Narrative
    And User clicks on clear draft and verifies the draft is not present

    Examples: 
      | formname                                               | ratingSystem |
      | Fundamental Refrigerant Management-noCfcRefrigerants!C | O+M:EB       |

  #Scenario-5
  @SmokeTest
  Scenario Outline: Checking Auto Save functionality in Fundamental Refrigerant Management form for Comprehensive Phase-Out Plan
    Given User clicks on project <ratingSystem>
    And User navigates to the credits page of the project
    And User should be on form <formname>
    And User will select the Radio Button
    And User verifies form draft is saved automatically after 10 sec complete by selecting radio button
    And User verifies form draft is saved automatically after 10 sec complete by filling the Narrative
    And User will not make any changes in the form and verifies form is not saved as draft
    And User uploads files to Add file button and verifies form is not saved as draft
    And User checks the checkbox option and verifies form draft is saved automatically after 10 sec complete
    And User fills the table <tableName> with data <testData> isAddRow <isAddRow> and verifies
    And User verifies draft saved automatically after filling the table
    And User logout and login to the form <formname> and rating <ratingSystem>
    And User verifies warning message of form saved as draft is present and draft not changed
    Then User clicks on Save button and deletes the uploaded files
    And User verifies clear draft,warning message is not present
    And User verifies form draft is saved automatically after 10 sec complete by filling the Narrative
    And User clicks on clear draft and verifies the draft is not present

    Examples: 
      | formname                                                   | ratingSystem | tableName                                             | testData                      | isAddRow |
      | Fundamental Refrigerant Management-comprehensivePhaseOut!R | O+M:EB       | Table: Mechanical Cooling and Refrigeration Equipment | TD_FundamentalRefrigerantMgmt | Yes      |

  #Scenario-6
  @SmokeTest
  Scenario Outline: Checking Auto Save functionality in Fundamental Refrigerant Management form for Economic Analysis
    Given User clicks on project <ratingSystem>
    And User navigates to the credits page of the project
    And User should be on form <formname>
    And User will select the Radio Button
    And User verifies form draft is saved automatically after 10 sec complete by selecting radio button
    And User verifies form draft is saved automatically after 10 sec complete by filling the Narrative
    And User will not make any changes in the form and verifies form is not saved as draft
    And User uploads files to Add file button and verifies form is not saved as draft
    And User checks the checkbox option and verifies form draft is saved automatically after 10 sec complete
    And User verifies Auto save after entering text box
    And User fills the table <tableName> with data <testData> isAddRow <isAddRow> and verifies
    And User fills the table <tableName1> with data <testData1> isAddRow <isAddRow1> and verifies
    And User verifies draft saved automatically after filling the table
    And User logout and login to the form <formname> and rating <ratingSystem>
    And User verifies warning message of form saved as draft is present and draft not changed
    Then User clicks on Save button and deletes the uploaded files
    And User verifies clear draft,warning message is not present
    And User verifies form draft is saved automatically after 10 sec complete by filling the Narrative
    And User clicks on clear draft and verifies the draft is not present

    Examples: 
      | formname                                              | ratingSystem | tableName                                             | testData                      | isAddRow | tableName1            | testData1        | isAddRow1 |
      | Fundamental Refrigerant Management-economicAnalysis!R | O+M:EB       | Table: Mechanical Cooling and Refrigeration Equipment | TD_FundamentalRefrigerantMgmt | Yes      | Table: Simple Payback | TD_SimplePayback | No        |
