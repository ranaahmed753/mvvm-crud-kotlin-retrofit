package com.codecamp.mvvm_retrofit_crud.api

data class Person(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)