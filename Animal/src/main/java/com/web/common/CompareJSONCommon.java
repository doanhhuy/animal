package com.web.common;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by duyle on 06/03/2017.
 */
public class CompareJSONCommon {

    private static ArrayList<String> types = new ArrayList<String>(Arrays.asList("cat","bird",
            "squirrel", "bear", "civet", "hornbill", "toucan", "snake", "turtle", "insect", "reptile",
            "gallinaceous bird", "otter", "monkey", "primate", "wild dog", "goat", "leopard",
            "wild cat", "peacock", "bird of prey", "salamander", "parrot", "hawk", "rodent", "owl",
            "lizard"));

    private static ArrayList<String> colors = new ArrayList<String>(Arrays.asList("black", "brown",
            "yellow", "white", "pink", "red", "orange", "blue", "green", "purple", "aquatic"));

    public boolean checkType(String name){
        if(types.contains(name)){
            return true;
        } else {
            return false;
        }
    }

    public boolean checkTypeVietnamese(String name){
        if(name.matches("con.+")){
            return true;
        } else {
            return false;
        }
    }

    public boolean checkColor(String color){
        if(colors.contains(color)){
            return true;
        } else {
            return false;
        }
    }

    public boolean checkColorVietnamese(String name){
        if(name.matches("màu.+")){
            return true;
        } else {
            return false;
        }
    }

}
