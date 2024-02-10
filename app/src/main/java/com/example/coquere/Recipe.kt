package com.example.coquere

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
class Recipe(var img:String,

    var tittle:String,
    var des:String,
    var ing:String,
    var catergory:String
) {
    @JvmField
    @PrimaryKey(autoGenerate = true)
    var uid=0

}