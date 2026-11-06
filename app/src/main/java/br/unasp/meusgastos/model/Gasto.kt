package br.unasp.meusgastos.model

/**
 * O id existe para a lista saber qual item remover.
 * A descrição não serve: dois gastos podem ter o mesmo texto.
 */
data class Gasto(
    val id: Long,
    val descricao: String,
    val valor: Double,
    val categoria: Categoria
)
