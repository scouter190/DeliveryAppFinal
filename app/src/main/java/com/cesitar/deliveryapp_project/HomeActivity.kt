package com.cesitar.deliveryapp_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.cesitar.deliveryapp_project.data.database.AppDatabase
import com.cesitar.deliveryapp_project.data.model.PedidoModel
import com.cesitar.deliveryapp_project.data.repository.ArticuloRepository
import com.cesitar.deliveryapp_project.data.repository.PedidoDetalleRepository
import com.cesitar.deliveryapp_project.data.repository.PedidoRepository
import com.cesitar.deliveryapp_project.data.repository.UsuarioRepository
import com.cesitar.deliveryapp_project.data.util.DatabaseUtil
import com.cesitar.deliveryapp_project.navigation.AppNavigation
import com.cesitar.deliveryapp_project.ui.theme.DeliveryApp_projectTheme
import kotlinx.coroutines.launch

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = AppDatabase.getDatabase(this)

        val usuarioRepository = UsuarioRepository(db.usuarioDao())
        val articuloRepository = ArticuloRepository(db.articuloDao())
        val pedidoRepository = PedidoRepository(db.pedidoDao())
        val pedidoDetalleRepository = PedidoDetalleRepository(db.pedidoDetalleDao())

        val util = DatabaseUtil(
            usuarioRepository,
            articuloRepository,
            pedidoRepository,
            pedidoDetalleRepository
        )
        lifecycleScope.launch {
            util.initData()
            setContent {
                    AppNavigation(
                        usuarioRepository,
                        articuloRepository,
                        pedidoRepository,
                        pedidoDetalleRepository
                    )
            }
        }

    }

}

