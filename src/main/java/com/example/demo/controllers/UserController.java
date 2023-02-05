package com.example.demo.controllers;

import com.example.demo.models.UserModel;
import com.example.demo.models.UserWithReservationsModel;
import com.example.demo.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("user")
public class UserController {
    private final IUserService userService;

    private final PasswordEncoder passwordEncoderService;

    public UserController(IUserService userService, PasswordEncoder passwordEncoderService) {
        this.userService = userService;
        this.passwordEncoderService = passwordEncoderService;
    }

    @GetMapping("index")
    @CrossOrigin("*")
    public List<UserModel> Index() {return userService.Index();}

    @GetMapping("get/user/by/{email}")   // Ovde koristimo path varijablu
    @CrossOrigin("*")
    public UserModel GetByEmail(@PathVariable("email") String email) { return userService.GetByEmail(email); }

    @GetMapping("get/all/by/name")
    @CrossOrigin("*")
    public List<UserModel>  GetAllByFirstName(String firstName) { return userService.GetAllByFirstName(firstName); }

    @GetMapping("get/user/reservations/by/email")
    @CrossOrigin("*")
    public UserWithReservationsModel GetUserAndReservationsById(String email) { return userService.GetUserAndReservations(email); }

    @GetMapping("get/user/by/id")
    @CrossOrigin("*")
    public Optional<UserModel> GetUser(Integer userId) { return userService.GetUser(userId); }

    @PostMapping("create")
    @CrossOrigin("*")
    public ResponseEntity<?> Insert(@RequestBody @Valid UserModel model, BindingResult result) {

        if (result.hasErrors())
        {
            return new ResponseEntity<String>("Creating user unsuccessful!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try
        {
            model.setPassword(passwordEncoderService.encode(model.getPassword()));
            return new ResponseEntity<UserModel>(userService.CreateUser(model), HttpStatus.OK);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<String>("Creating user unsuccessful!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("edit")
    @CrossOrigin("*")
    public ResponseEntity<?> Edit(@RequestBody @Valid UserModel model, BindingResult result) {

        if (result.hasErrors())
        {
            return new ResponseEntity<String>("Editing user unsuccessful!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try
        {
            return new ResponseEntity<UserModel>(userService.EditUser(model), HttpStatus.OK);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<String>("Editing user unsuccessful!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("delete")
    @CrossOrigin("*")
    public void Delete(@RequestBody UserModel model) {
        userService.DeleteUser(model.getUserId());
    }


}
