package com.codingworld.liquibasedemo.repository;

import com.codingworld.liquibasedemo.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {

}
