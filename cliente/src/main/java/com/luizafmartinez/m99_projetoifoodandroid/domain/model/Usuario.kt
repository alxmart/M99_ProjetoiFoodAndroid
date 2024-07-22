package com.luizafmartinez.m99_projetoifoodandroid.domain.model

data class Usuario(
    val email: String,
    val senha: String,
    val nome: String = "",
    val telefone: String = ""
)
