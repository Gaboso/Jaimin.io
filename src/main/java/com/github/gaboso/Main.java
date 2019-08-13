package com.github.gaboso;


import com.github.gaboso.model.Issue;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ParserCSV parserCSV = new ParserCSV();
        List<Issue> content = parserCSV.getContent("FL_insurance_sample.csv");
        System.out.println(content.size());
    }

}
