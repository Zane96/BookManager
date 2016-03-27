package com.example.zane.bookmanager.utils;

/**
 * Created by Zane on 16/3/21.
 */
public class TextUtil {

    public static String getProcessText(String text){
        StringBuilder sb = new StringBuilder();
        int index = 0;
        sb.append("     ");
        for (int i = 0; i < text.length(); i++){
            if (text.substring(i, i+1).equals("\n")){
                String other = text;
                sb.append(other.substring(index, i+1)).append("    ");
                index = i+1;
            }
            if (i == (text.length() - 1)){
                sb.append(text.substring(index, i));
            }
        }
        return sb.toString();
    }

}
