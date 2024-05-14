package org.ahmedukamel.shipsmarter.repository;

import jakarta.transaction.Transactional;
import org.ahmedukamel.shipsmarter.model.AccessToken;
import org.ahmedukamel.shipsmarter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, String> {
    @Transactional
    @Modifying
    @Query(value = """
            UPDATE AccessToken t
            SET t.revoked = true
            WHERE t.user = :user
            """)
    void revokeAll(@Param(value = "user") User user);
}