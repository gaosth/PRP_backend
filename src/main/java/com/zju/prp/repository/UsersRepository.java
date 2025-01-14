package com.zju.prp.repository;

import com.zju.prp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {  // 注意这里使用 Integer 而不是 Long
    // 自定义查询方法，根据邮箱和密码查询用户
    Optional<Users> findByEmailAndPassword(String email, String password);
}