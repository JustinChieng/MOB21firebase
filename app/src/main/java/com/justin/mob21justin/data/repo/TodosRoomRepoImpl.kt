//package com.justin.mob21justin.data.repo
//
//
//import com.justin.mob21justin.data.db.TodoDao
//import com.justin.mob21justin.data.model.Todo
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.map
//
//class TodosRoomRepoImpl(
//    private val dao: TodoDao
//): TodosRepo {
//    override fun getAllTodos(): Flow<List<Todo>> {
//        return dao.getAllTodos().map {
//            it.map { todo -> todo.copy(id = todo._id.toString()) }
//        }
//    }
//
//    override suspend fun insert(todo: Todo) {
//        dao.insert(todo)
//    }
//
//    override suspend fun getTodo(id: String): Todo? {
//        val todo = dao.getById(id.toInt())
//        return todo?.copy(id = todo._id.toString())
//    }
//
//    override suspend fun update(id:String, todo: Todo){
//        dao.insert(todo.copy(_id = id.toInt()))
//    }
//
//    override suspend fun delete(id: String) {
//        dao.delete(id.toInt())
//    }
//
//    override suspend fun storeUserName(id: String, name: String) {
//        TODO("Not yet implemented")
//    }
//
//}