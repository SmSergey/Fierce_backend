package com.app.fierce_backend.domain.user;

import com.app.fierce_backend.common.ApiResponse;
import com.app.fierce_backend.domain.chat.interfaces.ChatsDto;
import com.app.fierce_backend.domain.user.dto.LoginRequest;
import com.app.fierce_backend.domain.user.dto.RegisterAccountRequest;
import com.app.fierce_backend.domain.user.dto.UpdateAccountInfoRequest;
import com.app.fierce_backend.domain.user.interfaces.SuccessMessages;
import com.app.fierce_backend.domain.user.interfaces.UserDto;
import com.app.fierce_backend.domain.user.interfaces.UserRepository;
import com.app.fierce_backend.helpers.ConfirmCodeGenerator;
import com.app.fierce_backend.helpers.mail.EmailSenderService;
import com.app.fierce_backend.helpers.portal.PortalConfig;
import com.app.fierce_backend.security.jwt.JwtTokenRepository;
import com.app.fierce_backend.security.jwt.JwtTokenRepository.Tokens;
import com.app.fierce_backend.security.services.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final EmailSenderService emailSenderService;
    private final UserRepository userRepository;
    private final JwtTokenRepository jwtTokenRepository;
    private final PortalConfig portalConfig;
    private final AuthenticationManager authenticationManager;
    private final ProjectionFactory projectionFactory = new SpelAwareProxyProjectionFactory();


    @PostMapping(path = "auth/login")
    public ResponseEntity<String> login(@Validated @RequestBody LoginRequest loginRequest) {

        val auth = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );

        authenticationManager.authenticate(auth);

        User user = userRepository.findUserByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(loginRequest.getEmail()));

        val tokens = jwtTokenRepository.generateTokens(String.valueOf(user.getId()));

        return new ApiResponse()
                .addField("accessToken", tokens.getAccessToken())
                .addField("refreshToken", tokens.getRefreshToken())
                .setStatus(200).build();
    }


    @PostMapping(path = "/auth/register")
    public ResponseEntity<String> registerAccount(@Validated @RequestBody RegisterAccountRequest request) {
        System.out.println("request");
        String code = ConfirmCodeGenerator.generateCode();

        User userToCreate = new User();
        userToCreate.setVerificationCode(code);
        userToCreate.setEmail(request.getEmail());

        userRepository.save(userToCreate);

        emailSenderService.sendConfirmAccountEmail(userService.generateConfirmEmailLink(code), request.getEmail());

        return new ApiResponse()
                .setStatus(200)
                .setMessage(SuccessMessages.CONFIRM_MESSAGE_SENT).build();
    }

    @PatchMapping("/auth/register")
    public ResponseEntity<String> updateAccountInfo(@Validated @RequestBody UpdateAccountInfoRequest request) {
        User user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with this email %s doesn't exists", request.getEmail())));

        if (user.getVerificationCode() == null) throw new ValidationException("account already created");
        if (!user.getVerificationCode().equals(request.getVerificationCode()) && user.getVerificationCode() != null) {
            throw new ValidationException("Wrong verification code");
        }

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setVerificationCode(null);
        user.setIsActivated(true);

        userRepository.save(user);

        return new ApiResponse()
                .setStatus(200)
                .setMessage("Account activated").build();
    }

    @GetMapping(path = "/users/{verification_code}")
    public RedirectView activateUser(@PathVariable("verification_code") String verificationCode) {
        User user = userRepository.findUserByVerificationCode(verificationCode)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Code %s is not correct", verificationCode)));

        user.setIsActivated(true);
        userRepository.save(user);

        System.out.println("code is " + user.getVerificationCode());

        return new RedirectView(String.format("http://%s:%s/login/registration?email=%s&code=%s",
                portalConfig.getAddress(),
                portalConfig.getPort(),
                user.getEmail(),
                user.getVerificationCode()
        ));
    }

    @GetMapping("/me")
    public UserDto getMe() {
        User user = userRepository.findById(
                Long.valueOf(SecurityService.getCurrentUserId())
        ).orElseThrow(() -> new EntityNotFoundException("user wasn't found "));

        return projectionFactory.createProjection(UserDto.class, user);
    }

    @PostConstruct
    public void test() {
        System.out.println(ConfirmCodeGenerator.generateCode());
    }
}
