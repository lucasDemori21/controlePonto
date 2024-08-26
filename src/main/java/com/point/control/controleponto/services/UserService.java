package com.point.control.controleponto.services;

import com.point.control.controleponto.dtos.UserRecordDto;
import com.point.control.controleponto.models.UserModel;
import com.point.control.controleponto.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserModel saveUser(UserRecordDto userRecordDto) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDto, userModel);
        return userRepository.save(userModel);
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserModel> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public UserModel updateUser(UUID id, UserRecordDto userRecordDto) {
        Optional<UserModel> userModel = userRepository.findById(id);
        if (userModel.isEmpty()) {

            return null;
        }
        UserModel user = userModel.get();
        BeanUtils.copyProperties(userRecordDto, user);
        return userRepository.save(user);
    }

    public boolean deleteUserById(UUID id) {
        Optional<UserModel> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }
}
