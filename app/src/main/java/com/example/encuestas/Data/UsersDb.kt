package com.example.encuestas.Data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.encuestas.Entity.EntityUsuario

class UsersDb {

    private var connectionDb:ConnectionDb
    private lateinit var sqliteDataBase: SQLiteDatabase

    constructor(context: Context){
        connectionDb= ConnectionDb(context)
    }

    companion object {
        const val ID         ="Id"
        const val Nombre     ="Nombre"
        const val ApellidoP  ="ApellidoPaterno"
        const val ApellidoM  ="ApellidoMaterno"
        const val Telefono   ="Telefono"
        const val Genero     ="Genero"
        const val Delegacion ="Delegacion"
        const val Direccion  ="Direccion"
        const val EstadoCivil="EstadoCivil"
        const val Correo     ="Correo"
        const val Contrasena ="Contrasena"
        var usuario_registrado = EntityUsuario()
    }


    fun loginUserdb(login: EntityUsuario): Int {
        var encontrado =- 1
        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_READ)
        val fields = arrayOf(ID, Nombre, Contrasena)
        val cursor = sqliteDataBase.query(ConnectionDb.TABLE_NAME_USERS,fields,null,null,null,null,null)
        if(cursor.moveToFirst()){
            do{
                if(cursor.getString(1)== login.nombre && cursor.getString(2)==login.password){
                    encontrado = cursor.getInt(0)
                    Log.d("LOGIN_UDELP","Usuario encontrado")
                }
            }while(cursor.moveToNext())
        }
        return encontrado
    }


    fun  usersAdd(User:EntityUsuario): Long{
        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_WRITE)

        val values = ContentValues()
        values.put(Nombre,User.nombre)
        values.put(ApellidoP,User.apellidoPaterno)
        values.put(ApellidoM,User.apellidoMaterno)
        values.put(Telefono,User.telefono)
        values.put(Genero,User.genero)
        values.put(Delegacion,User.delegacion)
        values.put(Direccion,User.direccion)
        values.put(EstadoCivil,User.estadoCivil)
        values.put(Correo,User.correo)
        values.put(Contrasena,User.password)

        return sqliteDataBase.insert(ConnectionDb.TABLE_NAME_USERS,null,values)
    }


    fun usersGetOne(idUser: Int ):EntityUsuario{
        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_READ)
        val fields = arrayOf(ID, Nombre, ApellidoP, ApellidoM, Telefono, Genero, Delegacion ,Direccion , EstadoCivil , Correo , Contrasena )
        var selection = "Id=?"
        var args = arrayOf(idUser.toString())
        val cursor = sqliteDataBase.query(ConnectionDb.TABLE_NAME_USERS,fields,selection,args,null,null,null)
        var user = EntityUsuario()
        if(cursor.moveToFirst()){
            var id = cursor.getInt(0)
            var nombre = cursor.getString(1)
            var apellidoP = cursor.getString(2)
            var apellidoM = cursor.getString(3)
            var telefono = cursor.getString(4)
            var genero = cursor.getInt(5)
            var delegacion = cursor.getInt(6)
            var direccion = cursor.getString(7)
            var estadoCivil = cursor.getInt(8)
            var correo = cursor.getString(9)
            var passwd = cursor.getString(10)
            user = EntityUsuario(id.toInt(),"${nombre}","$apellidoP","$apellidoM","$telefono",genero.toInt(),delegacion.toInt(),"$direccion",estadoCivil,"$correo","$passwd")
            Log.d("USERudelp","${id.toString()},${nombre},$apellidoP,$apellidoM,$telefono,$genero,$delegacion,$direccion,$estadoCivil,$correo,$passwd")
        }
        return user
    }


    fun usersGetAll() {
        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_READ)
        val fields = arrayOf( ID, Nombre, ApellidoP, ApellidoM, Telefono, Genero, Delegacion,Direccion, EstadoCivil, Correo, Contrasena)
        val cursor = sqliteDataBase.query(ConnectionDb.TABLE_NAME_USERS,fields,null,null,null,null,null)
        if (cursor.moveToFirst()) {
            do {
                var id = cursor.getInt(0)
                var nombre = cursor.getString(1)
                var apellidoP = cursor.getString(2)
                var apellidoM = cursor.getString(3)
                var telefono = cursor.getString(4)
                var genero = cursor.getInt(5)
                var delegacion = cursor.getInt(6)
                var direccion = cursor.getString(7)
                var estadoCivil = cursor.getInt(8)
                var correo = cursor.getString(9)
                var passwd = cursor.getString(10)
                Log.d(
                    "USER_ALL",
                    "${id.toString()},${nombre},$apellidoP,$apellidoM,$telefono,$genero,$delegacion,$direccion,$estadoCivil,$correo,$passwd"
                )
            } while (cursor.moveToNext())
        }
    }
}