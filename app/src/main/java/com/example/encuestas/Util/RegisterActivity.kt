package com.example.encuestas.Util

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.encuestas.Data.ListaUsuarios
import com.example.encuestas.Data.UsersDb
import com.example.encuestas.Entity.EntityUsuario
import com.example.encuestas.R
import com.example.encuestas.Util.ValidateEmail.Companion.isEmailValid
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    val usuario = ListaUsuarios()
    val usersDB = UsersDb(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnEnviar.setOnClickListener {
            val miusuario = EntityUsuario()

            if(edtNombreRegistro.text.toString().trim().isNotEmpty()){
                miusuario.nombre = edtNombreRegistro.text.toString()
                var nombre = edtNombreRegistro.text.toString()

                if(edtApellidoPaterno.text.toString().trim().isNotEmpty()){
                    miusuario.apellidoPaterno = edtApellidoPaterno.text.toString()
                    var apellidoP = edtApellidoPaterno.text.toString()

                    if(edtApellidoMaterno.text.toString().trim().isNotEmpty()){
                        miusuario.apellidoMaterno = edtApellidoMaterno.text.toString()
                        var apellidoM = edtApellidoMaterno.text.toString()

                        if(edtTelefono.text.toString().trim().isNotEmpty()){
                            miusuario.telefono = edtTelefono.text.toString()
                            var telefono = edtTelefono.text.toString()

                            val selectedServicio = rdgGenero.checkedRadioButtonId
                            var genero = 0
                            if (selectedServicio != -1) {
                                when (selectedServicio) {
                                    rdbMasculino.id -> {
                                        miusuario.genero = 1
                                        genero = 1
                                    }
                                    rdbFemenino.id -> {
                                        miusuario.genero = 0
                                        genero = 0
                                    }
                                }

                                val delegacionposition = spnDelegacion.selectedItemPosition
                                if (delegacionposition > 0) {
                                    miusuario.delegacion = delegacionposition
                                    var delegacion = delegacionposition

                                   /* val genero = spnDelegacion.getSelectedItemId().toString()
                                    Log.d("udelp", "Seleccionaste Genero $genero")*/

                                    if(edtDireccion.text.toString().trim().isNotEmpty()) {
                                        miusuario.direccion = edtDireccion.text.toString()
                                        var direccion = edtDireccion.text.toString()

                                        val edoCivil = spnEdoCivil.selectedItemPosition
                                        var estadoCivil = 0
                                        if (edoCivil > 0) {
                                            miusuario.estadoCivil = edoCivil
                                            estadoCivil = edoCivil

                                            if((edtCorreo.text.toString().trim().isNotEmpty()) and (isEmailValid(edtCorreo.text.toString().trim().toString()))){
                                                miusuario.correo = edtCorreo.text.toString()
                                                var correo = edtCorreo.text.toString()

                                                if(edtContrasenaRegistro.text.toString().trim().isNotEmpty()) {
                                                    miusuario.password = edtContrasenaRegistro.text.toString()
                                                    var password = edtContrasenaRegistro.text.toString()

                                                    var values= EntityUsuario(-1,nombre,apellidoP,apellidoM,telefono, genero, delegacion, direccion, estadoCivil, correo, password)

                                                    var id = usersDB.usersAdd(values)

                                                    usersDB.usersGetAll()

                                                    Toast.makeText(this@RegisterActivity,"Usuario Guardado",Toast.LENGTH_SHORT).show()
                                                    Log.d("tamañoLISTA",usuario.agregarUsuario(miusuario).toString())

                                                    edtNombreRegistro.text.clear()
                                                    edtApellidoPaterno.text.clear()
                                                    edtApellidoMaterno.text.clear()
                                                    edtTelefono.text.clear()
                                                    rdgGenero.clearCheck()
                                                    spnDelegacion.setSelection(0)
                                                    edtDireccion.text.clear()
                                                    spnEdoCivil.setSelection(0)
                                                    edtCorreo.text.clear()
                                                    edtContrasenaRegistro.text.clear()

                                                }else{
                                                    Snackbar.make(it, "Por favor ingrese contraseña", Snackbar.LENGTH_SHORT).show()
                                                }
                                            }else{
                                                Snackbar.make(it, "Por favor ingrese correo electrónico", Snackbar.LENGTH_SHORT).show()
                                            }
                                        }else{
                                            Snackbar.make(it, "Por favor seleccione estado civil", Snackbar.LENGTH_SHORT).show()
                                        }
                                    }else{
                                        Snackbar.make(it, "Por favor ingresa una dirección", Snackbar.LENGTH_SHORT).show()
                                    }
                                }else{
                                    Snackbar.make(it, "Por favor seleccione el genero", Snackbar.LENGTH_SHORT).show()
                                }
                            }else{
                                Snackbar.make(it, "Por favor seleccione la delegación", Snackbar.LENGTH_SHORT).show()
                            }
                        }else{
                            Snackbar.make(it, "Por favor ingresa el teléfono", Snackbar.LENGTH_SHORT).show()
                        }
                    }else{
                        Snackbar.make(it, "Por favor ingresa el apellido materno", Snackbar.LENGTH_SHORT).show()
                    }
                }else{
                    Snackbar.make(it, "Por favor ingresa el apellido paterno", Snackbar.LENGTH_SHORT).show()
                }
            }else{
                Snackbar.make(it, "Por favor ingresa el nombre", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}