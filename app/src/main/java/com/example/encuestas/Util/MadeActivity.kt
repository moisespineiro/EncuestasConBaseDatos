package com.example.encuestas.Util

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.encuestas.R
import com.example.encuestas.Data.ListaEncuestas

import com.example.encuestas.Data.SurveysDb
import com.example.encuestas.Data.SurveysDb.Companion.listStringIDS
import kotlinx.android.synthetic.main.activity_made.*

class MadeActivity : AppCompatActivity() {
    var ListaEncuestas = ListaEncuestas()
    val encuestasDb = SurveysDb(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_made)

        //if(ListaEncuestas.devuelveListEncuesta().size >0){
        if(encuestasDb.encuestasGetAllString().size >0){
            //val miAdaptador = ArrayAdapter<String> (this@MadeActivity, android.R.layout.simple_list_item_1,ListaEncuestas.devuelveListEncuestaString())
            val miAdaptador = ArrayAdapter<String> (this@MadeActivity, android.R.layout.simple_list_item_1,encuestasDb.encuestasGetAllString())
            encuestasDb.encuestasGetAll()
            ltvEncuestas.adapter=miAdaptador

            ltvEncuestas.setOnItemClickListener {
                adapterView: AdapterView<*>, view1: View, position: Int, id: Long ->
                //val itemSeleccionado = adapterView.getItemAtPosition(position)
                //var index = listStringIndex[position]
                var index = listStringIDS[position]
                Toast.makeText(this@MadeActivity,"Seleccionaste $index",Toast.LENGTH_SHORT).show()

                val intent = Intent(this@MadeActivity, DetailActivity::class.java)
                //intent.putExtra("ID", position.toString())
                intent.putExtra("ID", index)
                startActivity(intent)
            }
        }
    }
}