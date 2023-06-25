package com.laba.solvd.databases.parser;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class XMLValidator {
  public static void main(String[] args) {
    String xmlFilePath = "src/main/resources/musicdata.xml";
    String xsdFilePath = "src/main/resources/musicdata.xsd";

    try {
      // Validate XML file against XSD schema
      SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      Schema schema = schemaFactory.newSchema(new File(xsdFilePath));
      Validator validator = schema.newValidator();
      validator.validate(new StreamSource(new File(xmlFilePath)));

      System.out.println("XML is valid.");
    } catch (Exception e) {
      System.out.println("XML is invalid: " + e.getMessage());
    }
  }

}
