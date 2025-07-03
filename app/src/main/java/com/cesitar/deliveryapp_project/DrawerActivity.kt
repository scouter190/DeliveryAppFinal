package com.cesitar.deliveryapp_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.cesitar.deliveryapp_project.data.database.AppDatabase
import com.cesitar.deliveryapp_project.data.repository.ArticuloRepository
import com.cesitar.deliveryapp_project.data.repository.PedidoDetalleRepository
import com.cesitar.deliveryapp_project.data.repository.PedidoRepository
import com.cesitar.deliveryapp_project.data.repository.UsuarioRepository
import com.cesitar.deliveryapp_project.data.util.DatabaseUtil
import kotlinx.coroutines.launch

class DrawerActivity : ComponentActivity() {
    private lateinit var db: AppDatabase
    private lateinit var util: DatabaseUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        db = AppDatabase.getDatabase(this);

        var usuarioRepository = UsuarioRepository(db.usuarioDao());
        var articuloRepository = ArticuloRepository(db.articuloDao());
        var pedidoRepository = PedidoRepository(db.pedidoDao());
        var pedidoDetalleRepository = PedidoDetalleRepository(db.pedidoDetalleDao());

        util = DatabaseUtil(
            usuarioRepository,
            articuloRepository,
            pedidoRepository,
            pedidoDetalleRepository
        );
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            util.initData();
        }
        setContent {
           val navController = rememberNavController()
            DrawerScreen(
                navController,
                usuarioRepository,
                articuloRepository,
                pedidoRepository,
                pedidoDetalleRepository)
        }
    }
}