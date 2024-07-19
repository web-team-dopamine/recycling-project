package com.dopamine.recycling.service;

import com.dopamine.recycling.domain.constant.Role;
import com.dopamine.recycling.domain.constant.UserType;
import com.dopamine.recycling.domain.dto.UserRequestDto;
import com.dopamine.recycling.domain.entity.User;
import com.dopamine.recycling.repository.UserRepository;
import com.dopamine.recycling.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    public User registerUser(UserRequestDto request) {

        if(request == null) return null;

        User user = User.builder()
                .name(request.getName())
                .nickname(request.getNickname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER.getName())
                .type(UserType.USER_SEED.getUserType())
                .address(request.getAddress())
                .postcode(request.getPostcode())
                .point(0L)
                .isPaused(false)
                .unpauseDate(null)
                .deleteDate(null)
                .build();

        return userRepository.save(user);
    }

    public User updateNickname(UserRequestDto request) {
        return userRepository.updateNicknameById(request.getId(), request.getNickname());
    }

    public void updatePassword(Long id, String password) {
        userRepository.updatePasswordById(id, password);
    }

    public User updateAddress(UserRequestDto request) {
        return userRepository.updateAddressById(request.getId(), request.getAddress(), request.getPostcode());
    }

    public void updateDeleteDate(UserRequestDto request) {
        userRepository.updateDeleteDateById(request.getId(), LocalDateTime.now());
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public void updateUnpauseDate(UserRequestDto request) {
        userRepository.updateUnpauseDateById(request.getId(), LocalDateTime.now().plusWeeks(1));
    }

    public void pauseUser(UserRequestDto request) {
        userRepository.updateIsPausedById(request.getId());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("email not found : "+ email));
        return new CustomUserDetails(user);
    }

    public boolean isDuplicateNickname(String newNickname, String currentNickname) {
        return newNickname.equals(currentNickname) || isExistNickname(newNickname);
    }

    public boolean isSamePassword(String requestPassword, String userPassword) {
        return passwordEncoder.matches(requestPassword, userPassword);
    }

    public boolean passedDeleteDate(LocalDateTime deleteDate) {
        return deleteDate.isAfter(LocalDateTime.now());
    }

    public boolean isExistEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public boolean isExistNickname(String nickname) {
        return userRepository.findByNickname(nickname).isPresent();
    }
}
