package ru.itis;

public class SignUpService {
    private PasswordBlackList passwordBlackList;

    public SignUpService(PasswordBlackList passwordBlackList) {
        this.passwordBlackList = passwordBlackList;
    }

    public void signUp(String password) {

        if (passwordBlackList.blackList(password)) {
            System.err.println("Bad password");
        } else {
            System.out.println("Good password");
        }
    }
}
