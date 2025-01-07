package com.example.test_doubles


interface Database {
    fun save(data: String)
    fun get(): String

}

class InMemoryDatabase : Database {
    var dataHolder : String = ""
    override fun save(data: String) {
        dataHolder = data
    }

    override fun get(): String {
        return dataHolder
    }

}
class DatabaseManger(val database: Database)  {
    fun save(data: String){
        database.save(data)
    }
    fun get() = database.get()
}