package com.cesitar.deliveryapp_project.data.entity

import androidx.room.Database
import androidx.room.RoomDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ARTICULO")
data class Articulo(
    @PrimaryKey(autoGenerate = true) val id_articulo: Int = 0,
    val nombre: String,
    val stock: Int,
    val precio: Float
)
