package br.com.alura.linguagens.api

import org.springframework.data.mongodb.repository.MongoRepository


interface LinguagemRepository : MongoRepository<Linguagem, String?>