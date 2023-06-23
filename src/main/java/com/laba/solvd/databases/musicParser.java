package com.laba.solvd.databases;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class musicParser {

  public static void parseMusicData(String xmlFilePath) {
    try {
      XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
      XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(new FileInputStream(xmlFilePath));

      while (xmlStreamReader.hasNext()) {
        int event = xmlStreamReader.next();

        switch (event) {
          case XMLStreamConstants.START_ELEMENT:
            String elementName = xmlStreamReader.getLocalName();
            // Handle start element event
            if (elementName.equals("User")) {
              // Example: Process user element
              String userIdString = xmlStreamReader.getAttributeValue(null, "UserId");
              int userId = userIdString != null ? Integer.parseInt(userIdString) : 0;

              String username = xmlStreamReader.getAttributeValue(null, "Username");
              String email = xmlStreamReader.getAttributeValue(null, "Email");
              String password = xmlStreamReader.getAttributeValue(null, "Password");
              // Create User object and do something with the data
            }
            break;
          case XMLStreamConstants.END_ELEMENT:
            // Handle end element event
            break;
          case XMLStreamConstants.CHARACTERS:
            // Handle character data event
            String data = xmlStreamReader.getText();
            // Do something with the data
            break;
          // Handle other events as per your needs
        }
      }

      xmlStreamReader.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (XMLStreamException e) {
      e.printStackTrace();
    }
  }

}
