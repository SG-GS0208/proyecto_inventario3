package com.example.proyecto_inventario.controlador.servicios

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.proyecto_inventario.R
import com.example.proyecto_inventario.controlador.Consultasapp
import com.example.proyecto_inventario.controlador.HomeProyect
import com.example.proyecto_inventario.controlador.Inicio_sesion
import com.example.proyecto_inventario.controlador.Inventarioapp
import com.example.proyecto_inventario.controlador.Productosapp
import com.example.proyecto_inventario.databinding.FragmentHomeprincipalBinding

class homeprincipal : Fragment(R.layout.fragment_homeprincipal) {
    lateinit var  bindingHomeprincipal: FragmentHomeprincipalBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingHomeprincipal =  FragmentHomeprincipalBinding.bind(view)

        //de la 27 a 31 me sirve para que el boton de retroceso del celular no funcione
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)


        bindingHomeprincipal.cerrarsesion.setOnClickListener {
            mensajecerrarsesion()
        }


        //opciones de mi app

        bindingHomeprincipal.IBInventario.setOnClickListener {
            startActivity(Intent(requireContext(), Inventarioapp::class.java))
        }
        bindingHomeprincipal.IBConsulta.setOnClickListener {
            startActivity(Intent(requireContext(), Consultasapp::class.java))
        }
        bindingHomeprincipal.IBProducto.setOnClickListener {
            startActivity(Intent(requireContext(), Productosapp::class.java))
        }

    }
    private fun mensajecerrarsesion() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("Cerrar Sesión")
        builder.setMessage("¿Estás seguro de que deseas cerrar sesión?")

        builder.setPositiveButton("Sí") { dialog, which ->
            // Aquí colocas el código que deseas ejecutar al presionar "Sí"
            // Por ejemplo, puedes cerrar el fragmento o realizar otra acción.
            startActivity(Intent(requireContext(), Inicio_sesion::class.java))
        }

        builder.setNegativeButton("No") { dialog, which ->
            // Aquí colocas el código que deseas ejecutar al presionar "No"
        }



        builder.show()
    }


}

