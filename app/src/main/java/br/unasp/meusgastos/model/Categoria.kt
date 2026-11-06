package br.unasp.meusgastos.model

/**
 * CAMADA MODEL.
 * Kotlin puro: não conhece Compose, ViewModel nem tela.
 */
enum class Categoria(val rotulo: String) {
    ALIMENTACAO("Alimentação"),
    TRANSPORTE("Transporte"),
    MORADIA("Moradia"),
    LAZER("Lazer"),
    OUTROS("Outros")
}
