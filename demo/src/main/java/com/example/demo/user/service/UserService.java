package com.example.demo.user.service;


import com.example.demo.entretien.entity.Entretien;
import com.example.demo.user.entity.User;

import java.util.List;

public interface UserService {


    public List<User> findAll();
    public void save(User user);

    public User findById(Long theId);

    public void deleteById(Long theId);

    public List<User> searchBy(String username);




}
