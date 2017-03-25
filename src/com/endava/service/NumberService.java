package com.endava.service;

import com.endava.entity.MobilePhone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        int amountOfInputAttempts = 0;
        String telephoneNumber;
        System.out.println("Enter your phone number: ");
        do{
            telephoneNumber = keyboardInput();

            if(telephoneNumber.matches("[+][(][3][7][3][)]-[67][089]-[0-9]{2}-[0-9]{2}-[0-9]{2}")) {
                this.setPhoneNumber(telephoneNumber);
                return telephoneNumber;
            }else if (telephoneNumber.matches("[+][3][7][3] [67][089] [0-9]{6}")){
                this.setPhoneNumber(telephoneNumber);
                return telephoneNumber;
            }else if(telephoneNumber.matches("[+][3][7][3] [67][089] [0-9]{3}[-][0-9]{3}")){
                this.setPhoneNumber(telephoneNumber);
                return telephoneNumber;
            }
            else {
                amountOfInputAttempts++;
                System.out.println("Possible types of number:");
                System.out.println("+373 60 066006 / +(373)-79-85-85-85 / +373 79 666-999");
                System.out.println("Remained attempts: " + (4 - amountOfInputAttempts));
            }
        }while(amountOfInputAttempts < 4);

        System.out.println("Input is wrong =( See you later =)");
        this.setPhoneNumber("");
        return "";
    }

    private int checkMoneyAmount(){
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
                return amountOfMoney;
            } else {
                amountOfAttempts++;
                System.out.println("Wrong amount of money. Possible amount 50 - 500");
                System.out.println("Remained attempts: " + (3 - amountOfAttempts));
                regexValidator = true;
            }
        }
        System.out.println("Wrong input. Sorry. Goodbye");
        return -1;
    }

    private String keyboardInput(){
        String inputFromConsole = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            inputFromConsole = br.readLine();
        }catch (IOException ex){
            System.err.println("Input/Output Exception!");
            ex.printStackTrace();
        }
        return inputFromConsole;
    }

    private boolean checkInputMoney(String string){
        boolean checkIfMatches = (string.matches("[0-9]+"));
        return checkIfMatches;
    }
}
