package com.youcode.product_managementV2.dto.update;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRoleUpdateDto {

    @NotNull(message = "Role ID is required")
    private Long roleId;
}
