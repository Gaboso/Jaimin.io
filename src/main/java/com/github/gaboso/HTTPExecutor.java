package com.github.gaboso;

import com.github.gaboso.model.Issue;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class HTTPExecutor {

    private static final Logger LOGGER = Logger.getLogger(HTTPExecutor.class);

    private static final String PRIVATE_TOKEN = "XXXXX";
    private static final String GIT_URL = "https://gitlab.example.com";
    private static final String PROJECT_ID = "28";

    public void execute(String title, String description) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        try {
            HttpPost createIssuePost = new HttpPost(GIT_URL + "/api/v4/projects/" + PROJECT_ID + "/issues");
            createIssuePost.addHeader("PRIVATE-TOKEN", PRIVATE_TOKEN);

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("title", title));
            params.add(new BasicNameValuePair("description", description));

            createIssuePost.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8.toString()));

            executePostRequest(httpClient, createIssuePost);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void execute(List<Issue> issues) {
        for (Issue issue : issues) {
            String title = issue.getTitle();
            String description = issue.getDescription();

            execute(title, description);
        }
    }

    private void executePostRequest(CloseableHttpClient client, HttpPost loginHttpPost) {
        try {
            client.execute(loginHttpPost);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}