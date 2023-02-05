package com.example.demo.services;

import com.example.demo.entity.UserEntity;
import com.example.demo.models.UserModel;
import com.example.demo.models.UserWithReservationsModel;
import com.example.demo.repositories.IUserRepository;
import com.example.demo.repositories.IUserReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service()  // Za automatsko ozicavanje i otkrivanje zrna
public class UserService implements IUserService{


    private IUserRepository userRepository;
    private IUserReservationRepository userReservationRepository;

    private ModelMapper mapper;

    public UserService(IUserRepository userRepository, IUserReservationRepository userReservationRepository, ModelMapper mapper)
    {
        this.userRepository = userRepository;
        this.userReservationRepository = userReservationRepository;
        this.mapper = mapper;
    }

    @Override
    public List<UserModel> Index() {
        // mapiramo  tako da nam je origin metoda findAll(), a destination UserModel klasa
        // findAll() vraca listu entity-ja i mi je prebacujemo u listu modela
        var models = mapper.map( userRepository.findAll(), new ArrayList<UserModel>().getClass());
        return models;
    }

    @Override
    public UserModel GetByEmail(String email) {
        return mapper.map(userRepository.findByEmail(email), UserModel.class);
    }

    @Override
    public List<UserModel> GetAllByFirstName(String firstName) {
        return mapper.map(userRepository.findAllByFirstName(firstName), new ArrayList<UserModel>().getClass());
    }

    @Override
    public Optional<UserModel> GetUser(Integer userId) {

        var userFromDb = userRepository.findById(userId);

        if (userFromDb == null)
        {
             return null;
        }

        var userModel =  mapper.map(userFromDb, UserModel.class);
        return Optional.of(userModel);
    }

    @Override
    public UserWithReservationsModel GetUserAndReservations(String email) {
        return mapper.map(userReservationRepository.findByEmail(email), UserWithReservationsModel.class);
    }

    @Override
    public UserModel CreateUser(UserModel user) {
        var createdUser = userRepository.save(mapper.map(user, UserEntity.class));
        return mapper.map( createdUser, UserModel.class );
    }

    @Override
    public UserModel EditUser(UserModel user) {

        // Nalazimo starog usera, moze da vrati u null pa stavljamo Oprional
        var userFromDb = userRepository.findById(user.getUserId());


        if (userFromDb !=null)
        {
            user.setGender(userFromDb.get().getGender());
            user.setPassword(userFromDb.get().getPassword());
            user.setRoleId(userFromDb.get().getRoleId());
            user.setTimeOfCreation(userFromDb.get().getTimeOfCreation());
        }

        var editedUser = userRepository.save(mapper.map(user, UserEntity.class));
        return mapper.map( editedUser, UserModel.class );
    }

    @Override
    public void DeleteUser(Integer id) {
        userRepository.deleteById(id);
    }


    @Override
    /* Ova metoda mora da vrati UserDetails i nju moramo da override-ujemo kada implementiramo UserDetailService
    / nakon sto se ova metoda zavrsi i Spring moze da procita usera, on dozvoljava pristup metodama u kontrolerima */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserModel user = mapper.map(userRepository.findByEmail(username), UserModel.class);

        if (user == null){
            throw new UsernameNotFoundException("User not found!");
        }

        List authorities = Arrays.asList(new SimpleGrantedAuthority("user"));

        //Vracamo korisnikov email, password i role
        return new User(user.getEmail(), user.getPassword(), authorities);
    }

}

