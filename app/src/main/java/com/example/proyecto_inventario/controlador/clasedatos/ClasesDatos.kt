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
        var dni : String,
        var contrasena:String,
        var telefono:String,
        var correo:String

    )


    data class Registroproducto(
        var codigo: Int,
        var nombre: String,
        var descripcion: String,
        var cantidad :Int,
        var marca :String,
        var modelo:String,
        var preciou:Double,
        var preciototal:Double,
        var codigousuario:Int
    )


}