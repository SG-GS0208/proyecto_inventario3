package com.example.proyecto_inventario.controlador.servicios.productos

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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