package com.brunocarvalho.aulasqliteandroid.database

import com.brunocarvalho.aulasqliteandroid.model.Produto

interface IProdutoDAO {

    fun salvar(produto: Produto): Boolean
    fun atualizar(produto: Produto): Boolean
    fun remover(idProduto: Int): Boolean
    fun listar(): List<Produto>

}