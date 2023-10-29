package com.example.proyecto_inventario.controlador.servicios

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.proyecto_inventario.R
import com.example.proyecto_inventario.controlador.HomeProyect
import com.example.proyecto_inventario.databinding.FragmentIngresoAlappBinding


class ingreso_alapp : Fragment(R.layout.fragment_ingreso_alapp) {

    lateinit var bindingIngresoalapp: FragmentIngresoAlappBinding



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingIngresoalapp = FragmentIngresoAlappBinding.bind(view)


        bindingIngresoalapp.TVRegistro.setOnClickListener {
            findNavController().navigate(R.id.action_ingreso_alapp_to_registrodeusuario2)
        }
        bindingIngresoalapp.BotomIngresar.setOnClickListener {
            startActivity(Intent(requireContext(),HomeProyect::class.java))
        }

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // No hacer nada aquí para evitar acciones al presionar el botón "Atrás"
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)



    }


}