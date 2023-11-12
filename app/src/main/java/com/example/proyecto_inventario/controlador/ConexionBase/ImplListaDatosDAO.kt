package com.example.proyecto_inventario.controlador.ConexionBase

import com.example.proyecto_inventario.controlador.Listas.ListaDatosDAO
import java.sql.CallableStatement
import java.sql.ResultSet
import java.sql.SQLException
import android.util.Log
import com.example.proyecto_inventario.controlador.clasedatos.ClasesDatos

class ImplListaDatosDAO :ListaDatosDAO{
    private val tagErrorDB = "ERROR_CONEXION_BD"
    private val tagExcepcionDB = "ERROR_EXCEPCION_BD"
    private val tagExcepcion = "ERROR_EXCEPCION"
    private val messageErrorDB = "No está conectado con la base de datos. Lista Datos"
    private val conexion = conexiobasededatos().dbConexion()
    override fun listaprovinciasSpiner(): ArrayList<ClasesDatos.provincia> {

        val provinciaArrayList = ArrayList<ClasesDatos.provincia>()

        try{
            if(conexion == null){
                Log.e(tagErrorDB,messageErrorDB)
            }else{
                //Preparando Sentencia Call para ejecutar el Procedimiento Almacenado
                val cs: CallableStatement = conexion!!.prepareCall("{CALL sp_SelectProvincias}")

                //Ejecutando sentencia y guardando el resultado en la variable rs
                val rs: ResultSet = cs.executeQuery()
                //Recorrer todas las filas del resultado
                while (rs.next()) {

                    val codigo = rs.getInt("codigo_provincia")
                    val descripcion = rs.getString("provincia")

                    val provincia = ClasesDatos.provincia(codigo,descripcion )
                    //Agregamos la variable tipoDocumentoIdentidad al array list.
                    provinciaArrayList.add(provincia)
                }
            }
        } catch (e: SQLException) {
            Log.e(tagExcepcionDB, e.message!!)
        } catch (e: Exception) {
            Log.e(tagExcepcion, e.message!!)

        }
        return provinciaArrayList
    }
}