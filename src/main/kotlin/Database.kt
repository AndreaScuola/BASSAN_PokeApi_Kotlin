package org.example

import Pokemon
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class Database private constructor() {

    private var connection: Connection? = null

    init {
        try {
            val url = "jdbc:sqlite:Database/PokeDb.db"
            connection = DriverManager.getConnection(url)
            println("Connesso al db locale")
        } catch (e: SQLException) {
            System.err.println("Errore connessione: ${e.message}")
        }
    }

    companion object {
        private var instance: Database? = null

        fun getInstance(): Database {
            if (instance == null) {
                instance = Database()
            }
            return instance!!
        }
    }

    fun insertPokemon(pokemon: Pokemon) {

        val insertPokemon = """
            INSERT INTO pokemon 
            (name, height, weight, types)
            VALUES (?, ?, ?, ?)
        """

        try {
            val stmt = connection?.prepareStatement(insertPokemon)
            stmt?.setString(1, pokemon.name)
            stmt?.setInt(2, pokemon.height)
            stmt?.setInt(3, pokemon.weight)
            stmt?.setString(4, pokemon.typeNames.joinToString(","))
            stmt?.executeUpdate()

            println("Pokémon salvato nel DB")
        } catch (e: Exception) {
            println("Errore insertPokemon: ${e.message}")
        }
    }

    fun getPokemon(nomePokemon: String): Pokemon? {
        val query = "SELECT * FROM pokemon WHERE name = ?"

        try {
            val stmt: PreparedStatement? = connection?.prepareStatement(query)
            stmt?.setString(1, nomePokemon)

            val rs: ResultSet? = stmt?.executeQuery()

            if (rs != null && rs.next()) {
                val id = rs.getInt("id")
                val name = rs.getString("name")
                val height = rs.getInt("height")
                val weight = rs.getInt("weight")
                val typesString = rs.getString("types")

                //Ricostruisce la lista TypeSlot dalla stringa
                val typeList = typesString
                    .split(",")
                    .mapIndexed { index, typeName ->
                        Pokemon.TypeSlot(
                            slot = index + 1,
                            type = Pokemon.TypeInfo(typeName.trim(), "")
                        )
                    }

                return Pokemon(id, name, height, weight, typeList)
            }
        } catch (e: SQLException) {
            System.err.println("Errore getPokemon: ${e.message}")
        }

        return null
    }
}