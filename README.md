# Automatische Generierung von Testdaten für produktentwicklungsspezifische Artefakte

## Überblick Austauschformat
Die Arbeit beschäftigt sich mit dem Thema der Datengenerierung. Das
Ziel ist, automatisch Testdaten zu einem beliebigen Anwendungsfall
generieren zu können, wobei an die Testdaten besondere Anforderungen
gestellt werden. Sie sollten möglichst realistisch Datensätze nachbilden
können. Das Austauschformat basiert auf dem Eclipse Modeling Framework
(EMF) und soll den Workflow zwischen Modellierung und Codegen-
erierung unterstützen und vereinfachen. Es vereinigt die Technologien
UML, Java, XSD in einem Meta-Model (Ecore). Die serialisierte Form
dieses Models basiert auf dem XMI-Format.

Auf Basis dieser Constraints wird der Ansatz einer parametrischen
Testdatengenerierung verfolgt. Zu diesem Zweck wird ein Testdaten-
generator konzipiert, der auf einem generischen Framework aufbaut und
somit unabhängig vom Anwendungsbereich eingesetzt werden kann. Der
Prozess der Datengenerierung wird dabei in unterschiedliche Views
entkoppelt.


## Motivation & Hintergrund
Mit den Testdaten sollen die am Lehrstuhl entwickelten Retrieval Modelle
evaluiert werden können. Dabei sollen vor allem Aussagen über die
verwendeten Suchmechanismen wie z.B. facettierte Suche oder kontext-
abhängige Suche gemacht werden können.

Die meisten Anwendungen, mit denen Testdaten generiert werden
können, sind oft zu spezialisiert und nur bedingt auf andere
Anwendungs-bereiche anwendbar. Das Ziel ist es deshalb ein möglichst
generisches Framework zu erstellen. Dies gibt die Möglichkeit, ein sehr
breites An-wendungsfeld zur Generierung von Testdaten zu erschließen.

## Views
Modellierung und Datengenerierung unterliegen verschiedenen Sicht-
weisen und Rollen. Eine Kopplung dieser Prozesse erfolgt mittels eines
Austauschformats.


## Model-Driven-Data Generation
Eine Modellierung der Testdaten erfolgt in der sog. Model-Driven-Data
Generation View, in der die gewünschte Datenbasis definiert wird. Dieser
Prozess ist also in der ersten Phase von der Modellierung gesteuert.
Eine entsprechende Modellierung kann mit Hilfe von UML erfolgen.


## Austauschformat
Das Austauschformat basiert auf dem Eclipse Modeling Framework
(EMF) und soll den Workflow zwischen Modellierung und Codegen-
erierung unterstützen und vereinfachen. Es vereinigt die Technologien
UML, Java, XSD in einem Meta-Model (Ecore). Die serialisierte Form
dieses Models basiert auf dem XMI-Format.


## Artefakt-Driven-Data Generation
Die eigentliche Datengenerierung erfolgt in der zweiten View, der sog.
Artefact-Driven-Data Generation. Die Bedeutung eines Artefakts hängt
dabei stark vom Kontext und vom Anwendungsbereich ab, in dem die
generierten Testdaten verwendet werden sollen.
Die hier betrachteten Artefakte konzentrieren sich deshalb auf zwei
Kategorien: Produkt- und Dokument-Artefakte.


## Fazit
Unter Verwendung eines solchen Testdatengenerators ist es möglich, in
kurzer Zeit umfangreiche Testkollektionen automatisch zu verschiedenen
Anwendungsfällen zu erstellen. Eine manuelle Durchführung dieses Pro-
zesses wäre nicht nur sehr zeitaufwändig und fehleranfällig, sondern
würde sich auf einen kleinen Anwendungsbereich beschränken.

