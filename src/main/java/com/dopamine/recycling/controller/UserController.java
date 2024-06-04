package com.dopamine.recycling.controller;

import com.dopamine.recycling.domain.dto.UserRequestDto;
import com.dopamine.recycling.domain.dto.UserResponseDto;
import com.dopamine.recycling.domain.entity.User;
import com.dopamine.recycling.security.CustomUserDetails;
import com.dopamine.recycling.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> RegisterUser(@RequestBody UserRequestDto request) {
        if(userService.isExistEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists: " + request.getEmail());
        } else {
            User user = userService.registerUser(request);
            UserResponseDto response = user.toResponse();

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
    }

    @PutMapping("/new/nickname")
    public ResponseEntity<?> UpdateNickname(@RequestBody UserRequestDto request, @AuthenticationPrincipal CustomUserDetails userDetails) {
        String userNickname = userDetails.getUsername();

        if (userService.isDuplicateNickname(request.getNickname(), userNickname)) {
            return ResponseEntity.badRequest().body("Invalid Nickname: " + request.getNickname());
        }

        User user = userService.updateNickname(request);
        UserResponseDto response = user.toResponse();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/new/password")
    public ResponseEntity<?> UpdatePassword(@RequestBody UserRequestDto request, @AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userService.isSamePassword(request.getPassword(), userDetails.getPassword())) {
            return ResponseEntity.badRequest().body("New password cannot be the same as the current password");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        userService.updatePassword(userDetails.getUserId(), encodedPassword);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/secession")
    public ResponseEntity<?> setDeleteDate(@RequestBody UserRequestDto request, @AuthenticationPrincipal CustomUserDetails userDetails) {
        if(userService.isSamePassword(request.getPassword(), userDetails.getPassword())) {
            userService.updateDeleteDate(request);

            return ResponseEntity.status(HttpStatus.OK).build();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @DeleteMapping("/secession/permanent")
    public ResponseEntity<?> DeleteUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        if(userService.passedDeleteDate(userDetails.getDeleteDate())) {
            userService.deleteUserById(userDetails.getUserId());

            return ResponseEntity.status(HttpStatus.OK).build();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PutMapping("/pause")
    public ResponseEntity<?> pauseUser(@RequestBody UserRequestDto request) {
        userService.pauseUser(request);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/unpause-date")
    public ResponseEntity<?> updateUnpauseDate(@RequestBody UserRequestDto request) {
        userService.updateUnpauseDate(request);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
