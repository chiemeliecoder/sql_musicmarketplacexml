package com.laba.solvd.databases.hiddenfiles;

import com.laba.solvd.databases.configurations.ConnectionPool;
import com.laba.solvd.databases.interfacedao.IGenericDAO;
import com.laba.solvd.databases.model.Playlist;
import com.laba.solvd.databases.model.Purchase;
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

public class PurchaseDAO implements IGenericDAO<Purchase> {

  private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

  @Override
  public Purchase getById(int id) throws SQLException {
    Connection connection = CONNECTION_POOL.getConnectionFromPool();

    Purchase purchase = new Purchase();

    Properties properties = new Properties();

    try(InputStream input = new FileInputStream("src/main/resources/db.properties")){
      properties.load(input);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }



    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM purchases WHERE ID=?");

    preparedStatement.setInt(1,id);

    ResultSet resultSet = preparedStatement.executeQuery();

    while (resultSet.next()){
      purchase.setId(resultSet.getInt("id"));
    }


    return purchase;
  }


  @Override
  public void create(Purchase entity) {
    Connection connection = CONNECTION_POOL.getConnectionFromPool();
    try(PreparedStatement preparedStatement = connection.prepareStatement("Insert into purchases (id, date, price) VALUES (?, ?, ?)",
        Statement.RETURN_GENERATED_KEYS)){
      preparedStatement.setInt(1, entity.getId());
      preparedStatement.setDate(2, entity.getPurchaseDate());
      preparedStatement.setBigDecimal(3, entity.getPrice());

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
  public List<Purchase> getAll() {
    List<Purchase> purchases = new ArrayList<>();

    Connection connection = CONNECTION_POOL.getConnectionFromPool();
    try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM purchases")){
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()){
        Purchase purchase = new Purchase();
        purchase.setId(resultSet.getInt("id"));
        purchase.setPurchaseDate(resultSet.getDate("date"));
        purchase.setPrice(resultSet.getBigDecimal("price"));

        purchases.add(purchase);
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }finally{
      CONNECTION_POOL.releaseConnectionToPool(connection);
    }
    return purchases;
  }



  /**
   * Retrieve an object that was previously persisted to the database using
   *
   * @param id
   */
  @Override
  public Purchase read(int id) {
    return null;
  }



  @Override
  public void update(Purchase entity) {

  }


  @Override
  public void delete(int id) {

  }

  public static void main(String args[]) throws SQLException {

  }
}
