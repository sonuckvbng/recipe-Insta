package com.sonu.recipeinsta.service;

import com.sonu.recipeinsta.dto.requestdto.UserRquestDto;
import com.sonu.recipeinsta.entity.User;
import com.sonu.recipeinsta.exception.UserAlreadyExistException;
import com.sonu.recipeinsta.exception.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {


    List<User> getUsers() throws UserNotFoundException;
    User saveUser(UserRquestDto userRquestDto) throws UserAlreadyExistException, UserNotFoundException;

    User getUserByEmailId(String emailId) throws UserNotFoundException;

    User getUserById(Long userId) throws UserNotFoundException;

    String deleteUserById(Long userId) throws UserNotFoundException;
}
