package com.dictionaryapp.model.binding;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRegisterBindingModel {

    @NotNull
    @Size(min = 3,max = 20,message = "Username length be between 3 and 20 characters!")
    private String username;
    @NotBlank(message = "Email cannot be empty!")
    @Email
    private String email;
    @NotNull
    @Size(min = 3,max = 20,message = "Password length be between 3 and 20 characters!")
    private String password;
    @NotNull
    @Size(min = 3,max = 20)
    private String confirmPassword;

    public UserRegisterBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
