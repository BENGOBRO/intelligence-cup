package ru.bengobro.service;

import ru.bengobro.model.ResponseInfo;
import ru.bengobro.model.User;

public interface UserInfoProcessingService {

    User getUserInfo(ResponseInfo responseInfo, String vkServiceToken);

}
