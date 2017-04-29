package com.web;

import org.apache.commons.codec.binary.Base64;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by duyle on 28/02/2017.
 */
public class Test {
    public static void main(String[] args) {
        File file = new File("/home/duyle/Documents/biodiversity-backend/Animal/src/main/webapp/resources/images/anorrhinustickelli.jpg");

        try {
            /*
             * Reading a Image file from file system
			 */
            FileInputStream imageInFile = new FileInputStream(file);

            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);
            System.out.println(imageData.length);
            /*
             * Converting Image byte array into Base64 String
			 */
            String imageDataString = encodeImage(imageData);
            /*
             * Converting a Base64 String into Image byte array
			 */
            byte[] imageByteArray = decodeImage(imageDataString);
            System.out.println(imageDataString);
            System.out.println(imageByteArray.length);
			/*
			 * Write a image byte array into file system
			 */
            ByteArrayInputStream stream = new ByteArrayInputStream(imageByteArray);
            BufferedImage bufferedImage;
            bufferedImage = ImageIO.read(stream);
            BufferedImage scaledImg = Scalr.resize(bufferedImage, Scalr.Mode.AUTOMATIC, 1900, 1900);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            ImageIO.setUseCache(false);
            ImageIO.write(scaledImg, "jpg", outputStream);
            byte[] imageBytes = outputStream.toByteArray();
            FileOutputStream imageOutFile = new FileOutputStream("/home/duyle/Documents/anorrhinustickelli.jpg");
            imageOutFile.write(imageByteArray);

            imageInFile.close();
            imageOutFile.close();

            System.out.println("Image Successfully Manipulated!");
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
//        ArrayList<String> list = new ArrayList<String>();
//        list.add("abc");
//        list.add("DEF");
//        list.add("ghi");
//
//        String result = StringUtils.collectionToDelimitedString(list, ", ");
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
////        System.out.println(convertTimestampToDateSql(timestamp));
//        String date = convertTimestampToDateSql("1486483200000");
//
//        System.out.println(convertFromSQLDateToJAVADate());
//
//        try {
//            String startDate = "01-02-2013";
//            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
//            java.util.Date date = null;
//            date = sdf1.parse(startDate);
//            java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
//            System.out.print(sqlStartDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


    }

    public static String convertTimestampToDateSql(String timestamp) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = new java.util.Date(Long.parseLong(timestamp));
        String stringDate = dateFormat.format(date.getTime());
        return stringDate;
    }

    public static java.util.Date convertFromSQLDateToJAVADate(
            java.sql.Date sqlDate) {
        java.util.Date javaDate = null;
        if (sqlDate != null) {
            javaDate = new Date(sqlDate.getTime());
        }
        return javaDate;
    }

    /**
     * Encodes the byte array into base64 string
     *
     * @param imageByteArray - byte array
     * @return String a {@link java.lang.String}
     */
    public static String encodeImage(byte[] imageByteArray) {
        return Base64.encodeBase64URLSafeString(imageByteArray);
    }

    /**
     * Decodes the base64 string into byte array
     *
     * @param imageDataString - a {@link java.lang.String}
     * @return byte array
     */
    public static byte[] decodeImage(String imageDataString) {
        return Base64.decodeBase64(imageDataString);
    }

}
