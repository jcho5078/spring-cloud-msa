package com.example.userservice.repository;

import com.example.userservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public UserEntity findUserEntityById(long id);
    public UserEntity findByEmail(String email);
    public UserEntity findByUserId(String userId);

    public List<UserEntity> findTop10ByName(String name);
}
