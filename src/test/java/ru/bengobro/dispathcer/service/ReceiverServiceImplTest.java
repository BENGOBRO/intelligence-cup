package ru.bengobro.dispathcer.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.bengobro.model.ResponseInfo;
import ru.bengobro.model.User;
import ru.bengobro.service.impl.ApiServiceImpl;
import ru.bengobro.service.impl.UserInfoProcessingServiceImpl;

public class ReceiverServiceImplTest {

    private final static String vkServiceToken = "d9b83319d9b83319d9b83319eedaa97915dd9b8d9b83319bad93186009debb836fa184b";
    private UserInfoProcessingServiceImpl receiverService;

    @BeforeEach
    public void init() {
        ApiServiceImpl apiService = new ApiServiceImpl();
        receiverService = new UserInfoProcessingServiceImpl(apiService);
        apiService.init(vkServiceToken);
    }

    @Test
    void getUserInfo_shouldReturnSomeInfo() {
        User user = getUser();
        ResponseInfo responseInfo = getData();

        User result = receiverService.getUserInfo(responseInfo, vkServiceToken);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(user, result);
    }

    private User getUser() {

        User user = new User();

        user.setLastName("Стародубцев");
        user.setFirstName("Михаил");
        user.setMiddleName("");
        user.setMember(false);

        return user;
    }

    private ResponseInfo getData() {
        ResponseInfo responseInfo = new ResponseInfo();

        responseInfo.setUserId(237595431);
        responseInfo.setGroupId(103408267);

        return responseInfo;
    }

}
