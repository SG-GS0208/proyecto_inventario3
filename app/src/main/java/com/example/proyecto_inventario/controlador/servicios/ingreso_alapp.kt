package com.example.proyecto_inventario.controlador.servicios

import android.content.Context
import android.content.Intent
import android.graphics.Color
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
import com.example.proyecto_inventario.controlador.ConexionBase.ImplListaDatosDAO
import com.example.proyecto_inventario.controlador.ConexionBase.conexiobasededatos
import com.example.proyecto_inventario.controlador.HomeProyect
import com.example.proyecto_inventario.controlador.modelo.EstadoAutenticacion
import com.example.proyecto_inventario.databinding.FragmentIngresoAlappBinding
import com.google.android.material.snackbar.Snackbar


class ingreso_alapp : Fragment(R.layout.fragment_ingreso_alapp) {

    lateinit var bindingIngresoalapp: FragmentIngresoAlappBinding
    private var mensajeError=""
    private val listaDatosDAO = ImplListaDatosDAO()
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
            when (listaDatosDAO.autenticarCredenciales(usuarioIngresado, contraseñaIngresada)) {

                EstadoAutenticacion.ACCESO_EXITOSO -> {
                    val preferencias =
                        requireContext().getSharedPreferences("sesion", Context.MODE_PRIVATE)
                    val editor = preferencias.edit()
                    editor.putBoolean("sesion_iniciada", mantenerSesionSwitch.isChecked)
                    editor.apply()

                    // Credenciales válidas, iniciar sesión
                    startActivity(Intent(requireContext(), HomeProyect::class.java))
                }

                EstadoAutenticacion.CLAVE_INCORRECTA -> {
                    mensajeError = "La cotraseña es incorrecta"
                    mostrarMensajeError(view,mensajeError)

                }

                EstadoAutenticacion.USUARIO_INCORRECTO ->{
                    mensajeError = "El usuario es incorrecto"
                    mostrarMensajeError(view , mensajeError)
                }

                EstadoAutenticacion.CONEXION_FALLIDA -> {
                    mensajeError = "Errore de conexion"
                    mostrarMensajeError(view , mensajeError)

                }

            }
        }

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // No hacer nada aquí para evitar acciones al presionar el botón "Atrás"
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )


    }

    fun showConnectionStatusToast() {
        val connection = conexiobasededatos().dbConexion()
        if (connection != null) {
            Toast.makeText(requireContext(), "Conectado a la base de datos", Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(requireContext(), "No conectado a la base de datos", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun mostrarMensajeError(view: View, mensaje: String) {
        Snackbar.make(view, mensaje, Snackbar.LENGTH_LONG)
            .setBackgroundTint(Color.rgb(170, 0, 0))
            .setTextColor(Color.WHITE)
            .setDuration(1000)
            .setAction("Action", null)
            .show()
    }



    }

