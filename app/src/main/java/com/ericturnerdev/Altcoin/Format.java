package com.ericturnerdev.Altcoin;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Methods for formatting a price/number
 */
public class Format {

    public static String formatLong(double d, String secondary) {

        String fd;

        //If BTC
        if (secondary.toUpperCase().equals("BTC")) {

            //See if it should be in mBTC, Satoshi etc.
            if (d < 0.00001) {
                fd = removeZeros(String.format("%.3f", (d * 100000000))) + " " + "satoshi";
            } else if (d < 0.01) {
                fd = removeZeros(String.format("%.3f", (d * 1000))) + " " + "mBTC";
            } else {
                fd = removeZeros(String.format("%.3f", (d))) + " " + "BTC";
            }

            return fd;

        }

        //If LTC
        else if (secondary.toUpperCase().equals("LTC")) {

            if (d < 0.01) {
                fd = removeZeros(String.format("%.3f", (d * 1000))) + " " + "mLTC";
            } else {
                fd = removeZeros(String.format("%.3f", (d))) + " " + "LTC";
            }

            return fd;

        }

        //If XMP
        else if (secondary.toUpperCase().equals("XPM")) {

            if (d < 0.01) {
                fd = removeZeros(String.format("%.3f", (d * 1000))) + " " + "mXPM";
            } else {
                fd = removeZeros(String.format("%.3f", (d))) + " " + "XPM";
            }

            return fd;

        }

        //If USD
        else if (secondary.toUpperCase().equals("USD")) {

            if (d < 1) {
                fd = removeZeros(String.format("%.2f", (d * 100))) + " " + "cents";
            } else {
                fd = removeZeros(String.format("%.2f", (d))) + " " + "USD";
            }

            return fd;

        }

        //If EUR
        else if (secondary.toUpperCase().equals("EUR")) {

            if (d < 1) {
                fd = removeZeros(String.format("%.2f", (d * 100))) + " " + "cents";
            } else {
                fd = removeZeros(String.format("%.2f", (d))) + " " + "EUR";
            }

            return fd;

        }

        //If CNY
        else if (secondary.toUpperCase().equals("CNY")) {

            if (d < 1) {
                fd = removeZeros(String.format("%.2f", (d * 100))) + " " + "cents";
            } else {
                fd = removeZeros(String.format("%.2f", (d))) + " " + "CNY";
            }

            return fd;

        } else {
            return "formatting error";
        }

    }

    public static String formatShort(double d, String secondary) {

        String fd;

        //If BTC
        if (secondary.toUpperCase().equals("BTC")) {

            //See if it should be in mBTC, Satoshi etc.
            if (d < 0.00001) {
                fd = removeZeros(String.format("%.3f", (d * 100000000))) + "s";
            } else if (d < 0.01) {
                fd = removeZeros(String.format("%.3f", (d * 1000))) + "m";
            } else {
                fd = removeZeros(String.format("%.3f", (d)));
            }

            return fd;

        }

        //If LTC
        else if (secondary.toUpperCase().equals("LTC")) {

            if (d < 0.01) {
                fd = removeZeros(String.format("%.3f", (d * 1000))) + "m";
            } else {
                fd = String.format("%.3f", (d));
            }

            return fd;

        }

        //If XPM
        else if (secondary.toUpperCase().equals("XPM")) {

            if (d < 0.01) {
                fd = removeZeros(String.format("%.3f", (d * 1000))) + "m";
            } else {
                fd = String.format("%.3f", (d));
            }

            return fd;

        }

        //If USD
        else if (secondary.toUpperCase().equals("USD")) {

            if (d < 1) {
                fd = removeZeros(String.format("%.2f", (d * 100))) + " " + "¢";
            } else {
                fd = "$" + String.format("%.2f", (d));
            }

            return fd;

        }

        //If EUR
        else if (secondary.toUpperCase().equals("EUR")) {

            if (d < 1) {
                fd = removeZeros(String.format("%.2f", (d * 100))) + " " + "¢";
            } else {
                fd = "€" + String.format("%.2f", (d));
            }

            return fd;

        }

        //If CNY
        else if (secondary.toUpperCase().equals("CNY")) {

            if (d < 1) {
                fd = removeZeros(String.format("%.2f", (d * 100))) + " " + "¢";
            } else {
                fd = "¥" + String.format("%.2f", (d));
            }

            return fd;

        } else {
            return "formatting error";
        }

    }

    public static String formatNum(double d, String secondary) {

        String fd;

        //If BTC
        if (secondary.toUpperCase().equals("BTC")) {

            //See if it should be in mBTC, Satoshi etc.
            if (d < 0.00001) {
                fd = removeZeros(String.format("%.3f", (d * 100000000)));
            } else if (d < 0.01) {
                fd = removeZeros(String.format("%.3f", (d * 1000)));
            } else {
                fd = removeZeros(String.format("%.3f", (d)));
            }

            return fd;

        }

        //If LTC
        else if (secondary.toUpperCase().equals("LTC")) {

            if (d < 0.01) {
                fd = removeZeros(String.format("%.3f", (d * 1000)));
            } else {
                fd = String.format("%.3f", (d));
            }

            return fd;

        }

        //If XPM
        else if (secondary.toUpperCase().equals("XPM")) {

            if (d < 0.01) {
                fd = removeZeros(String.format("%.3f", (d * 1000)));
            } else {
                fd = String.format("%.3f", (d));
            }

            return fd;

        }

        //If USD
        else if (secondary.toUpperCase().equals("USD")) {

            //Log.i("Format", "USD, d is: " + d);
            if (d < 1) {
                fd = removeZeros(String.format("%.2f", (d * 100)));
            } else {
                fd = String.format("%.2f", (d));
            }

            //Log.i("Format", "USD, fd is: " + fd);
            return fd;

        }

        //If EUR
        else if (secondary.toUpperCase().equals("EUR")) {

            //Log.i("Format", "USD, d is: " + d);
            if (d < 1) {
                fd = removeZeros(String.format("%.2f", (d * 100)));
            } else {
                fd = String.format("%.2f", (d));
            }

            //Log.i("Format", "USD, fd is: " + fd);
            return fd;

        }

        //If CNY
        else if (secondary.toUpperCase().equals("CNY")) {

            //Log.i("Format", "USD, d is: " + d);
            if (d < 1) {
                fd = removeZeros(String.format("%.2f", (d * 100)));
            } else {
                fd = String.format("%.2f", (d));
            }

            //Log.i("Format", "USD, fd is: " + fd);
            return fd;

        } else {
            return "formatting error";
        }


    }


    public static String checkFormat(double d, String secondary) {

        String fd;

        //If BTC
        if (secondary.toUpperCase().equals("BTC")) {

            //See if it should be in mBTC, Satoshi etc.
            if (d < 0.00001) {
                fd = "satoshi";
            } else if (d < 0.01) {
                fd = "mBTC";
            } else {
                fd = "BTC";
            }

            return fd;

        }

        //If LTC
        else if (secondary.toUpperCase().equals("LTC")) {

            if (d < 0.01) {
                fd = "mLTC";
            } else {
                fd = "LTC";
            }

            return fd;

        }

        //If XMP
        else if (secondary.toUpperCase().equals("XPM")) {

            if (d < 0.01) {
                fd = "mXPM";
            } else {
                fd = "XPM";
            }

            return fd;

        }

        //If USD
        else if (secondary.toUpperCase().equals("USD")) {

            if (d < 1) {
                fd = "cents";
            } else {
                fd = "USD";
            }

            return fd;

        }

        //If EUR
        else if (secondary.toUpperCase().equals("EUR")) {

            if (d < 1) {
                fd = "cents";
            } else {
                fd = "EUR";
            }

            return fd;

        }

        //If CNY
        else if (secondary.toUpperCase().equals("CNY")) {

            if (d < 1) {
                fd = "cents";
            } else {
                fd = "CNY";
            }

            return fd;

        } else {
            return "formatting error";
        }

    }


    private static String removeZeros(String s) {
        BigDecimal bd = new BigDecimal("" + s);
        DecimalFormat df = new DecimalFormat("0.0##");
        String formatted = df.format(bd.stripTrailingZeros());
        return formatted;
    }


}
