package ru.mail.krivonos.al.lesson.eight;

public class StringService {
    void printCharacterIndexes(String targetString, char targetCharacter) {
        if (targetString.indexOf(targetCharacter) == -1) {
            System.out.println("Your string doesn't contain character '".concat(String.valueOf(targetCharacter)).concat("'."));
        }

        System.out.println("Indexes of character '".concat(String.valueOf(targetCharacter)).concat("' are:"));
        for (int i = 0; i < targetString.length(); i++) {
            if (targetString.charAt(i) == targetCharacter) {
                System.out.print(String.valueOf(i).concat(" "));
            }
        }
    }

    void printLastCharacterIndexes(String targetString) {
        char lastCharacter = targetString.charAt(targetString.length() - 1);
        printCharacterIndexes(targetString, lastCharacter);
    }

    void findStringsContainingTargetWords(String[] checkingStrings, String firstConditionWord, String secondConditionWord) {
        int nonConditionCounter = 0;

        for (String checkingString : checkingStrings) {
            if (checkingString.toUpperCase().indexOf(firstConditionWord.toUpperCase()) != -1) {
                System.out.println("Your string \"".concat(checkingString).concat("\" contains target word \"").concat(firstConditionWord).concat("\"")
                        .concat(" and its length is ".concat(String.valueOf(checkingString.length()))));
            } else if (checkingString.toUpperCase().indexOf(secondConditionWord.toUpperCase()) != -1) {
                System.out.println("Your string \"".concat(checkingString).concat("\" contains target word \"").concat(secondConditionWord).concat("\"")
                        .concat(" and its length is ".concat(String.valueOf(checkingString.length()))));
            } else {
                nonConditionCounter++;
            }
        }
        System.out.println("Amount of strings that haven't got contional words: ".concat(String.valueOf(nonConditionCounter)));
    }

    void deleteSpacesBetweenQuestionMarks(String targetString) {
        int firstQuestionMarkIndex = targetString.indexOf("?", targetString.indexOf("?") + 1);
        int secondQuestionMarkIndex = targetString.indexOf("?", firstQuestionMarkIndex + 1);
        String stringEnding = targetString.substring(secondQuestionMarkIndex);
        String resultString = targetString.substring(0, firstQuestionMarkIndex + 1);

        for (int i = firstQuestionMarkIndex + 1; i < secondQuestionMarkIndex; i++) {
            if (targetString.charAt(i) != ' ') {
                resultString = resultString.concat(String.valueOf(targetString.charAt(i)));
            }
        }
        resultString = resultString.concat(stringEnding);

        System.out.println("Your input string: \"".concat(targetString).concat("\""));
        System.out.println("Your string after transformation: \"".concat(resultString).concat("\""));
    }
}
