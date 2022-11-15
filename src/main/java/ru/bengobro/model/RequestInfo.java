package ru.bengobro.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestInfo {

    @JsonProperty(value = "user_id")
    @NotBlank
    private int userId;

    @JsonProperty(value = "group_id")
    @NotBlank
    private int groupId;

}
