package com.sonu.recipeinsta.controller;


import com.sonu.recipeinsta.config.JwtProvider;
import com.sonu.recipeinsta.dto.requestdto.LoginRequest;
import com.sonu.recipeinsta.dto.responsedto.AuthResponse;
import com.sonu.recipeinsta.entity.User;
import com.sonu.recipeinsta.repository.UserRepository;
import com.sonu.recipeinsta.service.CustomUserDetailsService;
import com.sonu.recipeinsta.utils.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/auth/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception {

        String email = user.getEmailId();
        String password = user.getPassword();
        String fullName = user.getUserFullName();

        User isExistEmail = userRepository.findByEmailId(email);
        if (isExistEmail != null) {
            throw new Exception("Email is already used with another account");
        }

        User newUser = User.builder()
                .emailId(email)
                .password(passwordEncoder.encode(password))
                .userFullName(fullName)
//                .dob(DateFormatter.getLocalDateDash(user.getDob().toString()))
                .build();
        User savedUser = userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        return AuthResponse.builder().jwt(token)
                .message("Sign up success")
                .build();
    }

    @PostMapping("/auth/signin")
    public AuthResponse signInHandler(@RequestBody LoginRequest loginRequest) {

        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        return AuthResponse.builder().jwt(token)
                .message("Sign in success")
                .build();
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("User not found");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
