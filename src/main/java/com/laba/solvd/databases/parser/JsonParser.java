package com.laba.solvd.databases.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laba.solvd.databases.model.User;
import java.io.File;
import java.io.IOException;

public class JsonParser {

  public static User parseJsonFile(String filePath) throws IOException {
    File jsonFile = new File(filePath);
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(jsonFile, User.class);
  }

}
