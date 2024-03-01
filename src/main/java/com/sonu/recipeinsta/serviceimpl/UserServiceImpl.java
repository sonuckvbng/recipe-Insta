package com.sonu.recipeinsta.serviceimpl;

import com.sonu.recipeinsta.dto.requestdto.UserRquestDto;
import com.sonu.recipeinsta.entity.User;
import com.sonu.recipeinsta.exception.UserAlreadyExistException;
import com.sonu.recipeinsta.exception.UserNotFoundException;
import com.sonu.recipeinsta.repository.UserRepository;
import com.sonu.recipeinsta.service.UserService;
import com.sonu.recipeinsta.utils.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * @return List<User>
     */
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * used to save user details
     * @param userRquestDto
     * @return User
     */
    @Override
    public User saveUser(UserRquestDto userRquestDto) throws UserAlreadyExistException {

        User isUserExist = getUserByEmailId(userRquestDto.getEmailId());
        if (!ObjectUtils.isEmpty(isUserExist)){
            throw new UserAlreadyExistException("This user already exist" + userRquestDto.getEmailId());
        }
       return userRepository.save(User.builder().userFullName(userRquestDto.getUserFullName())
                .dob(DateFormatter.getLocalDate(userRquestDto.getDob()))
                .emailId(userRquestDto.getEmailId())
                .password(userRquestDto.getPassword())
                .build());
    }

    /**
     * @param emailId
     * @return
     */
    @Override
    public User getUserByEmailId(String emailId) {
        return userRepository.findByEmailId(emailId);
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public User getUserById(Long userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
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
