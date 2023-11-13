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


    data class distrito (
        var codigo: Int,
        var descripcion : String
    ){
        override fun toString(): String {
            return descripcion
        }
    }

    data class sexo(
        var codigo:Int,
        var descripcion: String)



    data class Registrousuario(
        var codigo:Int,
        var nombre:String,
        var apellidoPaterno:String,
        var apellidoMaterno:String,
        var codigosexo :Int,
        var dni : String,
        var contrasena:String,
        var direccion:String,
        var telefono:String,
        var correo:String,
        var codigoprovincia:Int,
        var codigodistrito:Int
    )


}