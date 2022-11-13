package ru.bengobro.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseInfo {

    @JsonProperty(value = "user_id")
    private int userId;

    @JsonProperty(value = "group_id")
    private int groupId;

}
