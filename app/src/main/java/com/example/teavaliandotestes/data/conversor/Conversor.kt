package com.example.teavaliandotestes.data.conversor

import androidx.room.TypeConverter
import com.example.teavaliandotestes.data.dataclass.OpcaoQuestao
import com.example.teavaliandotestes.domain.enums.CategoriaQuestao
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDate


class Conversor(){

    val gson = Gson()

@TypeConverter
    fun deData(valor: LocalDate): String{
        return valor.toString()
    }
    @TypeConverter
    fun paraData(valor:String): LocalDate{
        return LocalDate.parse(valor)
    }
    @TypeConverter
    fun deCategoria(valor: CategoriaQuestao): String{
        return valor.name
    }
    @TypeConverter
    fun paraCategoria(valor:String): CategoriaQuestao{
        return CategoriaQuestao.valueOf(valor)
    }
    @TypeConverter
    fun paraJson(valor: List<OpcaoQuestao>):String{
        return gson.toJson(valor)
    }
    @TypeConverter
    fun deJson(valor:String):List<OpcaoQuestao>{
        return gson.fromJson(valor,object:TypeToken<List<OpcaoQuestao>>(){}.type)
    }

}