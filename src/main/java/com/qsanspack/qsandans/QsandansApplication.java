package com.qsanspack.qsandans;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.qsanspack.qsandans.entities.Role;
import com.qsanspack.qsandans.entities.User;
import com.qsanspack.qsandans.repos.UserRepo;

@SpringBootApplication
public class QsandansApplication {

	public static void main(String[] args) {
		SpringApplication.run(QsandansApplication.class, args);
	}


	@Bean
	CommandLineRunner run(UserRepo userRepo,PasswordEncoder encoder){

		
		return args->{

			if(userRepo.findByUsername("admin").isPresent()) return;

				// Role adminRole = userRepo.save(new Role("ADMIN"));
				// roleRepo.save(new Role("USER"));

				// Set<Role> roles = new HashSet<>();
				Set<Role> roles = new HashSet<>();

				roles.add(new Role("ADMIN"));

				//roles.add(new User);

				User admin = new User(0,"admin",encoder.encode("password"),"","Admin",roles);

				userRepo.save(admin);
		};
	}

}
