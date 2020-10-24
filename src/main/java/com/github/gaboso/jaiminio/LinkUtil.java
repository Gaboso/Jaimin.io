package com.github.gaboso.jaiminio;

public class LinkUtil {

    private static final String RTC_LINK = "https://taskcontrol.com";

    private LinkUtil() {
    }

    public static String createMarkdownLink(String taskID) {
        return "[Task link on RTC](" + RTC_LINK + "#action=com.ibm.team.workitem.viewWorkItem&id=" + taskID + ")";
    }

}
