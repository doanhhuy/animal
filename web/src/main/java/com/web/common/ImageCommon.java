package com.web.common;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by duyle on 3/9/17.
 */
public class ImageCommon {

    /**
     * Encodes the byte array into base64 string
     *
     * @param imageByteArray - byte array
     * @return String a {@link String}
     */
    public String encodeImage(byte[] imageByteArray) {
        return Base64.encodeBase64URLSafeString(imageByteArray);
    }

    /**
     * Decodes the base64 string into byte array
     *
     * @param imageDataString - a {@link String}
     * @return byte array
     */
    public byte[] decodeImage(String imageDataString) {
        return Base64.decodeBase64(imageDataString);
    }

}
