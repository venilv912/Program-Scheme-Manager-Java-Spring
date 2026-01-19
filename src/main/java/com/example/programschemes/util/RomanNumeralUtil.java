package com.example.programschemes.util;

public class RomanNumeralUtil {

    private RomanNumeralUtil() {
        // Prevent instantiation
    }

    public static String toRoman(int number) {
        if (number <= 0 || number > 3999) {
            throw new IllegalArgumentException("Number out of range for Roman numerals");
        }

        int[] values = {
                1000, 900, 500, 400,
                100, 90, 50, 40,
                10, 9, 5, 4, 1
        };

        String[] symbols = {
                "M", "CM", "D", "CD",
                "C", "XC", "L", "XL",
                "X", "IX", "V", "IV", "I"
        };

        StringBuilder roman = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (number >= values[i]) {
                number -= values[i];
                roman.append(symbols[i]);
            }
        }
        return roman.toString();
    }
}
