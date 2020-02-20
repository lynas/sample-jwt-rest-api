package com.lynas.springsecuritywithjwtdemo.controller

import com.lynas.springsecuritywithjwtdemo.dto.AuthorDTO
import com.lynas.springsecuritywithjwtdemo.dto.BookDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/protected")
class ProtectedController {

    @GetMapping
    fun protected() = "Protected Data"

    @GetMapping("/{authorId}")
    fun getOneAuthor(@PathVariable authorId: Long) = AuthorDTO(authorId, "Author $authorId")

}