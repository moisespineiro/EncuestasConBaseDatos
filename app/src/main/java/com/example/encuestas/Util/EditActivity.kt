package com.example.encuestas.Util

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.encuestas.Data.ListaEncuestas

import com.example.encuestas.Data.SurveysDb
import com.example.encuestas.Data.UsersDb.Companion.usuario_registrado
import com.example.encuestas.Entity.EntityEncuesta
import com.example.encuestas.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity(){
    //val encuesta = ListaEncuestas()
    var miencuesta = EntityEncuesta()
    val encuestasDb = SurveysDb(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        val position = intent.extras?.getString("ID")

        if (position != null) {
            //miencuesta = listaEncuesta[position.toInt()]
            miencuesta = encuestasDb.encuestasGetOne(position.toInt())
        }

        txvNombreEditar.setText("${miencuesta.nombre.toString()}")
        txvApellidoPaternoEditar.setText("${miencuesta.apellidoPaterno.toString()} ${miencuesta.apellidoMaterno.toString()}")
        txvCorreoEditar.setText("${miencuesta.correo.toString()}")
        var genero=""
        if (miencuesta.genero == 1) {
            genero = "Masculino"
        } else {
            genero = "Femenino"
        }
        txvGeneroEditar.setText("$genero")
        spnViajadoEditar.setSelection(miencuesta.viajado)
        spnFrecuenciaEditar.setSelection(miencuesta.frecuencia)
        spnExperienciaEditar.setSelection(miencuesta.experiencia)
        edtMejorasEditar.setText(miencuesta.mejora)
        if(miencuesta.promo){
            ckbPromoEditar.isChecked = true
        }
        if(miencuesta.ejecutiva){
            ckbEjecutivaEditar.isChecked = true
        }
        if(miencuesta.economica){
            ckbEconomicaEditar.isChecked = true
        }
        if(miencuesta.servicio == "Excelente"){
            rdbExcelenteEditar.isChecked =true

        }
        if(miencuesta.servicio == "Bueno"){
            rdbBuenoEditar.isChecked =true

        }
        if(miencuesta.servicio == "Malo"){
            rdbMaloEditar.isChecked =true

        }
        if(miencuesta.ofertas == true){
            swtOfertasDescuentosEditar.isChecked = true
        }

        btnEnviarEditar.setOnClickListener {
            // var miencuesta = EntityEncuesta()
            var viajado = ""
            var frecuencia = ""
            var exp = ""
            var ofertas = ""

            val aeropatitoposition = spnViajadoEditar.selectedItemPosition
            if (aeropatitoposition > 0) {
                miencuesta.viajado = aeropatitoposition

                val frecuenciaposition = spnFrecuenciaEditar.selectedItemPosition
                if (frecuenciaposition > 0) {
                    miencuesta.frecuencia = frecuenciaposition

                    val experienciaposition = spnExperienciaEditar.selectedItemPosition
                    if (experienciaposition > 0) {
                        miencuesta.experiencia = experienciaposition

                        val selectedServicio = rdgServicioEditar.checkedRadioButtonId
                        if (selectedServicio != -1) {
                            when (selectedServicio) {
                                rdbExcelenteEditar.id -> {
                                    miencuesta.servicio = "Excelente"
                                }
                                rdbBuenoEditar.id -> {
                                    miencuesta.servicio = "Bueno"
                                }
                                rdbMaloEditar.id -> {
                                    miencuesta.servicio = "Malo"
                                }
                            }
                            if ((ckbEconomicaEditar.isChecked) or (ckbEjecutivaEditar.isChecked) or (ckbPromoEditar.isChecked)) {
                                if (ckbEconomicaEditar.isChecked) {
                                    miencuesta.economica = true
                                }
                                if (ckbPromoEditar.isChecked) {
                                    miencuesta.promo = true
                                }
                                if (ckbEjecutivaEditar.isChecked) {
                                    miencuesta.ejecutiva = true
                                }
                                Log.d("udelp", miencuesta.correo)

                                if (swtOfertasDescuentosEditar.isChecked) {
                                    miencuesta.ofertas = true
                                }else{
                                    miencuesta.ofertas = false
                                }

                                miencuesta.mejora = edtMejorasEditar.text.toString()


                                if (position != null) {
                                    miencuesta.user = usuario_registrado.id
                                    encuestasDb.encuestasEdit(miencuesta)
                                    //Log.d("tamañoLISTA",encuesta.editarEncuesta(position.toInt(),miencuesta).toString())
                                }
                                Toast.makeText(this@EditActivity, "Editado", Toast.LENGTH_SHORT).show()
                            } else {
                                Snackbar.make(it,"Selecciona que tipo de esquema usaste ", Snackbar.LENGTH_SHORT).show()
                            }
                        }else{
                            Snackbar.make(it,"Selecciona como es nuestro servicio",Snackbar.LENGTH_SHORT).show()
                        }
                    }else{
                        Snackbar.make(it,"Selecciona tu experiencia",Snackbar.LENGTH_SHORT).show()
                    }
                }else{
                    Snackbar.make(it, "Por favor selecciona la frecuencia", Snackbar.LENGTH_SHORT).show()
                }
            } else{
                Snackbar.make(it, "Por favor selecciona si has viajado con nosotros", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}