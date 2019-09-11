package com.example.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.restfulwebservices.helloworld.HeloWorldBean;

@RestController
public class HelloWorldController {
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping(path = "/hello-world")
	public String helloworld() {
		return "Hello World";
	}

	@GetMapping(path = "/hello-world-bean")
	public HeloWorldBean helloworldBean() {
		return new HeloWorldBean("Hello World Bean");
	}

	@GetMapping(path = "/hello-world/user/{name}")
	public HeloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HeloWorldBean(String.format("Hello World %s", name));
	}
	
	@GetMapping(path = "/hello-world-internationalized")
	public String helloworldInternationalized(@RequestHeader(name="Accept-Language", required=false) Locale locale) {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}

}
