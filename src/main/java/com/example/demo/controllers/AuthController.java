package com.example.demo.controllers;

import com.example.demo.models.UserModel;
import com.example.demo.services.IUserService;
import com.example.demo.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {
    private final IUserService userService;
    private AuthenticationManager authenticationManager; // ovo je Springov servis
    private JwtUtil jwtTokenUtil; // klasa koja sadrzi metode za kreiranje i validaciju tokena
    private final PasswordEncoder passwordEncoderService;

    public AuthController(IUserService userService, JwtUtil jwtTokenUtil, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoderService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoderService = passwordEncoderService;
    }

    @PostMapping("registration")
    @CrossOrigin("*")
    public ResponseEntity<?> Registration(@RequestBody @Valid UserModel model, BindingResult result) {

        if (result.hasErrors())
        {
            // Ne treba vracati konkretnu gresku da na primer user vec postoji zbog zloupotrebe
            return new ResponseEntity<String>("Registration unsuccessful!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        model.setPassword(passwordEncoderService.encode(model.getPassword()));
        System.out.println("Sifra: "  + model.getPassword());
        // U koliko se pojavi neki exception koji baca CreateUser()
        try
        {
            // var koristimo kada nismo sigurni kog je tipa podatak, pa oznacimo sa var
            var user = userService.CreateUser(model);
            return new ResponseEntity<String>("Registration successful!", HttpStatus.OK);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<String>("Registration unsuccessful!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping ("login")
    @CrossOrigin("*")
    public ResponseEntity<?> Login(@RequestBody UserModel model) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(model.getEmail(), model.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        // Ako je autorizacija prosla vraca userDetails
        final UserDetails userDetails = userService.loadUserByUsername(model.getEmail());

        // i kreira token
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(jwt); // vracamo samo nas token - jason web token
    }

}
