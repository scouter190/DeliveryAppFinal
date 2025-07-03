package com.cesitar.deliveryapp_project.composable

import android.content.Context
import android.net.Uri
import android.util.Base64
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import com.cesitar.deliveryapp_project.data.model.PedidoModel
import com.cesitar.deliveryapp_project.data.repository.PedidoRepository
import kotlinx.coroutines.launch
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EstadoPedidoDialog(
    pedidoRepository: PedidoRepository,
    pedidoModel: PedidoModel,
    onDismiss: () -> Unit,
    onGrabar: (String) -> Unit
) {
    val opciones = listOf("--Seleccione--", "Entregado", "Entregado Parcial", "No Entregado")
    var estadoSeleccionado  = remember { mutableStateOf(opciones[0]) }
    var expanded = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val base64Image = remember { mutableStateOf<String?>(null) }
    val photoUri = remember { mutableStateOf<Uri?>(null) }
    val takePictureLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success && photoUri.value != null) {
                val inputStream = context.contentResolver.openInputStream(photoUri.value!!)
                val bytes = inputStream?.readBytes()
                val base64 = Base64.encodeToString(bytes, Base64.DEFAULT)
                base64Image.value = base64
            }
        }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {},
        dismissButton = {},
        title = null,
        text = {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = "Sistema Delivery",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "Actualización de estados del pedido",
                    fontSize = 14.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = pedidoModel.numero_pedido, color = Color(0xFF00BFA5), fontWeight = FontWeight.Bold)
                Text(text = "Estado Actual: ${pedidoModel.estado}")

                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Nuevo Estado")

                // Dropdown estado
                ExposedDropdownMenuBox(
                    expanded = expanded.value,
                    onExpandedChange = { expanded.value = !expanded.value }
                ) {
                    OutlinedTextField(
                        value = estadoSeleccionado.value,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Estado") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded.value) },
                        modifier = Modifier.menuAnchor().fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded.value,
                        onDismissRequest = { expanded.value = false }
                    ) {
                        opciones.forEach { opcion ->
                            DropdownMenuItem(
                                text = { Text(opcion) },
                                onClick = {
                                    estadoSeleccionado.value = opcion
                                    expanded.value = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Archivos:")

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    OutlinedButton(onClick = {
                        val file = crearArchivoFoto(context)
                        val uri = FileProvider.getUriForFile(
                            context,
                            "${context.packageName}.provider",
                            file
                        )
                        photoUri.value = uri
                        takePictureLauncher.launch(uri);

                    }) {
                        Icon(Icons.Default.AddCircle, contentDescription = "Adjuntar")
                        Spacer(Modifier.width(4.dp))
                        Text("Adjuntar")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Cerrar")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                var currentPedidod = pedidoRepository.obtenerPedidoPorId(pedidoModel.id_pedido.toLong());
                                if(currentPedidod != null){
                                    currentPedidod.photo = base64Image.value;
                                    currentPedidod.estado = estadoSeleccionado.value;
                                    pedidoRepository.updatePedido(currentPedidod);
                                    onGrabar(estadoSeleccionado.value)
                                }
                            }
                          },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Grabar")
                    }
                }
            }
        },
        shape = RoundedCornerShape(16.dp),
        containerColor = Color(0xFFF8F8F8)
    )
}

fun crearArchivoFoto(context: Context): File {
    val storageDir = context.cacheDir
    return File.createTempFile("temp_image", ".jpg", storageDir)
}
