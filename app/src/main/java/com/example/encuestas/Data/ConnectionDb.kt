package com.example.encuestas.Data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ConnectionDb (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,DATABASE_VERSION) {

    companion object{
        const val DATABASE_NAME = "ENCUESTAS"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME_USERS = "CTL_USERS"
        const val CREATE_TABLE = "CREATE TABLE $TABLE_NAME_USERS(Id INTEGER PRIMARY KEY AUTOINCREMENT,Nombre VARCHAR(20), ApellidoPaterno VARCHAR(20), ApellidoMaterno VARCHAR(20), Telefono VARCHAR(15), Genero VARCHAR(10), Delegacion INTEGER, Direccion VARCHAR(100), EstadoCivil INTEGER, Correo VARCHAR(50), Contrasena VARCHAR(20))"
        const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME_USERS"
        const val TABLE_NAME_SURVEYS = "CTL_SURVEYS"
        const val CREATE_TABLE2 = "CREATE TABLE $TABLE_NAME_SURVEYS(Id INTEGER PRIMARY KEY AUTOINCREMENT,Nombre VARCHAR(20), ApellidoPaterno VARCHAR(20), ApellidoMaterno VARCHAR(20), Correo VARCHAR(50), Genero INTEGER, Viajado INTEGER, Frecuencia INTEGER, Experiencia INTEGER, Ejecutiva INTEGER, Economica INTEGER, Promo INTEGER, Servicio VARCHAR(20), Mejora VARCHAR(150), Ofertas INTEGER, User INTEGER, Fecha DATE, FOREIGN KEY (User) REFERENCES $TABLE_NAME_USERS (Id))"
        const val DROP_TABLE2 = "DROP TABLE IF EXISTS $TABLE_NAME_SURVEYS"
        const val MODE_WRITE=1
        const val MODE_READ=2
    }

    fun openConnection(typeConnectionBD:Int): SQLiteDatabase {
        return when (typeConnectionBD){
            MODE_WRITE->
                return writableDatabase
            MODE_READ ->
                return readableDatabase
            else->
                return readableDatabase
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
        db?.execSQL(CREATE_TABLE2)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DROP_TABLE)
        db?.execSQL(DROP_TABLE2)
    }
}