package com.clackjones.cymraeg.gwasanaeth

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categories")
class CategoryController(val categoriManager: CategoriManager) {
    @GetMapping
    fun getAllCategories(): List<Categori> =
            categoriManager.findAll()

    @PreAuthorize("hasAuthority('ADD_REMOVE_CATEGORY')")
    @PostMapping("/{categoryName}")
    fun addNewCategory(@PathVariable("categoryName") categoryName: String) =
            categoriManager.saveCategori(categoryName)

    @PreAuthorize("hasAuthority('ADD_REMOVE_CATEGORY')")
    @DeleteMapping("/{categoryName}")
    fun removeCategory(@PathVariable("categoryName") categoryName: String) =
            categoriManager.removeCategori(categoryName)
}
