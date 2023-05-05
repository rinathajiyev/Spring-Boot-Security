package com.company.securitydemo.auth;

import com.company.securitydemo.config.*;
import com.company.securitydemo.user.*;
import com.company.securitydemo.user.User;
import lombok.*;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request){
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var token = jwtService.generateToken(user);

        return AuthenticationResponse
                .builder()
                .token(token)
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
       var user = userRepository.findByEmail(request.getEmail())
               .orElseThrow();
       var token  = jwtService.generateToken(user);

       return AuthenticationResponse.builder()
               .token(token)
               .build();
    }
}
