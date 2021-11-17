package kr.co.tjoeun.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.tjoeun.controller.dto.JoinDto;
import kr.co.tjoeun.model.Member;
import kr.co.tjoeun.model.MemberContext;
import kr.co.tjoeun.repository.MemberInfoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements UserDetailsService {

	private final MemberInfoRepository memberInfoRepository;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		Member member = memberInfoRepository.findByUserId(userId);
		if (member == null)
			throw new UserNotFoundException("유저가 존재하지 않습니다.");
		return new MemberContext(member, addAuthorities(member));
	}

	private Set<GrantedAuthority> addAuthorities(Member member) {
		Set<GrantedAuthority> grantedAuthority = new HashSet<GrantedAuthority>();
		grantedAuthority.add(new SimpleGrantedAuthority(member.getRole()));
		return grantedAuthority;
	}
}
