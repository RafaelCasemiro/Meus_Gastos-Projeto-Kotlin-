package br.unasp.meusgastos.model

/**
 * O CONTRATO: diz o QUE a fonte de dados faz, sem dizer COMO.
 * O ViewModel depende desta interface, nunca de uma implementação —
 * e é por isso que dá para trocar a fonte sem mexer no resto do app.
 */
interface GastoRepository {
    fun listar(): List<Gasto>
    fun adicionar(descricao: String, valor: Double, categoria: Categoria)
    fun remover(id: Long)
}

/**
 * Implementação de hoje: lista em memória.
 * Repare que NÃO usa mutableStateListOf — observabilidade é problema
 * da camada de apresentação, não da camada de dados.
 */
class GastoRepositoryMemoria : GastoRepository {

    private val gastos = mutableListOf<Gasto>()
    private var proximoId = 1L

    override fun listar(): List<Gasto> = gastos.toList()

    override fun adicionar(descricao: String, valor: Double, categoria: Categoria) {
        gastos.add(Gasto(proximoId++, descricao, valor, categoria))
    }

    override fun remover(id: Long) {
        gastos.removeAll { it.id == id }
    }
}

/**
 * Segunda implementação, com dados prontos.
 * Troque na linha do construtor do GastosViewModel e rode:
 * nenhum arquivo da pasta ui/ precisa mudar. Essa é a prova da arquitetura.
 */
class GastoRepositoryDemo : GastoRepository {

    private val gastos = mutableListOf(
        Gasto(1, "Almoço no bandejão", 18.50, Categoria.ALIMENTACAO),
        Gasto(2, "Passagem de ônibus", 9.20, Categoria.TRANSPORTE),
        Gasto(3, "Conta de luz", 142.00, Categoria.MORADIA),
        Gasto(4, "Cinema", 32.00, Categoria.LAZER)
    )
    private var proximoId = 5L

    override fun listar(): List<Gasto> = gastos.toList()

    override fun adicionar(descricao: String, valor: Double, categoria: Categoria) {
        gastos.add(Gasto(proximoId++, descricao, valor, categoria))
    }

    override fun remover(id: Long) {
        gastos.removeAll { it.id == id }
    }
}
