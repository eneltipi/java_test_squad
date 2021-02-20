package authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import authentication.helper.UserDetailsServiceImpl;
import authentication.jwt.JwtFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;
	
	@Bean
	public JwtFilter authenticationJwtTokenFilter() {
		return new JwtFilter();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	} 

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	   
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and()
        .csrf().disable()
    	.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeRequests().antMatchers("/").permitAll()
//		.antMatchers("/hienthitaikhoan2").hasAuthority("staff")
		.antMatchers("/hienthitaikhoan2").permitAll()
		.antMatchers("/JWTLogin").permitAll()
		.antMatchers("/getLaptopList").permitAll()
		.antMatchers("/upimg").hasAuthority("admin")
		.antMatchers("/angularLogin").permitAll()
		.antMatchers("/insert").permitAll()
		.anyRequest().authenticated();
     
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/vendor/**",
				"/fonts/**");
	}

}
