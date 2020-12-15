package com.example.passwordkeeper.services;

import java.util.Random;

public class PasswordService {

    public static String GetGeneratedPassword() {
        return GeneratePassword();
    }

    private static String GeneratePassword() {
        Random random = new Random();
        int utfCount = random.nextInt(5) + 10;
        char[] utfNumbers = new char[utfCount];

        for (int i = 0; i < utfCount; i++) {
            utfNumbers[i] = (char) (random.nextInt(93) + 33);
        }

        int temp = 0;
        for (int i = 0; i < 4; i++) {
            int randomPosition = random.nextInt(10);
            while (temp == randomPosition) {
                randomPosition = random.nextInt(10);
            }
            temp = randomPosition;
            utfNumbers[randomPosition] = (char) GetRandomChar(i);
        }

        return new String(utfNumbers);
    }

    private static int GetRandomChar(int a) {
        Random random = new Random();
        switch (a) {
            case 0:
                return random.nextInt(14) + 33;
            case 1:
                return random.nextInt(9) + 48;
            case 2:
                return random.nextInt(6) + 58;
            case 3:
                return random.nextInt(25) + 65;
        }
        return 0;
    }

}
