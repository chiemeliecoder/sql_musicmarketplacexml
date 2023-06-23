package com.laba.solvd.databases.configurations;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum Config {

  URL("db.url"),
  DRIVER("driver"),
  USERNAME("db.user"),
  PASSWORD("db.password"),
  POOLSIZE("poolSize");

  private String key;
  private static Properties properties;
  private static final Properties PROPERTIES;
  private String defaultValue;

  private static final String DBPROPERTIES = "db.properties";

static{
  PROPERTIES = loadProperties();
}
  Config(String key) {
    this.key = key;
  }

  Config(String key, String defaultValue) {
    this.key = key;
    this.defaultValue = defaultValue;
  }

  public String getValue() {
    // You have a properties file loaded into a Properties object
    return PROPERTIES.getProperty(key, defaultValue);
  }

  public static Properties loadProperties() {
    Properties properties = new Properties();
    try {
      InputStream fileInputStream = Config.class.getClassLoader().getResourceAsStream(DBPROPERTIES);
      properties.load(fileInputStream);
      fileInputStream.close();
    } catch (IOException e) {
      throw new RuntimeException("Unable to configure settings", e);
    }
    return properties;
  }

}
