package com.digimaster.myproject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MovieService {

	private MovieRepository movieRepository;
	
	private final Path root = Paths.get("F:\\Belajar Spring tool\\images\\myprojectimages");

	public MovieService(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}


	public void saveFile(MultipartFile file) {
		try {
			if (!Files.exists(root)) {
				Files.createDirectories(root);
			}
			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveFile(MultipartFile file, int id) {
		try {
			if (!Files.exists(root)) {
				Files.createDirectories(root);
			}

			Optional<MovieModel> currPerson = movieRepository.findById(id);
			if (currPerson.isPresent()) {
				currPerson.get().setImages(file.getOriginalFilename());
				movieRepository.save(currPerson.get());
				Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public MovieModel createMovie(MovieModel movieModel)
	{
		return movieRepository.save(movieModel);
	}

	public MovieModel getMovie(int id) {
		return movieRepository.findById(id).get();
	}
	
	public MovieModel getMovie(String title, String genre) {
		return movieRepository.getMovieModelByTitleAndGenre(title, genre);
	}
	
	public MovieModel getMovie1(String genre, String release) {
		return movieRepository.getMovieModelByGenreAndRelease(genre, release);
	}
	

	public Iterable<MovieModel>getAllMovie(){
		return movieRepository.findAll();
	}
	

	public Iterable<MovieModel>getGenre(String genre){
		return movieRepository.getMovieModelByGenre(genre);
	}
	
	
	public MovieModel updateMovie(MovieModel movieModel, int id) {
		Optional<MovieModel> currMovie = movieRepository.findById(id);
		if (currMovie.isPresent()) {
			currMovie.get().setTitle(movieModel.getTitle());
			currMovie.get().setGenre(movieModel.getGenre());
			currMovie.get().setRelease(movieModel.getRelease());
				
				return movieRepository.save(currMovie.get());
		}else {
			
				return movieModel;
			
		}
	}
	
	public boolean deleteMovie(int id) {
		movieRepository.deleteById(id);
		return true;
	}
	
	
	public MovieModel getMovieWithValidation(int id) {
		if (movieRepository.findById(id).isPresent()) {

			return movieRepository.findById(id).get();
		}else {
			return null;
		}
		
	}

}
