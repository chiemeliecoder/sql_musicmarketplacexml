SELECT * FROM musicmarketplace.user;
INSERT INTO user (id, username, email, password,userprofid) VALUES (3, 'JaneDoe', 'janedoe@example.com', 'password123',3);
UPDATE user SET username = 'JaneDoe' WHERE (id = 3);
DELETE FROM user WHERE (id = 3);
ALTER TABLE user ADD COLUMN age INT;
ALTER TABLE user DROP COLUMN age;
SELECT * FROM musicmarketplace.user JOIN musicmarketplace.playlists ON musicmarketplace.user.id = musicmarketplace.playlists.userid
JOIN musicmarketplace.wishlists ON musicmarketplace.user.id = musicmarketplace.wishlists.userid
JOIN musicmarketplace.purchases ON musicmarketplace.user.id = musicmarketplace.purchases.userid
JOIN musicmarketplace.reviews ON musicmarketplace.user.id = musicmarketplace.reviews.userid
JOIN musicmarketplace.tracks ON musicmarketplace.purchases.trackid = musicmarketplace.tracks.id
JOIN musicmarketplace.albums ON musicmarketplace.tracks.albumid = musicmarketplace.albums.id
JOIN musicmarketplace.artists ON musicmarketplace.albums.artistid = musicmarketplace.artists.id
JOIN musicmarketplace.artist_genre ON musicmarketplace.artists.id = musicmarketplace.artist_genre.artistid
JOIN musicmarketplace.genre ON musicmarketplace.artist_genre.genreid = musicmarketplace.genre.id
JOIN musicmarketplace.artist_achievements ON musicmarketplace.artists.id = musicmarketplace.artist_achievements.artistid;
-- Left Join
SELECT *
FROM musicmarketplace.user
LEFT JOIN playlists ON musicmarketplace.user.id= musicmarketplace.playlists.userid;

-- Self Join
SELECT *
FROM musicmarketplace.user u1
JOIN musicmarketplace.user u2 ON u1.id = u2.id;

======================================================================================
SELECT * FROM musicmarketplace.user_profile;
INSERT INTO user_profile (id, bio, profileimage, location) VALUES (1, 'John doe is into pop music', 'https://www.shutterstock.com/image-photo/profile-picture-happy-friendly-young-man-1863570355', 'Oregon');
UPDATE user SET location = 'New York' WHERE (id = 2);
DELETE FROM user_profile WHERE (id = 1);

-- Left Join
SELECT *
FROM musicmarketplace.user_profile
LEFT JOIN playlists ON musicmarketplace.user_profile.id= musicmarketplace.playlists.userid;

-- Self Join
SELECT *
FROM musicmarketplace.user_profile u1
JOIN musicmarketplace.user_profile u2 ON u1.id = u2.id;

==========================================================================================
SELECT * FROM musicmarketplace.wishlists;
INSERT INTO wishlists (id, userid, albumid) VALUES (1, 1, 1);
UPDATE wishlists SET albumid = 2 WHERE (id = 1);
DELETE FROM wishlists WHERE (id = 1);

-- Find the users who have added more than 1 albums to their wishlist
SELECT musicmarketplace.wishlists.userid, COUNT(musicmarketplace.wishlists.albumid) AS album_count
FROM musicmarketplace.wishlists
GROUP BY musicmarketplace.wishlists.userid
HAVING album_count >= 1;
==========================================================================================
SELECT * FROM musicmarketplace.tracks;
INSERT INTO tracks (id, title, duration, albumid) VALUES (1, 'Shake It Off', '00:03:39', 1);
UPDATE tracks SET title = 'Love Story', duration = '00:03:55' WHERE (id = 1);
DELETE FROM tracks WHERE (id = 1);
ALTER TABLE tracks ADD COLUMN is_featured BOOLEAN DEFAULT false;
ALTER TABLE tracks DROP COLUMN is_featured;

-- Count the number of tracks in each albums
SELECT musicmarketplace.albums.id, musicmarketplace.albums.title, COUNT(musicmarketplace.tracks.id) AS num_tracks
FROM musicmarketplace.albums
JOIN musicmarketplace.tracks ON musicmarketplace.albums.id = musicmarketplace.tracks.id
GROUP BY musicmarketplace.albums.id;

-- Calculate the total duration of tracks for each artist
SELECT musicmarketplace.artists.id, musicmarketplace.artists.name, SEC_TO_TIME(SUM(TIME_TO_SEC(musicmarketplace.tracks.duration))) AS total_duration
FROM musicmarketplace.artists
JOIN musicmarketplace.albums ON musicmarketplace.artists.id = musicmarketplace.albums.artistid
JOIN musicmarketplace.tracks ON musicmarketplace.albums.id = musicmarketplace.tracks.albumid
GROUP BY musicmarketplace.artists.id, musicmarketplace.artists.name;


-- Determine the playlists(s) with a tracks duration exceeding 4 minutes:
SELECT musicmarketplace.playlist_track.id, SEC_TO_TIME(SUM(TIME_TO_SEC(musicmarketplace.tracks.duration))) AS total_duration
FROM musicmarketplace.playlist_track
JOIN musicmarketplace.tracks ON musicmarketplace.playlist_track.idtrack = musicmarketplace.tracks.id
GROUP BY musicmarketplace.playlist_track.id
HAVING SEC_TO_TIME(SUM(TIME_TO_SEC(musicmarketplace.tracks.duration))) > TIME ('00:04:00');
===================================================================================================
SELECT * FROM musicmarketplace.albums;
INSERT INTO albums (id, title, date, artistid) VALUES (1, '1989', '2014-10-27', 1);
UPDATE albums SET title = 'Red', date = '2012-10-22' WHERE (id = 1);
DELETE FROM albums WHERE (id = 1);
ALTER TABLE albums MODIFY COLUMN title VARCHAR(100);
ALTER TABLE albums MODIFY COLUMN title VARCHAR(255);
-- Inner Join
SELECT *
FROM musicmarketplace.albums
INNER JOIN musicmarketplace.artists ON musicmarketplace.albums.artistid = musicmarketplace.artists.id;

-- Find the earliest release date for each artist
SELECT musicmarketplace.artists.id, musicmarketplace.artists.name, MIN(musicmarketplace.albums.date) AS earliest_release_date
FROM musicmarketplace.artists
JOIN musicmarketplace.albums ON  musicmarketplace.artists.id = musicmarketplace.albums.id
GROUP BY musicmarketplace.artists.id;

-- Find artists who have released 1 albums
SELECT musicmarketplace.artists.id, musicmarketplace.artists.name, COUNT(musicmarketplace.albums.id) AS num_albums
FROM musicmarketplace.artists
JOIN musicmarketplace.albums ON musicmarketplace.artists.id = musicmarketplace.albums.id
GROUP BY musicmarketplace.artists.id
HAVING COUNT(musicmarketplace.albums.id) >= 1;
================================================================================================
SELECT * FROM musicmarketplace.artist_achievements;
INSERT INTO artist_achievements (id, artistid, title, date) VALUES (1, 1, 'billbord 100', '2014-10-29');
UPDATE artist_achievements SET title = 'Grammy Award' WHERE (id = 1);


-- Determine the artists who have achieved at least 1 achievement
SELECT musicmarketplace.artists.id, musicmarketplace.artists.name, COUNT(musicmarketplace.artist_achievements.id) AS achievement_count
FROM musicmarketplace.artists
JOIN musicmarketplace.artist_achievements ON musicmarketplace.artists.id = musicmarketplace.artist_achievements.artistid
GROUP BY musicmarketplace.artists.id, musicmarketplace.artists.name
HAVING achievement_count >= 1;
================================================================================================
SELECT * FROM musicmarketplace.artist_genre;
INSERT INTO artist_genre (id, artistid, genreid) VALUES (1, 1, 1);
DELETE FROM artist_genre WHERE (id = 1);
ALTER TABLE artistgenre RENAME TO Artist_MusicGenre;

SELECT * 
FROM artist_genre
JOIN artists ON artist_genre.artistid = artists.id
JOIN genre ON artist_genre.genreid = genre.id;
================================================================================================
SELECT * FROM musicmarketplace.artists;
INSERT INTO artists (id, name) VALUES (1, 'Taylor Swift');
UPDATE artists SET name = 'Alison Swift' WHERE (id = 1);
DELETE FROM artists WHERE (id = 1);

-- Outer Join
SELECT *
FROM musicmarketplace.artists
LEFT OUTER JOIN musicmarketplace.artist_genre ON musicmarketplace.artists.id = musicmarketplace.artist_genre.artistid;
===============================================================================================
SELECT * FROM musicmarketplace.genre;
INSERT INTO genre (id, name) VALUES (1, 'Pop');
UPDATE genre SET name = 'Pop/Rock' WHERE (id = 1);
DELETE FROM genre WHERE (id = 1);
ALTER TABLE genre DROP COLUMN name;
ALTER TABLE genre ADD COLUMN name VARCHAR(255);

-- Find the total number of tracks in each genre
SELECT musicmarketplace.genre.id, musicmarketplace.genre.name, COUNT(musicmarketplace.tracks.id) AS track_count
FROM musicmarketplace.genre
LEFT JOIN musicmarketplace.artist_genre ON musicmarketplace.genre.id = musicmarketplace.artist_genre.genreid
LEFT JOIN musicmarketplace.artists ON musicmarketplace.artist_genre.artistid = musicmarketplace.artists.id
LEFT JOIN musicmarketplace.albums ON musicmarketplace.artists.id = musicmarketplace.albums.id
LEFT JOIN musicmarketplace.tracks ON musicmarketplace.albums.id = musicmarketplace.tracks.albumid
GROUP BY musicmarketplace.genre.id, musicmarketplace.genre.name;
===============================================================================================
SELECT * FROM musicmarketplace.playlist_track;
INSERT INTO playlist_track (id, idplaylist, idtrack) VALUES (1, 1, 1);
DELETE FROM playlist_track WHERE (id = 1);

-- Retrieve the playlists with distinct tracks
SELECT musicmarketplace.playlist_track.id, COUNT(DISTINCT musicmarketplace.playlist_track.idtrack) AS distinct_track_count
FROM musicmarketplace.playlist_track
GROUP BY musicmarketplace.playlist_track.id
HAVING COUNT(DISTINCT musicmarketplace.playlist_track.idtrack) > 0;
================================================================================================
SELECT * FROM musicmarketplace.playlists;
INSERT INTO playlists (id, name, userid) VALUES (1, 'My Favorites', 1);
UPDATE playlists SET name = 'Big Hits' WHERE (id = 1);
DELETE FROM playlists WHERE (id = 1);
-- Right Join
SELECT *
FROM musicmarketplace.playlists
RIGHT JOIN musicmarketplace.tracks ON musicmarketplace.playlists.id = musicmarketplace.tracks.id;

-- Determine the number of playlists created by each user:
SELECT musicmarketplace.playlists.userid, COUNT(musicmarketplace.playlists.id) AS playlist_count
FROM musicmarketplace.playlists
GROUP BY musicmarketplace.playlists.userid;
================================================================================================
SELECT * FROM musicmarketplace.purchases;
INSERT INTO purchases (id, userid, trackid, date, price) VALUES (1, 1, 1, '2023-05-25', 9.99);
UPDATE purchases SET price = 7.99 WHERE (id = 1);
DELETE FROM purchases WHERE (id = 1);

-- Calculate the total price of purchases for each user
SELECT userid, SUM(musicmarketplace.purchases.price) AS total_price
FROM musicmarketplace.purchases
GROUP BY musicmarketplace.purchases.userid;

-- Find users who have made at most 3 purchases with a total price less than $100
SELECT musicmarketplace.purchases.userid, COUNT(musicmarketplace.purchases.id) AS num_purchases, SUM(musicmarketplace.purchases.price) AS total_price
FROM musicmarketplace.purchases
GROUP BY musicmarketplace.purchases.userid
HAVING COUNT(musicmarketplace.purchases.id) <= 3 AND SUM(musicmarketplace.purchases.price) < 100;
================================================================================================
SELECT * FROM musicmarketplace.reviews;
INSERT INTO reviews (id, userid, albumid, rating, comment) VALUES (1, 1, 1, 5.0, 'Great albums!');
UPDATE reviews SET rating = 4.0 WHERE (id = 1);
DELETE FROM reviews WHERE (id = 1);

-- Find genres with an average rating greater than 3.5
SELECT musicmarketplace.genre.id, musicmarketplace.genre.name, AVG(musicmarketplace.reviews.rating) AS avg_rating
FROM musicmarketplace.genre
JOIN musicmarketplace.artist_genre ON musicmarketplace.genre.id = musicmarketplace.artist_genre.genreid
JOIN musicmarketplace.artists ON musicmarketplace.artist_genre.artistid = musicmarketplace.artists.id
JOIN musicmarketplace.albums ON musicmarketplace.artists.id = musicmarketplace.albums.artistid
JOIN musicmarketplace.reviews ON musicmarketplace.albums.id = musicmarketplace.reviews.albumid
GROUP BY musicmarketplace.genre.id, musicmarketplace.genre.name
HAVING AVG(musicmarketplace.reviews.rating) > 3.5;

-- Calculate the highest and lowest rating for each albums
SELECT musicmarketplace.reviews.albumid, MAX(musicmarketplace.reviews.rating) AS highest_rating, MIN(musicmarketplace.reviews.rating) AS lowest_rating
FROM musicmarketplace.reviews
GROUP BY musicmarketplace.reviews.albumid;
