@db
Feature: Homework DB verification

  Scenario Outline: Verify the homework first question's answer with DB
    Given "<customer_id>" "<first_name>" "<last_name>" "<city>" "<country>" "<sum>" should match with the DB record

    Examples:
      | customer_id | first_name     | last_name    | city                | country       | sum  |
      | 148         | Eleanor        |Hunt          | Saint-Denis         | Runion        | 211.55 |
      | 526         | Karl           |Seal          | Cape Coral          | United States | 208.58 |
      | 178         | Marion         |Snyder        | Santa Brbara dOeste | Brazil        | 194.61 |
      | 137         | Rhonda         |Kennedy       | Apeldoorn           | Netherlands   | 191.62 |
      | 144         | Clara          |Shaw          | Molodetno           | Belarus       | 189.60 |
