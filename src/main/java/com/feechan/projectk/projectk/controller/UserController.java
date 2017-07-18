package com.feechan.projectk.projectk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import java.io.File;

import javax.mail.*;
import javax.mail.internet.MimeMessage;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	JavaMailSender javaMailSender;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	String GetAllUsers() {
		return "Anda mencari data semua user";
	}
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	String GetUser(@PathVariable("id") long id) {
		return "Anda mencari data user dengan id : " + id;
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
