package com.aloharoombackend.repository;

import com.aloharoombackend.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //다른데서 성능 개선될듯 일단 남ㄱ겨
    @Override
    @EntityGraph(attributePaths = {"board", "myHashtags"})
    Optional<User> findById(Long id);

    public User findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByNickname(String nickname);
}
