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

        when (res) {
            in 0.0..18.5 -> {
                cor = getColor(R.color.imc0)
                classificacao_aux = getString(R.string.baixo_peso)
                valor_imc = getString(R.string.imc0)
            }
            in 18.5..24.99 -> {
                cor = getColor(R.color.imc1)
                classificacao_aux = getString(R.string.peso_normal)
                valor_imc = getString(R.string.imc1)
            }
            in 25.0..29.99 -> {
                cor = getColor(R.color.imc2)
                classificacao_aux = getString(R.string.excesso_peso)
                valor_imc = getString(R.string.imcA2)
            }
            in 30.0..34.99 -> {
                cor = getColor(R.color.imc3)
                classificacao_aux = getString(R.string.obesidade_classe_1)
                valor_imc = getString(R.string.imcA3)
            }
            in 35.0..39.9 -> {
                cor = getColor(R.color.imc4)
                classificacao_aux = getString(R.string.obesidade_classe_2)
                valor_imc = getString(R.string.imcA4)
            }
            else -> {
                cor = getColor(R.color.imc5)
                classificacao_aux = getString(R.string.obesidade_classe_3)
                valor_imc = getString(R.string.imcA5)
            }
        }
    }

    private fun calcularIMCIdoso(res: Double) {

        when (res) {
            in 0.0..21.99 -> {
                cor = getColor(R.color.imc0)
                classificacao_aux = getString(R.string.baixo_peso)
                valor_imc = getString(R.string.imcI0)
            }
            in 22.0..27.0 -> {
                cor = getColor(R.color.imc1)
                classificacao_aux = getString(R.string.adequado_ou_eutrofico)
                valor_imc = getString(R.string.imcI1)
            }
            else -> {
                cor = getColor(R.color.imc2)
                classificacao_aux = getString(R.string.sobrepeso)
                valor_imc = getString(R.string.imcI2)
            }
        }
    }
}