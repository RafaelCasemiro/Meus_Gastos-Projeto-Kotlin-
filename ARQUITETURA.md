# ARQUITETURA — Meus Gastos

## 1. Se o app precisar buscar os gastos de uma API em vez da memória, quais arquivos mudam? E quais NÃO mudam?

**Muda:** só `model/GastoRepository.kt`, onde eu criaria uma nova classe (por exemplo `GastoRepositoryApi : GastoRepository`) que busca os dados na internet em vez de usar a lista em memória. Fora isso, mudo uma única linha em `GastosViewModel.kt`: o valor padrão do construtor, `GastoRepositoryMemoria()` passa a ser `GastoRepositoryApi()`.

**Com certeza NÃO mudam:**
- `Categoria.kt` e `Gasto.kt`: os dados continuam com o mesmo formato.
- Todos os arquivos de `ui/` (`TelaGastos`, `FormularioGasto`, `FiltroCategorias`, `ResumoTotal`, `LinhaGasto`, `Etiqueta`): eles só conhecem o ViewModel e recebem valores prontos.
- As regras do ViewModel (validação do `adicionar()`, `gastosVisiveis`, `total`, `quantidade`, filtro), porque ele só chama `listar()`, `adicionar()` e `remover()` da interface, sem saber de onde os dados vêm.
- A interface `GastoRepository` também não muda, desde que o contrato (listar, adicionar, remover) continue servindo.

**Ressalva honesta:** chamada de API é lenta e pode falhar, e hoje o `listar()` é uma função comum, não assíncrona. Se eu precisar de `suspend` ou de estado de "carregando", aí o contrato e o ViewModel mudam um pouco. Com a troca para memória/Demo/Room (síncronos) isso não aparece.

## 2. Por que o total é calculado e não guardado? Bug concreto se fosse uma variável atualizada manualmente.

No código, `total` é um `get()` que faz `gastosVisiveis.sumOf { it.valor }`. Ele é refeito a partir da lista toda vez que a tela precisa, então não tem como ficar diferente da lista.

Se fosse `var total by mutableStateOf(0.0)` atualizado só dentro de `adicionar()`:
1. Adiciono três gastos (18,50 + 9,20 + 142,00) e o total mostra 169,70.
2. Removo o de 142,00. A função `remover()` não atualiza o total, e a tela continua mostrando 169,70 enquanto a lista soma só 27,70. **A tela mente.**
3. Mesmo problema com o filtro: ao filtrar por "Lazer", a lista mostra só o Cinema (32,00), mas o total guardado continuaria com o valor de tudo.

Cada função nova que mexe na lista teria que lembrar de atualizar o total. Um esquecimento e dá erro. Calculado, o filtro já funciona "de graça".

## 3. O FormularioGasto não recebe o ViewModel. O que isso me permitiu fazer que não seria possível se recebesse?

- **Criar `@Preview` do formulário em vários estados sem rodar o app:** o `FormularioVazioPreview` e o `FormularioComErroPreview` passam `erro = "O valor precisa ser um número"` direto como parâmetro, e eu vejo a tela de erro sem digitar nada nem provocar o erro. Com o ViewModel como parâmetro, eu precisaria montar um ViewModel (com repositório) só para desenhar o preview, e forçar o erro por dentro.
- **Reutilizar o componente:** se existisse uma segunda tela que cadastra gastos, o mesmo `FormularioGasto` serviria, bastando ligar os eventos (`aoDigitarDescricao`, `aoAdicionar` etc.) em outro lugar.
- **Garantir que a regra fique fora da tela:** como ele só recebe valores e devolve eventos, não tem como ele decidir se o gasto é válido; quem decide é o ViewModel.
- Também facilita testar a UI isolada, com valores fixos.
