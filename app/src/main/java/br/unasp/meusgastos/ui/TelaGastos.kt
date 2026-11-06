package br.unasp.meusgastos.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.unasp.meusgastos.model.Categoria
import br.unasp.meusgastos.viewmodel.GastosViewModel

/**
 * CAMADA VIEW.
 * Este é o ÚNICO Composable do app que conhece o ViewModel.
 * Repare: não existe um único "if" de regra aqui dentro.
 */
@Composable
fun TelaGastos(
    vm: GastosViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Fundo)
            .padding(16.dp)
    ) {
        Text("Meus Gastos", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Navy)

        Spacer(Modifier.height(14.dp))

        FormularioGasto(
            descricao = vm.descricao,
            valorTexto = vm.valorTexto,
            categoria = vm.categoriaSelecionada,
            erro = vm.erro,
            aoDigitarDescricao = vm::aoDigitarDescricao,
            aoDigitarValor = vm::aoDigitarValor,
            aoSelecionarCategoria = vm::aoSelecionarCategoria,
            aoAdicionar = vm::adicionar
        )

        Spacer(Modifier.height(16.dp))

        FiltroCategorias(selecionada = vm.filtro, aoFiltrar = vm::aoFiltrar)

        Spacer(Modifier.height(12.dp))

        ResumoTotal(total = vm.total, quantidade = vm.quantidade)

        Spacer(Modifier.height(12.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(vm.gastosVisiveis) { gasto ->
                LinhaGasto(gasto = gasto, aoRemover = { vm.remover(gasto.id) })
            }
        }
    }
}

@Composable
fun FiltroCategorias(
    selecionada: Categoria?,
    aoFiltrar: (Categoria?) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        item {
            Etiqueta("Todas", ativa = selecionada == null, aoClicar = { aoFiltrar(null) })
        }
        items(Categoria.entries.toList()) { c ->
            Etiqueta(c.rotulo, ativa = selecionada == c, aoClicar = { aoFiltrar(c) })
        }
    }
}

@Preview(showBackground = true, heightDp = 780)
@Composable
private fun FiltroPreview() {
    FiltroCategorias(selecionada = Categoria.LAZER, aoFiltrar = {}, modifier = Modifier.padding(16.dp))
}
