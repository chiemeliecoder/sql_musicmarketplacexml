package com.laba.solvd.databases.service.interfaceservice;

import com.laba.solvd.databases.model.User;
import java.util.List;

public interface IUserService {

  User create( User entity);
  List<User> getAllUsers();

}
