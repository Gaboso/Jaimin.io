package com.github.gaboso.jaiminio;

import com.github.gaboso.jaiminio.model.Issue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        ParserCSV parserCSV = new ParserCSV();
        List<Issue> issues = parserCSV.getContent("YOUR_FILE.csv");

        HTTPExecutor httpExecutor = new HTTPExecutor();
        httpExecutor.execute(issues);

        LOGGER.info("Import finished.");
    }

}
