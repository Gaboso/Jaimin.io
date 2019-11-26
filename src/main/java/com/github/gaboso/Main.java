package com.github.gaboso;

import java.util.List;

import com.github.gaboso.model.Issue;
import org.apache.log4j.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        ParserCSV parserCSV = new ParserCSV();
        List<Issue> issues = parserCSV.getContent("/home/gabrielcarvalho/Downloads/issues.csv");

        HTTPExecutor httpExecutor = new HTTPExecutor();
        httpExecutor.execute(issues);

        LOGGER.info("Finalizado importação");
    }

}
