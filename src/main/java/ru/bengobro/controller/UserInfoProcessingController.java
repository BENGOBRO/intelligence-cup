package ru.bengobro.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.bengobro.model.RequestInfo;
import ru.bengobro.model.User;
import ru.bengobro.service.impl.UserInfoProcessingServiceImpl;

import javax.validation.Valid;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/gpn")
public class UserInfoProcessingController {

    private final UserInfoProcessingServiceImpl userInfoProcessingService;

    @Operation(summary = "Get user's full name and check group membership")
    @ApiResponse(responseCode = "200", description = "Found user information",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class)) })
    @GetMapping("/get_info")
    public User getUserInfo(@Valid @RequestBody RequestInfo requestInfo,
                            @RequestHeader Map<String, String> accessHeader) {
        return userInfoProcessingService
                .getUserInfo(requestInfo, accessHeader.get("vk_service_token"));
    }
}
