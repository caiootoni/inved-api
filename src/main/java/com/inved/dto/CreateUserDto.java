package com.inved.dto;

import com.inved.infra.security.RoleName;

public record CreateUserDto(String email, String password, RoleName role) {
}
