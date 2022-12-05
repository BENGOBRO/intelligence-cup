package ru.bengobro.configuration;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VkApiClientConfiguration {

    @Bean
    public VkApiClient getVkApiClient() {
        return new VkApiClient(new HttpTransportClient());
    }
}
