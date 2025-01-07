package com.example.test_doubles

class UserManger(val logger: Logger) {
    val users = mutableListOf<User>()
    fun addUser(user: User) {
        users.add(user)
        logger.log("User added: ${user.name}")
    }

}
data class User(val name : String)