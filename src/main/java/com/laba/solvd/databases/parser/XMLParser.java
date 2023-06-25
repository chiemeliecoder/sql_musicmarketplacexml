package com.laba.solvd.databases.parser;

import com.laba.solvd.databases.model.User;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XMLParser {
  public User parseXML(String filePath) {
    try {
      File xmlFile = new File(filePath);

      JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
      Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
      User user = (User) unmarshaller.unmarshal(xmlFile);

      return user;
    } catch (JAXBException e) {
      e.printStackTrace();
      return null;
    }
  }


}
