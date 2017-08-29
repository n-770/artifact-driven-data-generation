application: DataGenerator
version: 0.1
date: May/05/09
author: Michael Munz


Log/ folder has the job of logging exceptions and errors occuring
during runtime.

Configuring the Logger

The default values for loggers and handlers can be set using the shipped
properties file. The custom properties file is loaded by specifying a 
system property on the command line.

java -jar DataGenerator.jar -Djava.util.logging.config.file=Log/logging.properties

For convenience the system predefined settings within, defined in the package
'de.uni.bamberg.wiai.mi.minf.forflow.datagenerator.controller.log.Log'
So you don't have to call each time manually the properties file above.