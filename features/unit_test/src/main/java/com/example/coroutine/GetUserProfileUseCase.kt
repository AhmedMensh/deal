package com.example.coroutine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GetUserProfileUseCase(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getProfileDataAsync() = withContext(dispatcher) {
        val name = async { userRepository.getName() }
        val rate = async { userRepository.getRate() }
        val friends = async { userRepository.getFriends() }
        Profile(name.await(), rate.await(), friends.await())

    }

    suspend fun getProfileDataSync() = withContext(dispatcher) {
        val name = userRepository.getName()
        val rate = userRepository.getRate()
        val friends = userRepository.getFriends()
        Profile(name, rate, friends)
    }
}

interface UserRepository {
    suspend fun getName(): String
    suspend fun getFriends(): List<Friend>
    suspend fun getRate(): Float
}

class FakeUserRepository : UserRepository {
    override suspend fun getName(): String {
        return "Ahmed"
    }

    override suspend fun getFriends(): List<Friend> {
        return listOf(Friend("1", "Ahmed"), Friend("2", "Mensh"))
    }

    override suspend fun getRate(): Float {
        return 2.2f
    }

}
class FakeUserRepository2 : UserRepository {
    override suspend fun getName(): String {
        delay(1000)
        return "Ahmed"
    }

    override suspend fun getFriends(): List<Friend> {
        return listOf(Friend("1", "Ahmed"), Friend("2", "Mensh"))
    }

    override suspend fun getRate(): Float {
        return 2.2f
    }

}

data class Profile(val name: String, val rate: Float, val friends: List<Friend>)
data class Friend(val id: String, val name: String)
