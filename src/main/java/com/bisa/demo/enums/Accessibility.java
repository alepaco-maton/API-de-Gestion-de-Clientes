/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bisa.demo.enums;

/**
 *
 * @author alepaco.com
 */
public enum Accessibility {

    BUENA, REGULAR, MALA, NULA;

    public static boolean isValid(String accessibility) {
        return BUENA.name().equals(accessibility)
                || REGULAR.name().equals(accessibility)
                || MALA.name().equals(accessibility)
                || NULA.name().equals(accessibility);
    }

}
