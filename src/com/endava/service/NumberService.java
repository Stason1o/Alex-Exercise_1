package com.endava.service;

import com.endava.entity.MobilePhone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Stas on 22.03.2017.
 */
public class NumberService {

    private String phoneNumber;

    public NumberService() {
    }

    public MobilePhone createMobilePhone(){
        return new MobilePhone(validateNumber(), checkMoneyAmount());
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private String validateNumber(){
        System.out.println("NumberService.validateNumber: method will validate input data");
        printDate();
        int amountOfInputAttempts = 0;
        String telephoneNumber;
        System.out.println("Enter your phone number: ");
        do{
            telephoneNumber = keyboardInput();

            if(telephoneNumber.matches("[+][(][3][7][3][)]-[67][089]-[0-9]{2}-[0-9]{2}-[0-9]{2}")) {
                this.setPhoneNumber(telephoneNumber);
                System.out.println("NumberService.validateNumber: method returns input phone number as a string");
                printDate();
                return telephoneNumber;

            }else if (telephoneNumber.matches("[+][3][7][3] [67][089] [0-9]{6}")){
                this.setPhoneNumber(telephoneNumber);
                System.out.println("NumberService.validateNumber: method returns input phone number as a string");
                printDate();
                return telephoneNumber;
            }else if(telephoneNumber.matches("[+][3][7][3] [67][089] [0-9]{3}[-][0-9]{3}")){
                this.setPhoneNumber(telephoneNumber);
                System.out.println("NumberService.validateNumber: method returns input phone number as a string");
                printDate();
                return telephoneNumber;
            }
            else {
                amountOfInputAttempts++;
                System.out.println("Possible types of number:");
                System.out.println("+373 60 066006 / +(373)-79-85-85-85 / +373 79 666-999");
                System.out.println("Remained attempts: " + (4 - amountOfInputAttempts));
                printDate();
            }
        }while(amountOfInputAttempts < 4);

        System.out.println("NumberService.validateNumber: method validated input data and it's wrong =( See you later =)");
        this.setPhoneNumber("");
        return "";
    }

    private int checkMoneyAmount(){
        System.out.println("NumberService.checkMoneyAmount: method will check input money");
        printDate();
        if(getPhoneNumber().equals(""))
            return -1;
        int amountOfAttempts = 0;
        boolean regexValidator = true;
        System.out.println("Enter amount of money to charge account:");
        while(amountOfAttempts < 3) {
            int amountOfMoney = 0;
            while(regexValidator) {
                String keyboardInputAmountOfMoney = keyboardInput();
                regexValidator = checkInputMoney(keyboardInputAmountOfMoney);

                if (regexValidator) {
                    amountOfMoney = Integer.valueOf(keyboardInputAmountOfMoney);
                    regexValidator = false;
                }
            }

            if(amountOfMoney >= 50 && amountOfMoney <= 500){
                System.out.println("NumberService.checkMoneyAmount: Input is correct. Proceeding....");
                printDate();
                return amountOfMoney;
            } else {
                amountOfAttempts++;
                System.out.println("Wrong amount of money. Possible amount 50 - 500");
                System.out.println("Remained attempts: " + (3 - amountOfAttempts));
                regexValidator = true;
            }
        }

        System.out.println("NumberService.checkMoneyAmount: Wrong input. Sorry. Goodbye");
        printDate();
        return -1;
    }

    private String keyboardInput(){
        String inputFromConsole = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            inputFromConsole = br.readLine();
            printDate();
        }catch (IOException ex){
            System.err.println("Input/Output Exception!");
            ex.printStackTrace();
        }
        return inputFromConsole;
    }

    private boolean checkInputMoney(String string){
        System.out.println("NumberService.checkInputMoney: method checks if input matches regex");
        printDate();
        boolean checkIfMatches = (string.matches("[0-9]+"));
        System.out.println("NumberService.checkInputMoney: method returns true/false ");
        printDate();
        return checkIfMatches;
    }

    private void printDate(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println("Action taken on: " + dateFormat.format(date));
    }
}
