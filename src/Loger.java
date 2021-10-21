package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;

import static java.lang.Integer.parseInt;

public class Loger {
    private int currentGameNum;
    private File currentFile;
    private BufferedWriter writer;

    public Loger() throws Exception {
        var gameNumPattern = Pattern.compile("Game №(\\d+)");
        currentFile = new File("LogGame.log");
        writer = new BufferedWriter(new FileWriter(currentFile.getName(), true));
        var log = Files.readAllLines(Path.of("LogGame.log"));
        for (String str : log) {
            var matcher = gameNumPattern.matcher(str);
            if (matcher.find()) {
                currentGameNum = parseInt(matcher.group(1)) + 1;
            }
        }
    }

    public void writeGameStart(String secretStr) throws IOException {
        writer = new BufferedWriter(new FileWriter(currentFile.getName(), true));
        Date dateStart = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        writer.append("Game №")
                .append(String.valueOf(currentGameNum))
                .append(" ")
                .append(sdf.format(dateStart))
                .append(" Загаданная строка была ")
                .append(secretStr)
                .append("\n");
        writer.close();
        System.out.println(secretStr);
    }

    public void writeAttempt(int cows, int bulls, String str) throws IOException {
        writer = new BufferedWriter(new FileWriter(currentFile.getName(), true));
        writer.append("\tЗапрос: ")
                .append(str)
                .append(" Ответ: ")
                .append(String.valueOf(cows))
                .append(" ")
                .append(getCowsStr(cows))
                .append(", ")
                .append(String.valueOf(bulls))
                .append(" ")
                .append(getBullsStr(bulls))
                .append("\n");
        writer.close();
    }

    public void writeEnd(int attempts) throws IOException {
        writer = new BufferedWriter(new FileWriter(currentFile.getName(), true));
        writer.append("Игрок победил, кол-во попыток: ")
                .append(String.valueOf(attempts))
                .append(" .")
                .append("\n");
        writer.close();
    }

    private String getCowsStr(int num) {
        if (num == 1) return "Корова";
        if (num > 1 && num < 5) return "Коровы";
        return "Коров";
    }

    private String getBullsStr(int num) {
        if (num == 1) return "Бык";
        if (num > 1 && num < 5) return "Быка";
        return "Быков";
    }
}
