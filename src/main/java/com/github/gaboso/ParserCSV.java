package com.github.gaboso;

import com.github.gaboso.model.Issue;
import org.apache.log4j.Logger;

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

public class ParserCSV {

    private static final Logger LOGGER = Logger.getLogger(ParserCSV.class);
    private static final String DELIMITER = "\t";

    private static final int TITLE_INDEX = 0;
    private static final int ID_INDEX = 1;

    private Function<String, Issue> mapToIssue = line -> {
        String[] columns = line.split(DELIMITER);

        if (columns.length > 1) {
            Issue issue = new Issue();

            String title = columns[TITLE_INDEX];
            title = title.replaceAll("\"", "");
            issue.setTitle(title);

            String taskID = columns[ID_INDEX];
            taskID = taskID.replaceAll("\"", "");
            String markdownLink = LinkUtil.createMarkdownLink(taskID);
            issue.setDescription(markdownLink);
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