Feature: Planification d'un rendez-vous

  Scenario: Le médecin est disponible
    Given un médecin avec un créneau libre demain à 10h
    When un patient demande un rendez-vous à ce créneau
    Then le rendez-vous est planifié avec succès

  Scenario: Le créneau est déjà pris
    Given un médecin avec un rendez-vous existant demain à 10h
    When un autre patient tente de prendre ce créneau
    Then une erreur est levée