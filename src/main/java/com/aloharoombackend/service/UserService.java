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
        //validateDuplicateMember(user);
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

    public User findOne(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("찾는 사용자가 존재하지 않습니다."));
    }

    public User findOneFetchAll(Long id) {
        User findUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("찾는 사용자가 존재하지 않습니다."));
        findUser.getMyHashtags().stream().
                forEach(myHashtag -> myHashtag.getHash());
        findUser.getMyProducts().stream().
                forEach(myProduct -> myProduct.getName());
        findUser.getLikeHashtags().stream().
                forEach(likeHashtag -> likeHashtag.getHash());
        findUser.getLikeProducts().stream().
                forEach(likeProduct -> likeProduct.getName());
        return findUser;
    }

    public User findOneFetch(Long id) {
        User findUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("찾는 사용자가 존재하지 않습니다."));
        findUser.getMyHashtags().stream().
                forEach(myHashtag -> myHashtag.getHash());
        findUser.getMyProducts().stream().
                forEach(myProduct -> myProduct.getName());
        return findUser;
    }

}
