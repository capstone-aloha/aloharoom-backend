package com.aloharoombackend.repository;

import com.aloharoombackend.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);

    @Override
    @EntityGraph(attributePaths = {"myProducts"})
    Optional<User> findById(Long aLong);
}
