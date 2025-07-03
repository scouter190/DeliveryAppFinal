package com.cesitar.deliveryapp_project

import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cesitar.deliveryapp_project.data.model.ArticuloModel
import com.cesitar.deliveryapp_project.data.model.PedidoDetalleModel
import com.cesitar.deliveryapp_project.data.model.PedidoModel
import com.cesitar.deliveryapp_project.data.model.UsuarioModel
import com.cesitar.deliveryapp_project.data.repository.ArticuloRepository
import com.cesitar.deliveryapp_project.data.repository.PedidoDetalleRepository
import com.cesitar.deliveryapp_project.data.repository.PedidoRepository
import com.cesitar.deliveryapp_project.data.repository.UsuarioRepository
import com.cesitar.deliveryapp_project.data.util.DrawerSectionUtil
import com.cesitar.deliveryapp_project.navigation.AppScreens
import kotlinx.coroutines.launch

@Composable
fun DrawerScreen(
    navController: NavController,
    usuarioRepository: UsuarioRepository,
    articuloRepository: ArticuloRepository,
    pedidoRepository: PedidoRepository,
    pedidoDetalleRepository: PedidoDetalleRepository
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var currentSection by remember { mutableStateOf<DrawerSectionUtil>(DrawerSectionUtil.Pedido) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(
                    navController,
                    onSectionSelected = { section ->
                        currentSection = section
                        scope.launch { drawerState.close() }
                    }
                )
            }
        },
        gesturesEnabled = true
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    onOpenDrawer = {
                        scope.launch {
                            if (drawerState.isClosed) drawerState.open() else drawerState.close()
                        }
                    }
                )
            }
        ) {
                paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                when(currentSection){
                    is DrawerSectionUtil.Pedido -> PedidoScreen(
                        usuarioRepository = usuarioRepository,
                        articuloRepository = articuloRepository,
                        pedidoRepository = pedidoRepository,
                        pedidoDetalleRepository = pedidoDetalleRepository
                    )
                    is DrawerSectionUtil.Main -> MainScreen()
                }
            }

        }
    }
}

@Composable
fun DrawerContent(navController: NavController,onSectionSelected: (DrawerSectionUtil) -> Unit , modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "SistemaDelivery",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        HorizontalDivider()

        NavigationDrawerItem(
            icon = { Icon(Icons.Default.Info, contentDescription = "Reporte Pedidos") },
            label = {
                Text(
                    text = "Listado de Pedidos",
                    fontSize = 17.sp
                )
            },
            selected = false,
            onClick = {
                onSectionSelected(DrawerSectionUtil.Pedido)
            }
        )
        Spacer(modifier = Modifier.height(4.dp))
        NavigationDrawerItem(
            icon = { Icon(Icons.Default.Info, contentDescription = "Main") },
            label = {
                Text(
                    text = "Otra ventana",
                    fontSize = 17.sp
                )
            },
            selected = false,
            onClick = {
                onSectionSelected(DrawerSectionUtil.Main)
            }
        )
        Spacer(modifier = Modifier.height(4.dp))

        NavigationDrawerItem(
            icon = { Icon(Icons.Default.Close, contentDescription = "Cerrar Sesión") },
            label = {
                Text(
                    text = "Cerrar Sesión",
                    fontSize = 17.sp
                )
            },
            selected = false,
            onClick = {
                navController.navigate("login_screen")
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onOpenDrawer: () -> Unit) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(0.6f)
        ),
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                modifier = Modifier
                    .padding(start = 16.dp, end = 10.dp)
                    .size(28.dp)
                    .clickable { onOpenDrawer() }
            )
        },
        title = {
            Text(text = "Listado De Pedidos")
        },
        actions = {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Refresh",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(28.dp)
            )
        }
    )
}