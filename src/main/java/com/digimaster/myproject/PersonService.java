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
public class PersonService {

	private PersonRepository personRepository;

	private final Path root = Paths.get("F:\\Belajar Spring tool\\images");

	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
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

			Optional<PersonModel> currPerson = personRepository.findById(id);
			if (currPerson.isPresent()) {
				currPerson.get().setProfile(file.getOriginalFilename());
				personRepository.save(currPerson.get());
				Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public PersonModel createPerson(PersonModel personModel) {
		return personRepository.save(personModel);
	}

	public Iterable<PersonModel> createPerson(Iterable<PersonModel> personModel) {
		return personRepository.saveAll(personModel);
	}

	public PersonModel getPerson(int id) {
		return personRepository.findById(id).get();
	}
	
	public PersonModel getPersonWithValidation(int id) {
		if (personRepository.findById(id).isPresent()) {

			return personRepository.findById(id).get();
		}else {
			return null;
		}
		
	}

	public PersonModel getPerson(String name) {
		return personRepository.getPersonModelByName(name);
	}

	public PersonModel getPersons(String name, String city) {
		return personRepository.getPersonModelByNameAndCity(name, city);
	}

	public Iterable<PersonModel> getAllPersons() {
		return personRepository.findAll();
	}

	public Iterable<PersonModel> getPersonCity(String city) {
		return personRepository.getPersonModelByCity(city);
	}

	public PersonModel updatePerson(PersonModel personModel, int id) {
		Optional<PersonModel> currPerson = personRepository.findById(id);
		if (currPerson.isPresent()) {
			currPerson.get().setAge(personModel.getAge());
			currPerson.get().setCity(personModel.getCity());
			currPerson.get().setName(personModel.getName());

			return personRepository.save(currPerson.get());
		} else {

			return personModel;

		}
	}

	public boolean deletePerson(int id) {
		personRepository.deleteById(id);
		return true;
	}

	public void deletePersonByName(String name) {
		personRepository.deletePersonModelByName(name);
	}

}
