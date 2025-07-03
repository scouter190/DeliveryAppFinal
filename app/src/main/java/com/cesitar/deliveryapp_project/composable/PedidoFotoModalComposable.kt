package com.cesitar.deliveryapp_project.composable

import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.asImageBitmap

@Composable
fun PedidoFotoModalComposable(
    fotoBase64: String,
    onClose: () -> Unit
) {
    val imageBitmap = base64ToImageBitmap(fotoBase64)
    AlertDialog(
        onDismissRequest = onClose,
        title = { Text("Imagen Adjunta") },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                imageBitmap?.let {
                    Image(
                        bitmap = it,
                        contentDescription = "Imagen capturada",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )
                } ?: Text("No se pudo cargar la imagen.")
            }
        },
        confirmButton = {
            Button(onClick = onClose) {
                Text("Cerrar")
            }
        }
    )
}

fun base64ToImageBitmap(base64: String): ImageBitmap? {
    return try {
        val decodedBytes = Base64.decode(base64, Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        bitmap?.asImageBitmap()
    } catch (e: Exception) {
        null
    }
}

