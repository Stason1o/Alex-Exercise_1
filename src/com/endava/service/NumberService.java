package com.endava.service;

import com.endava.entity.MobilePhone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Stas on 22.03.2017.
 */
public class NumberService {

    String phoneNumber;

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

    public String validateNumber(){
        int counter = 0;
        String tmp;
        System.out.println("Enter your phone number: ");
        do{
            tmp = keyboardInput();

            if(tmp.matches("[+][(][3][7][3][)]-*[67][089]*-*[0-9]{2}-*[0-9]{2}-*[0-9]{2}")){
                this.setPhoneNumber(tmp);
                return tmp;
            } else if(tmp.matches("[+][3][7][3] [67][089] [0-9]{6}")) {
                this.setPhoneNumber(tmp);
                return tmp;
            }else if(tmp.matches("[+][3][7][3] [67][089] [0-9]{3}[-][0-9]{3}")) {
                this.setPhoneNumber(tmp);
                return tmp;
            }
            else {
                counter++;
                System.out.println("Possible types of number:");
                System.out.println("+373 60 066006 / +(373)-79-85-85-85 / +373 79 666-999");
                System.out.println("Remained attempts: " + (4 - counter));
            }
        }while(counter < 4);

        System.out.println("Input is wrong =( See you later =)");
        this.setPhoneNumber("");
        return "";
    }

    public int checkMoneyAmount(){
        if(this.getPhoneNumber().equals(""))
            return -1;
        int count = 0;
        boolean flag;
        System.out.println("Enter amount of money to charge account:");
        do {
            int money = 0;
            do {
                String tmp = keyboardInput();
                flag = checkInputMoney(tmp);

                if (flag == true) {
                    money = Integer.valueOf(tmp);
                }
            }while(flag);

            if(money >= 50 && money <= 500){
                return money;
            } else {
                count++;
                System.out.println("Wrong amount of money. Possible amount 50 - 500");
                System.out.println("Remained attempts: " + (3 - count));
            }
        }while(count < 3);
        System.out.println("Wrong input. Sorry. Goodbye");
        return -1;
    }

    public String keyboardInput(){
        String tmp = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            tmp = br.readLine();
        }catch (IOException ex){
            System.err.println("Input/Output Exception!");
            ex.printStackTrace();
        }
        return tmp;
    }

    public boolean checkInputMoney(String string){
        if(string.matches("[0-9]+")){
            return true;
        }
        else return false;
    }
}
