# Maven_XML_Parser
This application takes in a given XML doc of contacts and can convert to JSON or reconvert to XML

# NOTE
I realize my work is incomplete, in the spirit of honesty this is as far as I got with the given time constraints. I was busy with 
family all weekend which is why my submission went in on June 7, 2021. I had to quickly pick up on how to use Maven in order to give the most functional 
program within the alloted time. As it stands now, there was't time for a GUI so path to the Address Book XML had to hardcoded in. Thank you for your
time and consideration.


## TO USE
Go to: /MavenXML_Validator_Converter/src/main/java/XMLTools/MavenXML_Validator_Converter/App.java
Open App.java and define:
  xml_path: Where the ab.xml is located
  json_output_path: give path to desire output folder
  xml_output_path: give path to desire output folder

## MAVEN DEPENDENCIES
\.m2\repository\com\googlecode\json-simple\json-simple\1.1.1\json-simple-1.1.1.jar

## Functionality
The program works with App.java being the main engine that makes all the action calls. The ab.xml file in input with by defining the file
path and the desired output. The XML is taken in, parsed and all data is loaded in a Contact object to then be easily and readily outputed into
whatever the desired output is (JSON or XML).  

# XSD PATH
/MavenXML_Validator_Converter/src/main/java/xsd/contact.xsd
