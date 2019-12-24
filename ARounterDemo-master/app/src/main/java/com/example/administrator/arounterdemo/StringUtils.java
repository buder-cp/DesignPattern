package com.example.administrator.arounterdemo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xujun  on 4/7/2018.
 */
public class StringUtils {

    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

}
