package com.example.demo.services;

import com.example.demo.models.UserModel;
import com.example.demo.models.UserWithReservationsModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface IUserService extends UserDetailsService {

    List<UserModel> Index();
    UserModel GetByEmail(String email);
    List<UserModel> GetAllByFirstName(String firstName);

    Optional<UserModel> GetUser(Integer userId);

    UserWithReservationsModel GetUserAndReservations(String email);

    UserModel CreateUser(UserModel user);
    UserModel EditUser(UserModel user);

    void DeleteUser(Integer id);


}
