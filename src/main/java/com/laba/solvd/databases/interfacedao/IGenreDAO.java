package com.laba.solvd.databases.interfacedao;

import com.laba.solvd.databases.model.Genre;
import java.sql.SQLException;
import java.util.List;

public interface IGenreDAO {
  void createGenre(Genre genre);
  Genre getGenreById(int id) throws SQLException;
  List<Genre> getAllGenres();

}
