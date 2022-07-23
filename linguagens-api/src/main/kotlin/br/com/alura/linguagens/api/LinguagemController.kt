package br.com.alura.linguagens.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LinguagemController {

    @Autowired
    private val repositorio: LinguagemRepository? = null

    @GetMapping("/linguagens")
    fun obterLinguagens(): List<Linguagem?>? {
        return repositorio?.findAll()
    }

    @PostMapping("/linguagens")
    fun cadastrarLinguagem(@RequestBody linguagem: Linguagem): Linguagem? {
        return repositorio?.save(linguagem)
    }
}