package Servicios;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.retolibros.retolibros.RetolibrosApplication;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@WebServlet("/api/json")
public class APIrequest extends RetolibrosApplication {
    private final String url = "https://gutendex.com/books";
    private final Gson gson;
    private final HttpClient client;


    public APIrequest() {
        this.client = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    public String getAPIrequest(String url) {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);

        }
        String json = response.body();
        return json;
    }
}
