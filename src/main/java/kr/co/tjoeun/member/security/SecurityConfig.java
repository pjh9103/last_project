package kr.co.tjoeun.member.security;

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
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import static kr.co.tjoeun.member.security.SecurityConstants.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private final UserDetailsService memberService;

	private final AuthenticationFailureHandler MemberLoginFailHandler;

	public SecurityConfig(UserDetailsService memberService, AuthenticationFailureHandler MemberLoginFailHandler) {
		this.memberService = memberService;
		this.MemberLoginFailHandler = MemberLoginFailHandler;
	}


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
				.antMatchers("/favicon.ico", PATH_JOIN, "/login/**","/main").permitAll()
				.antMatchers(PATH_ALL).hasRole(ROLE_USER);

		http.formLogin()
				.loginProcessingUrl("/login_proc")
				.loginPage(PATH_LOGIN)
				.usernameParameter(PARAMETER_USER_ID)
				.defaultSuccessUrl(PATH_MAIN)
				.failureHandler(MemberLoginFailHandler)
				.permitAll();
		http.sessionManagement().maximumSessions(1);


		http.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl(PATH_MAIN);


		http.addFilterBefore(new MemberSuccessRedirect(), UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(getCharacterEncodingFilter(), CsrfFilter.class);
	}

	private CharacterEncodingFilter getCharacterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}


	@Bean
	public PasswordEncoder passWordEncoder() {
		return new BCryptPasswordEncoder();
	}


}
