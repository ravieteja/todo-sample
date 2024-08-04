package com.sample.todolist.utils.extensions

fun String.Capitalize(): String {
    return this[0].uppercase() + this.substring(1).lowercase()
}