package com.feechan.projectk.projectk.controller;

import com.feechan.projectk.projectk.entity.User;
import com.feechan.projectk.projectk.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	UserRepository userRepository;

	@Autowired
	JavaMailSender javaMailSender;

	@Cacheable(value = "user-all")
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> GetAllUsers() {
		return userRepository.findAll();
	}

	@Cacheable(value = "user-single", key="#username")
	@RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
	public User GetUser(@PathVariable("username") String username) {
		return userRepository.findByUsername(username);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public User PostUser(@Valid @RequestBody User user){
		user.setId(ObjectId.get());
		return userRepository.save(user);
	}
	
	@RequestMapping(value = "/users/sendmail", method = RequestMethod.GET)
	String SendEmail() {
		try {
			MimeMessage mail = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail, true);
			helper.setTo("chen.fa25@gmail.com");
			helper.setSubject("Percobaan aja");
			helper.setText("Halo ini tes email", false);
			FileSystemResource file = new FileSystemResource(new File("D:/percobaan.txt"));
			helper.addAttachment("percobaan.txt", file);
			javaMailSender.send(mail);
			System.out.println("Done");
			return "Anda berhasil mengirim email";

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "Mungkin terjadi kesalahan";
	}
}
