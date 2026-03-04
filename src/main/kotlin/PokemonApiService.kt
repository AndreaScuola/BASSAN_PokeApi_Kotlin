interface PokemonApiService {
    fun sendRequest(nomePokemon: String): Pokemon?
}