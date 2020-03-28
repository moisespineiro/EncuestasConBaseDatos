package com.example.encuestas.Data

import android.util.Log
import com.example.encuestas.Data.ListaUsuarios.Companion.usuario_registrado
import com.example.encuestas.Entity.EntityEncuesta

class ListaEncuestas {

    /*companion object {
        val listaEncuesta = arrayListOf<EntityEncuesta>()
        private val listString = arrayListOf<String>()
        val listStringIndex = arrayListOf<String>()
    }

    public fun agregarEncuestaPRUEBA(encuestaR:EntityEncuesta): Int {
        listaEncuesta.add(encuestaR)
        Log.d("encuestasUDELP","$encuestaR")

        return listaEncuesta.size
    }

    public fun editarEncuesta(id:Int,encuestaE:EntityEncuesta){
        listaEncuesta.set(id,encuestaE)
    }

    public fun devuelveListEncuesta(): Array<EntityEncuesta> {
        val elems = listaEncuesta
        return elems.toTypedArray()
    }

    public fun devuelveListEncuestaString(): Array<String> {
        listString.clear()
        listStringIndex.clear()
        var index = 0
        for (entityEncuesta in listaEncuesta) {
            if(entityEncuesta.user == usuario_registrado) {
                listString.add(entityEncuesta.nombre)
                listStringIndex.add(index.toString())
            }
            index ++
        }
        //Log.d("Test", listString[0])
        //Log.d("Ultimo", listString[listString.size -1])
        val elems = listString
        return elems.toTypedArray()
    }

    public fun eliminarEncuesta(id: Int){
        listaEncuesta.removeAt(id)
    }*/
}