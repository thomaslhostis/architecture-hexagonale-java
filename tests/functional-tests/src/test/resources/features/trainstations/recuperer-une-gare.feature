# language: fr
Fonctionnalité: Récupérer une gare

  Scénario: Récupérer une gare et ses prochains départs
    Étant donnée l'existence d'une gare
    Et le prochain départ pour "destination-a" est à 12h
    Et le prochain départ pour "destination-b" est à 13h
    Lorsque je récupère les informations et prochains départs de cette gare
    Alors je reçois les informations et prochains départs de cette gare
