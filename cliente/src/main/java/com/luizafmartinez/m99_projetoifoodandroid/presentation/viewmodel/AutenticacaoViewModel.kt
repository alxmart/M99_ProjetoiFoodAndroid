package com.luizafmartinez.m99_projetoifoodandroid.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luizafmartinez.m99_projetoifoodandroid.domain.model.Usuario
import com.luizafmartinez.m99_projetoifoodandroid.domain.usecase.AutenticacaoUseCase
import com.luizafmartinez.m99_projetoifoodandroid.domain.usecase.ResultadoValidacao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AutenticacaoViewModel @Inject constructor(
    private val autenticacaoUseCase: AutenticacaoUseCase
): ViewModel(){

    private val _resultadoValidacao = MutableLiveData<ResultadoValidacao>()
    val resultadoValidacao: LiveData<ResultadoValidacao>
    get() = _resultadoValidacao

    fun cadastrarUsuario( usuario: Usuario) {
        // Verificar os dados do usuário
        val retornoValidacao = autenticacaoUseCase.validarCadastroUsuario( usuario)
        _resultadoValidacao.value = retornoValidacao
        // Cadastrar o usuário
    }

    fun logarUsuario( usuario: Usuario) {
        val retornoValidacao = autenticacaoUseCase.validarLoginUsuario( usuario)
        _resultadoValidacao.value = retornoValidacao
    }





}