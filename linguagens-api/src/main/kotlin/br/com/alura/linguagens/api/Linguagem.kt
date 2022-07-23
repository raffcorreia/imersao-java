package br.com.alura.linguagens.api

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "principaislinguagens")
data class Linguagem(
    @Id
    val id: String?,
    var title: String,
    var image: String,
    var ranking: Int
){

}

//    constructor() {}
//    constructor(title: String?, image: String?, ranking: Int) {
//        this.title = title
//        this.image = image
//        this.ranking = ranking
//    }
//}