package ooptest.valuess.utils.text;

import java.util.ArrayList;

/**
 * Created by eleven on 3/20/14.
 */

/**
 * TextTool use for parser text -> word Array and get Sentence that contains a specified word
 */
public class TextTool {

    private String content = "";
    private String original;

    public TextTool(String content) {
        this.original = content;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            if (Character.isLetter(c)) {
                builder.append(c);
            } else {
                if (c == '.' || c == '?' || c == '!' || c == '\n')
                    builder.append('.');
                else if (c == '\'' && i != 0 && i != content.length() && (Character.isLetter(content.charAt(i - 1)) & Character.isLetter(content.charAt(i + 1))))
                    builder.append('\'');
                else
                    builder.append(' ');
            }

        }
        this.content = builder.toString().toLowerCase();
    }

    public String getOriginal() {
        return original;
    }

    public ArrayList<Word> toWordArray() {
        ArrayList<Word> arr = new ArrayList<Word>();
        StringBuilder builder = new StringBuilder();
        if (content.length() > 0)
            builder.append(content.charAt(0));
        for (int i = 1; i < content.length(); i++) {
            if (content.charAt(i) == ' ' && content.charAt(i - 1) == ' ') {
            } else builder.append(content.charAt(i));
        }

        String[] str = builder.toString().split(" ");  // split string into String array

        for (int i = 0; i < str.length; i++) {
            str[i] = str[i].replace(".", "");
            if (str[i].compareTo("") == 0) continue;
            boolean check = false;                     // check if str[i] is a near word of one of word in arr ArrayList
            for (int j = 0; j < arr.size(); j++) {
                if ((check = arr.get(j).checkNearWord(str[i]))) {
                    arr.get(j).addAWord(str[i]);
                    break;
                }
            }

            if (!check) arr.add(new Word(str[i]));
        }
        return arr;
    }

    public String getSentence(String word) {
        int start = 0;
        int index = 0;
        int count = 0;
        word = word.toLowerCase();

        while (index < content.length()) {
            char c = content.charAt(index);
            if (Character.isLetter(c)) {
                count = 0;
                while ((index < content.length()) && (count < word.length()) && (c == word.charAt(count))) {
                    index++;
                    count++;
                    c = content.charAt(index);
                }

                if (count == word.length() && (c == ' ' || c == '.' || index > content.length())) {
                    /* match word detect */
                    for (int i = index; i < content.length(); i++) {
                        /* return when meet the '.' character*/
                        if (content.charAt(i) == '.')
                            return original.substring(start, i);
                    }

                    /* in others case */
                    return original.substring(start);
                }

                /* pass letter character */
                for (int i = index; i < content.length(); i++) {
                    if (!Character.isLetter(content.charAt(i))) {
                        index = i;
                        break;
                    }
                }
            } else {
                if (c == '.')
                    start = index + 1;
                index++;
            }
        }
        return null;
    }

}
