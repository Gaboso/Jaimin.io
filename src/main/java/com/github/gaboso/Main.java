package com.github.gaboso;

import com.github.gaboso.model.Issue;
import org.apache.log4j.Logger;

import java.util.List;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        ParserCSV parserCSV = new ParserCSV();
        List<Issue> issues = parserCSV.getContent("FL_insurance_sample.csv");

        HTTPExecutor httpExecutor = new HTTPExecutor();
        httpExecutor.execute(issues);

        LOGGER.info("Finalizado importação");
    }

}
