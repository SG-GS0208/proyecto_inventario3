package com.example.proyecto_inventario.controlador.ConexionBase

import com.example.proyecto_inventario.controlador.Listas.ListaDatosDAO
import java.sql.CallableStatement
import java.sql.ResultSet
import java.sql.SQLException
import android.util.Log
import org.mindrot.jbcrypt.BCrypt
import com.example.proyecto_inventario.controlador.clasedatos.ClasesDatos
import com.example.proyecto_inventario.controlador.modelo.EstadoAutenticacion
import java.text.ParseException

class ImplListaDatosDAO :ListaDatosDAO{
    private val tagErrorDB = "ERROR_CONEXION_BD"
    private val tagExcepcionDB = "ERROR_EXCEPCION_BD"
    private val tagExcepcion = "ERROR_EXCEPCION"
    private val messageErrorDB = "No está conectado con la base de datos"
    private val conexion = conexiobasededatos().dbConexion()

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


    override fun autenticarCredenciales(dni: String, contrasena: String): EstadoAutenticacion {

            try {
                return if (conexion == null) {
                    Log.e(tagErrorDB, messageErrorDB)
                    return EstadoAutenticacion.CONEXION_FALLIDA
                }
                else {
                    // Preparando la sentencia CALL para ejecutar el procedimiento almacenado de inicio de sesión
                    val cs: CallableStatement = conexion!!.prepareCall("{CALL SP_AutenticarUsuario(?)}")

                    cs.setString(1, dni)


                    // Ejecutando la sentencia y guardando el resultado en la variable rs
                    val rs: ResultSet = cs.executeQuery()

                    // Verificando si la autenticación fue exitosa
                    if (rs.next()) {

                        if(validarcontrasena(contrasena,rs.getString("contrasena"))){
                            EstadoAutenticacion.ACCESO_EXITOSO
                        }
                        else{
                            EstadoAutenticacion.CLAVE_INCORRECTA
                        }

                    }else{
                        EstadoAutenticacion.USUARIO_INCORRECTO
                    }
                }
            } catch (e: SQLException) {
                Log.e(tagExcepcionDB, e.message!!)
                return EstadoAutenticacion.CONEXION_FALLIDA
            } catch (e: Exception) {
                Log.e(tagExcepcion, e.message!!)
                return EstadoAutenticacion.CONEXION_FALLIDA

            }


        }

    override fun Registrodeproducto(producto: ClasesDatos.Registroproducto): Boolean {
        var isSuccessful = false
        try{
            if (conexion == null){

                Log.e(tagErrorDB,messageErrorDB)
            }else{
                //Preparando Sentencia Call para ejecutar el Procedimiento Almacenado
                val cs: CallableStatement = conexion!!.prepareCall("{CALL SP_RegistroProducto(?,?,?,?,?,?,?,?)}")

                cs.setString(1,producto.nombre)
                cs.setString(2,producto.descripcion)
                cs.setInt(3,producto.cantidad)
                cs.setString(4,producto.marca)
                cs.setString(5,producto.modelo)
                cs.setDouble(6,producto.preciou)
                cs.setDouble(7,producto.preciototal)
                cs.setInt(8,producto.codigousuario)
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

    override fun obtenerusuarioporid(usuario: String):ClasesDatos.Registrousuario {
       try {
           if(conexion == null){
               Log.e(tagErrorDB,messageErrorDB)
           }
           else{
               val cs:CallableStatement = conexion!!.prepareCall("{CALL SP_AutenticarUsuario(?)}")
               cs.setString(1,usuario)

               //Ejecutando procedimiento almacenado y guardando el resultado
               val rs: ResultSet = cs.executeQuery()
               // Comprobando si recorre una fila con el resultado de la ejecución del SP
               if (rs.next()) {
                   return ClasesDatos.Registrousuario(
                       codigo = rs.getInt("codigo_usuario"),
                       nombre =rs.getString("nombre"),
                       apellidoPaterno = rs.getString("ape_paterno"),
                       apellidoMaterno = rs.getString("ape_materno"),
                       dni =rs.getString("dni"),
                       contrasena="",
                       telefono = rs.getString("telefono"),
                       correo = rs.getString("correo")
                   )

               }
           }
       } catch (e: SQLException) {
           Log.e(tagExcepcionDB, e.message!!)
       } catch (e: Exception) {
           Log.e(tagExcepcion, e.message!!)
       } finally {
           /**conexion!!.close()*/
       }
        return ClasesDatos.Registrousuario(
            codigo = 0,
            nombre ="",
            apellidoPaterno = "",
            apellidoMaterno = "",
            dni ="",
            contrasena="",
            telefono = "",
            correo = ""
        )

    }

    override fun tablaproducto(): ArrayList<ClasesDatos.Verproducto> {
        val tablaproductoArrayList = ArrayList<ClasesDatos.Verproducto>()

        try{
            if(conexion == null){
                Log.e(tagErrorDB,messageErrorDB)
            }else{
                //Preparando Sentencia Call para ejecutar el Procedimiento Almacenado
                val cs: CallableStatement = conexion!!.prepareCall("{CALL sp_MostrarProductos}")

                //Ejecutando sentencia y guardando el resultado en la variable rs
                val rs: ResultSet = cs.executeQuery()
                //Recorrer todas las filas del resultado
                while (rs.next()) {

                    val nombre = rs.getString("nombre_producto")
                    val descripcion = rs.getString("descripcion")
                    val cantidad = rs.getInt("cantidad")
                    val marca = rs.getString("marca")
                    val modelo = rs.getString("modelo")
                    val preciou = rs.getDouble("preciou")
                    val preciototal = rs.getDouble("preciototal")

                    val tablaproducto = ClasesDatos.Verproducto(nombre,descripcion,cantidad,marca,modelo,preciou,preciototal)
                    //Agregamos la variable tipoDocumentoIdentidad al array list.
                    tablaproductoArrayList.add(tablaproducto)
                }
            }
        } catch (e: SQLException) {
            Log.e(tagExcepcionDB, e.message!!)
        } catch (e: Exception) {
            Log.e(tagExcepcion, e.message!!)

        }
        return tablaproductoArrayList
    }

    override fun tablaproductoporusuario(idusuario:Int): ArrayList<ClasesDatos.VerproductoPorusuario> {
        val tablaproductoporusuarioArrayList = ArrayList<ClasesDatos.VerproductoPorusuario>()

        try{
            if(conexion == null){
                Log.e(tagErrorDB,messageErrorDB)
            }else{
                //Preparando Sentencia Call para ejecutar el Procedimiento Almacenado
                val cs: CallableStatement = conexion!!.prepareCall("{CALL sp_MostrarProductosPorClienteEJE(?)}")

                cs.setInt(1, idusuario)

                //Ejecutando sentencia y guardando el resultado en la variable rs
                val rs: ResultSet = cs.executeQuery()
                //Recorrer todas las filas del resultado
                while (rs.next()) {

                    val nombre = rs.getString("nombre_producto")
                    val descripcion = rs.getString("descripcion")
                    val cantidad = rs.getInt("cantidad")
                    val marca = rs.getString("marca")
                    val modelo = rs.getString("modelo")
                    val preciou = rs.getDouble("preciou")
                    val preciototal = rs.getDouble("preciototal")

                    val tablaproductoporusuario = ClasesDatos.VerproductoPorusuario(nombre,descripcion,cantidad,marca,modelo,preciou,preciototal)
                    //Agregamos la variable tipoDocumentoIdentidad al array list.
                    tablaproductoporusuarioArrayList.add(tablaproductoporusuario)
                }
            }
        } catch (e: SQLException) {
            Log.e(tagExcepcionDB, e.message!!)
        } catch (e: Exception) {
            Log.e(tagExcepcion, e.message!!)

        }
        return tablaproductoporusuarioArrayList
    }

    private fun validarcontrasena(contrasena: String,encriptado:String): Boolean{
        return BCrypt.checkpw(contrasena,encriptado)
    }


}