package com.point.control.controleponto.controller;

import com.point.control.controleponto.dtos.UserRecordDto;
import com.point.control.controleponto.models.UserModel;
import com.point.control.controleponto.services.UserService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserRecordDto userRecordDto) {
        UserModel userModel = userService.saveUser(userRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> getAllUsers() {
        List<UserModel> userModels = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userModels);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value="id") UUID id) {
        Optional<UserModel> user = userService.getUserById(id);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(user.get());
    }

    @PutMapping("users/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value="id") UUID id, @RequestBody @Valid UserRecordDto userRecordDto) {
        UserModel updateUser = userService.updateUser(id, userRecordDto);
        if (updateUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(updateUser);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value="id") UUID id) {
        if (!userService.deleteUserById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso");
    }
}
