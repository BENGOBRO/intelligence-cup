package ru.bengobro.service.impl;

import com.vk.api.sdk.client.Lang;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.groups.responses.IsMemberResponse;
import com.vk.api.sdk.objects.users.Fields;
import com.vk.api.sdk.objects.users.responses.GetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import ru.bengobro.model.RequestInfo;
import ru.bengobro.model.User;
import ru.bengobro.service.ApiService;

@Component
@RequiredArgsConstructor
public class ApiServiceImpl implements ApiService {

    private final VkApiClient vkApiClient;
    @Value("${application.id}")
    private int applicationId;

    @Cacheable("fullName")
    public User getFullName(RequestInfo responseInfo, String accessToken) {

        ServiceActor actor = new ServiceActor(applicationId, accessToken);
        var userId = responseInfo.getUserId();
        var groupId = responseInfo.getGroupId();

        GetResponse response;
        try {
            response = vkApiClient
                    .users()
                    .get(actor)
                    .userIds(String.valueOf(userId))
                    .fields(Fields.FIRST_NAME_NOM, Fields.FIRST_NAME_NOM, Fields.NICKNAME)
                    .lang(Lang.RU)
                    .execute().get(0);
        } catch (ApiException | ClientException e) {
            throw new RuntimeException(e);
        }

        boolean isMember = "1".equals(
                isMember(
                        userId,
                        groupId,
                        accessToken)
        );

        return User.builder()
                .firstName(response.getFirstName())
                .lastName(response.getLastName())
                .middleName(response.getNickname())
                .member(isMember)
                .build();

    }

    @Cacheable(value = "isMember", key = "{#userId, #groupId}")
    public String isMember(int userId, int groupId, String accessToken)  {
        ServiceActor actor = new ServiceActor(applicationId, accessToken);

        IsMemberResponse response;
        try {
            response = vkApiClient
                    .groups()
                    .isMember(actor, String.valueOf(groupId))
                    .userId(userId)
                    .execute();
        } catch (ApiException | ClientException e) {
            throw new RuntimeException(e);
        }

        return response.getValue();
    }
}
