package com.aloharoombackend.service;

import com.aloharoombackend.model.User;
import com.aloharoombackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * 회원가입
     */
    @Transactional
    public Long join(User user) {
        validateDuplicateMember(user);
        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateMember(User user) {
        List<User> findMembers = (List<User>) userRepository.findByUsername(user.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 이름입니다.");
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findOne(User user) {
        return userRepository.findById(user.getId());
    }

}