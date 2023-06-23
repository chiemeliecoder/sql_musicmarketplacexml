package com.laba.solvd.databases.configurations;

//import com.sun.jdi.connect.spi.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import com.laba.solvd.databases.configurations.Config;

public class ConnectionPool {

  private static final ConnectionPool INSTANCE = new ConnectionPool();
  private List<Connection> connectionPool;


  public ConnectionPool() {
    try {
      Class.forName(Config.DRIVER.getValue());
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }



    this.connectionPool = new ArrayList<>(Integer.parseInt(Config.POOLSIZE.getValue()));

    IntStream.range(0, Integer.parseInt(Config.POOLSIZE.getValue()))
        .boxed()
        .forEach(index -> connectionPool.add(createConnection()));
  }



  private Connection createConnection() {
    Connection connection;
    try {
      connection = DriverManager
          .getConnection(Config.URL.getValue(), Config.USERNAME.getValue(), Config.PASSWORD.getValue());
    } catch (SQLException e) {
      throw new RuntimeException("no connection made", e);
    }
    return connection;
  }


  public synchronized Connection getConnectionFromPool() {
    Connection connection = null;
    if(connectionPool.isEmpty()){
      try{
        while(connectionPool.isEmpty()){
          connectionPool.wait();
        }
      }catch (InterruptedException e){
        throw new RuntimeException("unable to connect", e);
      }
    }else{
      connection = connectionPool.remove(connectionPool.size() - 1);
    }
    return connection;
  }

  public synchronized void releaseConnectionToPool(Connection connection) {
    connectionPool.add(connection);
  }

  public static ConnectionPool getInstance() {
    return ConnectionPool.INSTANCE;
  }





}
