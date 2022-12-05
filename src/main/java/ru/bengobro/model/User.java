package ru.bengobro.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder(toBuilder = true)
public class User {

    @JsonProperty(value = "last_name")
    private String lastName;

    @JsonProperty(value = "first_name")
    private String firstName;

    @JsonProperty(value = "middle_name")
    private String middleName;

    @JsonProperty(value = "member")
    private boolean member;

}
