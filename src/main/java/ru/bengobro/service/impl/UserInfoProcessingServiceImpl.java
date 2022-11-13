package ru.bengobro.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bengobro.model.ResponseInfo;
import ru.bengobro.model.User;
import ru.bengobro.service.UserInfoProcessingService;

@Service
@AllArgsConstructor
public class UserInfoProcessingServiceImpl implements UserInfoProcessingService {

    private final ApiServiceImpl apiService;

    public User getUserInfo(ResponseInfo responseInfo, String vkServiceToken) {
        apiService.init(vkServiceToken);
        User user = apiService.getFullName(responseInfo.getUserId());
        user.setMember("1".equals(apiService.isMember(responseInfo.getUserId(), responseInfo.getGroupId())));
        return user;
    }

}
