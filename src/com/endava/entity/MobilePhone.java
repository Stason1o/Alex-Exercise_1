package com.endava.entity;

/**
 * Created by Stas on 22.03.2017.
 */
public class MobilePhone {
    private String phoneNumber;
    private int mobileAccount;

    public MobilePhone() {
        // empty constructor
    }

    public MobilePhone(String phoneNumber, int mobileAccount) {
        this.phoneNumber = phoneNumber;
        this.mobileAccount = mobileAccount;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getMobileAccount() {
        return mobileAccount;
    }

    public void setMobileAccount(int mobileAccount) {
        this.mobileAccount = mobileAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MobilePhone)) return false;

        MobilePhone that = (MobilePhone) o;

        return mobileAccount == that.mobileAccount && phoneNumber.equals(that.phoneNumber);
    }

    @Override
    public int hashCode() {
        int result = phoneNumber.hashCode();
        result = 31 * result + mobileAccount;
        return result;
    }

    @Override
    public String toString() {
        return "MobilePhone{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", mobileAccount=" + mobileAccount +
                '}';
    }
}
