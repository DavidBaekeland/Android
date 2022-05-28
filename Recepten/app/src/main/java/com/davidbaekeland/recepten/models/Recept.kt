package com.davidbaekeland.recepten.models

// https://stackoverflow.com/questions/38802269/firebase-user-is-missing-a-constructor-with-no-arguments
data class Recept(var id: Int = 0, val image: String = "", val title: String = "") {
}
