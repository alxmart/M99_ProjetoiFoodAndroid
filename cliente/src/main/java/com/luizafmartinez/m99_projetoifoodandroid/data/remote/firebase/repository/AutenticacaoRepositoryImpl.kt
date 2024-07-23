package com.luizafmartinez.m99_projetoifoodandroid.data.remote.firebase.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.luizafmartinez.m99_projetoifoodandroid.domain.model.Usuario
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AutenticacaoRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): IAutenticacaoRepository {
    override suspend fun cadastrarUsuario(usuario: Usuario): Boolean {
        return firebaseAuth.createUserWithEmailAndPassword(
            usuario.email, usuario.senha
        ).await() != null
    }
}