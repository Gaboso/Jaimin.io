package com.github.gaboso;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class LinkUtil {

    private static final Logger LOGGER = Logger.getLogger(LinkUtil.class);
    private static final String RTC_LINK = "https://taskcontrol.com";

    private LinkUtil(){
    }

    public static String encode(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return "";
    }

    public static String createMarkdownLink(String taskID) {
        return "[Link da tarefa no RTC](" + RTC_LINK + "#action=com.ibm.team.workitem.viewWorkItem%26id=" + taskID + ")";
    }

}
