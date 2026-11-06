package br.unasp.meusgastos.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.unasp.meusgastos.model.Categoria

/**
 * Componente sem estado. Não conhece o ViewModel: recebe valores e devolve
 * eventos. Por isso dá para desenhar a tela de erro no @Preview, sem rodar
 * o app e sem provocar o erro de verdade.
 */
@Composable
fun FormularioGasto(
    descricao: String,
    valorTexto: String,
    categoria: Categoria,
    erro: String?,
    aoDigitarDescricao: (String) -> Unit,
    aoDigitarValor: (String) -> Unit,
    aoSelecionarCategoria: (Categoria) -> Unit,
    aoAdicionar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
            .padding(14.dp)
    ) {
        TextField(
            value = descricao,
            onValueChange = aoDigitarDescricao,
            label = { Text("Descrição do gasto") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = valorTexto,
                onValueChange = aoDigitarValor,
                label = { Text("Valor") },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(10.dp))
            Button(
                onClick = aoAdicionar,
                colors = ButtonDefaults.buttonColors(containerColor = Navy)
            ) {
                Text("Adicionar")
            }
        }

        Spacer(Modifier.height(10.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(Categoria.entries.toList()) { c ->
                Etiqueta(
                    texto = c.rotulo,
                    ativa = c == categoria,
                    aoClicar = { aoSelecionarCategoria(c) }
                )
            }
        }

        // A tela não decide se há erro. Ela mostra o que o ViewModel disse.
        if (erro != null) {
            Spacer(Modifier.height(10.dp))
            Text(erro, color = Erro, fontSize = 13.sp)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF2F6FA)
@Composable
private fun FormularioVazioPreview() {
    FormularioGasto(
        descricao = "", valorTexto = "",
        categoria = Categoria.ALIMENTACAO, erro = null,
        aoDigitarDescricao = {}, aoDigitarValor = {},
        aoSelecionarCategoria = {}, aoAdicionar = {},
        modifier = Modifier.padding(16.dp)
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFF2F6FA)
@Composable
private fun FormularioComErroPreview() {
    FormularioGasto(
        descricao = "Café", valorTexto = "abc",
        categoria = Categoria.ALIMENTACAO,
        erro = "O valor precisa ser um número",
        aoDigitarDescricao = {}, aoDigitarValor = {},
        aoSelecionarCategoria = {}, aoAdicionar = {},
        modifier = Modifier.padding(16.dp)
    )
}
