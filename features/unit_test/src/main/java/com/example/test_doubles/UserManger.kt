package com.example.test_doubles

class UserManger(val logger: Logger) {
    val users = mutableListOf<User>()
    fun addUser(user: User) {
        users.add(user)
    }

}
data class User(val name : String)