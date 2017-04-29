package com.web.common;

import java.util.Random;

/**
 * Created by duyle on 13/02/2017.
 */
public class RandomKey {

    public int rand(int min, int max) {
        try {
            Random rn = new Random();
            int range = max - min + 1;
            int randomNum = min + rn.nextInt(range);
            return randomNum;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
