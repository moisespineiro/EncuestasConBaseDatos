package com.example.encuestas.Util

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.encuestas.Data.SurveysDb
import com.example.encuestas.Entity.EntityEncuesta
import com.example.encuestas.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(){
    val encuestasDb = SurveysDb(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val position = intent.extras?.getString("ID")
        //val numEncuesta = ((position?.toInt())?.plus((1)))
        //txvNumeroEncuesta.setText(":${numEncuesta.toString()}")
        var encuesta = EntityEncuesta()
        var idencuesta = position!!.toInt()
        //encuesta = listaEncuesta[position!!.toInt()]
        encuesta = encuestasDb.encuestasGetOne(idencuesta)
        var genero =""
        var viajado=""
        var frecuencia=""
        var exp=""
        var ofertas=""
        //txvNumeroEncuesta.setText(":${position+1.toString()}")
        txvNumeroEncuesta.setText(":${idencuesta.toString()}")
        txvNombreDetalle.setText("${encuesta.nombre.toString()}")
        txvApellidoPaternoDetalle.setText("${encuesta.apellidoPaterno.toString()}")
        txvApellidoMaternoDetalle.setText("${encuesta.apellidoMaterno.toString()}")
        txvCorreoDetalle.setText("${encuesta.correo.toString()}")
        if(encuesta.genero==1){
            genero= "Masculino"
        }else{
            genero= "Femenino"
        }
        txvGeneroDetalle.setText("$genero")
        if(encuesta.viajado==1){
            viajado= "Si ha viajadado en AeroPatito"
        }else{
            viajado= "No he viajado en AeroPatito"
        }
        txvViajadoDetalle.setText("$viajado")
        when(encuesta.frecuencia){
            1->{
                frecuencia = "1 a 3 veces al año"
            }
            2->{
                frecuencia = "4 a 7 veces al año"
            }
            3->{
                frecuencia = "Más de 7 veces al año"
            }
        }
        txvFrecuenciaDetalle.setText("$frecuencia")
        when(encuesta.experiencia){
            1->{
                exp="Mala"
            }
            2->{
                exp="Regular"
            }
            3->{
                exp="Buena"
            }
            4->{
                exp="Excelente"
            }
        }
        txvExperienciaDetalle.setText("$exp")
        var esquema = ""
        if(encuesta.economica == true){
            esquema += "Económica "
        }
        if(encuesta.promo == true){
            esquema += "Promo "
        }
        if(encuesta.ejecutiva){
            esquema += "Ejecutiva "
        }

        txvEsquemaDetalle.setText("$esquema")
        txvServicioDetalle.setText("${encuesta.servicio.toString()}")
        txvMejorasDetalle.setText("${encuesta.mejora.toString()}")

        if(encuesta.ofertas==true){
            ofertas = "Quiero recibir ofertas y descuentos"
        }else{
            ofertas = "No quiero recibir ofertas y descuentos"
        }
        txvOfertasDescuentosDetalle.setText("$ofertas")
    }
}