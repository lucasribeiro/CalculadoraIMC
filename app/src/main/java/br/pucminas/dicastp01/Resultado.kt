package br.pucminas.dicastp01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.pucminas.dicastp01.MainActivity.Companion.ALTURA
import br.pucminas.dicastp01.MainActivity.Companion.CLASSIFICACAO
import br.pucminas.dicastp01.MainActivity.Companion.PESO
import br.pucminas.dicastp01.databinding.ActivityResultadoBinding
import java.text.DecimalFormat

class Resultado : AppCompatActivity() {

    lateinit var binding: ActivityResultadoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        calcularIMC()
    }

    private fun calcularIMC() {
        val peso = intent.getDoubleExtra(PESO, 0.00)
        val altura = intent.getDoubleExtra(ALTURA,0.00)
        val classificacao = intent.getStringExtra(CLASSIFICACAO)

        val altura_aux = altura / 100
        val res = peso / (altura_aux * altura_aux)
        val df = DecimalFormat("##.##")
        var classificacao_aux = ""
        var cor = getColor(R.color.imc0)
        var valor_imc = ""

        if (res <= 18.5 )
        {
            cor = getColor(R.color.imc0)
            classificacao_aux = "Baixo peso"
            valor_imc = "<18,5"
        }
        else if (res in 18.5..24.9)
        {
            cor = getColor(R.color.imc1)
            classificacao_aux = "Peso normal"
            valor_imc = "18,5 a 24,9"
        }
        else if (res in 25.0..29.9)
        {
            cor = getColor(R.color.imc2)
            classificacao_aux = "Excesso de peso"
            valor_imc = "25,0 a 29,9"
        }
        else if (res in 30.0..34.9)
        {
            cor = getColor(R.color.imc3)
            classificacao_aux = "Obesidade de Classe 1"
            valor_imc = "30,0 a 34,9"
        }
        else if (res in 35.0..39.9)
        {
            cor = getColor(R.color.imc4)
            classificacao_aux = "Obesidade de Classe 2"
            valor_imc = "35,0 a 39,9"
        }
        else if (res > 40.0)
        {
            cor = getColor(R.color.imc5)
            classificacao_aux = "Obesidade de Classe 3"
            valor_imc = ">40,0"
        }
        else
        {
            cor = getColor(R.color.imc0)
            classificacao_aux = "Não foi possivel obter"
        }

        binding.tvIMC.text = df.format(res).toString()
        binding.tvIMC.visibility = View.VISIBLE

        binding.tvResIMC.text = valor_imc
        binding.txtResClassificacao.text = classificacao_aux
        binding.tvResIMC.setBackgroundColor(cor)

        binding.tvResIMC.visibility = View.VISIBLE
        binding.txtResClassificacao.visibility  = View.VISIBLE




    }
}