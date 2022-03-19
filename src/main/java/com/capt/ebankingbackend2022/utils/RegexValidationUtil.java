package com.capt.ebankingbackend2022.utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexValidationUtil {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    public static final Pattern VALID_PHONE_NUMBER_PATTERN =
            Pattern.compile("^(\\+\\d)?\\d{10}$");

    public static boolean isEmail(String emailString) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailString);
        return matcher.find();
    }

    public static boolean is10NumberPhone(String phoneNumber) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(phoneNumber);
        return matcher.find();
    }
}
