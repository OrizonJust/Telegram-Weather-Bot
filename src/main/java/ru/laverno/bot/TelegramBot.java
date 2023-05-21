package ru.laverno.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.laverno.Const;
import ru.laverno.config.BotConfig;
import ru.laverno.service.LocationService;
import ru.laverno.service.WeatherService;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig config;
    private final WeatherService weatherService;
    private final LocationService locationService;

    public TelegramBot(BotConfig config, WeatherService weatherService, LocationService locationService) {
        this.config = config;
        this.weatherService = weatherService;
        this.locationService = locationService;
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
                final var result = locationService.setLocation(chatId, messageText);
                sendMessage(chatId, String.format(Const.LOCATION_MESSAGE, result.getName()));
            }
            if (messageText.startsWith("/weather")) {
                sendMessage(chatId, weatherService.getWeather(locationService.getLocationByChatId(chatId)));
            }
            if (messageText.startsWith("/hweather")) {
                sendMessage(chatId, weatherService.getHourlyWeather(locationService.getLocationByChatId(chatId)));
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
