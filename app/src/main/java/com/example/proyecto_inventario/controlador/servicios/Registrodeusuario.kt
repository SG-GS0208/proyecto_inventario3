package com.example.proyecto_inventario.controlador.servicios

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.proyecto_inventario.R
import com.example.proyecto_inventario.controlador.ConexionBase.ImplListaDatosDAO
import com.example.proyecto_inventario.controlador.ConexionBase.conexiobasededatos
import com.example.proyecto_inventario.controlador.clasedatos.ClasesDatos
import com.example.proyecto_inventario.databinding.FragmentRegistrodeusuarioBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.mindrot.jbcrypt.BCrypt


class Registrodeusuario : Fragment(R.layout.fragment_registrodeusuario) {

    lateinit var bindingRegistrodeusuario:FragmentRegistrodeusuarioBinding
    private val listaDatosDAO = ImplListaDatosDAO()
    private val saltRounds = 10



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingRegistrodeusuario = FragmentRegistrodeusuarioBinding.bind(view)


        bindingRegistrodeusuario.IBBotonCancelar.setOnClickListener {
            mensajeCancelarRegistro()
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            mensajeCancelarRegistro()
        }


        bindingRegistrodeusuario.BRegistro.setOnClickListener {
            val nombres = bindingRegistrodeusuario.TIETNombres.text.toString()
            val apellidoPaterno = bindingRegistrodeusuario.TIETApellidoPaterno.text.toString()
            val dni = bindingRegistrodeusuario.TIETNumeroDocumentoIdentidad.text.toString()
            val clave = bindingRegistrodeusuario.TIETClave.text.toString()
            val telefono = bindingRegistrodeusuario.TIETTelefono.text.toString()
            val correo = bindingRegistrodeusuario.TIETCorreo.text.toString()

            if (nombres.isNotEmpty() || apellidoPaterno.isNotEmpty() || dni.isNotEmpty() || clave.isNotEmpty() || telefono.isNotEmpty() || correo.isNotEmpty()) {
                if (registrarusuarios()) {
                    findNavController().navigate(R.id.action_registrodeusuario2_to_ingreso_alapp)
                    // Si la función registrarusuarios() devuelve true (registro exitoso), muestra un Toast de éxito
                    Toast.makeText(requireContext(), "REGISTRADO CON ÉXITO", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                // Si la función registrarusuarios() devuelve false (registro no exitoso), muestra un Toast de error
                Toast.makeText(requireContext(), "ERROR AL REGISTRAR", Toast.LENGTH_SHORT).show()
            }

        }


    }

    private fun encryptPassword(password: String): String {
        val salt = BCrypt.gensalt(saltRounds)
        return BCrypt.hashpw(password, salt)
    }
    private fun registrarusuarios():Boolean{
        var clavecifrada= encryptPassword(bindingRegistrodeusuario.TIETClave.text.toString())
        val datosusuario = ClasesDatos.Registrousuario(
            codigo =0,
            nombre =bindingRegistrodeusuario.TIETNombres.text.toString(),
            apellidoPaterno = bindingRegistrodeusuario.TIETApellidoPaterno.text.toString(),
            apellidoMaterno = bindingRegistrodeusuario.TIETApellidoMaterno.text.toString(),
            dni = bindingRegistrodeusuario.TIETNumeroDocumentoIdentidad.text.toString(),
            contrasena= clavecifrada,
            telefono =bindingRegistrodeusuario.TIETTelefono.text.toString(),
            correo = bindingRegistrodeusuario.TIETCorreo.text.toString()
        )
        return listaDatosDAO.Registrodeusuario(datosusuario)

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