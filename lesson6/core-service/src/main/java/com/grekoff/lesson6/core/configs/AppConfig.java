package com.grekoff.lesson6.core.configs;

import com.grekoff.lesson6.core.properties.CartServiceIntegrationProperties;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.concurrent.TimeUnit;

import static io.netty.channel.ChannelOption.CONNECT_TIMEOUT_MILLIS;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(
        CartServiceIntegrationProperties.class
)
public class AppConfig {
    private final CartServiceIntegrationProperties cartServiceIntegrationProperties;

    @Bean
    public WebClient cartServiceWebClient() {
//        HttpClient.create();
//        return WebClient
//                .builder()
//                .baseUrl(cartServiceIntegrationProperties.getUrl())
//                .clientConnector(new ReactorClientHttpConnector(HttpClient.newConnection()
//                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, cartServiceIntegrationProperties.getConnectTimeout())
//                        .doOnConnected(connection -> {
//                            connection.addHandlerLast(new ReadTimeoutHandler(cartServiceIntegrationProperties.getReadTimeout(), TimeUnit.MILLISECONDS));
//                            connection.addHandlerLast(new WriteTimeoutHandler(cartServiceIntegrationProperties.getWriteTimeout(), TimeUnit.MILLISECONDS));
//                        })))
//                .build();

    HttpClient httpClient = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, cartServiceIntegrationProperties.getConnectTimeout())
            .doOnConnected(connection ->
                    connection.addHandlerLast(new ReadTimeoutHandler(cartServiceIntegrationProperties.getReadTimeout(), TimeUnit.MILLISECONDS))
                            .addHandlerLast(new WriteTimeoutHandler(cartServiceIntegrationProperties.getWriteTimeout(), TimeUnit.MILLISECONDS)));

    return WebClient.builder()
            .baseUrl(cartServiceIntegrationProperties.getUrl())
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .build();

    }
}

