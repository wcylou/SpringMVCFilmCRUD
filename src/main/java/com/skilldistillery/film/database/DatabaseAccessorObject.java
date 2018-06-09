package com.skilldistillery.film.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;
import com.skilldistillery.film.entities.FilmInventory;
import com.skilldistillery.film.entities.Language;

@Component
public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid";
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error loading mySql driver");
            e.printStackTrace();
        }
    }

	@Override
	public List<Film> getFilmBySearchTerm(String searchTerm) throws SQLException {
		List<Film> films = new ArrayList<>();
		Connection conn = DriverManager.getConnection(URL, "student", "student");
		String sqltext = "SELECT id, title, release_year, rating, description FROM film WHERE title like ?";
		PreparedStatement stmt = conn.prepareStatement(sqltext);
		stmt.setString(1, "%" + searchTerm + "%");
		ResultSet searchResult = stmt.executeQuery();
		while (searchResult.next()) {
			Actor actor = new Actor();
			int id = searchResult.getInt(1);
			String title = searchResult.getString(2);
			int releaseYear = searchResult.getInt(3);
			String rating = searchResult.getString(4);
			String description = searchResult.getString(5);
			Language language = getLanguageOfFilm(id);
			List<Actor> actors = getActorsByFilmId(id);
			StringBuilder actorList = actor.actorsListed(actors);

			Film film = new Film(title, releaseYear, description, rating, language, actors);

			films.add(film);
		}
		searchResult.close();
		stmt.close();
		conn.close();
		return films;
	}

	@Override
	// title, year, rating, and description are displayed when this is returned
	public List<Film> getFilmById(int filmId) throws SQLException {
		List<Film> films = new ArrayList<>();
		Film filmFull = null;
		Connection conn = DriverManager.getConnection(URL, "student", "student");
		String sql = "SELECT id, title, release_year, rating, description, language_id, rental_duration,"
				+ "rental_rate, length, replacement_cost, special_features FROM film WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet filmResult = stmt.executeQuery();
		if (filmResult.next()) {
			// Here is our mapping of query columns to our object fields:
			// Actor actor = new Actor();
			int id = filmResult.getInt(1);
			String title = filmResult.getString(2);
			int releaseYear = filmResult.getInt(3);
			String rating = filmResult.getString(4);
			String description = filmResult.getString(5);
			int languageId = filmResult.getInt(6);
			int rentalDuration = filmResult.getInt(7);
			double rentalRate = filmResult.getDouble(8);
			int length = filmResult.getInt(9);
			double replacementCost = filmResult.getDouble(10);
			String specialFeatures = filmResult.getString(11);
			Language language = getLanguageOfFilm(id);
			List<Actor> actors = getActorsByFilmId(id);
			// StringBuilder actorList = actor.actorsListed(actors);
			filmFull = new Film(id, title, description, releaseYear, languageId, rentalDuration, rentalRate, length,
					replacementCost, rating, specialFeatures, language, actors);
			films.add(filmFull);
		}
		filmResult.close();
		stmt.close();
		conn.close();
		return films;
	}
	
	@Override
	public Actor getActorById(int actorId) throws SQLException {
		Actor actor = null;
		Connection conn = DriverManager.getConnection(URL, "student", "student");
		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);
		ResultSet actorResult = stmt.executeQuery();
		if (actorResult.next()) {
			actor = new Actor(); // Create the object
			actor.setId(actorResult.getInt(1));
			actor.setFirstName(actorResult.getString(2));
			actor.setLastName(actorResult.getString(3));
		}
		actorResult.close();
		stmt.close();
		conn.close();
		return actor;
	}

	@Override
	public List<Actor> getActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, "student", "student");
			String sql = "SELECT a.id, a.first_name, a.last_name FROM film f JOIN film_actor"
					+ " ON film_actor.film_id = f.id JOIN actor a ON film_actor.actor_id = a.id" + " WHERE film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int actorId = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);

				Actor actor = new Actor(actorId, firstName, lastName);
				actors.add(actor);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}

	@Override
	public Language getLanguageOfFilm(int filmId) throws SQLException {
		Language language = null;
		Connection conn = DriverManager.getConnection(URL, "student", "student");
		String sql = "SELECT l.name FROM film f JOIN language l on l.id = f.language_id WHERE f.id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			language = new Language();
			language.setLanguage(rs.getString(1));
		}
		rs.close();
		stmt.close();
		conn.close();
		return language;
	}

	@Override
	public Film getAllFilmDetails(int filmId) throws SQLException {
		Film film = null;
		Connection conn = DriverManager.getConnection(URL, "student", "student");
		String sql = "SELECT id, title, description, release_year, language_id, rental_duration, rental_rate, "
				+ "length, replacement_cost, rating, special_features FROM film WHERE film_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet filmDetails = stmt.executeQuery();
		if (filmDetails.next()) {
			int id = filmDetails.getInt(1);
			String title = filmDetails.getString(2);
			String description = filmDetails.getString(3);
			int releaseYear = filmDetails.getInt(4);
			int languageId = filmDetails.getInt(5);
			int rentalDuration = filmDetails.getInt(6);
			double rentalRate = filmDetails.getDouble(7);
			int length = filmDetails.getInt(8);
			double replacementCost = filmDetails.getDouble(9);
			String rating = filmDetails.getString(10);
			String specialFeatures = filmDetails.getString(11);
			film = new Film(id, title, description, releaseYear, languageId, rentalDuration, rentalRate, length,
					replacementCost, rating, specialFeatures); // Create the object
		}
		filmDetails.close();
		stmt.close();
		conn.close();
		return film;
	}

	@Override
	public Film getFilmCategories(int filmId) throws SQLException {
		Film film = null;
		Connection conn = DriverManager.getConnection(URL, "student", "student");
		String sql = " SELECT cat.name FROM film f JOIN film_category fc ON fc.film_id = f.id JOIN category cat ON fc.category_id = cat.id WHERE f.id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet filmCategories = stmt.executeQuery();
		if (filmCategories.next()) {
			String categories = filmCategories.getString(1);
			film = new Film(categories);
		}
		filmCategories.close();
		stmt.close();
		conn.close();
		return film;
	}

	@Override
	public List<FilmInventory> getFilmInventory(int filmIdNum) throws SQLException {
		List<FilmInventory> filmInventory = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, "student", "student");
			String sql = "SELECT id, film_id, store_id, media_condition, last_update from inventory_item where film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmIdNum);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				int filmId = rs.getInt(2);
				int storeId = rs.getInt(3);
				String mediaCondition = rs.getString(4);
				String lastUpdate = rs.getString(5);

				FilmInventory fi = new FilmInventory(id, filmId, storeId, mediaCondition, lastUpdate);
				filmInventory.add(fi);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filmInventory;
	}

	public Film addFilm(Film film) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, "student", "student");
			conn.setAutoCommit(false); // START TRANSACTION

			String sql = "INSERT INTO film (title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features) "
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			stmt.setInt(3, film.getReleaseYear());
			stmt.setInt(4, film.getLanguageId());
			stmt.setInt(5, film.getRentalDuration());
			stmt.setDouble(6, film.getRentalRate());
			stmt.setInt(7, film.getLength());
			stmt.setDouble(8, film.getReplacementCost());
			stmt.setString(9, film.getRating());
			stmt.setString(10, film.getSpecialFeatures());
			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					int newFilmId = keys.getInt(1);
					film.setId(newFilmId);

				}
				System.out.println("Your film's ID is " + film.getId());
			} else {
				film = null;
			}
			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error inserting actor " + film);
		}
		return film;
	}

	@Override
	public boolean deleteFilm(Film film) throws SQLException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, "student", "student");
			conn.setAutoCommit(false); // START TRANSACTION
			
			// delete the child from film_actor
			String sql = "DELETE FROM film_actor WHERE film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, film.getId());
			int updateCount = stmt.executeUpdate();
			
			// delete the child from film_category
			sql = "DELETE FROM film_category WHERE film_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, film.getId());
			updateCount = stmt.executeUpdate();
			
			// delete the child from rental through inventory_item
			sql = "DELETE r FROM rental r JOIN inventory_item i ON i.id = r.inventory_id WHERE i.film_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, film.getId());
			updateCount = stmt.executeUpdate();
			
			// delete the child from inventory_item
			sql = "DELETE FROM inventory_item WHERE film_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, film.getId());
			updateCount = stmt.executeUpdate();
			
			// finally, delete the film from the parent class of "film"
			sql = "DELETE FROM film WHERE id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, film.getId());
			updateCount = stmt.executeUpdate();
			conn.commit(); // COMMIT TRANSACTION
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}

}
