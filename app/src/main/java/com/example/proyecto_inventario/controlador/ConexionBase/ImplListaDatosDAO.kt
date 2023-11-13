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

    override fun listadistritoSpiner(): ArrayList<ClasesDatos.distrito> {
        val distritoArrayList = ArrayList<ClasesDatos.distrito>()

        try{
            if(conexion == null){
                Log.e(tagErrorDB,messageErrorDB)
            }else{
                //Preparando Sentencia Call para ejecutar el Procedimiento Almacenado
                val cs: CallableStatement = conexion!!.prepareCall("{CALL sp_Selectdistrito}")

                //Ejecutando sentencia y guardando el resultado en la variable rs
                val rs: ResultSet = cs.executeQuery()
                //Recorrer todas las filas del resultado
                while (rs.next()) {

                    val codigo = rs.getInt("codigo_distrito")
                    val descripcion = rs.getString("distrito")

                    val distrito = ClasesDatos.distrito(codigo,descripcion )
                    //Agregamos la variable tipoDocumentoIdentidad al array list.
                    distritoArrayList.add(distrito)
                }
            }
        } catch (e: SQLException) {
            Log.e(tagExcepcionDB, e.message!!)
        } catch (e: Exception) {
            Log.e(tagExcepcion, e.message!!)

        }
        return distritoArrayList
    }

    override fun RGsexo(): ArrayList<ClasesDatos.sexo> {
        val sexoArrayList = ArrayList<ClasesDatos.sexo>()

        try{
            if(conexion == null){
                Log.e(tagErrorDB,messageErrorDB)
            }else{
                //Preparando Sentencia Call para ejecutar el Procedimiento Almacenado
                val cs: CallableStatement = conexion!!.prepareCall("{CALL sp_Selectsexo}")

                //Ejecutando sentencia y guardando el resultado en la variable rs
                val rs: ResultSet = cs.executeQuery()
                //Recorrer todas las filas del resultado
                while (rs.next()) {

                    val codigo = rs.getInt("codigo_sexo")
                    val descripcion = rs.getString("sexo")

                    val sexo = ClasesDatos.sexo(codigo,descripcion )
                    //Agregamos la variable tipoDocumentoIdentidad al array list.
                    sexoArrayList.add(sexo)
                }
            }
        } catch (e: SQLException) {
            Log.e(tagExcepcionDB, e.message!!)
        } catch (e: Exception) {
            Log.e(tagExcepcion, e.message!!)

        }
        return sexoArrayList
    }

    override fun Registrodeusuario(usuario: ClasesDatos.Registrousuario): Boolean {

        var resultado:Boolean=false
        try{
            if (conexion == null){
                resultado=false
                Log.e(tagErrorDB,messageErrorDB)
            }else{
                //Preparando Sentencia Call para ejecutar el Procedimiento Almacenado
                val cs: CallableStatement = conexion!!.prepareCall("{CALL SP_RegistroUsuario(?,?,?,?,?,?,?,?,?,?,?)}")

                cs.setString(1,usuario.nombre)
                cs.setString(2,usuario.apellidoPaterno)
                cs.setString(3,usuario.apellidoMaterno)
                cs.setInt(4,usuario.codigosexo)
                cs.setString(5,usuario.dni)
                cs.setString(6,usuario.contrasena)
                cs.setString(7,usuario.direccion)
                cs.setString(8,usuario.telefono)
                cs.setString(9,usuario.correo)
                cs.setInt(10,usuario.codigoprovincia)
                cs.setInt(11,usuario.codigodistrito)


                //Ejecutando sentencia y guardando el resultado en la variable rs
                val rs: ResultSet = cs.executeQuery()
                //Recorrer todas las filas del resultado
                if (rs.next()) {

                  resultado = true
                }
            }
        } catch (e: SQLException) {
            resultado = false
            Log.e(tagExcepcionDB, e.message!!)
        } catch (e: Exception) {
            resultado = false
            Log.e(tagExcepcion, e.message!!)

        }
        return resultado
    }
}