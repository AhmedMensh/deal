package com.example.test_doubles


interface UserService{
    fun getUsersCount() : Int
}

class RealUserService : UserService{
    override fun getUsersCount() = 20
}
class PremiumUsersManger(val userService: UserService) {

    fun getUsersCount() = userService.getUsersCount()
}