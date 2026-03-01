data class Pokemon(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<TypeSlot>
) {
    data class TypeSlot(
        val slot: Int,
        val type: TypeInfo
    )

    data class TypeInfo(
        val name: String,
        val url: String
    )

    val typeNames: List<String>
        get() = types.map { it.type.name }

    override fun toString(): String {
        return """
            ID: $id
            Nome: $name
            Altezza: $height
            Peso: $weight
            Tipi: ${typeNames.joinToString(", ")}
        """.trimIndent()
    }
}