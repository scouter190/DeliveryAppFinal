package com.cesitar.deliveryapp_project.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cesitar.deliveryapp_project.data.database.AppDatabase
import com.cesitar.deliveryapp_project.data.entity.Articulo
import com.cesitar.deliveryapp_project.data.repository.ArticuloRepository
/*
class ArticuloViewModel(application: Application): AndroidViewModel(application) {
    private val listarArticulos: LiveData<List<Articulo>>
    private val repository: ArticuloRepository

    init{
        val articuloDao = AppDatabase.getDatabase(application).articuloDao()
        repository = ArticuloRepository(articuloDao)
        listarArticulos = repository.listarArticulos
    }

}
 */