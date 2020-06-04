package com.example.Security403;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Security403Application {

	public static void main(String[] args) {
		SpringApplication.run(Security403Application.class, args);
	}
	@Bean
	public CommandLineRunner run(UserRepository userRepository, RoleRepository roleRepository) throws Exception {
		return (String[] args) -> {
			User user= new User("tmc","tmc@domian.com","tmc","tmc","tmc",true);
			Role role= new Role("tmc","ROLE_USER");
			userRepository.save(user);
			roleRepository.save(role);
			User user1= new User("super","super@domain.com","super","super","man",true);
			Role adminrole= new Role("super","ROLE_ADMIN");
			Role adminrole1= new Role("super","ROLE_USER");
			userRepository.save(user1);
			roleRepository.save(adminrole);
			roleRepository.save(adminrole1);
//			User admin = new User("super", "super@domain.com", " super ", "Super", "Man", true);
//			Role adminRole = new Role("super ", "ROLE_ADMIN");
//			userRepository.save(admin);
//			roleRepository.save(adminRole);
//			User user = new User("bart", "bart@domain.com", "bart", "Bart", "Simpson", true);
//			Role userRole = new Role("bart ", "ROLE_USER"); userRepository.save(user);
//			roleRepository.save(userRole);
//			User admin = new User("super", "super@domain.com", " super ", "Super", "Man", true);
//			Role adminRole = new Role("super ", "ROLE_ADMIN");
//			userRepository.save(admin);
//			roleRepository.save(adminRole);
		};
	} }