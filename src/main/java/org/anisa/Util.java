package org.anisa;

public class Util {
    public static String toTitleCase(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        String[] words = str.split(" ");
        StringBuilder titleCase = new StringBuilder();

        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }

            String lowerWord = word.toLowerCase();
            String titleCaseWord = lowerWord.substring(0, 1).toUpperCase() + lowerWord.substring(1);
            titleCase.append(titleCaseWord).append(" ");
        }

        return titleCase.toString().trim();
    }
}
