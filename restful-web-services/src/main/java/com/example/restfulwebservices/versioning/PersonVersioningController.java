package com.example.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {
	
	@GetMapping("/v1/person")
	public PersonV1 getPersonV1() {
		return new PersonV1("Bob Deol");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getPersonV2() {
		return new PersonV2(new Name("Bob", "Deol"));
	}

	@GetMapping(value="/person/param", params="version1")
	public PersonV1 getParamV1() {
		return new PersonV1("Bob Deol");
	}
	
	@GetMapping(value="/person/param", params="version2")
	public PersonV2 getParamV2() {
		return new PersonV2(new Name("Bob", "Deol"));
	}
	
	@GetMapping(value="/person/header", headers ="X_API_VERSION=1")
	public PersonV1 getHeaderV1() {
		return new PersonV1("Bob Deol");
	}
	
	@GetMapping(value="/person/header", headers="X_API_VERSION=2")
	public PersonV2 getHeaderV2() {
		return new PersonV2(new Name("Bob", "Deol"));
	}
	
	@GetMapping(value="/person/header", produces="application/v1.version+json")
	public PersonV1 getProducesV1() {
		return new PersonV1("Bob Deol");
	}
	
	@GetMapping(value="/person/header", produces="application/abc.company.app-v2+json")
	public PersonV2 getProducesV2() {
		return new PersonV2(new Name("Bob", "Deol"));
	}
}
