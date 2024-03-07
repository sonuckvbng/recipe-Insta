package com.sonu.recipeinsta.service;


import com.sonu.recipeinsta.Dto.requestdto.UserRequestDto;
import com.sonu.recipeinsta.entity.User;
import com.sonu.recipeinsta.exception.UserAlreadyExistException;
import com.sonu.recipeinsta.exception.UserNotFoundException;

import java.util.List;

public interface UserService {


    List<User> getUsers() throws UserNotFoundException;
    User saveUser(UserRequestDto userRequestDto) throws UserAlreadyExistException, UserNotFoundException;

    User getUserByEmailId(String emailId) throws UserNotFoundException;

    User getUserById(Long userId) throws UserNotFoundException;

    String deleteUserById(Long userId) throws UserNotFoundException;
}
