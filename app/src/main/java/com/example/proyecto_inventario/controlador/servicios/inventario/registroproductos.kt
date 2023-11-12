package com.example.proyecto_inventario.controlador.servicios.inventario

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.proyecto_inventario.R
import com.example.proyecto_inventario.controlador.HomeProyect
import com.example.proyecto_inventario.controlador.Productosapp
import com.example.proyecto_inventario.databinding.FragmentRegistroproductosBinding

class registroproductos : Fragment(R.layout.fragment_registroproductos) {

    lateinit var bindingRegistroproductos : FragmentRegistroproductosBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingRegistroproductos = FragmentRegistroproductosBinding.bind(view)

        bindingRegistroproductos.IBBotonCancelar.setOnClickListener {
            mensajeCancelarProducto()
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


}