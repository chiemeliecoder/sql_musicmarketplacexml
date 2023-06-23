package com.laba.solvd.databases.hiddenfiles;

import com.laba.solvd.databases.configurations.ConnectionPool;
import com.laba.solvd.databases.interfacedao.IReviewDAO;
import com.laba.solvd.databases.model.Purchase;
import com.laba.solvd.databases.model.Review;
import com.laba.solvd.databases.model.User;
import com.laba.solvd.databases.model.Wishlist;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ReviewDAO implements IReviewDAO {

  private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

  @Override
  public void createReview(Review review) {
    Connection connection = CONNECTION_POOL.getConnectionFromPool();
    try(PreparedStatement preparedStatement = connection.prepareStatement("Insert into User (id, rating, comment) VALUES (?, ?, ?)",
        Statement.RETURN_GENERATED_KEYS)){
      preparedStatement.setInt(1, review.getId());
      preparedStatement.setString(2, review.getComments());
      preparedStatement.setBigDecimal(3, review.getRatings());
     ;

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
  public Review getReviewById(int reviewId) throws SQLException {
    Connection connection = CONNECTION_POOL.getConnectionFromPool();

    Review review = new Review();

    Properties properties = new Properties();

    try(InputStream input = new FileInputStream("src/main/resources/db.properties")){
      properties.load(input);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }



    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM reviews WHERE ID=?");

    preparedStatement.setInt(1,reviewId);

    ResultSet resultSet = preparedStatement.executeQuery();

    while (resultSet.next()){
      review.setId(resultSet.getInt("id"));
      review.setComments(resultSet.getString("comments"));
      review.setRatings(resultSet.getBigDecimal("ratings"));
    }


    return review;
  }

  @Override
  public List<Review> getAllReviews() {
    List<Review> reviews = new ArrayList<>();

    Connection connection = CONNECTION_POOL.getConnectionFromPool();
    try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM reviews")){
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()){
        Review review = new Review();
        review.setId(resultSet.getInt("id"));
        review.setComments(resultSet.getString("comments"));
        review.setRatings(resultSet.getBigDecimal("ratings"));

        reviews.add(review);
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }finally{
      CONNECTION_POOL.releaseConnectionToPool(connection);
    }
    return reviews;
  }

  public static void main(String args[]) throws SQLException {

  }
}
