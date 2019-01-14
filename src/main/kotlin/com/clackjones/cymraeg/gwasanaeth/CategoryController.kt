package com.clackjones.cymraeg.gwasanaeth

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categories")
class CategoryController(val categoriManager: CategoriManager) {
    @GetMapping
    fun getAllCategories(): List<Categori> {
        return categoriManager.findAll()
    }

    @PostMapping("/{categoryName}")
    fun addNewCategory(@PathVariable("categoryName") categoryName: String) {
        categoriManager.saveCategori(categoryName)
    }
}
