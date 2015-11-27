package com.provectus.budgetrush.mail;

public class ResetPasswordMessageBuilder {

	private String password;
	private String name;
	
	private ResetPasswordMessageBuilder() {
	}

	public static ResetPasswordMessageBuilder newInstance() {
		return new ResetPasswordMessageBuilder();
	}

	public ResetPasswordMessageBuilder setPassword(String password) {
		this.password = password;
		return this;
	}
	
	public ResetPasswordMessageBuilder setName(String name) {
		this.name = name;
		return this;
	}
	
	public String build() {
		String emailText = String
				.format("<!DOCTYPE html>"
						+"<html lang='en'>"
						+"<head><meta charset='utf-8'></head>"
						+"<p><h2>Hello, %s !</h2></p><br>"
						+"<p>YOUR NEW PASSWORD: %s</p><br>"
						+"<p><h2>REMEMBER: </h2></p>"
						+ "<p>Everytime when you forget the password...  in the world dies one kitten.</p>"
						+ "<p><img src='http://troll-face.ru/static/mememaker/3/d/5564-grustnyij-kot.jpg'/></p>"
						, name,	password);

		return emailText;
	}

}
