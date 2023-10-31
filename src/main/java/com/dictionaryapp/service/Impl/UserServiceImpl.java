package com.dictionaryapp.service.Impl;

import com.dictionaryapp.model.binding.UserLoginBindingModel;
import com.dictionaryapp.model.binding.UserRegisterBindingModel;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.helper.CurrentUser;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    private PasswordEncoder passwordEncoder;

    private CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }


    @Override
    public void register(UserRegisterBindingModel userRegisterBindingModel) {
        if (userRegisterBindingModel == null) {
            return;
        }

        String username = userRegisterBindingModel.getUsername();

        if (userRepository.findByUsername(username) != null) {
            return;
        }

        User user = modelMapper.map(userRegisterBindingModel, User.class);

        user.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));

        userRepository.save(user);

    }

    @Override
    public boolean findByUsernameAndPassword(String username, String password) {
        User user =userRepository.findByUsername(username);

        if (user == null){
            return false;
        }

        return passwordEncoder.matches(password,user.getPassword());
    }

    @Override
    public void login(UserLoginBindingModel userLoginBindingModel) {

        User user = userRepository.findByUsername(userLoginBindingModel.getUsername());

        currentUser.setId(user.getId());
        currentUser.setUsername(user.getUsername());
        currentUser.setLogged(true);

    }


}
