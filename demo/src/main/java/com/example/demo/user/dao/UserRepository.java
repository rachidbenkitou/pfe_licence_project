package com.example.demo.user.dao;

import com.example.demo.entretien.entity.Entretien;
import com.example.demo.user.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username = :username")
    public User getUserByUsername(@Param("username") String username);



    @Query("SELECT t FROM User t WHERE t.username = ?1")
    public List<User> findByUsername(String username);
}
