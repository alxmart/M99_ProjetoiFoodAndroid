package com.luizafmartinez.m99_projetoifoodandroid

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.luizafmartinez.m99_projetoifoodandroid.databinding.ActivityCadastroBinding
import com.luizafmartinez.m99_projetoifoodandroid.databinding.ActivityMainBinding

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



    }
}