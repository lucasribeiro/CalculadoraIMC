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
    var classificacao_aux = ""
    var valor_imc = ""
    var cor = 0
    var classificacao = ""
    var peso = 0.0
    var altura = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        inicializarVariaveis()

        popularTela()
    }

    private fun inicializarVariaveis()
    {
        peso = intent.getDoubleExtra(PESO, 0.00)
        altura = intent.getIntExtra(ALTURA, 0)
        classificacao = intent.getStringExtra(CLASSIFICACAO).toString()
    }

    private fun popularTela() {


        val df = DecimalFormat("##.##")

        val res = calculaIMC().toDouble()

        if (classificacao == "A"){
            calcularIMCAdulto(res)
        }
        else
        {
            calcularIMCIdoso(res)
        }

        binding.tvIMC.text = df.format(res).toString()
        binding.tvIMC.visibility = View.VISIBLE

        binding.tvResIMC.text = valor_imc
        binding.txtResClassificacao.text = classificacao_aux
        binding.tvResIMC.setBackgroundColor(cor)

        binding.tvResIMC.visibility = View.VISIBLE
        binding.txtResClassificacao.visibility  = View.VISIBLE
    }

    private fun calculaIMC(): Double {


        val altura_aux = altura.toDouble() / 100

        return peso / (altura_aux * altura_aux)
    }

    private fun calcularIMCAdulto(res: Double) {
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
    }

    private fun calcularIMCIdoso(res: Double) {

        if (res <= 22 )
        {
            cor = getColor(R.color.imc0)
            classificacao_aux = "Baixo peso"
            valor_imc = "< 22"
        }
        else if (res in 22.0..27.0)
        {
            cor = getColor(R.color.imc1)
            classificacao_aux = "Adequado ou eutrófico"
            valor_imc = "> 22 e < 27"
        }
        else
        {
            cor = getColor(R.color.imc2)
            classificacao_aux = "Sobrepeso"
            valor_imc = "> 27"
        }
    }
}