package com.tw.heathify_me.infrastructure.llm;

import com.tw.heathify_me.infrastructure.dto.LlmChatRequestDTO;
import com.tw.heathify_me.infrastructure.dto.LlmMessageDTO;

public interface ILlmProxy {
    /**
     * @param request full conversation history
     * @return assistant response text
     */
    LlmMessageDTO chat(LlmChatRequestDTO request);
}
