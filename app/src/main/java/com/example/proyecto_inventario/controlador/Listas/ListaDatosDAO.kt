package com.example.proyecto_inventario.controlador.Listas

import com.example.proyecto_inventario.controlador.clasedatos.ClasesDatos

interface ListaDatosDAO {

    fun  listaprovinciasSpiner():ArrayList<ClasesDatos.provincia>

}