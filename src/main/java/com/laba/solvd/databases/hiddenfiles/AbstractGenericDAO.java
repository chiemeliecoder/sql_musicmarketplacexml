package com.laba.solvd.databases.hiddenfiles;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

public abstract class AbstractGenericDAO<T, ID>  {

  protected DataSource dataSource;

  public AbstractGenericDAO(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  protected Connection getConnection() throws SQLException {

    return dataSource.getConnection();
  }

  public abstract void create(T entity);
  public abstract T read(ID id);
  public abstract void update(T entity);
  public abstract void delete(ID id);

}
