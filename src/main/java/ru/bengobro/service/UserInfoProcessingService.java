package ru.bengobro.service;

import ru.bengobro.model.RequestInfo;
import ru.bengobro.model.User;

public interface UserInfoProcessingService {

    User getUserInfo(RequestInfo responseInfo, String vkServiceToken);

}
