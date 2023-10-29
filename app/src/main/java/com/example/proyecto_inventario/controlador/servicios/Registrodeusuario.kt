package com.example.proyecto_inventario.controlador.servicios

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.proyecto_inventario.R
import com.example.proyecto_inventario.databinding.FragmentRegistrodeusuarioBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class Registrodeusuario : Fragment(R.layout.fragment_registrodeusuario) {

    lateinit var bindingRegistrodeusuario:FragmentRegistrodeusuarioBinding



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingRegistrodeusuario = FragmentRegistrodeusuarioBinding.bind(view)

        bindingRegistrodeusuario.IBBotonCancelar.setOnClickListener {
            mensajeCancelarRegistro()
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            mensajeCancelarRegistro()
        }


    }
    private fun mensajeCancelarRegistro() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("Cancelar Registro")
        builder.setMessage("¿Estás seguro de que deseas cancelar el registro?")

        builder.setPositiveButton("Sí") { dialog, which ->
            // Aquí colocas el código que deseas ejecutar al presionar "Sí"
            // Por ejemplo, puedes cerrar el fragmento o realizar otra acción.
            findNavController().navigate(R.id.action_registrodeusuario2_to_ingreso_alapp)
        }

        builder.setNegativeButton("No") { dialog, which ->
            // Aquí colocas el código que deseas ejecutar al presionar "No"
        }



        builder.show()
    }





}