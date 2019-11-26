package com.github.gaboso;

public class LinkUtil {

    private static final String RTC_LINK = "https://clm.ungp.softplan.com.br/ccm/web/projects/OBRASGOV";

    private LinkUtil() {
    }

    public static String createMarkdownLink(String taskID) {
        return "[Link da tarefa no RTC](" + RTC_LINK + "#action=com.ibm.team.workitem.viewWorkItem&id=" + taskID + ")";
    }

}
