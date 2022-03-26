# language: fr
Fonctionnalité: Récupérer les gares

  Scénario: Récupérer la liste des gares
    Étant donnée l'existence d'une gare avec le code "TRN_A"
    Et l'existence d'une gare avec le code "TRN_B"
    Lorsque je récupère la liste des gares
    Alors je reçois toutes les gares
