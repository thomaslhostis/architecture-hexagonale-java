# language: fr
Fonctionnalité: Créer une gares

  Scénario: Créer une gare
    Étant donnée une gare à créer avec le libellé "Paris Saint-Lazard" et le code "TRN_PSL"
    Lorsque je crée cette gare
    Alors cette gare est créée avec succès

  Scénario: Tenter de créer une gare avec un code invalide
    Étant donnée une gare à créer avec le libellé "Paris Saint-Lazard" et le code "PSL"
    Lorsque je tente de créer cette gare
    Alors je reçois une erreur 400 avec le message "Le code de la station de train doit commencer par TRN_"

  Scénario: Tenter de créer une gare qui existe déjà
    Étant donnée l'existence d'une gare avec le code "TRN_A"
    Et une gare à créer avec le code "TRN_A"
    Lorsque je tente de créer cette gare
    Alors je reçois une erreur 400 avec le message "La station de train TRN_A existe déjà"
