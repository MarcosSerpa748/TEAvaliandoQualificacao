package com.example.teavaliandotestes.data.local.banco

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.teavaliandotestes.data.dataclass.OpcaoQuestao
import com.example.teavaliandotestes.data.local.daos.QuestaoDao
import com.example.teavaliandotestes.data.local.entidades.QuestaoEntity
import com.example.teavaliandotestes.domain.enums.CategoriaQuestao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Provider
import kotlinx.coroutines.launch
import javax.inject.Inject

class MeuBancoCallback@Inject constructor(private val daoEscolhido:Provider<QuestaoDao>): RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase){
        super.onCreate(db)

        CoroutineScope(Dispatchers.IO).launch{
            val dao = daoEscolhido.get()

            val listaQuestoes = listOf(
                QuestaoEntity(
                    enunciado = "O ENUNCIADO DA QUESTÃO FICARÁ AQUI",
                    arquivoLeituraEnunciado = "audio_teste",
                    textApoio = null,
                    arquivoLeituraTexto = null,
                    imagemApoio = "teuzinho",
                    categoria = CategoriaQuestao.LEITURA,
                    itens = listOf(
                        OpcaoQuestao("A","hasbulla"),
                        OpcaoQuestao("B","hasbulla"),
                        OpcaoQuestao("C","hasbulla"),
                        OpcaoQuestao("D","hasbulla")
                    ),
                    indicieItenCorreto = 0
                ),
                QuestaoEntity(
                    enunciado = "O ENUNCIADO DA QUESTÃO FICARÁ AQUI",
                    arquivoLeituraEnunciado = "audio_teste",
                    textApoio = null,
                    arquivoLeituraTexto = null,
                    imagemApoio = "teuzinho",
                    categoria = CategoriaQuestao.LEITURA,
                    itens = listOf(
                        OpcaoQuestao("A","hasbulla"),
                        OpcaoQuestao("B","hasbulla"),
                        OpcaoQuestao("C","hasbulla"),
                        OpcaoQuestao("D","hasbulla")
                    ),
                    indicieItenCorreto = 0
                ),
                QuestaoEntity(
                    enunciado = "O ENUNCIADO DA QUESTÃO FICARÁ AQUI",
                    arquivoLeituraEnunciado = "audio_teste",
                    textApoio = null,
                    arquivoLeituraTexto = null,
                    imagemApoio = "teuzinho",
                    categoria = CategoriaQuestao.LEITURA,
                    itens = listOf(
                        OpcaoQuestao("A","hasbulla"),
                        OpcaoQuestao("B","hasbulla"),
                        OpcaoQuestao("C","hasbulla"),
                        OpcaoQuestao("D","hasbulla")
                    ),
                    indicieItenCorreto = 0
                ),
                QuestaoEntity(
                    enunciado = "O ENUNCIADO DA QUESTÃO FICARÁ AQUI",
                    arquivoLeituraEnunciado = "audio_teste",
                    textApoio = null,
                    arquivoLeituraTexto = null,
                    imagemApoio = "teuzinho",
                    categoria = CategoriaQuestao.LEITURA,
                    itens = listOf(
                        OpcaoQuestao("A","hasbulla"),
                        OpcaoQuestao("B","hasbulla"),
                        OpcaoQuestao("C","hasbulla"),
                        OpcaoQuestao("D","hasbulla")
                    ),
                    indicieItenCorreto = 0
                ),
                QuestaoEntity(
                    enunciado = "O ENUNCIADO DA QUESTÃO FICARÁ AQUI",
                    arquivoLeituraEnunciado = "audio_teste",
                    textApoio = null,
                    arquivoLeituraTexto = null,
                    imagemApoio = "teuzinho",
                    categoria = CategoriaQuestao.LEITURA,
                    itens = listOf(
                        OpcaoQuestao("A","hasbulla"),
                        OpcaoQuestao("B","hasbulla"),
                        OpcaoQuestao("C","hasbulla"),
                        OpcaoQuestao("D","hasbulla")
                    ),
                    indicieItenCorreto = 0
                ),
                QuestaoEntity(
                    enunciado = "O ENUNCIADO DA QUESTÃO FICARÁ AQUI",
                    arquivoLeituraEnunciado = "audio_teste",
                    textApoio = "O TEXTO DE APOIO FICARÁ AQUI, ENTRE O ENUNCIADO E OS ITENS",
                    arquivoLeituraTexto = "audio_teste_apoio",
                    imagemApoio = null,
                    categoria = CategoriaQuestao.ESCRITA,
                    itens = listOf(
                        OpcaoQuestao("A","hasbulla"),
                        OpcaoQuestao("B","hasbulla"),
                        OpcaoQuestao("C","hasbulla"),
                        OpcaoQuestao("D","hasbulla")
                    ),
                    indicieItenCorreto = 0
                ),
                QuestaoEntity(
                    enunciado = "O ENUNCIADO DA QUESTÃO FICARÁ AQUI",
                    arquivoLeituraEnunciado = "audio_teste",
                    textApoio = null,
                    arquivoLeituraTexto = null,
                    imagemApoio = null,
                    categoria = CategoriaQuestao.ESCRITA,
                    itens = listOf(
                        OpcaoQuestao("A","hasbulla"),
                        OpcaoQuestao("B","hasbulla"),
                        OpcaoQuestao("C","hasbulla"),
                        OpcaoQuestao("D","hasbulla")
                    ),
                    indicieItenCorreto = 1
                ),
                QuestaoEntity(
                    enunciado = "O ENUNCIADO DA QUESTÃO FICARÁ AQUI",
                    arquivoLeituraEnunciado = "audio_teste",
                    textApoio = null,
                    arquivoLeituraTexto = null,
                    imagemApoio = null,
                    categoria = CategoriaQuestao.ESCRITA,
                    itens = listOf(
                        OpcaoQuestao("A","hasbulla"),
                        OpcaoQuestao("B","hasbulla"),
                        OpcaoQuestao("C","hasbulla"),
                        OpcaoQuestao("D","hasbulla")
                    ),
                    indicieItenCorreto = 2
                ),
                QuestaoEntity(
                    enunciado = "O ENUNCIADO DA QUESTÃO FICARÁ AQUI",
                    arquivoLeituraEnunciado = "audio_teste",
                    textApoio = null,
                    arquivoLeituraTexto = null,
                    imagemApoio = null,
                    categoria = CategoriaQuestao.ESCRITA,
                    itens = listOf(
                        OpcaoQuestao("A","hasbulla"),
                        OpcaoQuestao("B","hasbulla"),
                        OpcaoQuestao("C","hasbulla"),
                        OpcaoQuestao("D","hasbulla")
                    ),
                    indicieItenCorreto = 3
                ),
                QuestaoEntity(
                    enunciado = "O ENUNCIADO DA QUESTÃO FICARÁ AQUI",
                    arquivoLeituraEnunciado = "audio_teste",
                    textApoio = null,
                    arquivoLeituraTexto = null,
                    imagemApoio = null,
                    categoria = CategoriaQuestao.ESCRITA,
                    itens = listOf(
                        OpcaoQuestao("A","hasbulla"),
                        OpcaoQuestao("B","hasbulla"),
                        OpcaoQuestao("C","hasbulla"),
                        OpcaoQuestao("D","hasbulla")
                    ),
                    indicieItenCorreto = 0
                ),
                QuestaoEntity(
                    enunciado = "O ENUNCIADO DA QUESTÃO FICARÁ AQUI",
                    arquivoLeituraEnunciado = "audio_teste",
                    textApoio = null,
                    arquivoLeituraTexto = null,
                    imagemApoio = null,
                    categoria = CategoriaQuestao.LINGUAGEM_ORAL,
                    itens = listOf(
                        OpcaoQuestao("A","hasbulla"),
                        OpcaoQuestao("B","hasbulla"),
                        OpcaoQuestao("C","hasbulla"),
                        OpcaoQuestao("D","hasbulla")
                    ),
                    indicieItenCorreto = 0
                ),
                QuestaoEntity(
                    enunciado = "O ENUNCIADO DA QUESTÃO FICARÁ AQUI",
                    arquivoLeituraEnunciado = "audio_teste",
                    textApoio = null,
                    arquivoLeituraTexto = null,
                    imagemApoio = null,
                    categoria = CategoriaQuestao.LINGUAGEM_ORAL,
                    itens = listOf(
                        OpcaoQuestao("A","hasbulla"),
                        OpcaoQuestao("B","hasbulla"),
                        OpcaoQuestao("C","hasbulla"),
                        OpcaoQuestao("D","hasbulla")
                    ),
                    indicieItenCorreto = 1
                ),
                QuestaoEntity(
                    enunciado = "O ENUNCIADO DA QUESTÃO FICARÁ AQUI",
                    arquivoLeituraEnunciado = "audio_teste",
                    textApoio = null,
                    arquivoLeituraTexto = null,
                    imagemApoio = null,
                    categoria = CategoriaQuestao.LINGUAGEM_ORAL,
                    itens = listOf(
                        OpcaoQuestao("A","hasbulla"),
                        OpcaoQuestao("B","hasbulla"),
                        OpcaoQuestao("C","hasbulla"),
                        OpcaoQuestao("D","hasbulla")
                    ),
                    indicieItenCorreto = 2
                ),
                QuestaoEntity(
                    enunciado = "O ENUNCIADO DA QUESTÃO FICARÁ AQUI",
                    arquivoLeituraEnunciado = "audio_teste",
                    textApoio = null,
                    arquivoLeituraTexto = null,
                    imagemApoio = null,
                    categoria = CategoriaQuestao.LINGUAGEM_ORAL,
                    itens = listOf(
                        OpcaoQuestao("A","hasbulla"),
                        OpcaoQuestao("B","hasbulla"),
                        OpcaoQuestao("C","hasbulla"),
                        OpcaoQuestao("D","hasbulla")
                    ),
                    indicieItenCorreto = 3
                ),
                QuestaoEntity(
                    enunciado = "O ENUNCIADO DA QUESTÃO FICARÁ AQUI",
                    arquivoLeituraEnunciado = "audio_teste",
                    textApoio = null,
                    arquivoLeituraTexto = null,
                    imagemApoio = null,
                    categoria = CategoriaQuestao.LINGUAGEM_ORAL,
                    itens = listOf(
                        OpcaoQuestao("A","hasbulla"),
                        OpcaoQuestao("B","hasbulla"),
                        OpcaoQuestao("C","hasbulla"),
                        OpcaoQuestao("D","hasbulla")
                    ),
                    indicieItenCorreto = 0
                ),
                QuestaoEntity(
                    enunciado = "O ENUNCIADO DA QUESTÃO FICARÁ AQUI",
                    arquivoLeituraEnunciado = "audio_teste",
                    textApoio = null,
                    arquivoLeituraTexto = null,
                    imagemApoio = null,
                    categoria = CategoriaQuestao.CONSCIENCIA_FONOLOGICA,
                    itens = listOf(
                        OpcaoQuestao("A","hasbulla"),
                        OpcaoQuestao("B","hasbulla"),
                        OpcaoQuestao("C","hasbulla"),
                        OpcaoQuestao("D","hasbulla")
                    ),
                    indicieItenCorreto = 0
                ),
                QuestaoEntity(
                    enunciado = "O ENUNCIADO DA QUESTÃO FICARÁ AQUI",
                    arquivoLeituraEnunciado = "audio_teste",
                    textApoio = null,
                    arquivoLeituraTexto = null,
                    imagemApoio = null,
                    categoria = CategoriaQuestao.CONSCIENCIA_FONOLOGICA,
                    itens = listOf(
                        OpcaoQuestao("A","hasbulla"),
                        OpcaoQuestao("B","hasbulla"),
                        OpcaoQuestao("C","hasbulla"),
                        OpcaoQuestao("D","hasbulla")
                    ),
                    indicieItenCorreto = 1
                ),
                QuestaoEntity(
                    enunciado = "O ENUNCIADO DA QUESTÃO FICARÁ AQUI",
                    arquivoLeituraEnunciado = "audio_teste",
                    textApoio = null,
                    arquivoLeituraTexto = null,
                    imagemApoio = null,
                    categoria = CategoriaQuestao.CONSCIENCIA_FONOLOGICA,
                    itens = listOf(
                        OpcaoQuestao("A","hasbulla"),
                        OpcaoQuestao("B","hasbulla"),
                        OpcaoQuestao("C","hasbulla"),
                        OpcaoQuestao("D","hasbulla")
                    ),
                    indicieItenCorreto = 2
                ),
                QuestaoEntity(
                    enunciado = "O ENUNCIADO DA QUESTÃO FICARÁ AQUI",
                    arquivoLeituraEnunciado = "audio_teste",
                    textApoio = null,
                    arquivoLeituraTexto = null,
                    imagemApoio = null,
                    categoria = CategoriaQuestao.CONSCIENCIA_FONOLOGICA,
                    itens = listOf(
                        OpcaoQuestao("A","hasbulla"),
                        OpcaoQuestao("B","hasbulla"),
                        OpcaoQuestao("C","hasbulla"),
                        OpcaoQuestao("D","hasbulla")
                    ),
                    indicieItenCorreto = 3
                ),
                QuestaoEntity(
                    enunciado = "O ENUNCIADO DA QUESTÃO FICARÁ AQUI",
                    arquivoLeituraEnunciado = "audio_teste",
                    textApoio = null,
                    arquivoLeituraTexto = null,
                    imagemApoio = null,
                    categoria = CategoriaQuestao.CONSCIENCIA_FONOLOGICA,
                    itens = listOf(
                        OpcaoQuestao("A","hasbulla"),
                        OpcaoQuestao("B","hasbulla"),
                        OpcaoQuestao("C","hasbulla"),
                        OpcaoQuestao("D","hasbulla")
                    ),
                    indicieItenCorreto = 0
                )
            )
            dao.inserirQuestoes(listaQuestoes)
        }
    }
}