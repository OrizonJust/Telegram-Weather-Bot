package ru.laverno.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:bot_credentials.properties")
public class BotConfig {

    @Value("${bot.name}")
    private String name;

    @Value("${bot.token}")
    private String token;

    public String getName() {
        return this.name;
    }

    public String getToken() {
        return this.token;
    }
}
