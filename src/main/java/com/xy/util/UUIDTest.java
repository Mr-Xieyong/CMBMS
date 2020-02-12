package com.xy.util;

import java.util.UUID;

/**
 * @author Xieyong
 * @date 2019/12/13 - 15:50
 */
public class UUIDTest {
    public static void main(String[] args) {
        UUID u = UUID.randomUUID();
        String str = u.toString().replace("-","");

        System.out.println("UUID ====> " + str);
    }
}
