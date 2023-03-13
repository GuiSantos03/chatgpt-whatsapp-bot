package com.github.guisantos03.client;

import com.github.guisantos03.api.API;
import it.auties.whatsapp.api.Whatsapp;
import it.auties.whatsapp.model.info.MessageInfo;
import it.auties.whatsapp.model.message.standard.TextMessage;

import java.util.concurrent.ExecutionException;

public class Bot {
    public static void main(String... args) throws ExecutionException, InterruptedException {
        Whatsapp.lastConnection()
                .addLoggedInListener(() -> System.out.println("Conectado!"))
                .addNewMessageListener(Bot::onMessage)
                .connect()
                .get();
    }

    private static void onMessage(Whatsapp api, MessageInfo info) {

        boolean isMessageAText = info.message().content() instanceof TextMessage;

        if (isMessageAText) {
            TextMessage message = (TextMessage) info.message().content();

            if (message.text().toLowerCase().contains(".gpt")) {
                System.out.println(message.text());
                String fMessage = message.text().split(".gpt ")[1];
                String fResponse = API.request(fMessage).toString();
                api.sendMessage(info.chatJid(), fResponse
                        .split("\\[ChatCompletionChoice\\(index=0, message=ChatMessage\\(role=assistant, content=")[1]
                        .split("\\), finishReason=")[0]
                        .trim());
            }
        }
    }
}