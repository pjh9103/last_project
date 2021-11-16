package kr.co.tjoeun.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.tjoeun.model.Member;

public interface MemberInfoRepository extends JpaRepository<Member, Long> {

	Member findByUsername(String userName);
	Member findByUserId(String userId);
	Member findByBirth(String birth);
	Member findByUserPhone(String phone);
	Member findByUserIdAndPassword(String userId, String password);
}
