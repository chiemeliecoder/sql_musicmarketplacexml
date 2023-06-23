package com.laba.solvd.databases.interfacedao;

import com.laba.solvd.databases.model.Album;
import java.sql.SQLException;
import java.util.List;

public interface IGenericDAO<T>  {

  T getById(int id) throws SQLException;
  void create(T entity);

  /** Retrieve an object that was previously persisted to the database using
   */
  T read(int id);
  void update(T entity);
  void delete(int id);
  List<T> getAll();

}
