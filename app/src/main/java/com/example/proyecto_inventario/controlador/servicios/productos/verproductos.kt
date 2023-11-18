package com.example.proyecto_inventario.controlador.servicios.productos

import android.content.pm.ActivityInfo
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.proyecto_inventario.R
import com.example.proyecto_inventario.controlador.ConexionBase.ImplListaDatosDAO
import com.example.proyecto_inventario.controlador.ConexionBase.preferencias.preferenciasLogin
import com.example.proyecto_inventario.databinding.FragmentRegistroproductosBinding
import com.example.proyecto_inventario.databinding.FragmentVerproductosBinding

class verproductos : Fragment(R.layout.fragment_verproductos) {

    lateinit var bindingverproproductos: FragmentVerproductosBinding
    private lateinit var preferenciasLogin: preferenciasLogin
    private val clienteDAO = ImplListaDatosDAO()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingverproproductos = FragmentVerproductosBinding.bind(view)

        preferenciasLogin = preferenciasLogin(requireContext())

       val tablaproductoArrayList = clienteDAO.tablaproducto()

        val tableLayout = bindingverproproductos.tableLayout

        val tablaproductoArrayListOrdenada = tablaproductoArrayList.sortedBy { tablaproducto -> tablaproducto.nombre }

        // Iterar a través de los datos
        tablaproductoArrayListOrdenada.forEach { tablaproducto ->

            // Crear una fila para la tabla
            val row = TableRow(requireContext())


            // Agregar columnas a la fila
            row.addView(TextView(requireContext()).apply {
                text = tablaproducto.nombre
                background = resources.getDrawable(R.drawable.background_bordestabla)
                gravity = Gravity.CENTER
                setTextColor(ContextCompat.getColor(requireContext(), R.color.black))

            })
            row.addView(TextView(requireContext()).apply {
                text = " ".plus(tablaproducto.descripcion)
                background = resources.getDrawable(R.drawable.background_bordestabla)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            })
            row.addView(TextView(requireContext()).apply {
                text = tablaproducto.cantidad.toString()
                background = resources.getDrawable(R.drawable.background_bordestabla)
                setGravity(Gravity.CENTER)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            })
            row.addView(TextView(requireContext()).apply {
                text = tablaproducto.marca
                background = resources.getDrawable(R.drawable.background_bordestabla)
                setGravity(Gravity.CENTER)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            })
            row.addView(TextView(requireContext()).apply {
                text = tablaproducto.modelo
                background = resources.getDrawable(R.drawable.background_bordestabla)
                setGravity(Gravity.CENTER)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            })
            row.addView(TextView(requireContext()).apply {
                text =" S/.".plus( tablaproducto.preciou.toString())
                background = resources.getDrawable(R.drawable.background_bordestabla)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            })
            row.addView(TextView(requireContext()).apply {
                text =" S/.".plus( tablaproducto.preciototal.toString())
                background = resources.getDrawable(R.drawable.background_bordestabla)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            })

            // Agregar la fila a la tabla
            tableLayout.addView(row)
        }




        val idUsuario = preferenciasLogin.getIdUsuario()

        val tablaproductoArrayLists = clienteDAO.tablaproductoporusuario(idUsuario)



        val tableLayouts = bindingverproproductos.tableLayoutusu

        val tablaproductoArrayListOrdenadas = tablaproductoArrayLists.sortedBy { tablaproducto -> tablaproducto.nombre }

        // Iterar a través de los datos
        tablaproductoArrayListOrdenadas.forEach { tablaproductousu ->

            // Crear una fila para la tabla
            val row = TableRow(requireContext())


            // Agregar columnas a la fila
            row.addView(TextView(requireContext()).apply {
                text = tablaproductousu.nombre
                background = resources.getDrawable(R.drawable.background_bordestabla)
                setGravity(Gravity.CENTER)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.black)) // Reemplaza "your_text_color" con el nombre de tu color de texto

            })
            row.addView(TextView(requireContext()).apply {
                text = " ".plus(tablaproductousu.descripcion)
                background = resources.getDrawable(R.drawable.background_bordestabla)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            })
            row.addView(TextView(requireContext()).apply {
                text = tablaproductousu.cantidad.toString()
                background = resources.getDrawable(R.drawable.background_bordestabla)
                setGravity(Gravity.CENTER)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            })
            row.addView(TextView(requireContext()).apply {
                text = tablaproductousu.marca
                background = resources.getDrawable(R.drawable.background_bordestabla)
                setGravity(Gravity.CENTER)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            })
            row.addView(TextView(requireContext()).apply {
                text = tablaproductousu.modelo
                background = resources.getDrawable(R.drawable.background_bordestabla)
                setGravity(Gravity.CENTER)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            })
            row.addView(TextView(requireContext()).apply {
                text =" S/.".plus( tablaproductousu.preciou.toString())
                background = resources.getDrawable(R.drawable.background_bordestabla)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            })
            row.addView(TextView(requireContext()).apply {
                text =" S/.".plus( tablaproductousu.preciototal.toString())
                background = resources.getDrawable(R.drawable.background_bordestabla)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            })

            // Agregar la fila a la tabla
            tableLayouts.addView(row)
        }







    }
    override fun onResume() {
        super.onResume()

        // Cambiar la orientación a horizontal cuando se reanuda el fragmento
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    override fun onPause() {
        super.onPause()

        // Restaurar la orientación original cuando se pausa el fragmento
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    }
}