package com.laba.solvd.databases;

import com.laba.solvd.databases.model.Album;
import com.laba.solvd.databases.model.Artists;
import com.laba.solvd.databases.model.Playlist;
import com.laba.solvd.databases.model.User;
import com.laba.solvd.databases.model.UserProfile;
import com.laba.solvd.databases.model.Wishlist;
import com.laba.solvd.databases.parser.IParser;
import com.laba.solvd.databases.parser.JsonParser;
import com.laba.solvd.databases.parser.XMLParser;
import com.laba.solvd.databases.parser.musicParser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.xml.stream.XMLStreamException;
import org.apache.log4j.Logger;

public class Main {
  private static final Logger logger = Logger.getLogger(Main.class);

  public static void main(String args[]) throws FileNotFoundException, XMLStreamException {


    String xmlFilePath = "src/main/resources/musicdata.xml";

    IParser parser = new musicParser();
    User users =  parser.parseMusicData(xmlFilePath);

    System.out.println(users.getName());
    System.out.println(users.getUserProfile().getBio());
    System.out.println(users.getPlaylistList().get(0).getPlaylistName());
    System.out.println(users.getWishlistList().get(0).getName());
    System.out.println(users.getWishlistList().get(0).getAlbumList().get(0).getAlbumName());
//    try {
//      String jsonFilePath = "src/main/resources/musicdata.json";
//
//      User user = JsonParser.parseJsonFile(jsonFilePath);
//
//      // Access the parsed user object and perform operations
//      System.out.println(user.getName());
////      System.out.println(user.getUserProfile().getBio());
////      System.out.println(user.getPlaylistList().get(0).getPlaylistName());
////      System.out.println(user.getWishlistList().get(0).getName());
////      System.out.println(user.getWishlistList().get(0).getAlbumList().get(0).getAlbumName());
//    } catch (
//    IOException e) {
//      e.printStackTrace();
//    }
//
//
//    String xmlFile = "src/main/resources/musicdata.xml";
//    XMLParser xmlParser = new XMLParser();
//    User user = xmlParser.parseXML(xmlFile);
//
//    if (user != null) {
//      // Handle the parsed user object
//      System.out.println(user.getName());
//      System.out.println(user.getUserProfile().getBio());
//      System.out.println(user.getPlaylistList().get(0).getPlaylistName());
//      System.out.println(user.getWishlistList().get(0).getName());
//      System.out.println(user.getWishlistList().get(0).getAlbumList().get(0).getAlbumName());
//    } else {
//      // Handle the case when parsing fails
//      System.out.println("Failed to parse XML.");
//    }



    logger.info("end of information");




  }

}
