
## Refactoring: extract method pour les méthodes trop grandes
Les modifications présentées ici correspondent au commit 
`8fbbe65047a35a08ae8f0c7defb5c4b59fee16f6`.
* `org.jhotdraw.draw.liner.CurvedLiner`. Dans la méthode 
`lineout(ConnectionFigure)`, il y avait deux cas à traiter:

    - le cas où on "connecte" un même `Figure`: extrait dans la méthode
`lineoutWhenConnectingSameFigure(ConnectionFigure figure, BezierPath path, Connector start, Connector end)`

    - le cas où on "connecte" deux `Figure` différent, extrait dans la méthode
`lineoutWhenConnectingDifferentFigure(ConnectionFigure figure, BezierPath path, Connector start, Connector end)`

* Situation similaire pour `org.jhotdraw.draw.liner.ElbowLiner`
* Situation similaire pour `org.jhotdraw.draw.liner.SlantedLiner`

Objectifs:
* diminuer la responsabilité des méthodes trop grandes
* améliorer la lisibilité de la méthode en diminuant son contenu 

## Refatoring: push up method qui aboutit à la création d'une classe abstraite
Les modifications présentées ici correspondent au(x) commit(s):

Après le refactoring précédent, on remarque des méthodes communs pour `org.jhotdraw.draw.liner.CurvedLiner`, `org.jhotdraw.draw.liner.ElbowLiner` et `org.jhotdraw.draw.liner.SlantedLiner`

* Les méthodes `lineout()`  le même code pour `CurvedLiner`, `ElbowLiner` et `SlantedLiner`. Ainsi, on fait remonter cette méthode dans la classe abstraite
nouvellement créée.

* Les différences entre `CurvedLiner`, `ElbowLiner` et `SlantedLiner` concernant
le fonctionnement de `lineout()` se trouve dans `lineoutWhenConnectingSameFigure()`
et `lineoutWhenConnectingDifferentFigures`. Cela met en évidence le pattern *template method*.
Chaque sous-classes d'`AbstractLiner` devra donc définir le propre implémentation
de `lineoutWhenConnectingSameFigure()` et de `lineoutWhenConnectingDifferentFigures()`. Ces 2 méthodes seront donc déclarées en
tant que protégées dans `AbstractLiner`.
Pour s'assurer que les sous-classes ne rédéfinissent pas `AbstractLiner#lineout()`,
on le déclare en tant que `final`

* On constate qu'on peut aussi faire remonter les méthodes telles que `createHandles()` et `clone()`
