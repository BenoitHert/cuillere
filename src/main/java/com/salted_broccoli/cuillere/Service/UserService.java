package com.salted_broccoli.cuillere.Service;

import com.salted_broccoli.cuillere.Model.User;
import com.salted_broccoli.cuillere.Repository.UserRepository;
import com.salted_broccoli.cuillere.Service.form.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service("User Service")
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

    public User registration(RegistrationForm form){

        if(form.getPassword().equals(form.getConfirmPassword())) {

//            if(userRepository.findUserByEmail(form.getEmail()).equals(null)){

                User user = new User();
                user.setFirstName(form.getFirstName());
                user.setLastName(form.getLastName());
                user.setEmail(form.getEmail());
                user.setPassword(passwordEncoder.encode(form.getPassword()));
                return userRepository.save(user);
//            }
//            else {throw new ArithmeticException("Cette adresse mail est déjà utilisée");}
        }
            else{throw new ArithmeticException("Veuillez confirmer le même mot de passe");}
        }

    public User findUser(){
        //Spring Security-side identifier; tied to the user's session => user's email
        String connectedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        //Fetch according to the email
        return userRepository.findUserByEmail(connectedUserEmail);
    }

    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        userRepository.delete(user);
    }
}
