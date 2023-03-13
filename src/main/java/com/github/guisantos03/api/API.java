package com.github.guisantos03.api;

import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class API {

    private API() {
        throw new UnsupportedOperationException("Essa é uma classe utilitária e não deve ser instanciada");
    }

    private static final String API_KEY = "";

    public static List<ChatCompletionChoice> request(String message) {

        OpenAiService service = new OpenAiService(API_KEY, Duration.ofSeconds(60));

        List<ChatMessage> chatMessage = new ArrayList<>();
        chatMessage.add(new ChatMessage("user", message));

        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(chatMessage)
                .frequencyPenalty(.5)
                .temperature(.7)
                .maxTokens(4000)
                .build();

        return service.createChatCompletion(request).getChoices();
    }
}
