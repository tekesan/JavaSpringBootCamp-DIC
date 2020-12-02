package com.digimaster.myproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class MovieController {
	@Autowired
	private MovieService movieService;

	@PostMapping("/movie")
	public void createMovie(@RequestBody MovieModel movieModel) {
		movieService.createMovie(movieModel);	
	}
	

	@GetMapping("/movie/{id}")
	public MovieModel getMovie(@PathVariable int id) {
		return movieService.getMovie(id);
	}
	
	
	@GetMapping("/movie/get")
	public MovieModel getMovie(@RequestParam String title, @RequestParam String genre) {
		return movieService.getMovie(title, genre);
	}
	
	
	@GetMapping("/movie/get1")
	public MovieModel getMovie1(@RequestParam String genre, @RequestParam String release) {
		return movieService.getMovie1(genre,release);
	}
	
	@GetMapping("/allmovie")
	public Iterable<MovieModel> getMovies() {
		return movieService.getAllMovie();
	}
	
	@GetMapping("/moviegenre")
	public Iterable<MovieModel> getGenre(String genre) {
		return movieService.getGenre(genre);
	}
	
	
	@PutMapping("/movie/{id}")
	public MovieModel updateMovie(@PathVariable int id, @RequestBody MovieModel movieModel)
	{
		return movieService.updateMovie(movieModel, id);
	}
	
	@DeleteMapping("/movie/{id}")
	public boolean deleteMovie(@PathVariable int id)
	{
		return movieService.deleteMovie(id);
	}
	
	
	@PostMapping("/files")
	public boolean uploadFile(@RequestParam("file")MultipartFile file) {
		movieService.saveFile(file);
		return true;
	}

	
	@PostMapping("/files/id")
	public boolean uploadFile(@RequestParam("file")MultipartFile file, @RequestParam int id) {
		movieService.saveFile(file, id);
		return true;
	}
	

	@GetMapping("/movie")
	public BaseResponse<MovieModel> getMovieWithBaseResponse(@RequestParam int id) {
		MovieModel movie = movieService.getMovieWithValidation(id);
		
		BaseResponse<MovieModel> baseResponse = new BaseResponse<>();
		
		if (movie != null) {
			baseResponse.setCode(200);
			baseResponse.setMessage("user ketemu");
			baseResponse.setSuccess(true);
//			baseResponse.setData(movie);
		}else {
			baseResponse.setCode(404);
			baseResponse.setMessage("user tidak ketemu");
			baseResponse.setSuccess(false);
//			baseResponse.setData(movie);
		}

		return baseResponse;
		
	}
}
	