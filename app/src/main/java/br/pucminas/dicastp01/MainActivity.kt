package br.pucminas.dicastp01

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.pucminas.dicastp01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // objeto utilizado para fornecer acesso à view.
    private lateinit var binding: ActivityMainBinding
    var strClassificacao = "N"

    companion object {
        const val PESO = "PARAMETRO_PESO"
        const val ALTURA = "PARAMETRO_ALTURA"
        const val CLASSIFICACAO = "PARAMETRO_CLASSIFICACAO"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflando o layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        // Vinculando a Activity à View
        setContentView(binding.root)
        // Função utilizada para configurar os listeners.
        configListeners()
    }

    private fun configListeners() {
        configurarButtomListener()
        configurarRG()
    }

    private fun configurarButtomListener() {
        binding.btnEnviar.setOnClickListener {
            val strPeso = binding.etPeso.text.toString()
            val strAltura = binding.etAltura.text.toString()

            if (strClassificacao == "N")
            {
                Toast.makeText(this, "Seleciona Idoso ou Adulto !", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (strPeso.isNullOrBlank() || strPeso.toDouble() <= 0)
            {
                Toast.makeText(this, "Entre com o peso !", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (strAltura.isNullOrBlank() || strAltura.toDouble() <= 0)
            {
                Toast.makeText(this, "Entre com a altura !", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }


            val intent = Intent(this, Resultado::class.java).apply {
                putExtra(PESO, strPeso.toDouble())
                putExtra(ALTURA, strAltura.toInt())
                putExtra(CLASSIFICACAO, strClassificacao)
            }
            startActivity(intent)

            //Toast.makeText(this, classificacao, Toast.LENGTH_LONG).show()
        }
    }

    private fun configurarRG() {
        // Exemplo de uso do radio button
        // A função setOnCheckedChangeListener identifica quando o estado do componente
        // mudar, o item selecionado é encontrado pelo Id dele.
        binding.rgClassificacaoIdade.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbAdulto -> {
                    //Toast.makeText(this, getString(R.string.adulto_lbl), Toast.LENGTH_LONG).show()
                    //Log.d("PUCMINAS", getString(R.string.adulto_lbl))
                    strClassificacao = "A"
                }
                R.id.rbIdoso -> {
                    //Toast.makeText(this, "Idoso", Toast.LENGTH_LONG).show()
                    //Log.d("PUCMINAS", "Idoso")
                    strClassificacao = "I"
                }
            }
        }
    }

    private fun mudarCorBotao(i: Int) {
// Altera a cor do botão, de acordo com a opção selecionada.
        val color = when (i) {
            1 -> {
                Log.d("PUCMINAS", "Cor Preto")
                getColor(R.color.black)
            }
            else -> {
                Log.d("PUCMINAS", "Cor Preto")
                getColor(R.color.teal_700)
            }
        }
        binding.btnEnviar.setBackgroundColor(color)
    }
}