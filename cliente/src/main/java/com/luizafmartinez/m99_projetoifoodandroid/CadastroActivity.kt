package com.luizafmartinez.m99_projetoifoodandroid

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.luizafmartinez.m99_projetoifoodandroid.databinding.ActivityCadastroBinding
import com.luizafmartinez.m99_projetoifoodandroid.databinding.ActivityMainBinding
import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator

class CadastroActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCadastroBinding.inflate( layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        inicializar()
    }

    private fun inicializar() {
        inicializarToolbar()
        inicializarEventosClique()
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

                val valNome = nome.validator()
                    //.nonEmpty()
                    .minLength(6)
                    //.maxLength(20)
                    .check()

                //val valEmail = email.validEmail()
                val valEmail = email.validator()
                    .validEmail()
                    .check()

                val valSenha = senha.validator()
                    .minLength(6)
                    .check()

                val valTelefone = telefone.validator()
                    .minLength(14)
                    .check()

                Log.i("Validacao", "\nNome:($valNome) " +
                        "\nE-mail:($valEmail) " +
                        "\nSenha:($valSenha) " +
                        "\nTelefone:($valTelefone)")
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