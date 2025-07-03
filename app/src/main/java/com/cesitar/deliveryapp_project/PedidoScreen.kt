package com.cesitar.deliveryapp_project

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import com.cesitar.deliveryapp_project.data.entity.Pedido
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
fun HomeScreen(
    usuarioRepository: UsuarioRepository,
    articuloRepository: ArticuloRepository,
    pedidoRepository: PedidoRepository,
    pedidoDetalleRepository: PedidoDetalleRepository
) {
    var pedidosModel = mutableListOf<PedidoModel>()
    LaunchedEffect(true) {
        var listaPedidos = pedidoRepository.listarPedidos();
        for (pedido in listaPedidos) {
            var pedidoDetallesModel = mutableListOf<PedidoDetalleModel>()
            var pedidoDetalles =
                pedidoDetalleRepository.listarPedidoDetalle(pedido.id_pedido.toLong())
            for(pedidoDetalle in pedidoDetalles){
                var articulo = articuloRepository.obtenerArticuloPorId(pedidoDetalle.id_articulo.toLong())
                var usuario = usuarioRepository.obtenerUsuarioPorId(pedidoDetalle.id_usuario_registro.toLong());
                var pedidoDetalleModel = PedidoDetalleModel.from(pedidoDetalle);
                pedidoDetalleModel.articulo = ArticuloModel.from(articulo);
                pedidoDetalleModel.usuario_registro = UsuarioModel.from(usuario);
                pedidoDetallesModel.add(PedidoDetalleModel.from(pedidoDetalle));
            }
            var pedidoModel = PedidoModel.from(pedido);
            pedidoModel.pedidoDetalle = pedidoDetallesModel;
            pedidosModel.add(pedidoModel);
        }
    }
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Listado de Pedidos") },
                    navigationIcon = {
                        IconButton(onClick = { }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menú")
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* Refrescar */ }) {
                            Icon(Icons.Default.Refresh, contentDescription = "Refrescar")
                        }
                    }
                )
            }
        ) { padding ->
            ScreenContent(padding, pedidosModel)
        }
    }


@Composable
fun ScreenContent(padding: PaddingValues, pedidosModel: List<PedidoModel>) {
    LazyColumn(
        modifier = Modifier
            .padding(padding)
            .padding(8.dp)
    ) {
        items(pedidosModel) { pedido ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text("Pedido: ${pedido.numero_pedido}", style = MaterialTheme.typography.titleMedium)
                    Text("Estado: ${pedido.estado}")
                    Text("Fecha: ${pedido.fecha_entrega}")
                    Text("Cliente: ${pedido.documento}")
                    Text("Dirección: ${pedido.direccion_tienda}")
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Detalle:")
                    pedido.pedidoDetalle.forEach { detalle ->
                        Text("- ${detalle.articulo?.nombre} x${detalle.cantidad} (S/.${detalle.articulo?.precio})")
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
