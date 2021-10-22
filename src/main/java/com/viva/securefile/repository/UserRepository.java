package com.viva.securefile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viva.securefile.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
