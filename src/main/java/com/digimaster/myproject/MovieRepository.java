package com.digimaster.myproject;

import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<MovieModel, Integer> {


	MovieModel getMovieModelByTitleAndGenre(String title, String genre);
	MovieModel getMovieModelByGenreAndRelease(String genre, String release);



	Iterable<MovieModel> getMovieModelByGenre(String genre);
}
