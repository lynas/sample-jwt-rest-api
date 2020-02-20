package com.lynas.springsecuritywithjwtdemo.controller

import com.lynas.springsecuritywithjwtdemo.dto.BookDTO
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/public")
class PublicController {

    @GetMapping
    fun public() = "Public Data"

    @GetMapping("/{bookId}")
    fun getOneBook(@PathVariable bookId: Long) = BookDTO(bookId, "Book $bookId")

    @PostMapping
    fun createANewBook(@RequestBody bookDTO: BookDTO) = bookDTO


}