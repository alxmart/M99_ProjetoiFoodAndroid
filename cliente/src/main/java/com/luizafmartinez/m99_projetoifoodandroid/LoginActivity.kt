package com.luizafmartinez.m99_projetoifoodandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.luizafmartinez.core.AlertaCarregamento
import com.luizafmartinez.core.exibirMensagem
import com.luizafmartinez.m99_projetoifoodandroid.databinding.ActivityLoginBinding
import com.luizafmartinez.m99_projetoifoodandroid.databinding.ActivityMainBinding
import com.luizafmartinez.m99_projetoifoodandroid.domain.model.Usuario
import com.luizafmartinez.m99_projetoifoodandroid.presentation.viewmodel.AutenticacaoViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val alertaCarregamento by lazy {
        AlertaCarregamento(this)
    }

    private val autenticacaoViewModel: AutenticacaoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        inicializar()
        //FirebaseAuth.getInstance().signOut()
    }

    override fun onStart() {
        super.onStart()
        autenticacaoViewModel.verificarUsuarioLogado()
    }

    private fun inicializar() {
        inicializarEventoClique()
        inicializarObservaveis()
    }

    fun navegarPrincipal() {
        startActivity(
            Intent(this, MainActivity::class.java)
        )
    }

    private fun inicializarObservaveis() {

        autenticacaoViewModel.carregando.observe(this) { carregando ->
            if ( carregando ) {
                alertaCarregamento.exibir("Efetuando login")
            } else {
                alertaCarregamento.fechar()
            }
        }

        autenticacaoViewModel.usuarioEstaLogado.observe(this) { usuarioEstaLogado ->
            if ( usuarioEstaLogado ) {
                navegarPrincipal()
            }
        }

        autenticacaoViewModel.sucesso.observe(this) { sucesso ->
            if (sucesso) {
                /*Toast.makeText(
                    this,"Cadastro realizado com sucesso",
                    Toast.LENGTH_SHORT).show()*/
                navegarPrincipal()
            } else {
                exibirMensagem("Erro ao fazer login")
            }
        }

        autenticacaoViewModel.resultadoValidacao
            .observe(this) { resultadoValidacao ->

                with (binding) {

                    editLoginEmail.error =
                        if ( resultadoValidacao.email) null else getString(R.string.erro_cadastro_email)

                    editLoginSenha.error =
                        if ( resultadoValidacao.senha) null else getString(R.string.erro_cadastro_senha)
                }
            }
    }

    private fun inicializarEventoClique() {

        with(binding) {

            textCadastro.setOnClickListener {
                startActivity(
                    Intent(applicationContext, CadastroActivity::class.java)
                )
            }

            btnLogin.setOnClickListener {
                val email = editLoginEmail.text.toString()
                val senha = editLoginSenha.text.toString()
                val usuario = Usuario(
                    email, senha
                )
                autenticacaoViewModel.logarUsuario( usuario )
            }
        }

    }

}