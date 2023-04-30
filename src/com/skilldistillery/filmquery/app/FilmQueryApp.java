package com.skilldistillery.filmquery.app;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	private DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//		app.test();
		app.launch();
	}

	private void test() {
//	  Actor actor = db.findActorById(143);
//	  if (actor != null) {
//	  System.out.println(actor);
//	  } else {
//		  System.out.println("No such actor found");
//	  }

//		List<Film> films = db.findFilmsByActorId(78);
//		if(films != null) {
//		for(Film f : films) {
//			System.out.println(f);
//			System.out.println();
//		}
//		} else {
//			System.out.println("No Films Were Found");
//		}

//		Film film = db.findFilmById(3);
//		if (film != null) {
//			System.out.println(film);
//		} else {
//			System.out.println("No such film id exists");
//		}
		

//		List<Actor> actors = db.findActorsByFilmId(350);
//		if (actors != null) {
//			for (Actor a : actors) {
//				System.out.println(a);
//				System.out.println();
//			}
//		} else {
//			System.out.println("Film ID Not Found");
//		}
		
		
//		String language = db.defineLanguage(5);
//		System.out.println(language);
		

		List<Film> films = db.findFilmByKeyword("we");
		for(Film f : films) {
			System.out.println(f.reducedFilmString(f));
			System.out.println();
		}
		
	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		System.out.println("confirmed close");
		input.close();
	}

	private void startUserInterface(Scanner input) {
		boolean run = true;
		while (run) {
			menu();
			String option = input.nextLine();

			switch (option) {

			case "1":
				try {
					System.out.println("Enter Film ID: ");
					int filmId = input.nextInt();
					Film film = db.findFilmById(filmId);
					
					if (film != null) {
						System.out.println(film.reducedFilmString(film));
					} else {
						System.out.println("No Films With Film ID: " + filmId + " Were Found In The Database");

					}
				} catch (InputMismatchException e) {
					System.out.println("A 'Film ID' is exclusively a series of numbers, eg. '123' ");
				}
					String flush = input.nextLine();
				break;
			case "2":
					System.out.println("Enter Keyword: ");
					String keyWord = input.nextLine();
					List<Film> film = db.findFilmByKeyword(keyWord);
					System.out.println(film.size());
					if (film.size() > 0) {
						for (Film f : film) {
						System.out.println(f.reducedFilmString(f));	
						}
					} else{
						System.out.println("No Films With Keyword: " + keyWord + " Were Found In The Database");
					}
					String flush2 = input.nextLine();
				break;
			case "3":
				System.out.println("exit");
				run = false;
				break;
			default:
				System.out.println("Invalid Input, Try again.");
			}
		}

	}

	private void menu() {
		System.out.println("\n	-FILM SEARCH MENU-");
		System.out.println("\nSelect From The Following Options:");
		System.out.println("  [1] Search Film By Film ID ");
		System.out.println("  [2] Search Film By Keyword");
		System.out.println("  [3] Exit");
	}

}
