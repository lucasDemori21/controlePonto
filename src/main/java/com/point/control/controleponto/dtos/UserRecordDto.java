package com.point.control.controleponto.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record UserRecordDto(@NotBlank String nome, @Email String email, @NotNull String senha, String telefone, @NotBlank String cpf, Date dataNascimento) {
}
