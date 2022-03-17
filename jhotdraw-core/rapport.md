
## Refactoring: extract method pour les méthodes trop grandes

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
