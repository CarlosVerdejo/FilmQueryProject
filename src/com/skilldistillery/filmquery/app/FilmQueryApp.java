package com.skilldistillery.filmquery.app;

import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
  
  private DatabaseAccessor db = new DatabaseAccessorObject();

  public static void main(String[] args) {
    FilmQueryApp app = new FilmQueryApp();
    app.test();
//    app.launch();
  }

  private void test() {
//	  Actor actor = db.findActorById(143);
//	  if (actor != null) {
//	  System.out.println(actor);
//	  } else {
//		  System.out.println("No such actor found");
//	  }
//	  db.findFilmsByActorId(45);
    Film film = db.findFilmById(3);
    if (film != null) {
    System.out.println(film);
    } else {
    	System.out.println("No such film id exists");
    }
    }

  private void launch() {
    Scanner input = new Scanner(System.in);
    
    startUserInterface(input);
    
    input.close();
  }

  private void startUserInterface(Scanner input) {
    
  }

}
