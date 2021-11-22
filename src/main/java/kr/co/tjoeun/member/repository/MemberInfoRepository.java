package kr.co.tjoeun.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.tjoeun.member.model.Member;

public interface MemberInfoRepository extends JpaRepository<Member, Long> {
	Member findByUsername(String userName);
	Member findByUserId(String userId);
}
