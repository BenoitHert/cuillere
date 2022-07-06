package com.salted_broccoli.cuillere.Service.form;

import lombok.Data;

@Data
public class RegistrationForm {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;

}
