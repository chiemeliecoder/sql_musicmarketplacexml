package com.laba.solvd.databases.parser;
import com.google.protobuf.TextFormat.ParseException;
import com.laba.solvd.databases.model.Album;
import com.laba.solvd.databases.model.Playlist;
import com.laba.solvd.databases.model.User;
import com.laba.solvd.databases.model.UserProfile;
import com.laba.solvd.databases.model.Wishlist;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class musicParser implements IParser{

  public User parseMusicData(String xmlFilePath) {
    User user = null;
    UserProfile userProfile;
    List<Playlist> playlistList = new ArrayList<>();
    List<Wishlist> wishlistList = new ArrayList<>();

    try {
      XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
      XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(new FileInputStream(xmlFilePath));


      while (xmlStreamReader.hasNext()) {
        int event = xmlStreamReader.next();

        switch (event) {
          case XMLStreamConstants.START_ELEMENT:
            String elementName = xmlStreamReader.getName().getLocalPart();

            if (elementName.equals("User")) {
              String userIdString = xmlStreamReader.getElementText();
              int userId = userIdString != null ? Integer.parseInt(userIdString) : 0;

              String username = xmlStreamReader.getElementText();
              String email = xmlStreamReader.getElementText();
              String password = xmlStreamReader.getElementText();

              user = new User(userId, username, email, password);
            } else if (elementName.equals("UserProfile")) {
              userProfile = parseUserProfile(xmlStreamReader);
              user.setUserProfile(userProfile);
            } else if (elementName.equals("Playlist")) {
              Playlist playlist = parsePlaylist(xmlStreamReader);
              playlistList.add(playlist);
            } else if (elementName.equals("Wishlist")) {
              Wishlist wishlist = parseWishlist(xmlStreamReader);
              wishlistList.add(wishlist);
            }
            break;
          case XMLStreamConstants.END_ELEMENT:
            // Handle end element event
            String endElementName = xmlStreamReader.getLocalName();
            // Handle end element event based on the element name
            if (endElementName.equals("User")) {
              // Finish processing the user element
              // Perform any necessary actions or validations
            } else if (endElementName.equals("Playlist")) {
              // Finish processing the playlist element
              // Perform any necessary actions or validations
            } else if (endElementName.equals("Wishlist")) {
              // Finish processing the wishlist element
              // Perform any necessary actions or validations
            }
            break;
          case XMLStreamConstants.CHARACTERS:

            // Handle character data event
            String data = xmlStreamReader.getText();
            // Handle character data event based on the current element being processed
            if (data != null && !data.trim().isEmpty()) {
              if (xmlStreamReader.getLocalName().equals("Username")) {
                // Process the username data
              } else if (xmlStreamReader.getLocalName().equals("Email")) {
                // Process the email data
              }
            }
            break;
        }
      }

      xmlStreamReader.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (XMLStreamException e) {
      e.printStackTrace();
    }

    // Set the parsed playlistList and wishlistList to the user object
    user.setPlaylistList(playlistList);
    user.setWishlistList(wishlistList);

    return user;
  }



  private UserProfile parseUserProfile(XMLStreamReader xmlStreamReader) throws XMLStreamException {
    Integer id = null;
    String bio = null;
    String profileImage = null;
    String location = null;

    while (xmlStreamReader.hasNext()) {
      int event = xmlStreamReader.next();

      switch (event) {
        case XMLStreamConstants.START_ELEMENT:
          String elementName = xmlStreamReader.getName().getLocalPart();

          if (elementName.equals("Id")) {
            String idString = xmlStreamReader.getElementText();
            id = idString != null ? Integer.parseInt(idString) : null;
          } else if (elementName.equals("Bio")) {
            bio = xmlStreamReader.getElementText();
          } else if (elementName.equals("Profileimage")) {
            profileImage = xmlStreamReader.getElementText();
          } else if (elementName.equals("Location")) {
            location = xmlStreamReader.getElementText();
          }
          break;
        case XMLStreamConstants.END_ELEMENT:
          if (xmlStreamReader.getLocalName().equals("UserProfile")) {
            return new UserProfile(id, bio, profileImage, location);
          }
          break;
      }
    }

    return null;
  }


  private Playlist parsePlaylist(XMLStreamReader xmlStreamReader) throws XMLStreamException {
    int playlistId = 0;
    String playlistName = null;

    while (xmlStreamReader.hasNext()) {
      int event = xmlStreamReader.next();

      switch (event) {
        case XMLStreamConstants.START_ELEMENT:
          String elementName = xmlStreamReader.getName().getLocalPart();

          if (elementName.equals("PlaylistId")) {
            String playlistIdString = xmlStreamReader.getElementText();
            playlistId = playlistIdString != null ? Integer.parseInt(playlistIdString) : 0;
          } else if (elementName.equals("PlaylistName")) {
            playlistName = xmlStreamReader.getElementText();
          }
          break;
        case XMLStreamConstants.END_ELEMENT:
          if (xmlStreamReader.getLocalName().equals("Playlist")) {
            return new Playlist(playlistId, playlistName);
          }
          break;
      }
    }

    return null;
  }

  private Wishlist parseWishlist(XMLStreamReader xmlStreamReader) throws XMLStreamException {
    int wishlistId = 0;
    String name = null;
    List<Album> albumList = new ArrayList<>();

    while (xmlStreamReader.hasNext()) {
      int event = xmlStreamReader.next();

      switch (event) {
        case XMLStreamConstants.START_ELEMENT:
          String elementName = xmlStreamReader.getName().getLocalPart();

          if (elementName.equals("WishlistId")) {
            String wishlistIdString = xmlStreamReader.getElementText();
            wishlistId = wishlistIdString != null ? Integer.parseInt(wishlistIdString) : 0;
          } else if (elementName.equals("Name")) {
            name = xmlStreamReader.getElementText();
          } else if (elementName.equals("Album")) {
            String albumIdString = xmlStreamReader.getAttributeValue(null, "AlbumId");
            int albumId = albumIdString != null ? Integer.parseInt(albumIdString) : 0;

            String albumTitle = xmlStreamReader.getAttributeValue(null, "AlbumTitle");

            String releaseDateString = xmlStreamReader.getAttributeValue(null, "ReleaseDate");
            Date releaseDate = null;
            try {
              releaseDate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(releaseDateString);
            } catch (java.text.ParseException e) {
              e.printStackTrace();
            }

            Album album = new Album(albumId, albumTitle, releaseDate);
            albumList.add(album);
          }
          break;
        case XMLStreamConstants.END_ELEMENT:
          if (xmlStreamReader.getLocalName().equals("Wishlist")) {
            return new Wishlist(wishlistId, name, albumList);
          }
          break;
      }
    }

    return null;
  }


}
