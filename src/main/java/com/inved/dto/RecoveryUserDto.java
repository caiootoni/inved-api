package com.inved.dto;

import com.inved.model.Role;

import java.util.List;

public record RecoveryUserDto(Long id, String email, List<Role> roles) {
}
