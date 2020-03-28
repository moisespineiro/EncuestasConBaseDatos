package com.example.encuestas.Util

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.encuestas.Data.ListaUsuarios
import com.example.encuestas.Data.UsersDb
import com.example.encuestas.Data.UsersDb.Companion.usuario_registrado
import com.example.encuestas.Entity.EntityUsuario
import com.example.encuestas.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_authentication.*

class AuthenticationActivity : AppCompatActivity()  {
    val misusuario = ListaUsuarios()
    var encontrado = 0
    val usersDB = UsersDb(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        btnOk.setOnClickListener {
            val user=edtNombreUsuario.text.toString()
            val password=edtContrasena.text.toString()

            val user1 = EntityUsuario()
            user1.nombre = "moy"
            user1.password = "passwd"
            misusuario.agregarUsuario(user1)

            val user2 = EntityUsuario()
            user2.nombre = user
            user2.password = password
           // encontrado = misusuario.buscarUsuario(user2)
            encontrado = usersDB.loginUserdb(user2)

            if (encontrado >= 0){
                Snackbar.make(it,"Usuario existente $user $password", Snackbar.LENGTH_SHORT).show()
                val intent = Intent(this@AuthenticationActivity,MainActivity::class.java)
                intent.putExtra("usuario", "$user");
               // usuario_registrado = user
                usuario_registrado = usersDB.usersGetOne(encontrado)
                usersDB.usersGetAll()
                startActivity(intent)
            }else{
                Snackbar.make(it,"Usuario incorrecto", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.authentication_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.itmRegistro -> {
                val intent = Intent(this@AuthenticationActivity, RegisterActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }
}