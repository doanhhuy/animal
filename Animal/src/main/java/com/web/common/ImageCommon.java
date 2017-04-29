package com.web.common;

import org.apache.commons.codec.binary.Base64;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by duyle on 3/9/17.
 */
public class ImageCommon {

    /**
     * Encodes the byte array into base64 string
     *
     * @param imageByteArray - byte array
     * @return String a {@link java.lang.String}
     */
    public String encodeImage(byte[] imageByteArray) {
        return Base64.encodeBase64URLSafeString(imageByteArray);
    }

    /**
     * Decodes the base64 string into byte array
     *
     * @param imageDataString - a {@link java.lang.String}
     * @return byte array
     */
    public byte[] decodeImage(String imageDataString) {
        return Base64.decodeBase64(imageDataString);
    }

    public byte[] resizeImage(byte[] imageByteArray) {
        try {
            ByteArrayInputStream stream = new ByteArrayInputStream(imageByteArray);
            BufferedImage bufferedImage;
            bufferedImage = ImageIO.read(stream);
            BufferedImage scaledImg = Scalr.resize(bufferedImage, Scalr.Mode.AUTOMATIC, 1900, 1900);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            ImageIO.setUseCache(false);
            ImageIO.write(scaledImg, "jpg", outputStream);
            byte[] imageBytes = outputStream.toByteArray();
            return imageBytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
