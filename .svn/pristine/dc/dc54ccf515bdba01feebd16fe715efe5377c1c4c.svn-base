/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.aria.pelaksefid.utility;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author user
 */
public class TokenUtil {

    public static String getToken(String cellNumber) {
        if (cellNumber == null || !cellNumber.matches("09([0-9]{9})")) {
            return null;
        }
        Date now = new Date();
        String time = String.valueOf(now.getTime() + 7 * 86400000);
        return encrypt(cellNumber, time);
    }

    private static String encrypt(String cellNumber, String time) {
        char[] ec = new char[19];
        int d = 0;
        d = (int) (cellNumber.charAt(9)) + 0;
        ec[0] = (char) d;
        d = (int) (cellNumber.charAt(4)) + 0;
        ec[1] = (char) d;
        d = (int) (cellNumber.charAt(10)) + 0;
        ec[2] = (char) d;
        d = (int) (cellNumber.charAt(8)) + 0;
        ec[3] = (char) d;
        d = (int) (cellNumber.charAt(1)) + 0;
        ec[4] = (char) d;
        d = (int) (cellNumber.charAt(5)) + 0;
        ec[5] = (char) d;
        d = (int) (cellNumber.charAt(6)) + 0;
        ec[6] = (char) d;
        d = (int) (cellNumber.charAt(2)) + 0;
        ec[7] = (char) d;
        d = (int) (cellNumber.charAt(7)) + 0;
        ec[8] = (char) d;
        d = (int) (cellNumber.charAt(0)) + 0;
        ec[9] = (char) d;
        d = (int) (cellNumber.charAt(3)) + 0;
//        d = (int) (cellNumber.charAt(9)) + 2;
//        ec[0] = (char) d;
//        d = (int) (cellNumber.charAt(4)) + 3;
//        ec[1] = (char) d;
//        d = (int) (cellNumber.charAt(10)) + 1;
//        ec[2] = (char) d;
//        d = (int) (cellNumber.charAt(8)) + 3;
//        ec[3] = (char) d;
//        d = (int) (cellNumber.charAt(1)) + 2;
//        ec[4] = (char) d;
//        d = (int) (cellNumber.charAt(5)) + 6;
//        ec[5] = (char) d;
//        d = (int) (cellNumber.charAt(6)) + 5;
//        ec[6] = (char) d;
//        d = (int) (cellNumber.charAt(2)) + 3;
//        ec[7] = (char) d;
//        d = (int) (cellNumber.charAt(7)) + 4;
//        ec[8] = (char) d;
//        d = (int) (cellNumber.charAt(0)) + 1;
//        ec[9] = (char) d;
//        d = (int) (cellNumber.charAt(3)) + 4;
        ec[10] = (char) d;
        ec[11] = (char) ThreadLocalRandom.current().nextInt(48, 58);
        ec[12] = (char) ThreadLocalRandom.current().nextInt(48, 58);
        ec[13] = (char) ThreadLocalRandom.current().nextInt(48, 58);
        ec[14] = (char) ThreadLocalRandom.current().nextInt(48, 58);
        ec[15] = (char) ThreadLocalRandom.current().nextInt(48, 58);
        ec[16] = (char) ThreadLocalRandom.current().nextInt(48, 58);
        ec[17] = (char) ThreadLocalRandom.current().nextInt(48, 58);
        ec[18] = (char) ThreadLocalRandom.current().nextInt(48, 58);

        char[] et = new char[19];
        int cntr = 0;
        for (cntr = 0; cntr < 19 - time.length(); cntr++) {
            d = (int) '0';
            et[cntr] = (char) d;
        }
        int cntrTime = 0;
        for (; cntr < 19; cntr++) {
            d = (int) time.charAt(cntrTime++);
            et[cntr] = (char) d;
        }
        int cntr1 = 0;
        int cntr2 = 0;
        char[] ect = new char[38];
        for (cntr = 0; cntr < 38; cntr++) {
            ect[cntr] = (char) (cntr % 2 == 0 ? ec[cntr1++] : et[cntr2++]);
        }

        return String.valueOf(ect);
    }

    private static Long decryptTime(String encrypted) {
        char[] dt = new char[19];
        dt[0] = encrypted.charAt(1);
        dt[1] = encrypted.charAt(3);
        dt[2] = encrypted.charAt(5);
        dt[3] = encrypted.charAt(7);
        dt[4] = encrypted.charAt(9);
        dt[5] = encrypted.charAt(11);
        dt[6] = encrypted.charAt(13);
        dt[7] = encrypted.charAt(15);
        dt[8] = encrypted.charAt(17);
        dt[9] = encrypted.charAt(19);
        dt[10] = encrypted.charAt(21);
        dt[11] = encrypted.charAt(23);
        dt[12] = encrypted.charAt(25);
        dt[13] = encrypted.charAt(27);
        dt[14] = encrypted.charAt(29);
        dt[15] = encrypted.charAt(31);
        dt[16] = encrypted.charAt(33);
        dt[17] = encrypted.charAt(35);
        dt[18] = encrypted.charAt(37);
        return Long.valueOf(String.valueOf(dt));
    }

    private static String decryptCell(String encrypted) {
        char[] dc = new char[11];
        dc[0] = (char) ((int) encrypted.charAt(18) - 0);
        dc[1] = (char) ((int) encrypted.charAt(8) - 0);
        dc[2] = (char) ((int) encrypted.charAt(14) - 0);
        dc[3] = (char) ((int) encrypted.charAt(20) - 0);
        dc[4] = (char) ((int) encrypted.charAt(2) - 0);
        dc[5] = (char) ((int) encrypted.charAt(10) - 0);
        dc[6] = (char) ((int) encrypted.charAt(12) - 0);
        dc[7] = (char) ((int) encrypted.charAt(16) - 0);
        dc[8] = (char) ((int) encrypted.charAt(6) - 0);
        dc[9] = (char) ((int) encrypted.charAt(0) - 0);
        dc[10] = (char) ((int) encrypted.charAt(4) - 0);
//        dc[0] = (char) ((int) encrypted.charAt(18) - 1);
//        dc[1] = (char) ((int) encrypted.charAt(8) - 2);
//        dc[2] = (char) ((int) encrypted.charAt(14) - 3);
//        dc[3] = (char) ((int) encrypted.charAt(20) - 4);
//        dc[4] = (char) ((int) encrypted.charAt(2) - 5);
//        dc[5] = (char) ((int) encrypted.charAt(10) - 6);
//        dc[6] = (char) ((int) encrypted.charAt(12) - 5);
//        dc[7] = (char) ((int) encrypted.charAt(16) - 4);
//        dc[8] = (char) ((int) encrypted.charAt(6) - 3);
//        dc[9] = (char) ((int) encrypted.charAt(0) - 2);
//        dc[10] = (char) ((int) encrypted.charAt(4) - 1);
        return String.valueOf(dc);
    }
}
