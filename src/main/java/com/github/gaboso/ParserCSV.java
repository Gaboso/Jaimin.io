package com.github.gaboso;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.github.gaboso.model.Issue;
import org.apache.log4j.Logger;

public class ParserCSV {

    private static final Logger LOGGER = Logger.getLogger(ParserCSV.class);
    private static final String DELIMITER = "\t";
    private static final String DOUBLE_QUOTE = "\"";
    private static final String EMPTY_SPACE = "";
    private static final String SPACE = " ";

    private static final int TITLE_INDEX = 0;
    private static final int ESTIMATE_INDEX = 1;
    private static final int ID_INDEX = 2;

    private Function<String, Issue> mapToIssue = line -> {
        String[] columns = line.split(DELIMITER);

        if (columns.length > 2) {
            Issue issue = new Issue();

            String estimateTime = columns[ESTIMATE_INDEX];
            estimateTime = estimateTime.toLowerCase()
                    .replace(DOUBLE_QUOTE, EMPTY_SPACE);

            String[] estimate = estimateTime.split(SPACE);
            String hour = estimate[0];
            int seconds = Integer.parseInt(hour) * 3600;
            issue.setHumanEstimateTime(hour + "h");
            issue.setEstimateTime(seconds);

            String taskID = columns[ID_INDEX];
            taskID = taskID.replace(DOUBLE_QUOTE, EMPTY_SPACE);
            String markdownLink = LinkUtil.createMarkdownLink(taskID);
            issue.setDescription("/estimate " + issue.getHumanEstimateTime() + "\n " + markdownLink);

            String title = columns[TITLE_INDEX];
            title = title.replace(DOUBLE_QUOTE, EMPTY_SPACE);
            issue.setTitle(taskID + " - " + title);
            return issue;
        } else {
            return new Issue();
        }
    };

    public List<Issue> getContent(String filePath) {
        List<Issue> issues = new ArrayList<>();

        File file = new File(filePath);

        try (
                InputStream inputStream = new FileInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_16LE))
        ) {

            issues = reader
                    .lines()
                    .skip(1)
                    .map(mapToIssue)
                    .filter(issue -> issue.getTitle() != null)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return issues;
    }

}