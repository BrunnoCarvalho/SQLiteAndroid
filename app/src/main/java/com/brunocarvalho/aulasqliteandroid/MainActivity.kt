package com.brunocarvalho.aulasqliteandroid

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.cursoradapter.widget.SimpleCursorAdapter.ViewBinder
import com.brunocarvalho.aulasqliteandroid.database.DatabaseHelper
import com.brunocarvalho.aulasqliteandroid.database.ProdutoDAO
import com.brunocarvalho.aulasqliteandroid.databinding.ActivityMainBinding
import com.brunocarvalho.aulasqliteandroid.model.Produto

class MainActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var produtoDAO: ProdutoDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        produtoDAO = ProdutoDAO(this)

        with(binding){
            btnSalvar.setOnClickListener {
                salvar()
            }

            btnListar.setOnClickListener {
                listar()
            }

            btnAtualizar.setOnClickListener {
                atualizar()
            }

            btnRemover.setOnClickListener {
                remover()
            }
        }
    }

    private fun remover() {

        //val produtoDAO = ProdutoDAO(this)
        val titulo = binding.editProduto.text.toString()
        val id = titulo.toIntOrNull()
        produtoDAO.remover(id!!)
    }

    private fun atualizar() {

        val titulo = binding.editProduto.text.toString()

        //val produtoDAO = ProdutoDAO(this)
        val produto = Produto( 1, titulo, "Descricao..."
        )
        produtoDAO.atualizar(produto)
    }

    private fun listar() {

        //val produtoDAO = ProdutoDAO(this)
        val listaProdutos = produtoDAO.listar()

        var texto = ""
        if(listaProdutos.isNotEmpty()){
            listaProdutos.forEach { produto ->
                texto += "${produto.idProduto} - ${produto.titulo} \n"
                Log.i("info_db", "${produto.idProduto} - ${produto.titulo}")
            }
            binding.textResultado.text = texto
        }else{
            binding.textResultado.text = "Nenhum item cadastrado"
        }

    }

    private fun salvar(){

        val titulo = binding.editProduto.text.toString()

        //val produtoDAO = ProdutoDAO(this)
        val produto = Produto(
            -1, titulo, "Descrição..."
        )

        if( produtoDAO.salvar(produto) ){
            Toast.makeText(this, "Sucesso ao cadastrar produto", Toast.LENGTH_SHORT).show()
            binding.editProduto.setText("")
        }else{
            Toast.makeText(this, "Erro ao cadastrar produto", Toast.LENGTH_SHORT).show()
        }
    }

}