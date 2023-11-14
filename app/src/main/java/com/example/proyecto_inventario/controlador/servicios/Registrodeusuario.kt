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
            registrarusuarios()


        }



       /* val radioGroup = view.findViewById<RadioGroup>(R.id.RG_sexo)
        val sexoArrayList = listaDatosDAO.RGsexo()


        for (sexo in sexoArrayList) {
            val radioButton = RadioButton(requireContext())
            radioButton.text = sexo.descripcion
            radioButton.id = View.generateViewId() // Generar un nuevo ID único para cada RadioButton
            radioButton.tag = sexo.descripcion

            // Agregar el RadioButton al RadioGroup
            radioGroup.addView(radioButton)

            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            radioGroup.gravity = Gravity.CENTER_HORIZONTAL
            radioButton.layoutParams = layoutParams
        }
        val radioButtonId = radioGroup.checkedRadioButtonId

        if (radioButtonId != -1) {
            val radioButton = view.findViewById<RadioButton>(radioButtonId)
            val descripcionSexoSeleccionado = radioButton.tag as? String ?: ""
            // Ahora puedes usar 'descripcionSexoSeleccionado' como String en tu código de registro
        } else {
            // Manejar el caso en que no se ha seleccionado ningún RadioButton
        }
*/

    }

    private fun encryptPassword(password: String): String {
        val salt = BCrypt.gensalt(saltRounds)
        return BCrypt.hashpw(password, salt)
    }
    private fun registrarusuarios():Boolean{

        val datosusuario = ClasesDatos.Registrousuario(
            codigo =0,
            nombre =bindingRegistrodeusuario.TIETNombres.text.toString(),
            apellidoPaterno = bindingRegistrodeusuario.TIETApellidoPaterno.text.toString(),
            apellidoMaterno = bindingRegistrodeusuario.TIETApellidoMaterno.text.toString(),
            dni = bindingRegistrodeusuario.TIETNumeroDocumentoIdentidad.text.toString(),
            contrasena= bindingRegistrodeusuario.TIETClave.text.toString(),
            telefono =bindingRegistrodeusuario.TIETTelefono.text.toString(),
            correo = bindingRegistrodeusuario.TIETCorreo.text.toString()
        )
        return listaDatosDAO.Registrodeusuario(datosusuario)
    }
   /* private fun cargarDatosprovincia() {

        val listaprovincias = listaDatosDAO.listaprovinciasSpiner()

        val autoCompleteTextView = bindingRegistrodeusuario.ACTVProvincia // Ajusta el ID según tu diseño

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, listaprovincias)
        autoCompleteTextView.setAdapter(adapter)

        autoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
            provincia = adapter.getItem(position) as ClasesDatos.provincia
            // Puedes realizar acciones con la provincia seleccionada si es necesario
        }
    }
    private fun cargarDatosdistrito() {
        val listadistrito = listaDatosDAO.listadistritoSpiner()

        val autoCompleteTextView = bindingRegistrodeusuario.ACTVDistrito // Ajusta el ID según tu diseño

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, listadistrito)
        autoCompleteTextView.setAdapter(adapter)

        autoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
            distrito = adapter.getItem(position) as ClasesDatos.distrito
            // Puedes realizar acciones con la provincia seleccionada si es necesario
        }
    }*/
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

   /* companion object{
        private lateinit var sexo: ClasesDatos.sexo
        private lateinit var provincia: ClasesDatos.provincia
        private lateinit var distrito: ClasesDatos.distrito
    }*/





}