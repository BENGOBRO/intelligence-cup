package ru.bengobro.service;

import ru.bengobro.model.RequestInfo;
import ru.bengobro.model.User;

public interface ApiService {

    User getFullName(RequestInfo responseInfo, String accessToken);

    String isMember(int userId, int groupId, String accessToken);

}
