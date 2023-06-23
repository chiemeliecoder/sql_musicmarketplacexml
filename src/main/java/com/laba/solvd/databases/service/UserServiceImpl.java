package com.laba.solvd.databases.service;

import com.laba.solvd.databases.dao.UserDAO;
import com.laba.solvd.databases.interfacedao.IGenericDAO;
import com.laba.solvd.databases.interfacedao.IUserDAO;
import com.laba.solvd.databases.interfacedao.IUserProfileDAO;
import com.laba.solvd.databases.model.Playlist;
import com.laba.solvd.databases.model.User;
import com.laba.solvd.databases.model.UserProfile;
import com.laba.solvd.databases.service.interfaceservice.IPlaylistService;
import com.laba.solvd.databases.service.interfaceservice.IUserProfileService;
import com.laba.solvd.databases.service.interfaceservice.IUserService;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements IUserService {

  private final IUserDAO userDAO;
  private final IPlaylistService playlistService;
  private final IUserProfileService userProfileService;

  public UserServiceImpl() {
    this.userDAO = new UserDAO();
    this.playlistService = new PlaylistServiceImpl();
    this.userProfileService = new UserProfileServiceImpl();
  }



  @Override
  public User create(User entity) {
    if (entity == null) {
      throw new IllegalArgumentException("User entity cannot be null");
    }

    // Validate user input
    if (entity.getName() == null || entity.getName().isEmpty()) {
      throw new IllegalArgumentException("User name cannot be empty");
    }


    entity.setId(null);
    userDAO.createUser(entity);


    if(entity.getPlaylistsList() != null){
      List<Playlist> playlists = entity.getPlaylistsList().stream()
          .map(playlist -> playlistService.create(playlist)).collect(
          Collectors.toList());
      entity.setPlaylistsList(playlists);
    }

    if(entity.getUserProfile() != null){
      UserProfile userProfile = userProfileService.create(entity.getUserProfile());
      entity.setUserProfile(userProfile);
    }

    return entity;
  }




  @Override
  public List<User> getAllUsers() {

    return userDAO.getAllUsers();
  }
}
