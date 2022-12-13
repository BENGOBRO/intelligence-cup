package ru.bengobro.service.impl;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.bengobro.model.RequestInfo;
import ru.bengobro.model.User;

public class UserInfoProcessingServiceImplTest {

    private final static String vkServiceToken = "d9b83319d9b83319d9b83319eedaa97915dd9b8d9b83319bad93186009debb836fa184b";
    private UserInfoProcessingServiceImpl receiverService;

    @BeforeEach
    public void init() {
        ApiServiceImpl apiService = new ApiServiceImpl(new VkApiClient(new HttpTransportClient()));
        receiverService = new UserInfoProcessingServiceImpl(apiService);
    }

    @Test
    void getUserInfo_shouldReturnSomeInfo() {
        User user = getUser();
        RequestInfo responseInfo = getData();

        User result = receiverService.getUserInfo(responseInfo, vkServiceToken);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(user, result);
    }

    private User getUser() {

        return User
                .builder()
                .firstName("Михаил")
                .lastName("Стародубцев")
                .middleName("")
                .member(false)
                .build();
    }

    private RequestInfo getData() {
        RequestInfo requestInfo = new RequestInfo();

        requestInfo.setUserId(237595431);
        requestInfo.setGroupId(103408267);

        return requestInfo;
    }
}
