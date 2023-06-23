package com.laba.solvd.databases;

import com.laba.solvd.databases.dao.UserDAO;
import com.laba.solvd.databases.interfacedao.IUserDAO;
import com.laba.solvd.databases.model.Album;
import com.laba.solvd.databases.model.ArtistAchievement;
import com.laba.solvd.databases.model.Artists;
import com.laba.solvd.databases.model.Genre;
import com.laba.solvd.databases.model.Playlist;
import com.laba.solvd.databases.model.Purchase;
import com.laba.solvd.databases.model.Review;
import com.laba.solvd.databases.model.Track;
import com.laba.solvd.databases.model.User;
import com.laba.solvd.databases.model.UserProfile;
import com.laba.solvd.databases.model.Wishlist;
import com.laba.solvd.databases.service.AlbumServiceImpl;
import com.laba.solvd.databases.service.ArtistServiceImpl;
import com.laba.solvd.databases.service.IMusicService;
import com.laba.solvd.databases.service.MusicService;
import com.laba.solvd.databases.service.PlaylistServiceImpl;
import com.laba.solvd.databases.service.TrackImpl;
import com.laba.solvd.databases.service.UserProfileServiceImpl;
import com.laba.solvd.databases.service.UserServiceImpl;
import com.laba.solvd.databases.service.WishlistImpl;
import com.laba.solvd.databases.service.interfaceservice.IAlbumService;
import com.laba.solvd.databases.service.interfaceservice.IArtistService;
import com.laba.solvd.databases.service.interfaceservice.IPlaylistService;
import com.laba.solvd.databases.service.interfaceservice.ITrackService;
import com.laba.solvd.databases.service.interfaceservice.IUserProfileService;
import com.laba.solvd.databases.service.interfaceservice.IUserService;
import com.laba.solvd.databases.service.interfaceservice.IWishlistService;
import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;

public class Main {
  private static final Logger logger = Logger.getLogger(Main.class);

  public static void main(String args[]){

    // Create instances of dependencies
    IPlaylistService playlistService = new PlaylistServiceImpl();

    // Create an instance of UserServiceImpl
    IUserService userService = new UserServiceImpl();

    IArtistService artistService = new ArtistServiceImpl();

    IWishlistService wishlistService = new WishlistImpl();

    IAlbumService albumService = new AlbumServiceImpl();

    IUserProfileService userProfileService = new UserProfileServiceImpl();

    ITrackService trackService = new TrackImpl();

    // Create an instance of MusicService
    MusicService musicService = new MusicService();

    UserProfile userProfile = new UserProfile();

    UserDAO userDAO = new UserDAO();

    Calendar cal = Calendar.getInstance();
    cal.set(2023, Calendar.MARCH, 28);
    Date date = cal.getTime();
    java.sql.Date d = new java.sql.Date(date.getTime());

    Time time = Time.valueOf(LocalTime.of(03,01,00));

    BigDecimal p = BigDecimal.valueOf(9.99);
    BigDecimal r = BigDecimal.valueOf(4.5);



    List<ArtistAchievement> artistAchievementList = new ArrayList<>();
    ArtistAchievement artistAchievement = new ArtistAchievement();
    artistAchievement.setTitle("Best Video Game Music 2023");
    artistAchievement.setAwardDate(d);
    artistAchievementList.add(artistAchievement);
    //==============================================================
    List<Genre> genreList = new ArrayList<>();
    Genre genre = new Genre();
    genre.setGenreName("Jpop");
    genreList.add(genre);
    //==============================================================
    List<Review> reviewList = new ArrayList<>();
    Review review = new Review();
    review.setRatings(r);
    review.setComments("Best final fantasy song");
    reviewList.add(review);
    //==============================================================
    List<Purchase> purchaseList = new ArrayList<>();
    Purchase purchase = new Purchase();
    purchase.setPrice(p);
    purchase.setPurchaseDate(d);
    purchaseList.add(purchase);
    //==============================================================
    List<Track> trackList = new ArrayList<>();
    Track track = new Track();
    track.setTitle("Tsuki wo mieta");
    track.setDuration(time);
    track.setPurchase(purchaseList);
    trackList.add(track);
    //==============================================================
    List<Album> albumList = new ArrayList<>();
    Album album = new Album();
    album.setAlbumName("Tsuki wo mieta");
    album.setAlbumDate(d);
    album.setTracks(trackList);
    album.setReviews(reviewList);
    albumList.add(album);
    //==============================================================
    List<Playlist> playlistList = new ArrayList<>();
    Playlist playlist = new Playlist();
    playlist.setPlaylistName("Game music playlist");
    playlist.setTracks(trackList);
    playlistList.add(playlist);
    //==============================================================
    List<Wishlist> wishlistList = new ArrayList<>();
    Wishlist wishlist = new Wishlist();
    wishlist.setName("final fantasy");
    wishlist.setAlbumList(albumList);
    wishlistList.add(wishlist);


    // Call the methods of MusicService
    musicService.performOperation();


    Album firstAlbum = new Album();
    firstAlbum.setAlbumName("Tsuki wo mieta");
    firstAlbum.setAlbumDate(d);
    firstAlbum.setTracks(trackList);
    firstAlbum.setReviews(reviewList);

    Artists firstArtist = new Artists();
    firstArtist.setArtistName("Kenshi Yonezu");
    firstArtist.setAlbums(albumList);
    firstArtist.setGenres(genreList);
    firstArtist.setAchievements(artistAchievementList);

    Track firstTrack = new Track();
    firstTrack.setTitle("Tsuki wo mieta");
    firstTrack.setDuration(time);
    firstTrack.setPurchase(purchaseList);

    Playlist firstPlaylist = new Playlist();
    firstPlaylist.setPlaylistName("Game music playlist");
    firstPlaylist.setTracks(trackList);


    Wishlist firstWishlist = new Wishlist();
    firstWishlist.setName("final fantasy");
    firstWishlist.setAlbumList(albumList);



    UserProfile firstUserProfile = new UserProfile();
    firstUserProfile.setBio("I like video games");
    firstUserProfile.setProfileimage("https://cdn.shopify.com/s/files/1/0416/8083/0620/files/ecomm-CHGAL-Core2021_367x353px_07-CN_1000x.png?v=1614324462");
    firstUserProfile.setLocation("Belarus");


    User firstUser = new User();
    firstUser.setName("Jen Alero");
    firstUser.setEmail("jen@example.com");
    firstUser.setPassword("password007");
    firstUser.setUserProfile(firstUserProfile);
    firstUser.setReviewsList(reviewList);
    firstUser.setWishlistsList(wishlistList);
    firstUser.setPurchasesList(purchaseList);
    firstUser.setPlaylistsList(playlistList);



    // Call the create method
//    firstUser = userService.create(firstUser);
//    System.out.println("Created user: " + firstUser);
//
//    firstArtist = artistService.createArt(firstArtist);
//    System.out.println("Created artist: " + firstArtist);
//
//    firstAlbum = albumService.create(firstAlbum);
//    System.out.println("Created album: " + firstAlbum);
//
//    firstPlaylist = playlistService.create(firstPlaylist);
//    System.out.println("Created playlist: " + firstPlaylist);
//
//    firstTrack = trackService.create(firstTrack);
//    System.out.println("Created track: " + firstTrack);
//
//    firstUserProfile = userProfileService.create(firstUserProfile);
//    System.out.println("Created user profile: " + firstUserProfile);
//
//    firstWishlist = wishlistService.create(firstWishlist);
//    System.out.println("Created Wishlist: " + firstWishlist);



    //call the list method

    List<Artists> allArtists = artistService.getAllArtists();
    System.out.println("All artists: " + allArtists);

    List<Album> allAlbums = albumService.getAll();
    System.out.println("All albums: " + allAlbums);

    List<Playlist> allPlaylist = playlistService.getAll();
    System.out.println("All Playlist: " + allPlaylist);

    List<Track> allTrack = trackService.getAll();
    System.out.println("All Track: " + allTrack);

    List<Wishlist> allWishlist = wishlistService.getAll();
    System.out.println("All Wishlist: " + allWishlist);

    List<UserProfile> allUserProfile = userProfileService.getAll();
    System.out.println("All UserProfile: " + allUserProfile);

    List<User> allUser = userService.getAllUsers();
    System.out.println("All User: " + allUser);

    int userCount = musicService.countUsers();
    System.out.println("Number of users: " + userCount);

    int artistCount = musicService.countArtists();
    System.out.println("Total number of artists: " + artistCount);

    int albumCount = musicService.countAlbums();
    System.out.println("Total number of albums: " + albumCount);

    int playlistCount = musicService.countPlaylists();
    System.out.println("Total number of playlists: " + playlistCount);

    int trackCount = musicService.countTracks();
    System.out.println("Total number of tracks: " + trackCount);

    int wishlistCount = musicService.countWishlists();
    System.out.println("Total number of wishlists: " + wishlistCount);




//    Purchase firstPurchase = new Purchase();
//    firstPurchase.setPurchaseDate(d);
//
//    Review firstReview = new Review();
//    firstReview.setComments("Wonderful comeback");
//
//    int maxId = userDAO.getMaxUserId();
//
//    // Create a new user
//    User newUser = new User();
//    newUser.setId(maxId + 1);
//    newUser.setName("hannah Doe");
//    newUser.setEmail("hannah@example.com");
//    newUser.setPassword("password009");
//
//
//
//    // Call the retrieveUserProfileById method
//    int userProfileId = 2;
//    UserProfile userProfiles = userDAO.retrieveUserProfileById(userProfileId);
//
//    userProfile.setId(2);
//    userProfile.setBio("I like cute anime music");
//    userProfile.setProfileimage("https://cdn.shopify.com/s/files/1/0416/8083/0620/files/ecomm-CHGAL-Core2021_367x353px_07-CN_1000x.png?v=1614324462");
//    userProfile.setLocation("London");
//    System.out.println("Retrieved user profile: " + userProfiles);
//
//    newUser.setUserProfile(userProfiles);
//
//
//
//
//
//
//
//    // Get all users
//    List<User> allUsers = userService.getAllUsers();
//    for (User user : allUsers) {
//      if (user.getUserProfile() == null) {
//        UserProfile userProf = new UserProfile();
//        userProfile.setId(0); // Set the appropriate ID
//        userProfile.setBio(""); // Set the appropriate bio
//        userProfile.setProfileimage(""); // Set the appropriate profile image
//        userProfile.setLocation(""); // Set the appropriate location
//        user.setUserProfile(userProf);
//      }
//    }
//    System.out.println("All users: " + allUsers);
//
//    int maxArtId = artistService.getMaxArtistId();
//    Artists newArtist = new Artists();
//    newArtist.setId(maxArtId + 1);
//    newArtist.setArtistName("E ve");
//
//    Album album = new Album();
//    album.setId(2);
//    newArtist.setAlbum(album.getId());

    logger.info("end of information");



  }

}
