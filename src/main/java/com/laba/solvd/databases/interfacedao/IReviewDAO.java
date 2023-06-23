package com.laba.solvd.databases.interfacedao;

import com.laba.solvd.databases.model.Review;
import java.sql.SQLException;
import java.util.List;

public interface IReviewDAO {

  void createReview(Review review);
  Review getReviewById(int reviewId) throws SQLException;
  List<Review> getAllReviews();
}
