package org.ahmedukamel.shipsmarter.service.token;

import org.ahmedukamel.shipsmarter.model.User;

public interface IAccessTokenService {
    String generate(User user);

    User getUser(String token);

    void revoke(String token);

    void revokeAll(User user);
}