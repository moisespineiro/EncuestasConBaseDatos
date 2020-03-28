package com.example.encuestas.Data

import android.util.Log
import com.example.encuestas.Entity.EntityUsuario

class ListaUsuarios {
    companion object {
        val listaUsuario= arrayListOf<EntityUsuario>()
        private val listString = arrayListOf<String>()
        var usuario_registrado = ""
    }

    public fun agregarUsuario(user:EntityUsuario): Int{
        listaUsuario.add(user)
        Log.d("encuestasUDELP","$user")
        return listaUsuario.size
    }

    public fun buscarUsuario(login: EntityUsuario): Int {
        var encontrado =- 1
        for (user in listaUsuario){
            if(login.nombre == user.nombre && login.password == user.password){
                encontrado = 1
            }
        }
        return encontrado
    }


}