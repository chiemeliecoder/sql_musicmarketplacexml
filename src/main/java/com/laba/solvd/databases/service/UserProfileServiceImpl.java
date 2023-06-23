package com.laba.solvd.databases.service;

import com.laba.solvd.databases.dao.UserProfileDAO;
import com.laba.solvd.databases.interfacedao.IUserProfileDAO;
import com.laba.solvd.databases.model.UserProfile;
import com.laba.solvd.databases.service.interfaceservice.IUserProfileService;
import java.util.List;

public class UserProfileServiceImpl implements IUserProfileService {

  private IUserProfileDAO userProfileDAO;

  public UserProfileServiceImpl() {
    //the interface IUserDAO is assigned to the UserProfileDAO class where it is implemented
    this.userProfileDAO = new UserProfileDAO();
  }

  @Override
  public UserProfile create(UserProfile entity) {
    userProfileDAO.createUser(entity);

    return entity;
  }

  @Override
  public List<UserProfile> getAll() {
    return userProfileDAO.getAllUsersProfiles();
  }
}
