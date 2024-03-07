package com.sonu.recipeinsta.service.impl;


import com.sonu.recipeinsta.Dto.requestdto.UserRequestDto;
import com.sonu.recipeinsta.entity.User;
import com.sonu.recipeinsta.exception.UserAlreadyExistException;
import com.sonu.recipeinsta.exception.UserNotFoundException;
import com.sonu.recipeinsta.repository.UserRepository;
import com.sonu.recipeinsta.service.UserService;
import com.sonu.recipeinsta.utils.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * @return List<User>
     */
    @Override
    public List<User> getUsers() throws UserNotFoundException {
        List<User> userList = userRepository.findAll();
        if (CollectionUtils.isEmpty(userList)) {
            throw new UserNotFoundException("No data found for Users");
        }
        return userList;
    }

    /**
     * used to save user details
     *
     * @param userRequestDto
     * @return User
     */
    @Override
    public User saveUser(UserRequestDto userRequestDto) throws UserAlreadyExistException {

        User isUserExist = userRepository.findByEmailId(userRequestDto.getEmailId());
        if (!ObjectUtils.isEmpty(isUserExist)) {
            throw new UserAlreadyExistException("This user already exist" + userRequestDto.getEmailId());
        }
        return userRepository.save(User.builder().userFullName(userRequestDto.getUserFullName())
                .dob(DateFormatter.getLocalDate(userRequestDto.getDob()))
                .emailId(userRequestDto.getEmailId())
                .password(userRequestDto.getPassword())
                .build());
    }

    /**
     * @param emailId
     * @return
     */
    @Override
    public User getUserByEmailId(String emailId) throws UserNotFoundException {
        User user = userRepository.findByEmailId(emailId);
        if (Objects.nonNull(user)) {
            return user;
        }
        throw new UserNotFoundException("User not found with emailId: " + emailId);
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public User getUserById(Long userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        }
        throw new UserNotFoundException("User not found for userId:" + userId);
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public String deleteUserById(Long userId) throws UserNotFoundException {
        User userById = getUserById(userId);
        userRepository.deleteById(userId);
        return "User deleted successfully for userId:" + userId;
    }
}
