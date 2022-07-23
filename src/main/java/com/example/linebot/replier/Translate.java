package com.example.linebot.replier;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Translate implements Replier{

    private final MessageEvent<TextMessageContent> event;

    public Translate(MessageEvent<TextMessageContent> event) {
        this.event = event;
    }

    @Override
    public Message reply() {

        TextMessageContent tmc = event.getMessage();
        String text = tmc.getText();

        RestTemplateBuilder templateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = templateBuilder.build();

        String regexp = Intent.TRANSLATE.getRegexp();
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(text);
        String planeText;
        if (matcher.matches()) {
            // テキストの中から元文を抜きだす
            planeText = matcher.group(1);
        } else {
            throw new IllegalArgumentException("textをスロットに分けられません");
        }

        // aws ec2
//        String url = String.format("http://{IPアドレス}:5000/translate?doc=%s");
        // ローカル
        String url = String.format("http://127.0.0.1:5000/translate?doc=%s", planeText);

        try {
            String result = restTemplate.getForObject(url, String.class);
            return new TextMessage(result);
        } catch (RestClientException e) {
            return new TextMessage(Objects.requireNonNull(e.getMessage()));
        }
    }
}
