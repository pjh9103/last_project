package kr.co.tjoeun.member.service;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String string) {
		super(string);
	}

}
