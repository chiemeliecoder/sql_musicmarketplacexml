package com.laba.solvd.databases;

import com.laba.solvd.databases.model.User;
import com.laba.solvd.databases.parser.IParser;
import com.laba.solvd.databases.parser.musicParser;
import java.io.FileNotFoundException;
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


    logger.info("end of information for xml parser");




  }

}
