package ru.laverno.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.laverno.Const;
import ru.laverno.config.BotConfig;
import ru.laverno.model.Location;
import ru.laverno.service.HourlyWeather;
import ru.laverno.service.WeatherService;

import java.util.HashMap;
import java.util.Map;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig config;
    private final WeatherService weatherService;
    private final HourlyWeather hourlyWeather;
    private final Map<Long, Location> location = new HashMap<>();

    public TelegramBot(BotConfig config, WeatherService weatherService, HourlyWeather hourlyWeather) {
        this.config = config;
        this.weatherService = weatherService;
        this.hourlyWeather = hourlyWeather;
    }

    @Override
    public String getBotUsername() {
        return config.getName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            var messageText = update.getMessage().getText();
            final var chatId = update.getMessage().getChatId();

            if (messageText.startsWith("/location")) {
                messageText = messageText.substring(9).trim();
                location.put(chatId, weatherService.setLocation(messageText));
                sendMessage(chatId, String.format(Const.LOCATION_MESSAGE, location.get(chatId).getLocalName().getRuName()));
            }
            if (messageText.startsWith("/weather")) {
                sendMessage(chatId, weatherService.getWeather(location.get(chatId)));
            }
            if (messageText.startsWith("/hweather")) {
                sendMessage(chatId, hourlyWeather.getHourlyWeather(location.get(chatId)));
            }
        }
    }

    private void sendMessage(long chatId, String message) {
        final var sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(message);

        try {
            execute(sendMessage);
        } catch (TelegramApiException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
