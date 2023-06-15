package com.miko.osiptelimei.Domain.Model

data class UsuariosResponse(
    var codigo: String,
    var mensaje: String,
    var data: ArrayList<Usuario>
)