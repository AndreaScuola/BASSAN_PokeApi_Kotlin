package org.example

import ApiClient
import com.google.gson.Gson

fun main() {
    var nomePokemon = ""
    var continuare = true

    do{
        print("Inserisci il nome del pokemon da cercare: ")
        nomePokemon = readln().lowercase()

        //Chiamata Api + parse
        val pokemon = ApiClient().sendRequest(nomePokemon)

        if (pokemon != null) {
            println(pokemon)


        } else
            println("Pokémon non trovato")

        print("\nContinuare  (Y/y): ")
        val strContinuare = readln()
        if(strContinuare != "Y" && strContinuare != "y")
            continuare = false
    }while (continuare)
}