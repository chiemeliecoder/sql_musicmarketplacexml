package com.laba.solvd.databases.dao;

import com.laba.solvd.databases.configurations.ConnectionPool;
import com.laba.solvd.databases.interfacedao.IUserDAO;
import com.laba.solvd.databases.model.Purchase;
import com.laba.solvd.databases.model.User;
import com.laba.solvd.databases.model.UserProfile;
import java.sql.Connection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.*;

public class UserDAO implements IUserDAO {

  private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

  private static final String FIND_ALL_QUERY = "SELECT * FROM musicmarketplace.user " +
      "JOIN musicmarketplace.playlists ON musicmarketplace.user.id = musicmarketplace.playlists.userid " +
      "JOIN musicmarketplace.wishlists ON musicmarketplace.user.id = musicmarketplace.wishlists.userid " +
      "JOIN musicmarketplace.purchases ON musicmarketplace.user.id = musicmarketplace.purchases.userid " +
      "JOIN musicmarketplace.reviews ON musicmarketplace.user.id = musicmarketplace.reviews.userid " +
      "JOIN musicmarketplace.tracks ON musicmarketplace.purchases.trackid = musicmarketplace.tracks.id " +
      "JOIN musicmarketplace.albums ON musicmarketplace.tracks.albumid = musicmarketplace.albums.id " +
      "JOIN musicmarketplace.artists ON musicmarketplace.albums.artistid = musicmarketplace.artists.id " +
      "JOIN musicmarketplace.artist_genre ON musicmarketplace.artists.id = musicmarketplace.artist_genre.artistid " +
      "JOIN musicmarketplace.genre ON musicmarketplace.artist_genre.genreid = musicmarketplace.genre.id " +
      "JOIN musicmarketplace.artist_achievements ON musicmarketplace.artists.id = musicmarketplace.artist_achievements.artistid";

  public UserDAO() {

  }
//
//  @Override
//  public List<Purchase> getPurchase(int purchaseID) {
//    List<Purchase> purchases = new ArrayList<>();
//    Connection connection = CONNECTION_POOL.getConnectionFromPool();
//    try(PreparedStatement preparedStatement = connection.prepareStatement( "SELECT purchases.id, purchases.date, purchases.price " +
//        "FROM purchases " +
//        "JOIN user ON user.id = purchases.userid " +
//        "WHERE purchases.id = ?")){
//      preparedStatement.setInt(1, purchaseID);
//      ResultSet resultSet = preparedStatement.executeQuery();
//      while (resultSet.next()){
//        Purchase purchase = new Purchase();
//        purchase.setId(resultSet.getInt("id"));
//        purchase.setPurchaseDate(resultSet.getDate("date"));
//        purchase.setPrice(resultSet.getBigDecimal("price"));
//
//        purchases.add(purchase);
//      }
//    } catch (SQLException throwables) {
//      throwables.printStackTrace();
//    }finally{
//      CONNECTION_POOL.releaseConnectionToPool(connection);
//    }
//
//
//    return purchases;
//  }

  public User getUserById(int id) throws SQLException {

    Connection connection = CONNECTION_POOL.getConnectionFromPool();

    User user = new User();
    Properties properties = new Properties();
    //example jdbc:mysql://localhost:3306/w3spoint where 3306 is the port number and w3spoint is the database name.
    // Username: Username of MySql database, default is root.  Password: Password of MySql database.
    // "schema" is equivalent to a "database."
    try(InputStream input = new FileInputStream("src/main/resources/db.properties")){
      properties.load(input);
    } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }


      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM USER WHERE ID=?");

      preparedStatement.setInt(1,id);

      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()){
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("username"));
      }





    return user;

  }


  @Override
  public void createUser(User user) {
    Connection connection = CONNECTION_POOL.getConnectionFromPool();
    try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO  User (id, username, email, password,userprofid) VALUES (?, ?, ?, ?,?)",
        Statement.RETURN_GENERATED_KEYS)){
      preparedStatement.setInt(1, user.getId());
      preparedStatement.setString(2, user.getName());
      preparedStatement.setString(3, user.getEmail());
      preparedStatement.setString(4, user.getPassword());


      UserProfile userProfile = user.getUserProfile();
      if (userProfile != null) {
        // Check if the UserProfile object has a valid ID
        Integer userProfileId = userProfile.getId();
        if (userProfileId != null) {
          preparedStatement.setInt(5, userProfileId); // Set the valid foreign key value
        } else {
          throw new IllegalArgumentException("UserProfile ID cannot be null for the not null foreign key");
        }
      } else {
        throw new IllegalArgumentException("UserProfile cannot be null for the not null foreign key");
      }


      preparedStatement.executeUpdate();
      ResultSet resultSet = preparedStatement.getGeneratedKeys();
      while (resultSet.next()){
        user.setId(resultSet.getInt(1));
      }

    }catch(SQLException e){
      throw new RuntimeException("unable to create user", e);
    }finally {
      CONNECTION_POOL.releaseConnectionToPool(connection);
    }

  }

  @Override
  public List<User> getAllUsers() {
    List<User> users = new ArrayList<>();

    Connection connection = CONNECTION_POOL.getConnectionFromPool();
    try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM USER")){
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()){
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("username"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setUserProfile(new UserProfile());
        user.setPlaylistsList(new ArrayList<>());
        user.setPurchasesList(new ArrayList<>());
        user.setWishlistsList(new ArrayList<>());
        user.setReviewsList(new ArrayList<>());


        users.add(user);
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }finally{
      CONNECTION_POOL.releaseConnectionToPool(connection);
    }
    return users;
  }

  @Override
  public List<User> findAll() {
    List<User> users = new ArrayList<>();

    Connection connection = CONNECTION_POOL.getConnectionFromPool();
    try(PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY)){
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()){
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("username"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));

        users.add(user);
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }finally{
      CONNECTION_POOL.releaseConnectionToPool(connection);
    }
    return users;
  }

  @Override
  public UserProfile retrieveUserProfileById(int userProfileId) {
    UserProfile userProfile = new UserProfile();

    Connection connection = CONNECTION_POOL.getConnectionFromPool();
    try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM USER_PROFILE WHERE id = ?")) {
      preparedStatement.setInt(1, userProfileId);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        userProfile.setId(resultSet.getInt("id"));
        userProfile.setBio(resultSet.getString("bio"));
        userProfile.setProfileimage(resultSet.getString("profileimage"));
        userProfile.setLocation(resultSet.getString("location"));
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } finally {
      CONNECTION_POOL.releaseConnectionToPool(connection);
    }
    return userProfile;
  }


  public int getMaxUserId() {
    // Add the necessary logic to retrieve the maximum user ID from the database
    int maxId = 0;

    // Retrieve the maximum ID using a database query
    try (Connection connection = CONNECTION_POOL.getConnectionFromPool();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT MAX(id) FROM User")) {
      if (resultSet.next()) {
        maxId = resultSet.getInt(1);
      }
    } catch (SQLException e) {
      throw new RuntimeException("Unable to get the maximum user ID", e);
    }

    return maxId;
  }

//  public static void main(String args[]) throws SQLException{
//    User use = new UserDAO().getUserById(1);
//    System.out.println("User ID: " + use.getId());
//    System.out.println("Username: " + use.getName());
//
//
//
//    List<User> users = new UserDAO().getAllUsers();
//
//    for (User user : users) {
//      System.out.println("User ID: " + user.getId());
//      System.out.println("Username: " + user.getName());
//    }
//    List<User> userList = new UserDAO().findAll();
//
//    for (User user : userList) {
//      System.out.println("User ID: " + user.getId());
//      System.out.println("User Name: " + user.getName());
//      // Retrieve other user properties as needed
//      System.out.println("-------------------------");
//    }
//
//    int purchaseID = 1;
//    List<Purchase> purchaseList = new UserDAO().getPurchase(purchaseID);
//
//    for (Purchase purchase : purchaseList) {
//      System.out.println("Purchase ID: " + purchase.getId());
//      System.out.println("Purchase Date: " + purchase.getPurchaseDate());
//      System.out.println("Price: " + purchase.getPrice());
//      // Retrieve other purchase properties as needed
//      System.out.println("-------------------------");
//    }
//
////    UserDAO userDAO = new UserDAO();
////
////    User newUser = new User();
////    UserProfile usep = new UserProfile();
////    usep.setId(1);
////
////    newUser.setId(3);
////    newUser.setName("JaneDoe");
////    newUser.setEmail("janedoe@example.com");
////    newUser.setPassword("password123");
////    newUser.setUserProfile(usep.getId());
////
////    userDAO.createUser(newUser);
//
//
//
//
//
//
//  }


}
