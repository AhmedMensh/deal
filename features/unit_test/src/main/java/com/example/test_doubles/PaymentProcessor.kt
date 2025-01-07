package com.example.test_doubles


interface PaymentService{
    fun processPayment(amount: Double) : Boolean
}
class PaymentProcessor(val paymentService: PaymentService)  {

    fun pay(amount: Double) : Boolean{
       val result =  paymentService.processPayment(amount)
        println("Payment result is $result")
        return result
    }

}

