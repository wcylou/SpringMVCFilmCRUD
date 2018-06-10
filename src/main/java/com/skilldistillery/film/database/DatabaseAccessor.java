package com.skilldistillery.film.database;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;
import com.skilldistillery.film.entities.Inventory;
import com.skilldistillery.film.entities.Language;

public interface DatabaseAccessor{
  public List<Film> getFilmById(int filmId);
  public Actor getActorById(int actorId);
  public List<Actor> getActorsByFilmId(int filmId);
  public List<Film> getFilmBySearchTerm(String searchTerm);
  public Language getLanguageOfFilm(int filmId);
  public Film getAllFilmDetails(int filmId);
  public Film addFilm(Film film);
  public Film updateFilm(Film film, int filmId);
  public boolean deleteFilm(Film film);
  public Actor addActor(Actor actor);
  public List<Inventory> getInventoryFilms(int filmId);
  public List<String> getCategoriesByFilm(int filmId);
}
