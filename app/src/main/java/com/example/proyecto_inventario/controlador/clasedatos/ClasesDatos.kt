package com.example.proyecto_inventario.controlador.clasedatos

class ClasesDatos {
    data class provincia (
        var codigo: Int,
        var descripcion : String
    ){
        override fun toString(): String {
            return descripcion
        }
    }

}