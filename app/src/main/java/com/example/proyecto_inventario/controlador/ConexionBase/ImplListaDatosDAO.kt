package com.example.proyecto_inventario.controlador.ConexionBase

import com.example.proyecto_inventario.controlador.Listas.ListaDatosDAO
import java.sql.CallableStatement
import java.sql.ResultSet
import java.sql.SQLException
import android.util.Log
import com.example.proyecto_inventario.controlador.clasedatos.ClasesDatos
import java.text.ParseException

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

        var isSuccessful = false
        try{
            if (conexion == null){

                Log.e(tagErrorDB,messageErrorDB)
            }else{
                //Preparando Sentencia Call para ejecutar el Procedimiento Almacenado
                val cs: CallableStatement = conexion!!.prepareCall("{CALL SP_RegistroUsuario1(?,?,?,?,?,?,?)}")

                cs.setString(1,usuario.nombre)
                cs.setString(2,usuario.apellidoPaterno)
                cs.setString(3,usuario.apellidoMaterno)
                cs.setString(4,usuario.dni)
                cs.setString(5,usuario.contrasena)
                cs.setString(6,usuario.telefono)
                cs.setString(7,usuario.correo)




                //Ejecutando sentencia y guardando el resultado en la variable rs
                cs.executeUpdate()

                //Recorrer todas las filas del resultado
                conexion!!.commit()
                isSuccessful = true
            }
        } catch (e: SQLException) {
            conexion!!.rollback()
            Log.e(tagExcepcionDB, e.message!!)
        } catch (e: Exception) {
            conexion!!.rollback()
            Log.e(tagExcepcion, e.message!!)
            conexion!!.rollback()
        } catch (e: ParseException) {
            Log.e("Error de Parseo: ", e.message!!)
            conexion!!.rollback()
        }
        return isSuccessful
}

    override fun autenticarCredenciales(dni: String, contrasena: String): Boolean {
            var isSuccessful = false

            try {
                if (conexion == null) {
                    Log.e(tagErrorDB, messageErrorDB)
                } else {
                    // Preparando la sentencia CALL para ejecutar el procedimiento almacenado de inicio de sesión
                    val cs: CallableStatement = conexion!!.prepareCall("{CALL SP_AutenticarUsuario(?,?)}")

                    cs.setString(1, dni)
                    cs.setString(2, contrasena)

                    // Ejecutando la sentencia y guardando el resultado en la variable rs
                    val rs: ResultSet = cs.executeQuery()

                    // Verificando si la autenticación fue exitosa
                    if (rs.next()) {
                        val resultadoAutenticacion = rs.getInt("ResultadoAutenticacion")
                        isSuccessful = resultadoAutenticacion == 1
                    }
                }
            } catch (e: SQLException) {
                Log.e(tagExcepcionDB, e.message!!)
            } catch (e: Exception) {
                Log.e(tagExcepcion, e.message!!)
            }

            return isSuccessful
        }


}