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


    logger.info("end of information for xml parser");




  }

}
