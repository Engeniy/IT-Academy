package ru.mail.krivonos.al.test.impl;

import ru.mail.krivonos.al.test.TaskThreeService;

public class TaskThreeServiceImpl implements TaskThreeService {

    @Override
    public void printWithoutSomeSymbols(String targetString) {
        StringBuilder result = new StringBuilder();
        if (!targetString.contains("(")|| targetString.contains(")")) {
            System.err.println("There is no target symbols!");
            return;
        }
        int start = 0;
        for (int i = 0; i < targetString.length(); i++) {
            if (targetString.charAt(i) == '(') {
                result.append(targetString, start, i + 1);
            }
            if (targetString.charAt(i) == ')') {
                start = i;
            }
        }
        result.append(targetString.substring(start));
        System.out.println(result.toString());
    }
}
