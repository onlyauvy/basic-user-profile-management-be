package com.funworks.usermanagement.repository;

import com.funworks.usermanagement.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Async
    Optional<User> findByEmail(@NonNull String email);

    @Async
    Optional<List<User>> findByFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCase(@Nullable String firstName, @Nullable String lastName);

    @Async
    Optional<List<User>> findDistinctByOrderByIdAsc(Pageable pageable);

    @Transactional
    @Modifying
    @Query("update User u set u.password = :password where u.email = :email and u.password=:oldPassword" )
    int changePassword(@NonNull @Param("password") String password, @NonNull @Param("oldPassword") String oldPassword, @NonNull @Param("email") String email);

    @Transactional
    @Modifying
    @Query("update User u set u.profilePicture = :profilePicture where u.email = :email")
    int uploadPhoto(@NonNull @Param("profilePicture") byte[] profilePicture, @NonNull @Param("email") String email);

}