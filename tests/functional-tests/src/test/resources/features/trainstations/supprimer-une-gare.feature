# language: fr
Fonctionnalité: Supprimer une gare

  Scénario: Supprimer une gare
    Étant donnée l'existence d'une gare
    Lorsque je supprime cette gare
    Alors cette gare est supprimée avec succès

  Scénario: Tenter de supprimer une gare qui n'existe pas
    Étant donnée l'existence d'une gare avec le code "TRN_PSL"
    Lorsque je tente de supprimer la gare "TRN_AAA"
    Alors je reçois une erreur 404 avec le message "La station de train TRN_AAA n'existe pas"
