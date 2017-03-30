package com.endava.service;

import com.endava.entity.MobilePhone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Created by Stas on 22.03.2017.
 */
public class NumberService {
    private static final Logger log = Logger.getLogger(NumberService.class.getName());

    private static final int PHONE_MAX_ATTEMPTS = 4;
    private static final int BALANCE_MAX_ATTEMPTS = 3;
    private static final int MIN_AMOUNT = 50;
    private static final int MAX_AMOUNT = 500;



    private static final String[] NUMBER_PATTERNS = {
            "[+][(][3][7][3][)]-[67][089]-[0-9]{2}-[0-9]{2}-[0-9]{2}",
            "[+][3][7][3] [67][089] [0-9]{3}[-][0-9]{3}",
            "[+][3][7][3] [67][089] [0-9]{6}"
    };

    private String phoneNumber;

    public NumberService() {
        // empty constructor
    }

    public MobilePhone createMobilePhone() {
        return new MobilePhone(enterPhoneNumber(), checkMoneyAmount());
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private String enterPhoneNumber() {
        log.info("NumberService.enterPhoneNumber: method will validate input data");
        String phoneNumber;
        int attempts = 0;

        log.info("Enter your phone number: ");
        do {
            phoneNumber = readLine();

            if (isValidPhoneNumber(phoneNumber)) {
                this.phoneNumber = phoneNumber;
                return this.phoneNumber;
            } else {
                log.info("Possible types of number: +373 60 066006 / +(373)-79-85-85-85 / +373 79 666-999");
                log.warning("Remained attempts: " + (PHONE_MAX_ATTEMPTS - ++attempts));
            }
        } while (attempts < PHONE_MAX_ATTEMPTS);

        log.warning("NumberService.enterPhoneNumber: method validated input data and it's wrong =( See you later =)");
        return phoneNumber;
    }

    private int checkMoneyAmount() {
        log.info("NumberService.checkMoneyAmount: method will check input money");
        if (isEmpty(this.phoneNumber))
            throw new IllegalStateException("Phone number is not provided");

        int attempts = 0;
        int amountOfMoney = 0;

        log.info("Enter amount of money to charge account:");
        while (attempts < BALANCE_MAX_ATTEMPTS) {

            String input = readLine();

            if (isDigit(input))
                amountOfMoney = Integer.valueOf(input);

            if (amountOfMoney >= MIN_AMOUNT && amountOfMoney <= MAX_AMOUNT) {
                log.info("NumberService.checkMoneyAmount: Input is correct. Proceeding....");
                return amountOfMoney;
            } else {
                log.info("Wrong amount of money. Possible amount 50 - 500");
                log.warning("Remained attempts: " + (BALANCE_MAX_ATTEMPTS - ++attempts));
            }
        }

        log.warning("NumberService.checkMoneyAmount: Wrong input. Sorry. Goodbye");
        return amountOfMoney;
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        log.info("NumberService.isValidPhoneNumber: verifies if number matches any pattern");
        return Arrays.stream(NUMBER_PATTERNS).anyMatch(phoneNumber::matches);
    }

    private static String readLine() {
        log.info("NumberService.readLine: reads string from console");
        String input = null;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("NumberService.readLine: returns string from console");
        return input;
    }

    private static boolean isEmpty(String string) {
        log.info("NumberService.isEmpty: method checks if input string is empty");
        log.info("NumberService.isDigit: method returns true/false ");
        return string == null || string.length() == 0;
    }

    private static boolean isDigit(String string) {
        log.info("NumberService.isDigit: method checks if input matches regex");
        boolean matches = (string.matches("[0-9]+"));
        log.info("NumberService.isDigit: method returns true/false ");
        return matches;
    }

}
