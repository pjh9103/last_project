package kr.co.tjoeun.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "user_id", name = "uk_member_userId"))
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id", length = 20, nullable = false)
	private String userId;

	@Column(name = "user_password", nullable = false)
	private String password;

	@Column(name = "username", length = 50)
	private String username;

	@Column(name = "user_birth")
	private String birth;

	@Column(name = "user_phone", nullable = false)
	private String phone;
	
	@Column(name = "role", nullable = false)
	private String role = "ROLE_ANONYMOUS";

	public Member(String userId,  String password, String username, String birth, String phone) {
		this.userId = userId;
		this.password = password;
		this.username = username;
		this.birth = birth;
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public String getBirth() {
		return birth;
	}
	
	public String getPhone() {
		return phone;
	}

	public String getRole() {
		return role;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
