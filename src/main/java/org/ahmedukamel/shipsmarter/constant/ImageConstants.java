package org.ahmedukamel.shipsmarter.constant;

import com.nimbusds.common.contenttype.ContentType;

import java.util.List;

public interface ImageConstants {
    List<String> SUPPORTED_IMAGE_FORMATS = List.of(
            ContentType.IMAGE_PNG.getType(),
            ContentType.IMAGE_JPEG.getType()
    );
}