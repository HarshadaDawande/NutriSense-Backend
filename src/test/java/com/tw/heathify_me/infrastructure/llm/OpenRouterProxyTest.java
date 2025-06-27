package com.tw.heathify_me.infrastructure.llm;

import com.tw.heathify_me.infrastructure.dto.LlmChatRequestDTO;
import com.tw.heathify_me.infrastructure.dto.LlmMessageDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OpenRouterProxyTest {

    @Mock
    private RestTemplate restTemplate;

    private OpenRouterProxy proxy;

    @BeforeEach
    void setUp() {
        proxy = new OpenRouterProxy(restTemplate, "dummy-key");
    }

    @Test
    @DisplayName("Throws when API key is missing")
    void shouldThrowWhenApiKeyMissing() {
        OpenRouterProxy noKeyProxy = new OpenRouterProxy(restTemplate, " ");
        assertThrows(IllegalStateException.class,
                () -> noKeyProxy.chat(new LlmChatRequestDTO("model", List.of())));
    }

    @Test
    @DisplayName("Returns mapped first message when response has choices")
    void shouldReturnFirstChoiceMessage() {
        Map<String, Object> response = Map.of(
                "choices", List.of(Map.of(
                        "message", Map.of("role", "assistant", "content", "hello"))));

        when(restTemplate.postForObject(anyString(), any(HttpEntity.class), ArgumentMatchers.<Class<Map>>any()))
                .thenReturn(response);

        LlmMessageDTO dto = proxy.chat(new LlmChatRequestDTO("model", List.of()));

        assertNotNull(dto);
        assertEquals("assistant", dto.role());
        assertEquals("hello", dto.content());
    }

    @Nested
    class ReturnsNullOnEdgeCases {
        @Test
        void whenResponseNull() {
            when(restTemplate.postForObject(anyString(), any(HttpEntity.class), ArgumentMatchers.<Class<Map>>any()))
                    .thenReturn(null);
            assertNull(proxy.chat(new LlmChatRequestDTO("m", List.of())));
        }

        @Test
        void whenChoicesEmpty() {
            Map<String, Object> resp = Map.of("choices", List.of());
            when(restTemplate.postForObject(anyString(), any(HttpEntity.class), ArgumentMatchers.<Class<Map>>any()))
                    .thenReturn(resp);
            assertNull(proxy.chat(new LlmChatRequestDTO("m", List.of())));
        }

        @Test
        void whenMessageMissing() {
            Map<String, Object> resp = Map.of("choices", List.of(Map.of()));
            when(restTemplate.postForObject(anyString(), any(HttpEntity.class), ArgumentMatchers.<Class<Map>>any()))
                    .thenReturn(resp);
            assertNull(proxy.chat(new LlmChatRequestDTO("m", List.of())));
        }
    }
}
