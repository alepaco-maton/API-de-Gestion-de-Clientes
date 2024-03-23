/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.commons;

/**
 *
 * @author alepaco.com
 */
public class AppTools {

    public static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    public static boolean isTheLengthOfTheStringValid(String value, int min, int max) {
        return min <= value.length() && value.length() <= max;
    }

}
