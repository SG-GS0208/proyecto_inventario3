package com.example.proyecto_inventario.controlador.ConexionBase.preferencias
import android.content.Context
import android.content.SharedPreferences

class preferenciasLogin(context: Context) {
    private lateinit var preferenciasLogin: SharedPreferences

    private val sharedNamePreferences = "inicioSesion"

    //Variable storage(almacenamiento) donde se almacenaran el usuario y contraseña.
    private val storage = context.getSharedPreferences(sharedNamePreferences, 0)
    private val sharedUser = "user"
    private val sharedIdUser = "iduser"
    private val sharedRememberPassword = "remember"

    fun saveUsuario(user: String) = storage.edit().putString(sharedUser, user).apply()
    fun saveIdUsuario(iduser: Int) = storage.edit().putInt(sharedIdUser, iduser).apply()
    fun saveMantenerSesion(remember: Boolean) = storage.edit().putBoolean(sharedRememberPassword, remember).apply()

    fun getUsuario(): String {
        return storage.getString(sharedUser, "")!!
    }
    fun getIdUsuario(): Int {
        return storage.getInt(sharedIdUser, -1)
    }
    fun getMantenerSesion(): Boolean {
        return storage.getBoolean(sharedRememberPassword, false)
    }

    fun wipe(){
        storage.edit().clear().apply()
    }
}