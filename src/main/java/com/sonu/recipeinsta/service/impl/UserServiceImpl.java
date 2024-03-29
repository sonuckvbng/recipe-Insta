package com.sonu.recipeinsta.service.impl;

import com.sonu.recipeinsta.config.JwtProvider;
import com.sonu.recipeinsta.dto.requestdto.UserRequestDto;

import com.sonu.recipeinsta.entity.User;
import com.sonu.recipeinsta.exception.UserAlreadyExistException;
import com.sonu.recipeinsta.exception.UserNotFoundException;
import com.sonu.recipeinsta.repository.UserRepository;
import com.sonu.recipeinsta.service.UserService;
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

    @Autowired
    private JwtProvider jwtProvider;

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
     * @param userRquestDto
     * @return User
     */
    @Override
    public User saveUser(UserRequestDto userRquestDto) throws UserAlreadyExistException {

        User isUserExist = userRepository.findByEmailId(userRquestDto.getEmailId());
        if (!ObjectUtils.isEmpty(isUserExist)) {
            throw new UserAlreadyExistException("This user already exist" + userRquestDto.getEmailId());
        }
        return userRepository.save(User.builder().userFullName(userRquestDto.getUserFullName())
//                .dob(DateFormatter.getLocalDate(userRquestDto.getDob()))
                .emailId(userRquestDto.getEmailId())
                .password(userRquestDto.getPassword())
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

    /**
     * @param jwt
     * @return User
     * @throws Exception
     */
    @Override
    public User findUserByJwt(String jwt) throws Exception {
        String emailFromJwtToken = jwtProvider.getEmailFromJwtToken(jwt);
        if (emailFromJwtToken==null){
            throw new Exception("Provide valid jwt token");
        }

        User user = userRepository.findByEmailId(emailFromJwtToken);
        if (user==null){
            throw new Exception("User not found with email: " + emailFromJwtToken);
        }
        return user;
    }
}
