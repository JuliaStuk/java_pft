package ru.stqa.pft.test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;

public class TestBase {

    public static Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }

    public static Set<Issue> getIssues() throws IOException {
        String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json?limit=300")).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());
    }

    private static boolean isIssueOpen(int issueId) throws IOException {
        String json = getExecutor().execute(Request.Get(String.format("https://bugify.stqa.ru/api/issues/%s.json", issueId))).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement jsonIssues = parsed.getAsJsonObject().get("issues");
        Set<Issue> issues = new Gson().fromJson(jsonIssues, new TypeToken<Set<Issue>>() {
        }.getType());
        Issue issue = issues.iterator().next();
        String stateName = issue.getStateName();
        return !stateName.equals("Resolved");

    }

    public static void skipIfNotFixed(int issueId) throws Exception {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}