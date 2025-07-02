package com.cesitar.deliveryapp_project.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "USUARIO")
data class Usuario(
    @PrimaryKey(autoGenerate = true) val id_usuario: Int = 0,
    val nombre_completo: String,
    val usuario: String,
    val contraseña: String
)
