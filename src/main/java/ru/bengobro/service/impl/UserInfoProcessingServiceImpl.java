package ru.bengobro.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bengobro.model.RequestInfo;
import ru.bengobro.model.User;
import ru.bengobro.service.ApiService;
import ru.bengobro.service.UserInfoProcessingService;

@Service
@AllArgsConstructor
public class UserInfoProcessingServiceImpl implements UserInfoProcessingService {

    private final ApiService apiService;

    public User getUserInfo(RequestInfo responseInfo, String vkServiceToken) {

        return apiService.getFullName(
                responseInfo,
                vkServiceToken
        );

    }

}
