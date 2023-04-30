package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	public static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	Actor actor = null;
	String username = "student";
	String password = "student";
	String sql = null;

	@Override
	public Film findFilmByIdAll(int filmid) {
		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(URL, username, password);

			String sql = "select film.* from film where film.id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmid);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String desc = rs.getString("description");
				short releaseYear = rs.getShort("release_year");
				int langId = rs.getInt("language_id");
				int rentDur = rs.getInt("rental_duration");
				double rate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double repCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String features = rs.getString("special_features");
				film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
						features);

			}
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;

	}
	
	

	@Override
	public Film findFilmById(int filmid) {
		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(URL, username, password);

			String sql = "select film.* from film where film.id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmid);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				//title, year, rating, description
				String title = rs.getString("title");
				short releaseYear = rs.getShort("release_year");
				String rating = rs.getString("rating");
				String desc = rs.getString("description");
				int langid = rs.getInt("language_id");
				int filmId = rs.getInt("id");		
				film = new Film(title, desc, releaseYear, rating, langid, filmId);

			}
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;

	
	}


	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		try {
			Connection conn = DriverManager.getConnection(URL, username, password);

			String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet actorResult = stmt.executeQuery();

			if (actorResult.next()) {
				int id = actorResult.getInt("id");
				String fn = actorResult.getString("first_name");
				String ln = actorResult.getString("last_name");

				actor = new Actor(id, fn, ln);

			}
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actor;

	}

	@Override
	public List<Film> findFilmsByActorId(int actorId) {
		
		List<Film> films = new ArrayList<>();
		
		try {
			Connection conn = DriverManager.getConnection(URL, username, password);
			
			String sql = "SELECT film.* from film join film_actor on film.id = film_actor.film_id where film_actor.actor_id = ? ";
			
		
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();
			
			
			while (rs.next()) {
				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String desc = rs.getString("description");
				short releaseYear = rs.getShort("release_year");
				int langId = rs.getInt("language_id");
				int rentDur = rs.getInt("rental_duration");
				double rate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double repCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String features = rs.getString("special_features");
				Film film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
						features);
				films.add(film);
				
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmid) {
		
		List<Actor> actors = new ArrayList<>();
		
		try {
			Connection conn = DriverManager.getConnection(URL, username, password);
			
			String sql = "select actor.* from actor join film_actor on film_actor.actor_id = actor.id join film on film.id = film_actor.film_id where film.id = ?";
			
		
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmid);
			ResultSet actorResult = stmt.executeQuery();
			
			
			while (actorResult.next()) {
				int id = actorResult.getInt("id");
				String fn = actorResult.getString("first_name");
				String ln = actorResult.getString("last_name");

				actor = new Actor(id, fn, ln);
				actors.add(actor);

			}
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}



	@Override
	public String defineLanguage(int langId) {
		String lang = null;
		try {
			Connection conn = DriverManager.getConnection(URL, username, password);
			
			String sql = "select name from language join film on film.language_id = language.id where film.language_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, langId);
			ResultSet langResult = stmt.executeQuery();
			
			if(langResult.next()) {
				lang = langResult.getString("name");
			}
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lang; 
	}



	@Override
	public List<Film> findFilmByKeyword(String keyWord) {
		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, username, password);
			
			String sql = "select film.title, film.release_year, film.rating, film.description, film.language_id from film where film.title or film.description like ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyWord + "%");
			ResultSet kwResult = stmt.executeQuery();
			
			while (kwResult.next()) {
				String title = kwResult.getString("title");
				String description = kwResult.getString("description");
				int year = kwResult.getInt("release_year");
				String rating = kwResult.getString("rating");
				int langid = kwResult.getInt("language_id");
				int filmId = kwResult.getInt("id");
				
				Film film = new Film(title, description, year, rating, langid, filmId);
				films.add(film);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return films;
	}
	
	
	
	

}
