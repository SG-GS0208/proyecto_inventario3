package com.example.proyecto_inventario.controlador.ConexionBase
import android.os.StrictMode
import android.util.Log
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
class conexiobasededatos {
    /**LA BASE DE DATOS USADA ES SQL SERVER**/
    /**Variable ip, donde se encuentra el servidor de base de datos**/
    private val ip = "192.168.1.77:1433"
    /**Variable db, nombre de la base de datos**/
    private val db="BD_ALMACEN"
    /**Variable user, usuario para el acceso a la base de datos**/
    private val user="sa"
    /**Variable pass, clave para el acceso a la base de datos**/
    private val pass="sa123"



    /**Variable conexion donde se obtendrá la conexion**/
    private var conexion: Connection? = null

    fun dbConexion(): Connection? {
        val cnString: String
        try {
            val policy: StrictMode.ThreadPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance()
            cnString = "jdbc:jtds:sqlserver://$ip;databaseName=$db;user=$user;password=$pass"
            conexion = DriverManager.getConnection(cnString)
            if(conexion == null){
                Log.e("No Connected", "No esta conectado con la base de datos")
            } else {
                // Desactivar autocommit
                conexion!!.autoCommit = false
            }
        }catch (ex: SQLException){
            Log.e("Error SQLException: ", ex.message!!)
        }catch (ex: Exception){
            Log.e("Error Exception: ", ex.message!!)
        }
        return conexion
    }
}

