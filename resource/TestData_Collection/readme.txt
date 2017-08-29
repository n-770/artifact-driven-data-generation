application: DataGenerator
version: 0.1
date: Apr/14/09
author: Michael Munz


Test data generation phase
Another XML file gives information about how test data should be generated to get or build up valid
test data collections. To put it straight, it specifies how test data looks like.
A design decision of the test data generator is that test data collections generated are based on XML.
This is because XML is a markup language and with the help of this we have the opportunity to define
how it is organized. Note, the system at your hand makes intensive use of the XSD file for test
data generation. So keep the files safe at any time. Otherwise the application won't work properly.