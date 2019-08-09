#Feature: Entering data to Light Pollution Reduction Form - LEED v4.1 O+M: EB
#---------------------------------------------------------------------------------------------------------------------------------
# Authors :                                                                                                                                                          Reviewer    :
# Date   :                                                                                                                                                                   Reviewed On :
# Last Updated By:
# Last Updated On:
# Feature Name:
# Feature Description:
#---------------------------------------------------------------------------------------------------------------------------------
Feature: Entering data to Light Pollution Reduction Form - LEED v4.1 O+M: EB

  @SmokeTest
  Scenario Outline: Filling Light Pollution Reduction form for Fixture Shielding
    Given User clicks on project <project>
    And User navigates to the credits page of the project
    And User should be on form <formname> rating <ratingSystem>
    When User will select the Radio Button
    And User checks the checkbox option
    And User uploads files to Add file button
    And User enters data to text box with number
    And User Enters data to Narrative field
    And User downloads the uploaded files
    And User deletes the uploaded files
    And User clicks on Save button
    Then User can able to see the form got saved
    And User clicks the Complete icon to set status to Ready for Review

    Examples: 
      | formname                                   | project          | ratingSystem |
      | Light Pollution Reduction-fixtureShielding | BDD6 TestProject | O+M:EB       |

  @SmokeTest
  Scenario Outline: Filling Light Pollution Reduction form for Perimeter Measurements
    Given User clicks on project <project>
    And User navigates to the credits page of the project
    And User should be on form <formname> rating <ratingSystem>
    When User will select the Radio Button
    And User fills the table <tableName> with data <testData> isAddRow <isAddRow>
    And User enters data to text box with number
    And User uploads files to Add file button
    And User Enters data to Narrative field
    And User downloads the uploaded files
    And User deletes the uploaded files
    And User clicks on Save button
    Then User can able to see the form got saved
    And User clicks the Complete icon to set status to Ready for Review

    Examples: 
      | formname                                        | project          | ratingSystem | tableName                         | testData                    | isAddRow |
      | Light Pollution Reduction-perimeterMeasurements | BDD6 TestProject | O+M:EB       | Table: Site lighting measurements | TD_SiteLightingMeasurements | Yes      |

  @RegressionTest
  Scenario Outline: Checking Auto Save functionality in Light Pollution Reduction form for Fixture Shielding
    Given User clicks on project <project>
    And User navigates to the credits page of the project
    And User should be on form <formname> rating <ratingSystem>
    When User will select the Radio Button
    And User Enters data to Narrative field to check auto save on form <formname> rating <ratingSystem>
    And User uploads files to Add file button to check auto save on form <formname> rating <ratingSystem>
    And User verifies form not saved as draft
    And User Enters data to Narrative field to check auto save on form <formname> rating <ratingSystem>
    And User verifies warning message of form saved as draft
    And User checks form saved as draft
    And User cancels deleting the uploaded file
    And User deletes the uploaded files
    And User clears the draft
    And User will select the Radio Button
    And User Enters data to Narrative field after clear draft
    And User enters data to text box with number
    And User clicks on Save button
    Then User verifies clear draft,warning message are not present
    And User will not make any changes in the form
    And User verifies clear draft,warning message are not present
    And User verifies the revision icon present after saving the form
    And User dowloads the form as pdf
    And User verifies the narrative
    And User verifies text with pdf
    And User deletes the downloaded pdf file

    Examples: 
      | formname                                   | project          | ratingSystem |
      | Light Pollution Reduction-fixtureShielding | BDD6 TestProject | O+M:EB       |

  @RegressionTest
  Scenario Outline: Checking Auto Save functionality in Light Pollution Reduction form for Perimeter Measurements
    Given User clicks on project <project>
    And User navigates to the credits page of the project
    And User should be on form <formname> rating <ratingSystem>
    When User will select the Radio Button
    And User Enters data to Narrative field to check auto save on form <formname> rating <ratingSystem>
    And User uploads files to Add file button to check auto save on form <formname> rating <ratingSystem>
    And User verifies form not saved as draft
    And User Enters data to Narrative field to check auto save on form <formname> rating <ratingSystem>
    And User verifies warning message of form saved as draft
    And User checks form saved as draft
    And User cancels deleting the uploaded file
    And User deletes the uploaded files
    And User clears the draft
    And User will select the Radio Button
    And User fills the table <tableName> with data <testData> isAddRow <isAddRow>
    And User enters data to text box with number
    And User Enters data to Narrative field after clear draft
    And User deletes the table rows table <tableName> with data <testData>
    And User clicks on Save button
    Then User verifies clear draft,warning message are not present
    And User will not make any changes in the form
    And User verifies clear draft,warning message are not present
    And User verifies the revision icon present after saving the form
    And User dowloads the form as pdf
    And User verifies the narrative
    And User verifies text with pdf
    And User verifies the tables
    And User deletes the downloaded pdf file

    Examples: 
      | formname                                        | project          | ratingSystem | tableName                         | testData                    | isAddRow |
      | Light Pollution Reduction-perimeterMeasurements | BDD6 TestProject | O+M:EB       | Table: Site lighting measurements | TD_SiteLightingMeasurements | Yes      |
