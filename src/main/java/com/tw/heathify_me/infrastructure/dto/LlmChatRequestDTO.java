package com.tw.heathify_me.infrastructure.dto;

import java.util.List;

public record LlmChatRequestDTO(String model, List<LlmMessageDTO> messages) { }
