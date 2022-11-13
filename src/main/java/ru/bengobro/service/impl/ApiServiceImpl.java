package ru.bengobro.service.impl;

import com.vk.api.sdk.client.Lang;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.groups.responses.IsMemberResponse;
import com.vk.api.sdk.objects.users.Fields;
import com.vk.api.sdk.objects.users.responses.GetResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import ru.bengobro.model.User;
import ru.bengobro.service.ApiService;

@Component
public class ApiServiceImpl implements ApiService {

    private final VkApiClient vkApiClient;
    private ServiceActor actor;

    @Value("${application.id}")
    private int applicationId;

    public ApiServiceImpl() {
        TransportClient transportClient = new HttpTransportClient();
        this.vkApiClient = new VkApiClient(transportClient);
    }

    public void init(String accessToken) {
        actor = new ServiceActor(applicationId, accessToken);
    }

    @Cacheable("fullName")
    public User getFullName(int userId) {
        GetResponse response;
        try {
            response = vkApiClient.users()
                    .get(actor)
                    .userIds(String.valueOf(userId))
                    .fields(Fields.FIRST_NAME_NOM, Fields.FIRST_NAME_NOM, Fields.NICKNAME)
                    .lang(Lang.RU)
                    .execute().get(0);
        } catch (ApiException | ClientException e) {
            throw new RuntimeException(e);
        }
        return new User(response.getLastName(), response.getFirstName(), response.getNickname(), false);
    }

    @Cacheable(value = "isMember", key = "{#userId, #groupId}")
    public String isMember(int userId, int groupId)  {
        IsMemberResponse response;
        try {
            response = vkApiClient.groups()
                    .isMember(actor, String.valueOf(groupId))
                    .userId(userId)
                    .execute();
        } catch (ApiException | ClientException e) {
            throw new RuntimeException(e);
        }
        return response.getValue();
    }
}
