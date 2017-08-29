application: DataGenerator
version: 0.1
date: Apr/14/09
author: Michael Munz


This folder contains everything needed to get the system initialized properly.
There're different kind of files, but all of them are based on XML. To each
XML file exists a XSD file. XSD files are XML Schema Definition files and as
the word already states it defines the schema of its corresponding XML file.

The use of XSD files has some advantages. When any XML file is gonna be loaded
into the system it compares the XML file with the XSD file mentioned. The XSD
defines whether the XML file is valid or not. Valid files are loaded and invalid
ones are dismissed and an exception gives feedback to the user.


Initializing phase
Whenever the application gets started it initializes itself with the startup file.
It is based on XML and must be linked with a XSD file. In the startup file there are
all values needed to initialize itself and set paramters.
The file is desinged to get extended whenever the need is given. This can be done on
two ways. Either on using the system itself by using the appropriate functionality
or by using an ordinary XML editor. If you choose the 2nd approach make sure you modify
it in that way it is still valid. Some editors give you the opportunity to validate your
modifications so you are on the safe side.