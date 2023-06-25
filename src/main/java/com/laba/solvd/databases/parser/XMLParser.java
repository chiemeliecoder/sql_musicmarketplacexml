package com.laba.solvd.databases.parser;

import com.laba.solvd.databases.model.User;
import jakarta.xml.bind.JAXBContext;
import java.io.File;

import jakarta.xml.bind.Unmarshaller;


public class XMLParser {
  public User parseXML(String filePath) {
    try {
      File xmlFile = new File(filePath);

      JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
      Unmarshaller unmarshaller =  jaxbContext.createUnmarshaller();
      User user = (User) unmarshaller.unmarshal(xmlFile);

      return user;
    } catch ( jakarta.xml.bind.JAXBException e) {
      e.printStackTrace();
      return null;
    }
  }


}
