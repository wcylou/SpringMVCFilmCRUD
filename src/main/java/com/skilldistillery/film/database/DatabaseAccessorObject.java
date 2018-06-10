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
import com.skilldistillery.film.entities.Inventory;
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
	public List<Film> getFilmBySearchTerm(String searchTerm) {
		List<Film> films = new ArrayList<>();
		String sql = "select f.id, f.title, f.description, f.release_year, f.language_id, f.rental_duration, f.rental_rate, f.length, f.replacement_cost, f.rating, f.special_features, l.name from film f join language l on l.id = f.language_id where title like ? or description like ?";
		int count = 0;

		try (Connection conn = DriverManager.getConnection(URL, "student", "student");
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, "%" + searchTerm + "%");
			stmt.setString(2, "%" + searchTerm + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Film film = new Film();
				film.setId(rs.getInt(1));
				film.setTitle(rs.getString(2));
				film.setDescription(rs.getString(3));
				film.setReleaseYear(rs.getInt(4));
				film.setLanguageId(rs.getInt(5));
				film.setRentalDuration(rs.getInt(6));
				film.setRentalRate(rs.getDouble(7));
				film.setLength(rs.getInt(8));
				film.setReplacementCost(rs.getDouble(9));
				film.setRating(rs.getString(10));
				film.setSpecialFeatures(rs.getString(11));
				film.setActors(getActorsByFilmId(rs.getInt(1)));
				films.addAll(getFilmById(rs.getInt(1)));
				count++;
			}
			rs.close();
			stmt.close();
			conn.close();
			System.out.println(
					"\nThere are " + count + " films matching " + searchTerm + " in the title and/or description.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return films;
	}

	@Override
	// title, year, rating, and description are displayed when this is returned
	public List<Film> getFilmById(int filmId) {
		List<Film> films = new ArrayList<>();
		Film filmFull = null;
		try {
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
				List<String> categories = getCategoriesByFilm(id);
				List<Inventory> inventory = getInventoryFilms(id);
				// StringBuilder actorList = actor.actorsListed(actors);
				filmFull = new Film(id, title, description, releaseYear, languageId, rentalDuration, rentalRate, length,
						replacementCost, rating, specialFeatures, actors, language, categories, inventory);
				films.add(filmFull);
			}
			filmResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	@Override
	public Actor getActorById(int actorId) {
		Actor actor = null;
		try {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	public Language getLanguageOfFilm(int filmId) {
		Language language = null;
		try {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return language;
	}

	@Override
	public Film getAllFilmDetails(int filmId) {
		Film film = null;
		try {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public List<String> getCategoriesByFilm(int filmId) {
		String sql = "select c.name from film_category fc join category c on c.id = fc.category_id where fc.film_id = ?";
		List<String> categories = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(URL, "student", "student");
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				categories.add(rs.getString(1));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}

	@Override
	public List<Inventory> getInventoryFilms(int filmId) {
		String sql = "select i.id, i.media_condition from film f join inventory_item i on i.film_id = f.id where f.id = ?";
		List<Inventory> inventoryItems = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(URL, "student", "student");
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int iD = rs.getInt(1);
				String mediaCondition = rs.getString(2);
				Inventory inventory = new Inventory(iD, mediaCondition);
				inventoryItems.add(inventory);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inventoryItems;
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

	public Film updateFilm(Film film, int filmId) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, "student", "student");
			conn.setAutoCommit(false); // START TRANSACTION
			film.setId(filmId);
			String sql = "UPDATE film SET title=?, description=?, release_year=?, language_id=?, rental_duration=?, rental_rate=?, length=?, replacement_cost=?"
					+ ", rating=?, special_features=? WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
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
			stmt.setInt(11, filmId);
			System.out.println(stmt);
			int updateCount = stmt.executeUpdate();
			film.setActors(getActorsByFilmId(filmId));
			film.setCategories(getCategoriesByFilm(filmId));
			film.setInventoryItems(getInventoryFilms(filmId));
			System.out.println(updateCount);
			System.out.println(film);
			System.out.println("Your film's ID is " + filmId);

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

	public Actor updateActor(Actor actor, int actorId) {
		Connection conn = null;
		try {
			actor.setId(actorId);
			conn = DriverManager.getConnection(URL, "student", "student");
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "UPDATE actor SET first_name=?, last_name=? WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, actor.getFirstName());
			stmt.setString(2, actor.getLastName());
			stmt.setInt(3, actor.getId());
			System.out.println(stmt);
			int updateCount = stmt.executeUpdate();

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
			throw new RuntimeException("Error inserting actor " + actor);
		}
		return actor;
	}

	@Override
	public boolean deleteFilm(Film film) {
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

	@Override
	public Actor addActor(Actor actor) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, "student", "student");
			conn.setAutoCommit(false); // START TRANSACTION

			String sql = "INSERT INTO actor (first_name, last_name) VALUES (?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, actor.getFirstName());
			stmt.setString(2, actor.getLastName());
			int updateCount = stmt.executeUpdate();
			System.out.println(updateCount);
			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					int newActorId = keys.getInt(1);
					actor.setId(newActorId);

				}
				System.out.println("Your Actor ID is " + actor.getId());
			} else {
				actor = null;
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
			throw new RuntimeException("Error inserting actor " + actor);
		}
		return actor;
	}
}
