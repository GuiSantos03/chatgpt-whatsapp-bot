package com.github.guisantos03;

import com.github.guisantos03.client.Bot;

import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {
        try {
            Bot.main();
        } catch (ExecutionException | InterruptedException e) {
            System.out.println("Erro ao tentar executar. Mensagem de erro: " + e);
        }
    }
}