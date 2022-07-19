package com.capgemini.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.model.User;



@Repository("userRepository")
public interface IUserRepository extends JpaRepository<User, Integer> {


}
