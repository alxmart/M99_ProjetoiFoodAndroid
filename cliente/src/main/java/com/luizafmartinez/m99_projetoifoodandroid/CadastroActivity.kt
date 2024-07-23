package com.luizafmartinez.m99_projetoifoodandroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.luizafmartinez.core.AlertaCarregamento
import com.luizafmartinez.core.exibirMensagem
import com.luizafmartinez.m99_projetoifoodandroid.databinding.ActivityCadastroBinding
import com.luizafmartinez.m99_projetoifoodandroid.databinding.ActivityMainBinding
import com.luizafmartinez.m99_projetoifoodandroid.domain.model.Usuario
import com.luizafmartinez.m99_projetoifoodandroid.domain.usecase.AutenticacaoUseCase
import com.luizafmartinez.m99_projetoifoodandroid.presentation.viewmodel.AutenticacaoViewModel
import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CadastroActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCadastroBinding.inflate( layoutInflater)
    }

    private val alertaCarregamento by lazy {
        AlertaCarregamento(this)
    }

    private val autenticacaoViewModel : AutenticacaoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        inicializar()
    }

    private fun inicializar() {
        inicializarToolbar()
        inicializarEventosClique()
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
                alertaCarregamento.exibir("Fazendo seu cadastro.")
            } else {
                alertaCarregamento.fechar()
            }
        }


        autenticacaoViewModel.sucesso.observe(this) { sucesso ->
            if (sucesso) {
                /*Toast.makeText(
                    this,"Cadastro realizado com sucesso",
                    Toast.LENGTH_SHORT).show()*/
                navegarPrincipal()
            } else {
                exibirMensagem("Erro ao realizar cadastro")
            }
        }

        autenticacaoViewModel.resultadoValidacao
            .observe(this) { resultadoValidacao ->
                with (binding) {

                    editCadastroNome.error =
                        if ( resultadoValidacao.nome) null else getString(R.string.erro_cadastro_nome)

                    editCadastroEmail.error =
                        if ( resultadoValidacao.email) null else getString(R.string.erro_cadastro_email)

                    editCadastroSenha.error =
                        if ( resultadoValidacao.senha) null else getString(R.string.erro_cadastro_senha)

                    editCadastroTelefone.error =
                        if ( resultadoValidacao.telefone) null else getString(R.string.erro_cadastro_telefone)
                }
            }
    }

    private fun inicializarEventosClique() {

        with( binding ) {
            btnCadastrar.setOnClickListener {

                val nome = editCadastroNome.text.toString()
                val email = editCadastroEmail.text.toString()
                val senha = editCadastroSenha.text.toString()
                val telefone = editCadastroTelefone.text.toString()

                /* Validação de Campos - Exemplos:
                val valor = "20"
                val resultadoValidacao = valor.validator()
                    .validNumber()
                    .greaterThan(10)
                    .greaterThanOrEqual(10)
                    .lessThan(20)
                    .numberEqualTo(20)
                    .allLowerCase()
                    .allUpperCase()
                    .atleastOneLowerCase()
                    .atleastOneUpperCase()
                    .nonEmpty()
                    .startWithNumber()
                    .startWithNonNumber()
                    .noNumbers()
                    .onlyNumbers()
                    .atleastOneSpecialCharacters()
                    .startsWith("A") // or "1"
                    .endsWith("1")
                    .contains("j")
                    .notContains("j")
                    .creditCardNumber()
                    .creditCardNumberWithSpaces()
                    .creditCardNumberWithDashes()
                    .validUrl()
                    .check() //Confirmar que está fazendo a validação
                    */

                /*Log.i("Validacao", "\nNome:($valNome) " +
                        "\nE-mail:($valEmail) " +
                        "\nSenha:($valSenha) " +
                        "\nTelefone:($valTelefone)"
                )*/

                val usuario = Usuario(
                    email, senha, nome, telefone
                )
                autenticacaoViewModel.cadastrarUsuario(usuario)

            }
        }
    }

    private fun inicializarToolbar() {

        val toolbar = binding.includeTbPrincipal.tbPrincipal
        setSupportActionBar( toolbar ) //Define toolbar como support action bar

        supportActionBar?.apply {
            title = "Cadastro de usuário"
            setDisplayHomeAsUpEnabled(true) //Mostra botão de voltar na toolbar
        }
    }

}