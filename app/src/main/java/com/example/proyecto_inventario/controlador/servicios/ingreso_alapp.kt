package com.example.proyecto_inventario.controlador.servicios

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.proyecto_inventario.R
import com.example.proyecto_inventario.controlador.ConexionBase.conexiobasededatos
import com.example.proyecto_inventario.controlador.HomeProyect
import com.example.proyecto_inventario.databinding.FragmentIngresoAlappBinding


class ingreso_alapp : Fragment(R.layout.fragment_ingreso_alapp) {

    lateinit var bindingIngresoalapp: FragmentIngresoAlappBinding

    val usuarioPredeterminado = "admin"
    val contraseñaPredeterminada = "senati"

    lateinit var mantenerSesionSwitch: Switch

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingIngresoalapp = FragmentIngresoAlappBinding.bind(view)

        mantenerSesionSwitch = bindingIngresoalapp.SMantenerSesion


        val preferencias = requireContext().getSharedPreferences("sesion", Context.MODE_PRIVATE)
        val sesionIniciada = preferencias.getBoolean("sesion_iniciada", false)

        if (sesionIniciada) {
            startActivity(Intent(requireContext(), HomeProyect::class.java))
        }




        //ejemplo
        bindingIngresoalapp.TVTitulo.setOnClickListener {
            showConnectionStatusToast()
        }

        bindingIngresoalapp.TVRegistro.setOnClickListener {
            findNavController().navigate(R.id.action_ingreso_alapp_to_registrodeusuario2)
        }
        bindingIngresoalapp.BotomIngresar.setOnClickListener {
            val usuarioIngresado = bindingIngresoalapp.TIETUsuario.text.toString()
            val contraseñaIngresada = bindingIngresoalapp.TIETClave.text.toString()

            // Verificar las credenciales ingresadas con las predeterminadas
            if (usuarioIngresado == usuarioPredeterminado && contraseñaIngresada == contraseñaPredeterminada) {
                val preferencias = requireContext().getSharedPreferences("sesion", Context.MODE_PRIVATE)
                val editor = preferencias.edit()
                editor.putBoolean("sesion_iniciada", mantenerSesionSwitch.isChecked)
                editor.apply()

                // Credenciales válidas, iniciar sesión
                startActivity(Intent(requireContext(), HomeProyect::class.java))
            } else {
                bindingIngresoalapp.TILUsuario.error = "Usuario incorrecto"
                bindingIngresoalapp.TILClave.error = "Contraseña incorrecto"
            }
        }

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // No hacer nada aquí para evitar acciones al presionar el botón "Atrás"
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)



    }

    fun showConnectionStatusToast() {
        val connection = conexiobasededatos().dbConexion()
        if (connection != null) {
            Toast.makeText(requireContext(), "Conectado a la base de datos", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "No conectado a la base de datos", Toast.LENGTH_SHORT).show()
        }
    }



}