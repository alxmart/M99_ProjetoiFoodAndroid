package com.luizafmartinez.m99_projetoifoodandroid.data.remote.firebase.repository

import com.luizafmartinez.m99_projetoifoodandroid.domain.model.Usuario

interface IAutenticacaoRepository {

    suspend fun cadastrarUsuario( usuario: Usuario ) : Boolean




}