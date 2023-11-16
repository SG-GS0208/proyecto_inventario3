package com.example.proyecto_inventario.controlador.Listas

import com.example.proyecto_inventario.controlador.clasedatos.ClasesDatos
import com.example.proyecto_inventario.controlador.modelo.EstadoAutenticacion

interface ListaDatosDAO {

    fun  listaprovinciasSpiner():ArrayList<ClasesDatos.provincia>

    fun  listadistritoSpiner():ArrayList<ClasesDatos.distrito>

    fun RGsexo():ArrayList<ClasesDatos.sexo>


    fun Registrodeusuario(usuario:ClasesDatos.Registrousuario):Boolean

    fun autenticarCredenciales(usuario: String, contrasena: String): EstadoAutenticacion

}