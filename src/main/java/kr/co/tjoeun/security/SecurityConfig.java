package kr.co.tjoeun.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static kr.co.tjoeun.security.SecurityConstants.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private final UserDetailsService memberService;

	public SecurityConfig(UserDetailsService memberService) {
		this.memberService = memberService;
	}

	// 그냥 공식임....ㅅㅂ...
	@Override
	public void configure(WebSecurity web) {
		web.ignoring()
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(memberService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers(PATH_JOIN).permitAll()
				.antMatchers(PATH_ALL).hasRole(ROLE_USER);

		http.formLogin()
				.loginProcessingUrl("/login_proc")
				.loginPage(PATH_LOGIN)
				.usernameParameter(PARAMETER_USER_ID)
				.defaultSuccessUrl(PATH_MAIN)
				.failureHandler(new MemberLoginFailHandler())
				.permitAll();

		http.addFilterBefore(new MemberSuccessRedirect(), UsernamePasswordAuthenticationFilter.class);

		http.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl(PATH_MAIN);

	}
	@Bean
	public PasswordEncoder passWordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
