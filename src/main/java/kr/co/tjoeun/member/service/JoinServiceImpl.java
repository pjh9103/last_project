package kr.co.tjoeun.member.service;

import kr.co.tjoeun.member.controller.dto.JoinDto;
import kr.co.tjoeun.member.model.Member;
import kr.co.tjoeun.member.repository.MemberInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinServiceImpl implements JoinService {

    private final MemberInfoRepository memberInfoRepository;
    private final PasswordEncoder passwordEncoder;

    public void join(JoinDto joinDto) {
        Member joinMember = joinDto.toJoinMember();
        joinMember.setPassword(passwordEncoder.encode(joinMember.getPassword()));
        memberInfoRepository.save(joinMember);
    }

/*    // 정보 체크
    private void validationCheck(Member member) {
        isNotBlank(member.getUsername());
        isNotBlank(member.getUserId());
        isPasswordRangeCheck(member.getPassword());
        isNotBlank(member.getPhone());
    }


    // 이름 , ID, PW, 연락처
    private void isNotBlank(Object str) {
        if (str == null || str.equals(""))
            throw new IllegalArgumentException("값이 비어있습니다.");
    }

    // Pw
    private void isPasswordRangeCheck(String password) {
        int length = password.length();
        if (length < 10 && length >= 50) {
            throw new IllegalArgumentException("패스워드 길이는 10자리 이상 50 이하이어야합니다.");
        }
    }
*/

}
