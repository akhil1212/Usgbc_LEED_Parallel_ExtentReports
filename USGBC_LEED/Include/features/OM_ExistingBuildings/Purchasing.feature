#Feature: Entering data to Purchasing form
#---------------------------------------------------------------------------------------------------------------------------------
# Authors :                                                                                                                                                          Reviewer    :
# Date   :                                                                                                                                                                   Reviewed On :
# Last Updated By:
# Last Updated On:
# Feature Name:
# Feature Description:
#---------------------------------------------------------------------------------------------------------------------------------
Feature: Entering data to Purchasing form

  @SmokeTest
  Scenario Outline: Filling Purchasing form for Ongoing consumables
    Given User clicks on project <project>
    And User navigates to the credits page of the project
    And User should be on form <formname> rating <ratingSystem>
    When User will select the Radio Button
    And User enters data to text box with number
    And User uploads files to Add file button
    And User Enters data to Narrative field
    And User downloads the uploaded files
    And User deletes the uploaded files
    And User clicks on Save button
    Then User can able to see the form got saved
    And User clicks the Complete icon to set status to Ready for Review

    Examples: 
      | formname                      | project          | ratingSystem |
      | Purchasing-ongoingConsumables | BDD6 TestProject | O+M:EB       |

  @SmokeTest
  Scenario Outline: Filling Purchasing form for Building Products Materials
    Given User clicks on project <project>
    And User navigates to the credits page of the project
    And User should be on form <formname> rating <ratingSystem>
    When User will select the Radio Button
    And User enters data to text box with number
    And User uploads files to Add file button
    And User Enters data to Narrative field
    And User downloads the uploaded files
    And User deletes the uploaded files
    And User clicks on Save button
    Then User can able to see the form got saved
    And User clicks the Complete icon to set status to Ready for Review

    Examples: 
      | formname                             | project          | ratingSystem |
      | Purchasing-buildingProductsMaterials | BDD6 TestProject | O+M:EB       |

  @SmokeTest
  Scenario Outline: Filling Purchasing form for Electric Equipments
    Given User clicks on project <project>
    And User navigates to the credits page of the project
    And User should be on form <formname> rating <ratingSystem>
    When User will select the Radio Button
    And User checks the checkbox option
    And User enters data to text box with number
    And User uploads files to Add file button
    And User Enters data to Narrative field
    And User downloads the uploaded files
    And User deletes the uploaded files
    And User clicks on Save button
    Then User can able to see the form got saved
    And User clicks the Complete icon to set status to Ready for Review

    Examples: 
      | formname                      | project          | ratingSystem |
      | Purchasing-electricEquipments | BDD6 TestProject | O+M:EB       |

  @RegressionTest
  Scenario Outline: Checking Auto Save functionality in Purchasing form for Ongoing consumables
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
    And User Enters data to Narrative field after clear draft
    And User clicks on Save button
    Then User verifies clear draft,warning message are not present
    And User will not make any changes in the form
    And User verifies clear draft,warning message are not present
    And User dowloads the form as pdf
    And User verifies text with pdf
    And User verifies the narrative
    And User deletes the downloaded pdf file

    Examples: 
      | formname                      | project          | ratingSystem |
      | Purchasing-ongoingConsumables | BDD6 TestProject | O+M:EB       |

  @RegressionTest
  Scenario Outline: Checking Auto Save functionality in Purchasing form for Building Products Materials
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
    And User Enters data to Narrative field after clear draft
    And User clicks on Save button
    Then User verifies clear draft,warning message are not present
    And User will not make any changes in the form
    And User verifies clear draft,warning message are not present
    And User dowloads the form as pdf
    And User verifies text with pdf
    And User verifies the narrative
    And User deletes the downloaded pdf file

    Examples: 
      | formname                             | project          | ratingSystem |
      | Purchasing-buildingProductsMaterials | BDD6 TestProject | O+M:EB       |

  @RegressionTest
  Scenario Outline: Checking Auto Save functionality in Purchasing form for Electric Equipments
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
    And User Enters data to Narrative field after clear draft
    And User clicks on Save button
    Then User verifies clear draft,warning message are not present
    And User will not make any changes in the form
    And User verifies clear draft,warning message are not present
    And User dowloads the form as pdf
    And User verifies the narrative
    And User deletes the downloaded pdf file

    Examples: 
      | formname                      | project          | ratingSystem |
      | Purchasing-electricEquipments | BDD6 TestProject | O+M:EB       |
