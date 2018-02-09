package com.round2.task1;

import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class task {
    public static void main(String[] args){
        ArrayList<String> arrayOfLines = scanData();
        Set<String> sortedLines = parseData(arrayOfLines);
        printSetOfString(sortedLines);
    }

    private static void printSetOfString(Set<String> sortdeLines){
        for (String line : sortdeLines) {
            System.out.println(line);
        }

    }

    private static Set<String> parseData(ArrayList<String> arrayOfLines) {
        Set<String> finalArray = new HashSet<>();
        ArrayList<Pattern> regexWord = new ArrayList<>();
        try {
            String paternLine = arrayOfLines.get(0);
            Pattern.compile(paternLine);
            String[] buffArrayOfPatern =paternLine.split(" ");
            for (String aBuffArrayOfPatern : buffArrayOfPatern) {
                regexWord.add(Pattern.compile("^" + aBuffArrayOfPatern
                        .replace("^", "")
                        .replace("$", "") + "(;?)$"
                ));
            }
            for (Pattern patern : regexWord) {
                finalArray = matchArrayOfLines(patern,arrayOfLines,finalArray);
            }
        } catch (PatternSyntaxException e) {
            throw new RuntimeException("Регулярное выражение не валидно");
        }
        return finalArray;
    }

    private static Set<String> matchArrayOfLines(Pattern patern,ArrayList<String> lineForMAtcher,Set<String> arrayOfAnswer){
        for (int i = 1 ; i < lineForMAtcher.size() ; i++) {
            for (String word : lineForMAtcher.get(i).split(" ")) {
                Matcher m = patern.matcher(word);
                if (m.matches()) {
                    arrayOfAnswer.add(lineForMAtcher.get(i));
                }
            }
        }
        return arrayOfAnswer;
    }

    private static ArrayList<String> scanData() {
        String lineToCheck;
        ArrayList<String> arrayOfLines = new ArrayList<>();
        Scanner inputLine = new Scanner(System.in);
        System.out.println("Введите параметр программы");
        arrayOfLines.add(inputLine.nextLine());
        do{
            System.out.println("Введите строку");
            lineToCheck=inputLine.nextLine();
            arrayOfLines.add(lineToCheck);
        } while ( lineToCheck.length() > 0 );
        return arrayOfLines;
    }
}
