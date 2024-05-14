package org.ahmedukamel.shipsmarter.dto.api;

public record ApiResponse(
        boolean success,
        String message,
        Object data
) {
}