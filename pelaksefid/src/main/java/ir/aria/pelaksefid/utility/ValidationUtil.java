/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.aria.pelaksefid.utility;

import ir.aria.pelaksefid.web.v1.model.request.base.BaseReq;
import java.math.BigInteger;

/**
 *
 * @author user
 */
public class ValidationUtil {

    public static boolean isBaseReqValid(BaseReq input) {
        if (ValidationUtil.isEmpty(input.getToken())) {
            return false;
        }
        return true;
    }

    public static boolean isEmpty(String input) {
        return input == null || input.trim().isEmpty();
    }

    public static boolean isValidCellNumber(String cellNumber) {
        if (ValidationUtil.isEmpty(cellNumber)) {
            return false;
        }
        if (!cellNumber.matches("09([0-9]{9})")) {
            return false;
        }
        return true;
    }

    public static Boolean isNationalIdValid(String input) {

        int sum = 0;
        int cntr = 0;
        int a, b, c;
        if (input == null
                || !input.matches("[0-9]{10}")
                || input.matches("([0-9])\\1{9}")) {
            return false;
        }
        for (cntr = 0; cntr < 9; cntr++) {
            sum += Integer.parseInt(input.substring(cntr, cntr + 1)) * (10 - cntr);
        }
        a = sum % 11;
        b = 11 - a;
        c = Integer.parseInt(input.substring(9, 10));
        if (!(((a == 0 || a == 1) && a == c)
                || (a > 1 && b == c))) {
            return false;
        }
        return true;
    }

    public static Boolean isLegalNationalIdValid(String input) {

        if (input == null) {
            return false;
        }
        if (!input.matches("[0-9]{11}") && !input.matches("[0-9]{12}")) {
            return false;
        }
        //return true;
        int sumOfDigits = 0;
        int cntr = 0;
        int[] zaribArray = new int[]{29, 27, 23, 19, 17, 29, 27, 23, 19, 17};
        int Checkdigit = Integer.parseInt(input.substring(10, 11));
        int Lastnumber = Integer.parseInt(input.substring(9, 10));
        int[] nCodearray = new int[10];
        for (cntr = 0; cntr < 10; cntr++) {
            nCodearray[cntr] = Integer.parseInt(input.substring(cntr, cntr + 1));
            nCodearray[cntr] += Lastnumber + 2;
            nCodearray[cntr] *= zaribArray[cntr];
            sumOfDigits += nCodearray[cntr];
        }
        int mod = sumOfDigits % 11;
        if (mod > 9) {
            mod = 0;
        }
        if (mod != Checkdigit) {
            return false;
        }
        return true;
    }

    public static Boolean isIbanValid(String iban) {

        if (isEmpty(iban)) {
            return false;
        }
        if (iban.length() != 26) {
            return false;
        }
        short i;
        for (i = 2; i < 26; i++) {
            if (!Character.isDigit(iban.charAt(i))) {
                return false;
            }
        }
        String cd = iban.substring(2, 4);
        String bban = iban.substring(4);
        //"IR" --> "1827"
        BigInteger bIban = new BigInteger(bban + "1827" + cd);
        BigInteger rmnd = bIban.mod(new BigInteger("97"));
        if (rmnd.intValue() != 1) {
            return false;
        }
        return true;
    }

    public static int compare(String str1, String str2) {
        int l1 = str1.length();
        int l2 = str2.length();
        int lmin = Math.min(l1, l2);
        for (int i = 0; i < lmin; i++) {
            int str1_ch = (int) str1.charAt(i);
            int str2_ch = (int) str2.charAt(i);
            if (str1_ch != str2_ch) {
                return str1_ch - str2_ch;
            }
        }
        if (l1 != l2) {
            return l1 - l2;
        }
        return 0;
    }

    public static Double similarity(String s1, String s2) {
        String longer = s1, shorter = s2;
        if (s1.length() < s2.length()) {
            longer = s2;
            shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) {
            return 1.0;
        }
        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;

    }

    private static int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    costs[j] = j;
                } else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
                            newValue = Math.min(Math.min(newValue, lastValue),
                                    costs[j]) + 1;
                        }
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0) {
                costs[s2.length()] = lastValue;
            }
        }
        return costs[s2.length()];
    }

}
