package com.skilldistillery.film.database;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;
import com.skilldistillery.film.entities.FilmInventory;
import com.skilldistillery.film.entities.Language;

public interface DatabaseAccessor{
  public List<Film> getFilmById(int filmId) throws SQLException;
  public Actor getActorById(int actorId) throws SQLException;
  public List<Actor> getActorsByFilmId(int filmId) throws SQLException;
  public List<Film> getFilmBySearchTerm(String searchTerm) throws SQLException;
  public Language getLanguageOfFilm(int filmId) throws SQLException;
  public Film getAllFilmDetails(int filmId) throws SQLException;
  public List<FilmInventory> getFilmInventory(int filmId) throws SQLException;
  public Film addFilm(Film film) throws SQLException;
  public boolean deleteFilm(Film film) throws SQLException;
  public Film getFilmCategories(int filmId) throws SQLException;
  public Actor addActor(Actor actor) throws SQLException;
}
