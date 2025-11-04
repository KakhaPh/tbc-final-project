package pages;

import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BaseApiPage {
    protected final HttpClient client = HttpClient.newHttpClient();
    protected final Gson gson = new Gson();


    protected String sendGetRequest(String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Request failed: " + response.statusCode() + " " + response.body());
        }
        return response.body();
    }
}