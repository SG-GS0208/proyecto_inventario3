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
import android.widget.ArrayAdapter
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.proyecto_inventario.R
import com.example.proyecto_inventario.controlador.ConexionBase.ImplListaDatosDAO
import com.example.proyecto_inventario.controlador.ConexionBase.conexiobasededatos
import com.example.proyecto_inventario.controlador.clasedatos.ClasesDatos
import com.example.proyecto_inventario.databinding.FragmentRegistrodeusuarioBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class Registrodeusuario : Fragment(R.layout.fragment_registrodeusuario) {

    lateinit var bindingRegistrodeusuario:FragmentRegistrodeusuarioBinding



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingRegistrodeusuario = FragmentRegistrodeusuarioBinding.bind(view)
        cargarDatos()
        bindingRegistrodeusuario.IBBotonCancelar.setOnClickListener {
            mensajeCancelarRegistro()
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            mensajeCancelarRegistro()
        }


    }
    private fun cargarDatos() {
        val listaDatosDAO = ImplListaDatosDAO()
        val provincias = listaDatosDAO.listaprovinciasSpiner()

        val autoCompleteTextView = bindingRegistrodeusuario.ACTVProvincia // Ajusta el ID según tu diseño

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, provincias)
        autoCompleteTextView.setAdapter(adapter)

        autoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
            val provinciaSeleccionada = adapter.getItem(position) as ClasesDatos.provincia
            // Puedes realizar acciones con la provincia seleccionada si es necesario
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