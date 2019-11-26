package com.github.gaboso;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.github.gaboso.model.Issue;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

public class HTTPExecutor {

    private static final Logger LOGGER = Logger.getLogger(HTTPExecutor.class);

    private static final String PRIVATE_TOKEN = "8sWSVXeThbHM4ynFwWXt";
    private static final String GIT_URL = "https://gitlab.ungp.softplan.com.br/";
    private static final String PROJECT_ID = "2544";

    public void execute(String title, String description, int estimateTime, String humanEstimateTime) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        try {
            HttpPost createIssuePost = new HttpPost(GIT_URL + "/api/v4/projects/" + PROJECT_ID + "/issues");
            createIssuePost.addHeader("PRIVATE-TOKEN", PRIVATE_TOKEN);

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("title", title));
            params.add(new BasicNameValuePair("description", description));

//            JSONObject timeStats = new JSONObject();
//            timeStats.put("human_time_estimate", humanEstimateTime);
//            timeStats.put("time_estimate", estimateTime);
//            timeStats.put("total_time_spent", 0);
//            timeStats.put("human_total_time_spent", "null");
//
//            params.add(new BasicNameValuePair("time_stats", timeStats.toString()));

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
            int estimateTime = issue.getEstimateTime();
            String humanEstimateTime = issue.getHumanEstimateTime();

            execute(title, description, estimateTime, humanEstimateTime);
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