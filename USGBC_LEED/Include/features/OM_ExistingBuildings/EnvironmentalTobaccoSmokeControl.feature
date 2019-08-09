#Feature: Entering data to Environmental Tobacco Smoke Control form - LEED v4.1 O+M: EB
#---------------------------------------------------------------------------------------------------------------------------------
# Authors :                                                                                                                                                          Reviewer    :
# Date   :                                                                                                                                                                   Reviewed On :
# Last Updated By:
# Last Updated On:
# Feature Name:
# Feature Description:
#---------------------------------------------------------------------------------------------------------------------------------
Feature: Entering data to Environmental Tobacco Smoke Control form - LEED v4.1 O+M: EB

  @SmokeTest
  Scenario Outline: Filling Environmental Tobacco Smoke Control for NoSmoking
    Given User clicks on project <ratingSystem>
    And User navigates to the credits page of the project
    And User should be on form <formname>
    And User verifies form version,credit name matches in scorecard and form page
    And User checks the checkbox option
    When User will select the Radio Button
    And User uploads files to Add file button
    And User verifies the uploaded files
    And User Enters data to Narrative field and verifies
    And User clicks on Save button
    Then User can able to see the form got saved
    And User verifies the revision icon present after saving the form
    And User dowloads the generated pdf form
    And User verifies the narrative from pdf and form page
    And User verifies the uploaded files from pdf and form page
    And User clicks the Complete icon to set status to Ready for Review
    And User downloads the uploaded files and verifies
    And User clicks on cancel while deleting the uploaded file and verifies the files are retained
    And User deletes the uploaded files and verifies the files are deleted

    Examples: 
      | formname                                      | ratingSystem |
      | Environmental Tobacco Smoke Control-NoSmoking | O+M:EB       |

  @SmokeTest
  Scenario Outline: Filling Environmental Tobacco Smoke Control for CompartmentalizationSmokeAreas
    Given User clicks on project <ratingSystem>
    And User navigates to the credits page of the project
    And User should be on form <formname>
    And User verifies form version,credit name matches in scorecard and form page
    And User checks the checkbox option
    When User will select the Radio Button
    And User uploads files to Add file button
    And User verifies the uploaded files
    And User Enters data to Narrative field and verifies
    And User clicks on Save button
    Then User can able to see the form got saved
    And User verifies the revision icon present after saving the form
    And User dowloads the generated pdf form
    And User verifies the narrative from pdf and form page
    And User verifies the uploaded files from pdf and form page
    And User clicks the Complete icon to set status to Ready for Review
    And User downloads the uploaded files and verifies
    And User clicks on cancel while deleting the uploaded file and verifies the files are retained
    And User deletes the uploaded files and verifies the files are deleted
    
    Examples: 
      | formname                                                           | ratingSystem |
      | Environmental Tobacco Smoke Control-CompartmentalizationSmokeAreas | O+M:EB       |

  @RegressionTest
  Scenario Outline: Checking Auto Save functionality in Environmental Tobacco Smoke Control-NoSmoking form for Economic Analysis
    Given User clicks on project <ratingSystem>
    And User navigates to the credits page of the project
    And User should be on form <formname>
    And User checks the checkbox option
    When User will select the Radio Button
    And User verifies form draft is saved automatically after 10 sec complete by selecting radio button
    And User verifies form draft is saved automatically after 10 sec complete by filling the Narrative
    And User will not make any changes in the form and verifies form is not saved as draft
    And User uploads files to Add file button and verifies form is not saved as draft
    And User checks the checkbox option and verifies form draft is saved automatically after 10 sec complete
    And User logout and login to the form <formname> and rating <ratingSystem>
    And User verifies warning message of form saved as draft is present and draft not changed
    And User checks the checkbox option
    And User will select the Radio Button
    Then User clicks on Save button and deletes the uploaded files
    And User verifies clear draft,warning message is not present
    And User verifies form draft is saved automatically after 10 sec complete by filling the Narrative
    And User clicks on clear draft and verifies the draft is not present

    Examples: 
      | formname                                      | ratingSystem |
      | Environmental Tobacco Smoke Control-NoSmoking | O+M:EB       |

  @RegressionTest
  Scenario Outline: Checking Auto Save functionality in Environmental Tobacco Smoke Control-CompartmentalizationSmokeAreas form for Economic Analysis
    Given User clicks on project <ratingSystem>
    And User navigates to the credits page of the project
    And User should be on form <formname>
    And User checks the checkbox option
    When User will select the Radio Button
    And User verifies form draft is saved automatically after 10 sec complete by selecting radio button
    And User verifies form draft is saved automatically after 10 sec complete by filling the Narrative
    And User will not make any changes in the form and verifies form is not saved as draft
    And User uploads files to Add file button and verifies form is not saved as draft
    And User checks the checkbox option and verifies form draft is saved automatically after 10 sec complete
    And User logout and login to the form <formname> and rating <ratingSystem>
    And User verifies warning message of form saved as draft is present and draft not changed
    And User checks the checkbox option
    And User will select the Radio Button
    Then User clicks on Save button and deletes the uploaded files
    And User verifies clear draft,warning message is not present
    And User verifies form draft is saved automatically after 10 sec complete by filling the Narrative
    And User clicks on clear draft and verifies the draft is not present
    
    Examples: 
      | formname                                                           | ratingSystem |
      | Environmental Tobacco Smoke Control-CompartmentalizationSmokeAreas | O+M:EB       |
