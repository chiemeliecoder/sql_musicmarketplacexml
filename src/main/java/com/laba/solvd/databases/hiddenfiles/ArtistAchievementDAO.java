package com.laba.solvd.databases.hiddenfiles;

import com.laba.solvd.databases.configurations.ConnectionPool;
import com.laba.solvd.databases.interfacedao.IArtistAchievement;
import com.laba.solvd.databases.model.ArtistAchievement;
import com.laba.solvd.databases.model.Artists;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ArtistAchievementDAO implements IArtistAchievement {

  private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

  public ArtistAchievement getAchievementById(int id) throws SQLException {

    Connection connection = CONNECTION_POOL.getConnectionFromPool();

    ArtistAchievement achievements = new ArtistAchievement();
    Properties properties = new Properties();

    try(InputStream input = new FileInputStream("src/main/resources/db.properties")){
      properties.load(input);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }


      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ARTIST_ACHIEVEMENTS WHERE ID=?");

      preparedStatement.setInt(1,id);

      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()){
        achievements.setId(resultSet.getInt("id"));
        achievements.setTitle(resultSet.getString("title"));
        Date awardDate = resultSet.getDate("date");
        achievements.setAwardDate(awardDate);
      }


    return achievements;

  }

  @Override
  public void createArtistAchievement(ArtistAchievement achievement) {

    Connection connection = CONNECTION_POOL.getConnectionFromPool();
    try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO artist_achievements (id, title, date) VALUES  (?, ?, ?,?)",
        Statement.RETURN_GENERATED_KEYS)){
      preparedStatement.setInt(1, achievement.getId());
      preparedStatement.setString(2, achievement.getTitle());
      java.util.Date utilDate = new java.util.Date();
      java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

      preparedStatement.setDate(3, sqlDate);

      preparedStatement.executeUpdate();
      ResultSet resultSet = preparedStatement.getGeneratedKeys();
      while (resultSet.next()){}

    }catch(SQLException e){
      throw new RuntimeException("unable to create album", e);
    }finally {
      CONNECTION_POOL.releaseConnectionToPool(connection);
    }
  }


  @Override
  public List<ArtistAchievement> getAllArtistAchievements() {
    List<ArtistAchievement> artistAchievements = new ArrayList<>();

    Connection connection = CONNECTION_POOL.getConnectionFromPool();
    try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM artist_achievements")){
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()){
        ArtistAchievement artistAchievement1 = new ArtistAchievement();
        artistAchievement1.setId(resultSet.getInt("id"));
        artistAchievement1.setTitle((resultSet.getString("title")));
        artistAchievement1.setAwardDate(resultSet.getDate("date"));


        artistAchievements.add(artistAchievement1);
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }finally{
      CONNECTION_POOL.releaseConnectionToPool(connection);
    }
    return artistAchievements;
  }

//  public static void main(String args[]) throws SQLException{
//    ArtistAchievement artistAchievement = new ArtistAchievementDAO().getAchievementById(1);
//    System.out.println("Award ID: " + artistAchievement.getId());
//    System.out.println("AwardName: " + artistAchievement.getTitle());
//    System.out.println("AwardDate:" + artistAchievement.getAwardDate());
//
//  }


}
