package com.tw.nutrisense.controller;

import com.tw.nutrisense.infrastructure.llm.ILlmProxy;
import com.tw.nutrisense.infrastructure.dto.LlmChatRequestDTO;
import com.tw.nutrisense.infrastructure.dto.LlmMessageDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/llm")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class LlmTestController {

    private final ILlmProxy openrouter;

    public LlmTestController(@Qualifier("openRouter") ILlmProxy openrouter) {
        this.openrouter = openrouter;
    }

    @PostMapping("/openrouter")
    public ResponseEntity<LlmMessageDTO> chatOpenRouter(@RequestBody LlmChatRequestDTO request) {
        var response = openrouter.chat(request);
        return ResponseEntity.ok(response);
    }
}
