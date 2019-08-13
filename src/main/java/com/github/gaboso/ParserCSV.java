package com.github.gaboso;

import com.github.gaboso.model.Issue;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ParserCSV {

    private static final Logger LOGGER = Logger.getLogger(ParserCSV.class);
    private static final String COMMA_DELIMITER = ",";

    private static final int TITLE_INDEX = 0;
    private static final int ID_INDEX = 1;


    public List<Issue> getContent(String filePath) {
        List<Issue> issues = new ArrayList<>();

        File file = new File(filePath);
        try (
                InputStream inputStream = new FileInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))
        ) {

            issues = reader
                    .lines()
                    .skip(1)
                    .map(mapToIssue)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return issues;
    }

    private Function<String, Issue> mapToIssue = line -> {
        String[] columns = line.split(COMMA_DELIMITER);
        Issue issue = new Issue();

        String taskID = columns[ID_INDEX];
        String markdownLink = LinkUtil.createMarkdownLink(taskID);
        String markdownLinkEncoded = LinkUtil.encode(markdownLink);

        issue.setDescription(markdownLinkEncoded);
        issue.setTitle(columns[TITLE_INDEX]);
        return issue;
    };

}