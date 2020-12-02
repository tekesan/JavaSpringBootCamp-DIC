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


//review dulu gan
@RestController
@RequestMapping("/api")
public class ApiController {
	@Autowired
	private PersonService personService;

//	 public ApiController(PersonService personService) {
//		 this.personService = personService;
//	}

	@GetMapping("/helloWorld")
	public String helloWord() {
		return "hellodunia";
	}

	@GetMapping("/param")
	public String returnParam(@RequestParam String username) {
		return "Iniparam :" + username;
	}

	@PostMapping("/post")
	public String postParam(@RequestBody String text) {
		return "Initext :" + text;
	}

	@PostMapping("/postBody")
	public String postBody(@RequestBody PersonModel personModel) {
		return "ini orang: " + personModel.getName() + "&" + personModel.getCity();
	}

	@PostMapping("/create")
	public void createPerson(@RequestBody PersonModel personModel) {
		personService.createPerson(personModel);
	}

	
	@PostMapping("/creates")
	public Iterable<PersonModel> createPerson(@RequestBody Iterable<PersonModel> personModel) {
		return personService.createPerson(personModel);
	}

	
	
	
	@GetMapping("/person/{id}")
	public PersonModel getPerson(@PathVariable int id) {
		return personService.getPerson(id);
	}
	
	
	
	@GetMapping("/person")
	public BaseResponse<PersonModel> getPersonWithBaseResponse(@RequestParam int id) {
		PersonModel person = personService.getPersonWithValidation(id);
		
		BaseResponse<PersonModel> baseResponse = new BaseResponse<>();
		
		if (person != null) {
			baseResponse.setCode(200);
			baseResponse.setMessage("user ketemu");
			baseResponse.setSuccess(true);
//			baseResponse.setData(person);
		}else {
			baseResponse.setCode(404);
			baseResponse.setMessage("user tidak ketemu");
			baseResponse.setSuccess(false);
//			baseResponse.setData(person);
		}

		return baseResponse;
		
	}
	
	
	@GetMapping("/person/name")
	public PersonModel getPersonByName(@RequestParam String name) {
		return personService.getPerson(name);
	}
	

	@GetMapping("/person/get")
	public PersonModel getPersonByNameAndCity(@RequestParam String name, @RequestParam String city) {
		return personService.getPersons(name, city);
	}

	@GetMapping("/persons")
	public Iterable<PersonModel> getPersons() {
		return personService.getAllPersons();
	}
	
	@GetMapping("/persons2")
	public BaseResponse<PersonModel> getPersonWithValidation() {
		
		BaseResponse<PersonModel> baseResponse = new BaseResponse<>();
		
		
			baseResponse.setCode(200);
			baseResponse.setMessage("user ketemu");
			baseResponse.setSuccess(true);
			baseResponse.setData(personService.getAllPersons());
		
		return baseResponse;
		
	}
	
	@GetMapping("/personcity")
	public Iterable<PersonModel> getPersonCitys(String city) {
		return personService.getPersonCity(city);
	}
	
	@PutMapping("/person/{id}")
	public PersonModel updatePerson(@PathVariable int id, @RequestBody PersonModel personModel)
	{
		return personService.updatePerson(personModel, id);
	}
	
	@DeleteMapping("/person/{id}")
	public boolean deletePerson(@PathVariable int id)
	{
		return personService.deletePerson(id);
	}
	
	@DeleteMapping("/person/name/{name}")
	public boolean deletePersonByName(@PathVariable String name)
	{
		personService.deletePersonByName(name);
		return true;
	}
	
	
	@PostMapping("/file")
	public boolean uploadFile(@RequestParam("file")MultipartFile file) {
		personService.saveFile(file);
		return true;
	}

	
	@PostMapping("/file/id")
	public boolean uploadFile(@RequestParam("file")MultipartFile file, @RequestParam int id) {
		personService.saveFile(file, id);
		return true;
	}
}
