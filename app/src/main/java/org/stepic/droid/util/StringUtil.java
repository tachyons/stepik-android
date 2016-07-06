package org.stepic.droid.util;

import org.stepic.droid.base.MainApplication;
import org.stepic.droid.configuration.IConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public static Double safetyParseString(String str) {
        Double doubleScore = null;
        try {
            doubleScore = Double.parseDouble(str);

        } catch (Exception ignored) {
        }
        return doubleScore;
    }

    public static String getUriForCourse(String baseUrl, String slug){
        StringBuilder stringBuilder =new StringBuilder();
        stringBuilder.append(baseUrl);
        stringBuilder.append(AppConstants.WEB_URI_SEPARATOR);
        stringBuilder.append("course");
        stringBuilder.append(AppConstants.WEB_URI_SEPARATOR);
        stringBuilder.append(slug);
        stringBuilder.append(AppConstants.WEB_URI_SEPARATOR);
        return stringBuilder.toString();
    }

    public static String getDynamicLinkForCourse(IConfig config, String slug){
        String firebaseDomain = config.getFirebaseDomain();
        if (firebaseDomain == null){
            return getUriForCourse(config.getBaseUrl(), slug);
        }


        StringBuilder stringBuilder =new StringBuilder();
        stringBuilder.append(firebaseDomain);
        stringBuilder.append("?link=");
        stringBuilder.append(config.getBaseUrl());
        stringBuilder.append(AppConstants.WEB_URI_SEPARATOR);
        stringBuilder.append("course");
        stringBuilder.append(AppConstants.WEB_URI_SEPARATOR);
        stringBuilder.append(slug);
        stringBuilder.append(AppConstants.WEB_URI_SEPARATOR);
        stringBuilder.append("&amv=650");
        stringBuilder.append("&apn=");
        String packageName = MainApplication.getAppContext().getPackageName();
        if (packageName == null){
            return getUriForCourse(config.getBaseUrl(), slug);
        }
        stringBuilder.append(packageName);

//        stringBuilder.append("&ibi=com.AlexKarpov.Stepic");
        return stringBuilder.toString();
    }

    //Pull all links from the body for easy retrieval
    public static List<String> pullLinks(String text) {
        List<String> links = new ArrayList<>();

        String regex = "\\(?\\b(http://|www[.])[-A-Za-z0-9+&@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&@#/%=~_()|]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);
        while(m.find()) {
            String urlStr = m.group();
            if (urlStr.startsWith("(") && urlStr.endsWith(")")) {
                urlStr = urlStr.substring(1, urlStr.length() - 1);
            }
            links.add(urlStr);
        }
        return links;
    }

}
