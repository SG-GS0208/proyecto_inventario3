package com.example.proyecto_inventario.controlador.servicios.inventario

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.proyecto_inventario.R
import com.example.proyecto_inventario.controlador.ConexionBase.ImplListaDatosDAO
import com.example.proyecto_inventario.controlador.ConexionBase.preferencias.preferenciasLogin
import com.example.proyecto_inventario.controlador.HomeProyect
import com.example.proyecto_inventario.controlador.Inicio_sesion
import com.example.proyecto_inventario.controlador.Productosapp
import com.example.proyecto_inventario.controlador.clasedatos.ClasesDatos
import com.example.proyecto_inventario.databinding.FragmentRegistroproductosBinding

class registroproductos : Fragment(R.layout.fragment_registroproductos) {

    lateinit var bindingRegistroproductos : FragmentRegistroproductosBinding
    private lateinit var preferenciasLogin: preferenciasLogin
    private val clienteDAO = ImplListaDatosDAO()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingRegistroproductos = FragmentRegistroproductosBinding.bind(view)

        preferenciasLogin = preferenciasLogin(requireContext())

        bindingRegistroproductos.IBBotonCancelar.setOnClickListener {
            mensajeCancelarProducto()
        }

        bindingRegistroproductos.BRegistro.setOnClickListener {
            val nombre = bindingRegistroproductos.TIETProducto.text.toString()
            val descripcion = bindingRegistroproductos.TIETDescripcion.text.toString()
            val marca = bindingRegistroproductos.TIETMarca.text.toString()
            val modelo = bindingRegistroproductos.TIETModelo.text.toString()

            val cantidadText = bindingRegistroproductos.TIETCantidad.text.toString()
            val precioText = bindingRegistroproductos.TIETPrecioU.text.toString()

            if (nombre.isNotEmpty() && descripcion.isNotEmpty() && marca.isNotEmpty() && modelo.isNotEmpty() && cantidadText.isNotEmpty() && precioText.isNotEmpty()) {
                try {
                    // Intenta convertir la cantidad y precio a sus respectivos tipos


                    if (registrarproducto()) {
                        startActivity(Intent(requireContext(), HomeProyect::class.java))
                        Toast.makeText(requireContext(), "REGISTRADO CON ÉXITO", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "ERROR AL REGISTRAR", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: NumberFormatException) {
                    // Si hay un error al convertir a entero o double, muestra un mensaje de error
                    Toast.makeText(requireContext(), "Error en formato de cantidad o precio", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Todos los campos deben estar llenos", Toast.LENGTH_SHORT).show()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            mensajeCancelarProducto()
        }
    }
    private fun mensajeCancelarProducto() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("Cancelar Registro de Producto")
        builder.setMessage("¿Estás seguro de que deseas cancelar el registro del producto?")

        builder.setPositiveButton("Sí") { dialog, which ->
            // Aquí colocas el código que deseas ejecutar al presionar "Sí"
            // Por ejemplo, puedes cerrar el fragmento o realizar otra acción.
            startActivity(Intent(requireContext(), HomeProyect::class.java))
        }

        builder.setNegativeButton("No") { dialog, which ->
            // Aquí colocas el código que deseas ejecutar al presionar "No"
        }



        builder.show()
    }

    private fun registrarproducto():Boolean{

        var cantidad=bindingRegistroproductos.TIETCantidad.text.toString().toInt()
        var precio=bindingRegistroproductos.TIETPrecioU.text.toString().toDouble()
        val datoproducto = ClasesDatos.Registroproducto(
            codigo=0,
            nombre = bindingRegistroproductos.TIETProducto.text.toString(),
            descripcion=bindingRegistroproductos.TIETDescripcion.text.toString(),
            cantidad=cantidad,
            marca=bindingRegistroproductos.TIETMarca.text.toString(),
            modelo=bindingRegistroproductos.TIETModelo.text.toString(),
            preciou = precio,
            preciototal = cantidad * precio ,
            codigousuario = preferenciasLogin.getIdUsuario()
        )
        return clienteDAO.Registrodeproducto(datoproducto)
    }




}