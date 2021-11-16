package kr.co.tjoeun.service;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import kr.co.tjoeun.model.Member;
import kr.co.tjoeun.repository.MemberInfoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberInfoRepository memberInfoRepository;

	public MemberService(MemberInfoRepository memberInfoRepository) {
		this.memberInfoRepository = memberInfoRepository;
	}

	/**
	 *  1. 모든 소스는 검증을 해야해
	 *  2. 소스가 검증에 만족을 하면 그 데이터는 가공이 가능해
	 *  3. 가공이 가능하다는 것은 (조회, 수정, 삭제)
	 *  4. 그런데 여기서 조회, 수정, 삭제를 하려 하는데 ?
	 *  5. 예상치 못 하는 일이 일어나면 어떻게할꺼야 ?
	 *  try ~ catch ~ 를 사용 하는 거야
	 *  내가 여기서 이 소스를 시도할 껀데 만약에 여기서 내가 생각한 내용과 다르게 동작한다면 예외를 던져줄래 ?
	 *  예외 throw new ----- (); 잘못된 인자값을 넣었다
	 */

	// 회원정보조회
	public Member findByUserId(String userId) {
		isNotBlank(userId);
		Member findByUsername = memberInfoRepository.findByUserId(userId);
		if (findByUsername == null)
			throw new UserNotFoundException("유저가 존재하지 않습니다.");
		return findByUsername;
	}

	// 회원 가입
	public Member register(Member member) {
		try {
			isNotBlank(member);
			validationCheck(member);

			return memberInfoRepository.save(member);
		} catch (DataAccessException ex) {
			throw new IllegalArgumentException("관리자에게 문의하여 주세요");
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	//정보 체크
	private void validationCheck(Member member) {
		isNotBlank(member.getUsername());
		isNotBlank(member.getUserId());
		isPasswordRangeCheck(member.getPassword());
		member.getBirth();
		isNotBlank(member.getUserPhone());


	}


	//이름 , ID, PW, 연락처
	private  void isNotBlank(Object str) {
		if (str == null || str.equals("")) {
			throw new IllegalArgumentException("값이 비어있습니다.");
		}
	}
	//Pw 길이
	private  void isPasswordRangeCheck(String password) {
		int length = password.length();
		if (length < 10 && length >= 50) {
			throw new IllegalArgumentException("패스워드 길이는 10자리 이상 50 이하이어야합니다.");
		}
	}

}
