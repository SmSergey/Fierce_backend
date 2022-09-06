package com.app.fierce_backend.domain.user;

import com.app.fierce_backend.common.ApiResponse;
import com.app.fierce_backend.domain.user.dto.LoginRequest;
import com.app.fierce_backend.domain.user.dto.RegisterAccountRequest;
import com.app.fierce_backend.domain.user.interfaces.SuccessMessages;
import com.app.fierce_backend.domain.user.interfaces.UserRepository;
import com.app.fierce_backend.helpers.ConfirmCodeGenerator;
import com.app.fierce_backend.helpers.mail.EmailSenderService;
import com.app.fierce_backend.helpers.portal.PortalConfig;
import com.app.fierce_backend.security.jwt.JwtTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final EmailSenderService emailSenderService;
    private final UserRepository userRepository;
    private final JwtTokenRepository jwtTokenRepository;
    private final PortalConfig portalConfig;

    @PostMapping(path = "auth/login")
    public ResponseEntity<String> login(@Validated LoginRequest loginRequest) {

        val tokens = jwtTokenRepository.generateTokens("");

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

    @GetMapping(path = "/users/{verification_code}")
    public RedirectView activateUser(@PathVariable("verification_code") String verificationCode) {
        System.out.println("verification code is - " + verificationCode);

        User user = userRepository.findUserByVerificationCode(verificationCode)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Code %s is not correct", verificationCode)));

        user.setIsActivated(true);
        user.setVerificationCode(null);

        userRepository.save(user);

        return new RedirectView(String.format("http://%s:%s/login/registration", portalConfig.getAddress(), portalConfig.getPort()));
    }

    @PostConstruct
    public void test() {
        System.out.println(ConfirmCodeGenerator.generateCode());
    }
}
