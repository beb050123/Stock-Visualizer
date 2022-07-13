package com.example.financeplayground.data;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UserInput {


    public static String getDate() {
        Scanner scan = new Scanner(System.in);
        String date = "";
        boolean valid = false;
        while (!valid) {
            System.out.println("Enter a date (YYYY-MM-DD):");
            date = scan.nextLine();
            if (date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                valid = true;
            } else {
                System.out.println("Invalid date. Please try again.");
            }
        }
        return date;
    }



}
