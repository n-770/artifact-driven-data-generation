application: DataGenerator
version: 0.1
date: Apr/27/09
author: Michael Munz


'Resources' folder is used by the DataGenerator application.
Neither delete the folder, its sub-folders or files.
The structure is the following:

   Resoucres
      de
         addresses
         cities
         countries
         e-mails
         names
         prenames_boys
         prenames_girls
         sex
      us
	     addresses
		 ...
		 sex

In each file are common attribute values which are used
in the process of test data generation. To achieve the goal of realistic data sets,
real data is used for that purpose.

The system supports different kind of generators. Each generator has its
own lists attached. The lists are used, when one of the pre-defined
generators has been chosen. The generators are for convenience purposes.

The system's approach is, to be as generic as possible. This allowes to generate
a wide variety of different artefacts. But that leads also to drawbacks.
Because its generic, only a small subset of attributes are supported initially.
Therefore, it is recommended to create and use your own lists. That's especially
true, when you wanna generate data sets to very unusual attributes.
