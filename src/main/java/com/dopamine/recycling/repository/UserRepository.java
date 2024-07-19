package com.dopamine.recycling.repository;

import com.dopamine.recycling.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserRepository  extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByNickname(String nickname);

    @Modifying
    @Query("UPDATE User u SET u.deleteDate = :now WHERE u.id = :id")
    void updateDeleteDateById(@Param("id") Long id, @Param("now") LocalDateTime now);

    @Modifying
    @Query("UPDATE User u SET u.isPaused = CASE WHEN u.isPaused = true THEN false ELSE true END WHERE u.id = :id")
    void updateIsPausedById(@Param("id") Long id);

    @Modifying
    @Query("UPDATE User u SET u.unpauseDate = :afterAWeek WHERE u.id = :id")
    void updateUnpauseDateById(@Param("id") Long id, @Param("afterAWeek") LocalDateTime afterAWeek);

    @Modifying
    @Query("UPDATE User u SET u.nickname = :nickname WHERE u.id = :id")
    User updateNicknameById(@Param("id") Long id, @Param("nickname") String nickname);

    @Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.id = :id")
    void updatePasswordById(@Param("id") Long id, @Param("password") String password);

    @Modifying
    @Query("UPDATE User u SET u.address = :address, u.postcode = :postcode WHERE u.id = :id")
    User updateAddressById(@Param("id") Long id, @Param("address") String address, @Param("postcode") String postcode);
}
