package com.github.gaboso;

import com.github.gaboso.model.Issue;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class CurlExecutor {

    private static final Logger LOGGER = Logger.getLogger(CurlExecutor.class);

    private static final String PRIVATE_TOKEN = "XXXXX";
    private static final String GIT_URL = "https://gitlab.example.com";
    private static final String PROJECT_ID = "28";

    private CurlExecutor() {
    }

    public static void execute(String title, String description) {
        try {
            String command = "curl --request PUT --header \"PRIVATE-TOKEN: " + PRIVATE_TOKEN + "\" " +
                    GIT_URL + "/api/v4/projects/" + PROJECT_ID + "/issues?title=" + title + "&description=" + description;
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            process.destroy();
        } catch (IOException | InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public static void execute(List<Issue> issues) {
        for (Issue issue : issues) {
            String title = issue.getTitle();
            String description = issue.getDescription();

            execute(title, description);
        }
    }

}