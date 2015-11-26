package com.provectus.budgetrush.mail;

public class ResetPasswordEMailBuilder {

	private String password;

	private ResetPasswordEMailBuilder() {
	}

	public static ResetPasswordEMailBuilder newInstance() {
		return new ResetPasswordEMailBuilder();
	}

	public ResetPasswordEMailBuilder setPassword(String password) {
		this.password = password;
		return this;
	}

	public String build() {
		String emailText = String
				.format("<p><h2>YOUR NEW PASSWORD: %s</h2></p><br><br>"
						+ "<p><h2>Everytime when you forget the password...  in the world dies one kitten.</h2></p>"
						+ "<p><img src='http://troll-face.ru/static/mememaker/3/d/5564-grustnyij-kot.jpg'/></p>",
						password);

		return emailText;
	}

}
