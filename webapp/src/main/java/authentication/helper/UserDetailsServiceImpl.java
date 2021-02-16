package authentication.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserService userService;

	@Transactional
	public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("this is user_detail_service_impl");
		User user = userService.loadUserByUsername(email);
		return CustomUserDetails.build(user);
	}

}