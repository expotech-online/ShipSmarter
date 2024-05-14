package org.ahmedukamel.shipsmarter.repository;

import org.ahmedukamel.shipsmarter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = """
            SELECT u
            FROM User u
            WHERE u.provider = 'LOCAL'
            AND u.email = LOWER(:email)
            """)
    Optional<User> findByLocalEmail(@Param(value = "email") String email);

    default boolean existLocalEmail(String email) {
        return findByLocalEmail(email).isPresent();
    }
}