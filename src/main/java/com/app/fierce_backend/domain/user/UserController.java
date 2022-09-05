package com.app.fierce_backend.domain.user;

import com.app.fierce_backend.common.ApiResponse;
import com.app.fierce_backend.domain.user.dto.LoginRequest;
import com.app.fierce_backend.domain.user.dto.RegisterAccountRequest;
import com.app.fierce_backend.domain.user.interfaces.SuccessMessages;
import com.app.fierce_backend.helpers.ConfirmCodeGenerator;
import com.app.fierce_backend.helpers.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(path = "auth/login")
    public ResponseEntity<String> login(@Validated LoginRequest loginRequest) {


        return new ApiResponse()
                .addField("accessToken", "access_token")
                .addField("refreshToken", "ref_token")
                .setStatus(200).build();
    }


    @PostMapping(path = "auth/register")
    public String registerAccount(@Validated RegisterAccountRequest request) {
        String code = ConfirmCodeGenerator.generateCode();

        User userToCreate = new User();
        userToCreate.setConfirmCode(code);
        userToCreate.setEmail(request.getEmail());
        userToCreate.setPassword(passwordEncoder.encode(request.getPassword()));

        EmailSenderService.sendConfirmAccountEmail(code, request.getEmail());
        return SuccessMessages.CONFIRM_MESSAGE_SENT;
    }
}
