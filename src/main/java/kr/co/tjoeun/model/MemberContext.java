package kr.co.tjoeun.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class MemberContext extends User {

    private Member member;

    public MemberContext(Member member, Collection<? extends GrantedAuthority> grantedAuthority, Member member1) {
        super(member.getUserId(), member.getPassword(), grantedAuthority);
    }

}
