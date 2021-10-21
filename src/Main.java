package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        var sc = new Scanner(System.in);
        var game = new DataGame();
        Loger logger = null;

        try {
            logger = new Loger();
            logger.writeGameStart(game.getSecretString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Число загаданно.");
        var input = "";
        var attempts = 0;
        boolean isGameEnded = false;
        do{
            attempts++;
            input = sc.nextLine();
            isGameEnded = game.compareWithSecretStr(input);
            if(!isGameEnded){
                try{
                    int cows = game.Cows(input);
                    int bulls = game.Bulls(input);
                    if (cows == 1)
                        System.out.println(cows + " Корова");
                    if (cows > 1 && cows < 5)
                        System.out.println(cows + " Коровы");
                    if (cows == 0)
                        System.out.println(cows + " Коров");
                    if (bulls == 1)
                        System.out.println(bulls + " Бык");
                    if (bulls > 1 && bulls < 5)
                        System.out.println(bulls + " Быка");
                    if (bulls == 0)
                        System.out.println(bulls + " Быков");
                    logger.writeAttempt(cows, bulls,input);
                }catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        } while (!isGameEnded);
        try {
            logger.writeEnd(attempts);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Вы выйграли!" + input);
    }
}
