package kr.co.tjoeun.controller.dto;

import org.springframework.security.crypto.password.PasswordEncoder;

import kr.co.tjoeun.model.Member;

public class JoinDto {

	private String userId;

	private String password;

	private String username;

	private String birth;

	private String phone;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Member toJoinMember() {
		return new Member(userId, password, username, birth, phone);
	}
}
