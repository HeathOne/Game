package com.company;

import java.util.Random;

public class DataGame {

    private String secretString;

    public DataGame() {
        this.secretString = generateString();
    }

    private static String generateString() {
        var rnd = new Random();
        var generatedString = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            var nextInt = -1;
            do {
                nextInt = rnd.nextInt(10);
            } while (generatedString.indexOf((String.valueOf(nextInt)))!=-1);
            generatedString.append(nextInt);
        }
        return generatedString.toString();
    }

    private void validateString(String str) throws Exception {
        if(str.length()!=secretString.length())
        {
            throw new Exception("Неверная строка");
        }
        if(str.chars().distinct().count()!=secretString.length()){
            throw new Exception("Символы быть уникальными");
        }
    }

    public int Cows(String str) throws Exception {
        validateString(str);
        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            if(secretString.toCharArray()[i]==str.toCharArray()[i]){
                result++;
            }
        }
        return result;
    }

    public int Bulls(String str) throws Exception {
        validateString(str);
        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            if(secretString.toCharArray()[i]==str.toCharArray()[i]){
                result++;
            }
        }
        return result;
    }

    public boolean compareWithSecretStr(String str)
    {
        return secretString.equals(str);
    }

    public String getSecretString(){
        return secretString;
    }
}

