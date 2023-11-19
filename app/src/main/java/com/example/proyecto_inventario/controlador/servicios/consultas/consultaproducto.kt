package com.example.proyecto_inventario.controlador.servicios.consultas

import android.app.DownloadManager
import android.content.Context
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.proyecto_inventario.R
import com.example.proyecto_inventario.controlador.ConexionBase.ImplListaDatosDAO
import com.example.proyecto_inventario.controlador.ConexionBase.preferencias.preferenciasLogin
import com.example.proyecto_inventario.databinding.FragmentConsultaproductoBinding
import com.example.proyecto_inventario.databinding.FragmentRegistroproductosBinding
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream

import java.io.IOException


class consultaproducto : Fragment(R.layout.fragment_consultaproducto) {

    lateinit var bindingConsultaproductos: FragmentConsultaproductoBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingConsultaproductos = FragmentConsultaproductoBinding.bind(view)


        bindingConsultaproductos.sendButton.setOnClickListener {
            // Verificar si el comando es para "ver"
            val userInput = bindingConsultaproductos.userInput.text.toString()

            if (userInput.toLowerCase().startsWith("ver")) {
                // Obtener el nombre del producto
                val nombreProducto = userInput.substring("ver".length)

                // Crear el PDF en blanco
                val success = generarPdf("$nombreProducto.pdf", "")

                // Enviar un mensaje al chatbot
                if (success) {
                    mostrarRespuestaEnChat("PDF generado: $nombreProducto.pdf", false)
                } else {
                    mostrarRespuestaEnChat("Error al generar el PDF", false)
                }
            } else {
                // No es un comando "ver", simular la respuesta del chatbot
                val respuestaChatbot = obtenerRespuestaChatbot()
                mostrarRespuestaEnChat(respuestaChatbot, false) // false indica que es un mensaje del chatbot
            }
        }



    }

    private fun addToChatHistory(message: String, isUser: Boolean) {
        val linearLayout = bindingConsultaproductos.chatHistoryContainer

        // Crear un TextView para el mensaje
        val messageTextView = TextView(requireContext())
        messageTextView.text = message

        // Asignar estilos según si es un mensaje del usuario o del chatbot
        val gravity = if (isUser) Gravity.START else Gravity.END
        val backgroundColor = if (isUser) R.drawable.background_bordestabla else R.drawable.background_bordeschatbot
        val textColor = if (isUser) R.color.black else R.color.letras

        messageTextView.setBackgroundResource(backgroundColor)
        messageTextView.setTextColor(ContextCompat.getColor(requireContext(), textColor))
        messageTextView.gravity = gravity

        // Agregar el nuevo mensaje al contenedor del LinearLayout dentro del ScrollView
        linearLayout.addView(messageTextView)

        // Desplazarse al final del ScrollView para mostrar el nuevo mensaje
        bindingConsultaproductos.chatHistory.post {
            bindingConsultaproductos.chatHistory.fullScroll(View.FOCUS_DOWN)
        }
    }

    private fun mostrarRespuestaEnChat(respuesta: String, isUser: Boolean) {
        addToChatHistory(respuesta, isUser)
    }

    private fun generarPdf(filename: String , data:String):Boolean{

        val document: Document = Document()
        val writer: PdfWriter = PdfWriter.getInstance(document, FileOutputStream(filename))

        // Agregar el contenido al documento
        document.add(Paragraph(data))

        // Cerrar el documento
        document.close()

        // Devolver true si el PDF se creó correctamente
        return true
    }








    private fun obtenerRespuestaChatbot(): String {
        // Simular una respuesta de un chatbot con respuestas predefinidas
        val respuestas = listOf("¡Hola!", "¿En qué puedo ayudarte?", "Gracias por tu mensaje.")
        return respuestas.random()
    }
}