package com.tw.nutrisense.infrastructure.llm;

import com.tw.nutrisense.infrastructure.dto.LlmChatRequestDTO;
import com.tw.nutrisense.infrastructure.dto.LlmMessageDTO;

public interface ILlmProxy {
    /**
     * @param request full conversation history
     * @return assistant response text
     */
    LlmMessageDTO chat(LlmChatRequestDTO request);
}
