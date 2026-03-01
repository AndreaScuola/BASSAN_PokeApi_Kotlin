package org.example

import com.google.gson.Gson

fun main() {
    var nomePokemon = ""
    var continuare = true

    do{
        print("Inserisci il nome del pokemon da cercare: ")
        nomePokemon = readln()

        val jsonString = """{"id": 1, "name": "ditto", "height": "1", "weight": "20", "types": [
    {
      "slot": 1,
      "type": {
        "name": "normal",
        "url": "https://pokeapi.co/api/v2/type/1/"
      }
    }
  ]}"""
        //Parsing
        val pokemon = Gson().fromJson(jsonString, Pokemon::class.java)
        println(pokemon.toString())


        print("\nContinuare  (Y/y): ")
        val strContinuare = readln()
        if(strContinuare != "Y" && strContinuare != "y")
            continuare = false
    }while (continuare)
}