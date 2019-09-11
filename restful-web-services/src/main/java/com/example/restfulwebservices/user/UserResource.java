package com.example.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.example.restfulwebservices.user.UserNotFoundException;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService userDaoService;

	@GetMapping(path = "/users")
	public List<User> retrieveAllUsers() {
		return userDaoService.findAll();
	}

	@GetMapping(path = "/users/{id}")
	public User retrieveUSer(@PathVariable int id) {
		User user = userDaoService.findOne(id);
		if (user == null)
			throw new UserNotFoundException("id - " + id);
		
		//HATEOAS
		//Resource<User> user = new Resource<User>(user);
		return user;
	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User savedUser = userDaoService.save(user);

		// To return the URI to the user, build the requested URI with id and generate
		// the URI
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(savedUser.getId())
				.toUri();
		System.out.println("URI is " + location);

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = "/users/delete/{id}")
	public void deleteUser(@PathVariable int id) {
		User deletedUser = userDaoService.deleteUserById(id);

		if (deletedUser == null)
			throw new UserNotFoundException("id-" + id);

	}

}
