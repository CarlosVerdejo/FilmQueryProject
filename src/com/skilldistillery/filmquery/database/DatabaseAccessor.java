package com.skilldistillery.filmquery.database;

import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public interface DatabaseAccessor {
  public Film findFilmById(int filmid);
  public Film findFilmByIdAll(int filmid);
  public Actor findActorById(int actorId);
  public List<Actor> findActorsByFilmId(int filmId);
  public List<Film> findFilmsByActorId(int actorId);
  public String defineLanguage(int langId);
  public List<Film> findFilmByKeyword(String keyWord);
}
