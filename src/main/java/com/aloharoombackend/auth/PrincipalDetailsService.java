package com.aloharoombackend.auth;

import com.aloharoombackend.Repository.UserRepository;
import com.aloharoombackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 시큐리티 로그인을 하면 자동으로 loadUserByUsername() 실행
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    //Security session(내부 Authentication(내부 UserDetails(내부 User)))
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(username);
        System.out.println("이름: " + username + ", userEntity: " + userEntity);
        if(userEntity != null) {
            return new PrincipalDetails(userEntity); //UserDetails를 Authentication에 반환
        }
        return null;
    }
}
