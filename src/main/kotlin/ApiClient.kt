import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

class ApiClient {
    val client = OkHttpClient()

    public fun sendRequest(nomePokemon: String): Pokemon? {
        val request = Request.Builder()
            .url("https://pokeapi.co/api/v2/pokemon/$nomePokemon")
            .build()

        try {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful)
                    println("Error: Pokemon non trovato (${response.code})")
                else {
                    val body = response.body?.string()

                    if (body != null)
                        return parsePokemon(body)
                    else
                        println("Errore: risposta vuota")
                }
            }
        } catch (e: Exception) {
            println("Errore nella richiesta: ${e.message}")
        }

        return null
    }

    private fun parsePokemon(jsonPokemon: String): Pokemon {
        return Gson().fromJson(jsonPokemon, Pokemon::class.java)
    }
}