# language: fr
Fonctionnalité: Mettre à jour une gare

  Scénario: Mettre à jour une gare
    Étant donnée l'existence d'une gare avec le libellé "Paris Saint-Lazard"
    Lorsque je mets à jour cette gare avec le libellé "Paris Montparnasse"
    Alors cette gare est mise à jour avec succès

  Scénario: Tenter de mettre à jour une gare qui n'existe pas
    Étant donnée l'existence d'une gare avec le code "TRN_PSL"
    Lorsque je tente de mettre à jour la gare "TRN_AAA"
    Alors je reçois une erreur 404 avec le message "La station de train TRN_AAA n'existe pas"
