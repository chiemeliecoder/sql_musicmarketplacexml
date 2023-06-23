package com.laba.solvd.databases.dao;

import com.laba.solvd.databases.configurations.ConnectionPool;
import com.laba.solvd.databases.interfacedao.IUserProfileDAO;
import com.laba.solvd.databases.model.User;
import com.laba.solvd.databases.model.UserProfile;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserProfileDAO implements IUserProfileDAO {

  private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();


  public List<User> getAllUsers(int userId) {
    List<User> users = new ArrayList<>();

    Connection connection = CONNECTION_POOL.getConnectionFromPool();
    try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT user.id, user.username, user_profile.bio " +
        "FROM user " +
        "JOIN user_profile ON user.id = user_profile.user_id")){
      preparedStatement.setInt(1,userId);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()){
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("username"));

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
  public UserProfile getUserProfileById(int userId) throws SQLException {
    Connection connection = CONNECTION_POOL.getConnectionFromPool();
    UserProfile user = new UserProfile();
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

      preparedStatement.setInt(1,userId);

      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()){
        user.setId(resultSet.getInt("id"));
        user.setBio(resultSet.getString("bio"));
      }




    return user;
  }

  @Override
  public void createUser(UserProfile user) {
    Connection connection = CONNECTION_POOL.getConnectionFromPool();
    try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user_profile (id, bio, profileimage, location) VALUES (?, ?, ?, ?)",
        Statement.RETURN_GENERATED_KEYS)){
      preparedStatement.setInt(1, user.getId());
      preparedStatement.setString(2, user.getBio());
      preparedStatement.setString(3, user.getProfileimage());
      preparedStatement.setString(4, user.getLocation());


      preparedStatement.executeUpdate();
      ResultSet resultSet = preparedStatement.getGeneratedKeys();
      while (resultSet.next()){}

    }catch(SQLException e){
      throw new RuntimeException("unable to create user", e);
    }finally {
      CONNECTION_POOL.releaseConnectionToPool(connection);
    }

  }

  @Override
  public List<UserProfile> getAllUsersProfiles() {
    List<UserProfile> userProfiles = new ArrayList<>();

    Connection connection = CONNECTION_POOL.getConnectionFromPool();
    try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user_profile")){
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()){
        UserProfile user = new UserProfile();
        user.setId(resultSet.getInt("id"));
        user.setBio(resultSet.getString("bio"));

        userProfiles.add(user);
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }finally{
      CONNECTION_POOL.releaseConnectionToPool(connection);
    }
    return userProfiles;

  }

}
