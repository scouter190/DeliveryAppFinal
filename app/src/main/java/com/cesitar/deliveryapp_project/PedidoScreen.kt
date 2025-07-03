package com.cesitar.deliveryapp_project

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
        pedidosModel.value = pedidos;
        isReady.value = true;
    }
    if(isReady.value){
        Scaffold() { padding ->
            ScreenContent(usuarioRepository, articuloRepository, pedidoRepository, pedidoDetalleRepository, padding, pedidosModel.value, pedidosModel)
        }
    } else{
        // Mostrar un indicador de carga o un mensaje de espera mientras se cargan los datos
    }

}


@Composable
fun ScreenContent(usuarioRepository: UsuarioRepository,
                  articuloRepository: ArticuloRepository,
                  pedidoRepository: PedidoRepository,
                  pedidoDetalleRepository: PedidoDetalleRepository,
                  padding: PaddingValues,
                  pedidosModel: List<PedidoModel>,
                  pedidoMutable: MutableState<List<PedidoModel>>) {

    LazyColumn(
        modifier = Modifier
            .padding(padding)
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        items(pedidosModel) { pedido ->
            val mostrarImagen = remember { mutableStateOf(false) }
            var showDialog = remember { mutableStateOf(false) }

            if (showDialog.value) {
                PedidoModalComposable(
                    pedidoRepository,
                    pedido,
                    onDismiss = { showDialog.value = false },
                    onGrabar = { nuevoEstado ->
                        // Aquí grabas el estado

                        val pedidos = loadPedidos(
                            usuarioRepository = usuarioRepository,
                            articuloRepository = articuloRepository,
                            pedidoRepository = pedidoRepository,
                            pedidoDetalleRepository = pedidoDetalleRepository
                        )
                        pedidoMutable.value = pedidos;
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
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Pedido: ${pedido.numero_pedido}",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF008000)
                        )
                        Column {
                            Box(
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .clickable {
                                        showDialog.value = true;
                                    }
                                    .background(
                                        color = Color(0xFFFFA500), // Naranja
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
                            Spacer(modifier = Modifier.height(8.dp))
                            Box(
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .clickable {
                                        mostrarImagen.value = true;
                                    }
                                    .background(
                                        color = Color(0xFF00C853), // Naranja
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

                    Text("Empresa: ") // Ajusta si tienes ese campo
                    Text("Cliente: ${ pedido.usuario?.nombre_completo  ?: "N/A"}")
                    Text("Documento: ${pedido.documento}")
                    Text("Dirección: ${pedido.direccion_tienda}")
                    Text("Horario de Entrega: ${pedido.fecha_entrega}")
                    Text(
                        "Artículos: ${
                            pedido.pedidoDetalle.joinToString(", ") {
                                it.articulo?.nombre ?: ""
                            }
                        }"
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

suspend fun loadPedidos(
    usuarioRepository: UsuarioRepository,
    articuloRepository: ArticuloRepository,
    pedidoRepository: PedidoRepository,
    pedidoDetalleRepository: PedidoDetalleRepository
) : MutableList<PedidoModel> {
    val pedidosCargados = mutableListOf<PedidoModel>()
    var listaPedidos = pedidoRepository.listarPedidos();
    for (pedido in listaPedidos) {
        var pedidoDetallesModel = mutableListOf<PedidoDetalleModel>()
        var pedidoDetalles =
            pedidoDetalleRepository.listarPedidoDetalle(pedido.id_pedido.toLong())
        var usuarioPedido = usuarioRepository.obtenerUsuarioPorId(pedido.id_usuario.toLong())
        for (pedidoDetalle in pedidoDetalles) {
            var articulo =
                articuloRepository.obtenerArticuloPorId(pedidoDetalle.id_articulo.toLong())
            var usuario =
                usuarioRepository.obtenerUsuarioPorId(pedidoDetalle.id_usuario_registro.toLong());
            var pedidoDetalleModel = PedidoDetalleModel.from(pedidoDetalle);
            pedidoDetalleModel.articulo = ArticuloModel.from(articulo);
            pedidoDetalleModel.usuario_registro = UsuarioModel.from(usuario);
            pedidoDetallesModel.add(PedidoDetalleModel.from(pedidoDetalle));
        }
        var pedidoModel = PedidoModel.from(pedido).apply {
            this.pedidoDetalle = pedidoDetallesModel
            this.usuario = UsuarioModel.from(usuarioPedido)
        };
        pedidosCargados.add(pedidoModel);
    }
    return pedidosCargados
        .sortedWith(compareByDescending<PedidoModel> {
            it.estado.equals("Pendiente", ignoreCase = true)
        })
        .toMutableList()
}