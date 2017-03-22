package com.endava.main;

import com.endava.entity.MobilePhone;
import com.endava.service.NumberService;

/**
 * Created by Stas on 22.03.2017.
 */
public class Main {
    public static void main(String[] args) {
        NumberService numberService = new NumberService();
        MobilePhone mobilePhone_1 = numberService.createMobilePhone();
        System.out.println(mobilePhone_1);
    }
}
