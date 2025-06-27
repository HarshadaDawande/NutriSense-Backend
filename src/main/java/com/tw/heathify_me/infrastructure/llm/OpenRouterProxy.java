package com.tw.heathify_me.infrastructure.llm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;
import java.util.Optional;
import org.springframework.web.client.RestTemplate;
import com.tw.heathify_me.infrastructure.dto.LlmChatRequestDTO;
import com.tw.heathify_me.infrastructure.dto.LlmMessageDTO;

@Service
@Qualifier("openRouter")
public class OpenRouterProxy implements ILlmProxy {

    private final RestTemplate restTemplate;
    private final String apiKey;

    public OpenRouterProxy(RestTemplate restTemplate,
                           @Value("${openrouter.api-key:}") String apiKey) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
    }

    @Override
    @SuppressWarnings("unchecked")
    public LlmMessageDTO chat(LlmChatRequestDTO request) {
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("OpenRouter API key not configured (property openrouter.api-key is empty)");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<LlmChatRequestDTO> entity = new HttpEntity<>(request, headers);

        return Optional.ofNullable(restTemplate.postForObject(
                        "https://openrouter.ai/api/v1/chat/completions",
                        entity,
                        Map.class))
                .map(m -> (List<Map<String, Object>>) m.get("choices"))
                .filter(list -> !list.isEmpty())
                .map(list -> list.get(0))
                .map(first -> (Map<String, Object>) first.get("message"))
                .map(msg -> new LlmMessageDTO((String) msg.get("role"), (String) msg.get("content")))
                .orElse(null);
    }

}
