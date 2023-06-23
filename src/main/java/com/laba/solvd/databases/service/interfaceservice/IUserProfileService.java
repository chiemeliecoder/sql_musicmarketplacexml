package com.laba.solvd.databases.service.interfaceservice;

import com.laba.solvd.databases.model.User;
import com.laba.solvd.databases.model.UserProfile;
import java.util.List;

public interface IUserProfileService {

  UserProfile create( UserProfile entity);
  List<UserProfile> getAll();

}
