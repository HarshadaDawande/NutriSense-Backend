package com.tw.heathify_me.infrastructure.llm;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class LlmClientConfig {

    /**
     * Central RestTemplate bean reused by all LLM adapters. Tweaked here once (timeouts,
     * interceptors, logging) and injected everywhere.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
