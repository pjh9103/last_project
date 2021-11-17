package kr.co.tjoeun.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private final UserDetailsService memberService;

	public SecurityConfig(UserDetailsService memberService) {
		this.memberService = memberService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(memberService);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/join").permitAll()
				.antMatchers("/**").hasRole("USER");

		http.formLogin()
				.loginProcessingUrl("/login_proc")
				.loginPage("/login")
				.usernameParameter("userId")
				.defaultSuccessUrl("/main")
				.permitAll();
	}

	@Bean
	public PasswordEncoder passWordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
