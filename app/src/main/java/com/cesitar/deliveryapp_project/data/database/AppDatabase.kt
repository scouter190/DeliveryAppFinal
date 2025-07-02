package com.cesitar.deliveryapp_project.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cesitar.deliveryapp_project.data.entity.*
import com.cesitar.deliveryapp_project.data.dao.*

@Database(
    entities = [
        Usuario::class,
        Articulo::class,
        Pedido::class,
        PedidoDetalle::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun articuloDao(): ArticuloDao
    abstract fun pedidoDao(): PedidoDao
    abstract fun pedidoDetalleDao(): PedidoDetalleDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "delivery_database"
                )
                    .fallbackToDestructiveMigration() // útil en desarrollo
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
