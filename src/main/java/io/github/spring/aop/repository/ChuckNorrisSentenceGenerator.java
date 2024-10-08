package io.github.spring.aop.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.Optional;

public record ChuckNorrisSentenceGenerator(Call.Factory callFactory, ObjectMapper objectMapper) implements SentenceGenerator {

    @Override
    public String generate() {
        final var request = new Request.Builder()
                .url("https://api.chucknorris.io/jokes/random")
                .get()
                .build();

        try (var response = callFactory.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return Optional.ofNullable(response.body())
                        .map(this::retrieveSentence)
                        .orElse("Chuck Norris is sleeping...");
            } else {
                return "Chuck Norris not available...";
            }
        } catch (IOException e) {
            return "Chuck Norris cannot help you now...";
        }
    }

    private String retrieveSentence(ResponseBody body) {
        try {
            JsonNode root = objectMapper.readTree(body.string());
            return root.path("value").asText(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
