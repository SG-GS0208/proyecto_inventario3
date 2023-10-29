package com.example.proyecto_inventario.controlador.servicios.inventario

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.proyecto_inventario.R
import com.example.proyecto_inventario.databinding.FragmentRegistroproductosBinding

class registroproductos : Fragment(R.layout.fragment_registroproductos) {

    lateinit var bindingRegistroproductos : FragmentRegistroproductosBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingRegistroproductos = FragmentRegistroproductosBinding.bind(view)
    }

}