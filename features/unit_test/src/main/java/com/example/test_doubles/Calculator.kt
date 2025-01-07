package com.example.test_doubles

data class Dependency1(val value: Int)
data class Dependency2(val value: Int)
class Calculator(val dependency1: Dependency1, val dependency2: Dependency2) {
    fun add() = dependency1.value + dependency2.value
    fun subtract() = dependency1.value - dependency2.value
}

class MathService{
    fun add(a: Int, b: Int) = a + b
}

class Calculator2(val mathService: MathService) {
    fun add(a: Int, b: Int) = mathService.add(a, b)

}