package com.clackjones.cymraeg.gwasanaeth

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categories")
class CategoryController(val categoriManager: CategoriManager) {
    @GetMapping
    fun getAllCategories(): List<Categori> =
            categoriManager.findAll()

    @PostMapping("/{categoryName}")
    fun addNewCategory(@PathVariable("categoryName") categoryName: String) =
            categoriManager.saveCategori(categoryName)

    @DeleteMapping("/{categoryName}")
    fun removeCategory(@PathVariable("categoryName") categoryName: String) =
            categoriManager.removeCategori(categoryName)
}
