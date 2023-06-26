package com.ufv.extraordinaria.dis;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

@Service
public class Requests {

    public ArrayList<Spaceship> getShips(int page) throws URISyntaxException, IOException, InterruptedException {

        String url = "https://swapi.dev/api/starships/?page=%s";

        String urlRequest = String.format(url, page);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(urlRequest))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        Spaceships ships = gson.fromJson(response.body(), Spaceships.class);
        return ships.getResults();
    }

    public ArrayList<Spaceship> getShips() throws URISyntaxException, IOException, InterruptedException {

        String url = "https://swapi.dev/api/starships";
        String urlRequest = String.format(url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(urlRequest))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        Spaceships ships = gson.fromJson(response.body(), Spaceships.class);
        return ships.getResults();
    }

    public void saveData (ArrayList<Spaceship> shipsList) {
        String url = "http://localhost:8081/ships";

        Gson gson = new Gson();
        String shipsListJSON = gson.toJson(shipsList);
        System.out.println(shipsListJSON);

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .headers("Content-Type", "application/JSON")
                    .POST(HttpRequest.BodyPublishers.ofString(shipsListJSON))
                    .build();

            HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

