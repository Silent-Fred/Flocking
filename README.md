# An attempt at implementing C. Reynold's Boid flocking model

Wozu ist das hier alles?
------------------------

Bei wiederholten Code Retreats oder ähnlichen Veranstaltungen wird manchen Teilnehmern das klassische
Thema _"Conway's Game of Life"_ langweilig.
Eine eigenen kleine Vektorbibliothek ist z.B. sehr klar spezifiziert und damit gut testbar.
Das schwer voraussagbare Verhalten der "Boids" erfordert dagegen zusätzliche Überlegungen, wie hierfür
sinnvolle Tests geschrieben werden können. Daher könnte das Thema für einen Übungstag durchaus eine
interessante Aufgabe darstellen.

Buildfile
---------

Unter `fx:application` sollte die passende Versionsnummer eingetragen werden.
Gleiches gilt für das Attribut `Implementation-Version` im `<manifest>` Bereich des Buildfiles.

Im Tag `fx:deploy` muss je nach Plattform das Attribut `nativeBundles` auf einen anderen Wert als `dmg`
gesetzt werden (z.B. `all` - siehe dazu die entsprechende Dokumentation bei Oracle).

Das Buildskript muss in einer eigenen Runtime gestartet werden.
(Eclipse z.B.: _Run -> External Tools -> External Tools Configurations..._ Für `build.xml` unter _JRE_ _Separate JRE_ auswählen)
Andernfalls ist der Ausgabepfad der durch `fx:deploy` erzeugten Bundles nicht wie erwartet. Alternativ kann das Ant Property
`BundleOutputPath` auch auf einen absolut angegeben Pfad gesetzt werden.
