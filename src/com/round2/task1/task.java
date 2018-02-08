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
        System.out.println(parseData(arrayOfLines));
    }
    private static Set<String> parseData(ArrayList<String> arrayOfLines) {
        Set<String> finalArray = new HashSet<>();
        ArrayList<Pattern> regexWord = new ArrayList<>();
        try {
            String paternLine = arrayOfLines.get(0);
            Pattern.compile(paternLine);
            for (int i = 0 ; i < paternLine.split(" ").length ; i++) {
                String[] buffArrayOfPatern =paternLine.split(" ");
                if (buffArrayOfPatern[i].charAt(0)=='^'){
                    regexWord.add(Pattern.compile("(^|\\s)" + buffArrayOfPatern[i].substring(1,buffArrayOfPatern[i].length()) + "(;|\\s)"));
                }
                else if (buffArrayOfPatern[i].charAt(buffArrayOfPatern[i].length()-1)=='$'){
                    regexWord.add(Pattern.compile("(^|\\s)" + buffArrayOfPatern[i].substring(0,buffArrayOfPatern[i].length()-2) + "(;|\\s$)"));
                }
                else{
                    regexWord.add(Pattern.compile("(^|\\s)" + buffArrayOfPatern[i] + "(;|\\s)"));
                }

            }
            for (Pattern patern : regexWord) {
                for (String line : arrayOfLines) {
                    for (String word : line.split(" ")) {
                        Matcher m = patern.matcher(word+" ");
                        if (m.find()) {
                            finalArray.add(line);
                        }
                    }
                }
            }
        } catch (PatternSyntaxException e) {
            System.out.println("Регулярное выражение не валидно");
        }
        return finalArray;
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
        } while (lineToCheck.length()>0);
        return arrayOfLines;
    }
}
