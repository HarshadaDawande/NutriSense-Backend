package com.tw.nutrisense.infrastructure.dto;

import java.util.List;

public record LlmChatRequestDTO(String model, List<LlmMessageDTO> messages) { }
