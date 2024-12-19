package com.zju.prp.repository;

import com.zju.prp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {  // 注意这里使用 Integer 而不是 Long
}