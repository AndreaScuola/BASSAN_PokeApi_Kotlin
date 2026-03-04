package org.example

import ApiClient

fun main() {
    var nomePokemon = ""
    var continuare = true

    do{
        print("Inserisci il nome del pokemon da cercare: ")
        nomePokemon = readln().lowercase()

        //Cerca nel db il pokemon
        val dbResponse = Database.getPokemon(nomePokemon)

        if(dbResponse == null){
            //Chiamata Api + parse
            val pokemon = ApiClient().sendRequest(nomePokemon)

            if (pokemon != null) {
                println(pokemon)

                //Aggiunge il pokemon al DB locale
                Database.insertPokemon(pokemon)
            } else
                println("Pokémon non trovato")
        } else {
            //Pokemon presente nel DB locale
            println(dbResponse)
        }

        print("\nContinuare  (Y/y): ")
        val strContinuare = readln()
        if(strContinuare != "Y" && strContinuare != "y")
            continuare = false
    }while (continuare)
}