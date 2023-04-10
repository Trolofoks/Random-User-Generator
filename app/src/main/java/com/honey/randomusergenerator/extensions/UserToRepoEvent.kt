package com.honey.randomusergenerator.extensions

import com.honey.data.saved.SavedRepository
import com.honey.randomusergenerator.data.model.User

suspend fun User.saveToRepo(savRepo: SavedRepository): Boolean{
    return savRepo.saveUser(toDataUser())
}

suspend fun User.removeFromRepo(savRepo: SavedRepository): Boolean{
    return savRepo.deleteUser(toDataUser())
}