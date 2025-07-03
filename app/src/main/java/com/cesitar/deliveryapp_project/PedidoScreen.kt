package com.cesitar.deliveryapp_project

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cesitar.deliveryapp_project.composable.PedidoFotoModalComposable
import com.cesitar.deliveryapp_project.composable.PedidoModalComposable
import com.cesitar.deliveryapp_project.data.model.ArticuloModel
import com.cesitar.deliveryapp_project.data.model.PedidoDetalleModel
import com.cesitar.deliveryapp_project.data.model.PedidoModel
import com.cesitar.deliveryapp_project.data.model.UsuarioModel
import com.cesitar.deliveryapp_project.data.repository.ArticuloRepository
import com.cesitar.deliveryapp_project.data.repository.PedidoDetalleRepository
import com.cesitar.deliveryapp_project.data.repository.PedidoRepository
import com.cesitar.deliveryapp_project.data.repository.UsuarioRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PedidoScreen(
    usuarioRepository: UsuarioRepository,
    articuloRepository: ArticuloRepository,
    pedidoRepository: PedidoRepository,
    pedidoDetalleRepository: PedidoDetalleRepository
) {
    val pedidosModel = remember { mutableStateOf<List<PedidoModel>>(emptyList()) }
    val isReady = remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        val pedidos = loadPedidos(
            usuarioRepository,
            articuloRepository,
            pedidoRepository,
            pedidoDetalleRepository
        )
        pedidosModel.value = pedidos
        isReady.value = true
    }

    if (isReady.value) {
        Scaffold { padding ->
            ScreenContent(
                usuarioRepository,
                articuloRepository,
                pedidoRepository,
                pedidoDetalleRepository,
                padding,
                pedidosModel.value,
                pedidosModel
            )
        }
    }
}

@Composable
fun ScreenContent(
    usuarioRepository: UsuarioRepository,
    articuloRepository: ArticuloRepository,
    pedidoRepository: PedidoRepository,
    pedidoDetalleRepository: PedidoDetalleRepository,
    padding: PaddingValues,
    pedidosModel: List<PedidoModel>,
    pedidoMutable: MutableState<List<PedidoModel>>
) {
    LazyColumn(
        modifier = Modifier
            .padding(padding)
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        items(pedidosModel) { pedido ->
            val mostrarImagen = remember { mutableStateOf(false) }
            val showDialog = remember { mutableStateOf(false) }

            if (showDialog.value) {
                PedidoModalComposable(
                    pedidoRepository,
                    pedido,
                    onDismiss = { showDialog.value = false },
                    onGrabar = { nuevoEstado ->
                        val pedidos = loadPedidos(
                            usuarioRepository,
                            articuloRepository,
                            pedidoRepository,
                            pedidoDetalleRepository
                        )
                        pedidoMutable.value = pedidos
                        showDialog.value = false
                    }
                )
            }

            if (mostrarImagen.value && pedido.foto != null) {
                PedidoFotoModalComposable(pedido.foto) {
                    mostrarImagen.value = false
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "#${pedido.numero_pedido}",
                                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF008000)
                            )

                            val estadoColor = when (pedido.estado.trim().lowercase()) {
                                "entregado" -> Color(0xFF2E7D32)
                                "entregado parcial" -> Color(0xFFFB8C00)
                                "no entregado" -> Color(0xFFC62828)
                                else -> Color.Gray
                            }

                            Text(
                                text = "Estado: ${pedido.estado}",
                                color = estadoColor,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                        }

                        Row {
                            val colorGuia = when (pedido.estado.trim().lowercase()) {
                                "entregado" -> Color(0xFF2E7D32)
                                "entregado parcial" -> Color(0xFFFB8C00)
                                "no entregado" -> Color(0xFFC62828)
                                else -> Color(0xFF9E9E9E)
                            }

                            Box(
                                modifier = Modifier
                                    .clickable { showDialog.value = true }
                                    .background(
                                        color = colorGuia,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = "Guía de Remisión",
                                    color = Color.White,
                                    fontSize = MaterialTheme.typography.labelSmall.fontSize
                                )
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Box(
                                modifier = Modifier
                                    .clickable { mostrarImagen.value = true }
                                    .background(
                                        color = Color(0xFF00C853),
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = "Ver foto",
                                    color = Color.White,
                                    fontSize = MaterialTheme.typography.labelSmall.fontSize
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text("Empresa:")
                    Text("Cliente: ${pedido.usuario?.nombre_completo ?: "N/A"}")
                    Text("Documento: ${pedido.documento}")
                    Text("Dirección: ${pedido.direccion_tienda}")
                    Text("Horario de Entrega: ${pedido.fecha_entrega}")

                    val articulosConCantidad = pedido.pedidoDetalle.mapNotNull { detalle ->
                        val nombre = detalle.articulo?.nombre
                        val cantidad = detalle.cantidad
                        if (nombre != null) "$nombre ($cantidad)" else null
                    }
                    val cantidadTotal = pedido.pedidoDetalle.sumOf { it.cantidad }

                    Text(
                        text = "Artículos ($cantidadTotal): ${articulosConCantidad.joinToString(", ")}"
                    )
                }
            }
        }
    }
}

suspend fun loadPedidos(
    usuarioRepository: UsuarioRepository,
    articuloRepository: ArticuloRepository,
    pedidoRepository: PedidoRepository,
    pedidoDetalleRepository: PedidoDetalleRepository
): MutableList<PedidoModel> {
    val pedidosCargados = mutableListOf<PedidoModel>()
    val listaPedidos = pedidoRepository.listarPedidos()

    for (pedido in listaPedidos) {
        val pedidoDetallesModel = mutableListOf<PedidoDetalleModel>()
        val pedidoDetalles = pedidoDetalleRepository.listarPedidoDetalle(pedido.id_pedido.toLong())
        val usuarioPedido = usuarioRepository.obtenerUsuarioPorId(pedido.id_usuario.toLong())

        for (pedidoDetalle in pedidoDetalles) {
            val articulo = articuloRepository.obtenerArticuloPorId(pedidoDetalle.id_articulo.toLong())
            val usuario = usuarioRepository.obtenerUsuarioPorId(pedidoDetalle.id_usuario_registro.toLong())
            val pedidoDetalleModel = PedidoDetalleModel.from(pedidoDetalle)
            pedidoDetalleModel.articulo = ArticuloModel.from(articulo)
            pedidoDetalleModel.usuario_registro = UsuarioModel.from(usuario)
            pedidoDetallesModel.add(pedidoDetalleModel)
        }

        val pedidoModel = PedidoModel.from(pedido).apply {
            this.pedidoDetalle = pedidoDetallesModel
            this.usuario = UsuarioModel.from(usuarioPedido)
        }

        pedidosCargados.add(pedidoModel)
    }

    return pedidosCargados
        .sortedWith(compareByDescending<PedidoModel> {
            it.estado.equals("Pendiente", ignoreCase = true)
        })
        .toMutableList()
}
