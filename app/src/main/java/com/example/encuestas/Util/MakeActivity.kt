package com.example.encuestas.Util

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.encuestas.Data.ListaEncuestas
import com.example.encuestas.Data.ListaUsuarios
import com.example.encuestas.Data.SurveysDb
import com.example.encuestas.Data.UsersDb.Companion.usuario_registrado
import com.example.encuestas.Entity.EntityEncuesta
import com.example.encuestas.R
import com.example.encuestas.Util.ValidateEmail.Companion.isEmailValid
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_make.*
import java.text.SimpleDateFormat
import java.util.*

class MakeActivity : AppCompatActivity(){

    //val encuesta = ListaEncuestas()
    var user =""
    val encuestasDb = SurveysDb(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make)

        user = intent.extras?.getString("usuario").toString()
        btnEnviarRegistro.setOnClickListener {
            val miencuesta = EntityEncuesta()
            if (edtNombreRegistro.text.toString().trim().isNotEmpty()) {
                miencuesta.nombre = edtNombreRegistro.text.toString()
                if (edtApellidoPaternoRegistro.text.toString().trim().isNotEmpty()) {
                    miencuesta.apellidoPaterno = edtApellidoPaternoRegistro.text.toString()

                    if (edtApellidoMaternoRegistro.text.toString().trim().isNotEmpty()) {
                        miencuesta.apellidoMaterno = edtApellidoMaternoRegistro.text.toString()

                        if ((edtCorreoRegistro.text.toString().trim().isNotEmpty()) and (isEmailValid(edtCorreoRegistro.text.toString().trim().toString()))){
                            miencuesta.correo = edtCorreoRegistro.text.toString()
                            val generoposition = spnGeneroRegistro.selectedItemPosition

                            if (generoposition > 0){
                                miencuesta.genero = generoposition
                                val genero = spnGeneroRegistro.getSelectedItemId().toString()
                                Log.d("udelp","Seleccionaste Genero $genero")

                                val aeropatitoposition = spnViajadoRegistro.selectedItemPosition
                                if (aeropatitoposition > 0){
                                    miencuesta.viajado = aeropatitoposition

                                    val frecuenciaposition = spnFrecuenciaRegistro.selectedItemPosition
                                    if (frecuenciaposition > 0){
                                        miencuesta.frecuencia = frecuenciaposition

                                        val experienciaposition =spnExperienciaRegistro.selectedItemPosition
                                        if (experienciaposition > 0){
                                            miencuesta.experiencia = experienciaposition

                                            val selectedServicio = rdgServicio.checkedRadioButtonId
                                            if (selectedServicio != -1){
                                                when(selectedServicio){
                                                    rdbExcelente.id->{
                                                        miencuesta.servicio = "Excelente"
                                                    }
                                                    rdbBueno.id->{
                                                        miencuesta.servicio  = "Bueno"
                                                    }
                                                    rdbMalo.id->{
                                                        miencuesta.servicio  = "Malo"
                                                    }
                                                }

                                                if((ckbEconomica.isChecked) or (ckbEjecutiva.isChecked) or (ckbPromo.isChecked)){

                                                    if(ckbEconomica.isChecked){
                                                        miencuesta.economica = true
                                                    }
                                                    if(ckbPromo.isChecked){
                                                        miencuesta.promo = true
                                                    }
                                                    if(ckbEjecutiva.isChecked){
                                                        miencuesta.ejecutiva = true
                                                    }

                                                    Log.d("udelp",miencuesta.correo)
                                                    if(swtOfertasDescuentos.isChecked){
                                                        miencuesta.ofertas =true
                                                    }
                                                    miencuesta.mejora = edtMejoras.text.toString()
                                                    miencuesta.user = usuario_registrado.id
                                                    encuestasDb.encuestasAdd(miencuesta)
                                                    Toast.makeText(this@MakeActivity,"Guardado",Toast.LENGTH_SHORT).show()
                                                    //Log.d("tamañoLISTA",encuesta.agregarEncuestaPRUEBA(miencuesta).toString())
                                                    /*val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
                                                    val currentDate = sdf.format(Date())
                                                    miencuesta.fecha = sdf.format(Date())*/
                                                    //Toast.makeText(this@MakeActivity,"Guardado por $user",Toast.LENGTH_SHORT).show()

                                                    edtNombreRegistro.text.clear()
                                                    edtApellidoPaternoRegistro.text.clear()
                                                    edtApellidoMaternoRegistro.text.clear()
                                                    edtCorreoRegistro.text.clear()
                                                    spnGeneroRegistro.setSelection(0)
                                                    spnViajadoRegistro.setSelection(0)
                                                    spnFrecuenciaRegistro.setSelection(0)
                                                    spnExperienciaRegistro.setSelection(0)
                                                    ckbEconomica.isChecked = false
                                                    ckbPromo.isChecked = false
                                                    ckbEjecutiva.isChecked = false
                                                    rdgServicio.clearCheck()
                                                    edtMejoras.text.clear()
                                                    swtOfertasDescuentos.isChecked = false
                                                }else{
                                                    Snackbar.make(it,"Selecciona que tipo de esquema usaste ",Snackbar.LENGTH_SHORT).show()
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

                                }else{
                                    Snackbar.make(it, "Por favor selecciona si has viajado con nosotros", Snackbar.LENGTH_SHORT).show()
                                }


                            }else{
                                Snackbar.make(it, "Por favor selecciona Genero", Snackbar.LENGTH_SHORT).show()
                            }


                        }else{
                            Snackbar.make(it, "Por favor ingresa el Correo", Snackbar.LENGTH_SHORT).show()
                        }


                    } else {
                        Snackbar.make(it, "Por favor ingresa el Apellido Materno", Snackbar.LENGTH_SHORT).show()
                    }



                } else {
                    Snackbar.make(it, "Por favor ingresa el Apellido Paterno", Snackbar.LENGTH_SHORT).show()
                }


            } else {
                Snackbar.make(it, "Por favor ingresa el nombre", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}