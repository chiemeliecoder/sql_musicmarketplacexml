package com.laba.solvd.databases;

import com.laba.solvd.databases.model.Album;
import com.laba.solvd.databases.model.Artists;
import com.laba.solvd.databases.model.Playlist;
import com.laba.solvd.databases.model.User;
import com.laba.solvd.databases.model.UserProfile;
import com.laba.solvd.databases.model.Wishlist;
import com.laba.solvd.databases.parser.JsonParser;
import com.laba.solvd.databases.parser.musicParser;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.log4j.Logger;

public class Main {
  private static final Logger logger = Logger.getLogger(Main.class);

  public static void main(String args[]){

    Calendar cal = Calendar.getInstance();
    cal.set(2023, Calendar.MARCH, 28);
    Date date = cal.getTime();
    java.sql.Date d = new java.sql.Date(date.getTime());

    Time time = Time.valueOf(LocalTime.of(03,01,00));

    BigDecimal p = BigDecimal.valueOf(9.99);
    BigDecimal r = BigDecimal.valueOf(4.5);



    UserProfile firstUserProfile = new UserProfile();
    firstUserProfile.setBio("I like video games");
    firstUserProfile.setProfileimage("https://cdn.shopify.com/s/files/1/0416/8083/0620/files/ecomm-CHGAL-Core2021_367x353px_07-CN_1000x.png?v=1614324462");
    firstUserProfile.setLocation("Belarus");


    User firstUser = new User();
    firstUser.setName("Jen Alero");
    firstUser.setEmail("jen@example.com");
    firstUser.setPassword("password007");
    firstUser.setUserProfile(firstUserProfile);


    musicParser parser = new musicParser();

    String xmlFilePath = "src/main/resources/musicdata.xml";
    String jsonFilePath = "src/main/resources/musicdata.json";
    parser.parseMusicData(xmlFilePath);

    try {

    User user = JsonParser.parseJsonFile(jsonFilePath);

    // Access the parsed user object and perform operations
    System.out.println(user.getName());
    System.out.println(user.getUserProfile().getBio());
    System.out.println(user.getPlaylistList().get(0).getPlaylistName());
    System.out.println(user.getWishlistList().get(0).getName());
    System.out.println(user.getWishlistList().get(0).getAlbumList().get(0).getAlbumName());
    } catch (
    IOException e) {
      e.printStackTrace();
    }


    try {
      File xmlFile = new File("src/main/resources/musicdata.xml");

      JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
      Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
      User user = (User) unmarshaller.unmarshal(xmlFile);

      System.out.println(user.getName());
      System.out.println(user.getUserProfile().getBio());
      System.out.println(user.getPlaylistList().get(0).getPlaylistName());
      System.out.println(user.getWishlistList().get(0).getName());
      System.out.println(user.getWishlistList().get(0).getAlbumList().get(0).getAlbumName());
    } catch (JAXBException e) {
      e.printStackTrace();
    }

    logger.info("end of information");



  }

}
