package com.laba.solvd.databases.hiddenfiles;


import com.laba.solvd.databases.interfacedao.IGenericDAO;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

public class GenericDAO<T, ID> extends AbstractGenericDAO<T, ID> implements IGenericDAO<T> {

  public GenericDAO(DataSource dataSource) {
    super(dataSource);
  }

  @Override
  public T getById(int id) throws SQLException {
    return null;
  }

  @Override
  public void create(T entity) {

  }

  /**
   * Retrieve an object that was previously persisted to the database using
   *
   * @param id
   */
  @Override
  public T read(int id) {
    return null;
  }

  @Override
  public T read(ID id) {
    return null;
  }

  @Override
  public void update(T entity) {

  }

  @Override
  public void delete(int id) {

  }

  @Override
  public void delete(ID id) {

  }

  @Override
  public List<T> getAll() {
    return null;
  }
}
