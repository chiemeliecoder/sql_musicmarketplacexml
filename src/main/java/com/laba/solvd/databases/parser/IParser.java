package com.laba.solvd.databases.parser;

import com.laba.solvd.databases.model.User;
import java.io.FileNotFoundException;
import javax.xml.stream.XMLStreamException;

public interface IParser {

  User parseMusicData(String xmlFilePath) throws FileNotFoundException, XMLStreamException;
}
