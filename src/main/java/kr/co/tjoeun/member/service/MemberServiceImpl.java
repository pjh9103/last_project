package kr.co.tjoeun.member.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.tjoeun.member.model.Member;
import kr.co.tjoeun.member.model.MemberContext;
import kr.co.tjoeun.member.repository.MemberInfoRepository;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements UserDetailsService {

	private final MemberInfoRepository memberInfoRepository;
	
	public MemberServiceImpl(MemberInfoRepository memberInfoRepository) {
		super();
		this.memberInfoRepository = memberInfoRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		try {
			Member member = memberInfoRepository.findByUserId(userId);
			return new MemberContext(member, addAuthorities(member), member);
		} catch (EntityNotFoundException | DataIntegrityViolationException ex) {
			throw new UserNotFoundException("이미 있는 계정입니다.");
		}
	}

	private Set<GrantedAuthority> addAuthorities(Member member) {
		Set<GrantedAuthority> grantedAuthority = new HashSet<GrantedAuthority>();
		grantedAuthority.add(new SimpleGrantedAuthority(member.getRole()));
		return grantedAuthority;
	}
}
