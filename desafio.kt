enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

class Usuario(val nome: String, val email: String) {
    override fun toString(): String = "$nome - $email"
}

data class ConteudoEducacional(val nome: String, val duracao: Int = 60) {
    override fun toString(): String = "$nome - Duração: $duracao minutos"
}

class Formacao(val nome: String, val nivel: Nivel, val conteudos: List<ConteudoEducacional>) {

    private val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {
        if (inscritos.none { it.email == usuario.email }) {
            inscritos.add(usuario)
            println("Matrícula realizada com sucesso para ${usuario.nome}.")
        } else {
            println("${usuario.nome} já está matriculado nesta formação.")
        }
    }

    fun desmatricular(usuario: Usuario) {
        if (inscritos.remove(usuario)) {
            println("${usuario.nome} foi desmatriculado da formação.")
        } else {
            println("${usuario.nome} não está matriculado nesta formação.")
        }
    }

    fun listarInscritos() {
        println("Alunos inscritos na formação:")
        inscritos.forEachIndexed { index, aluno ->
            println("${index + 1}. $aluno")
        }
    }

    override fun toString(): String = "$nome - Nível: $nivel\nConteúdos:\n${conteudos.joinToString("\n")}"
}

fun main() {
    val conteudo1 = ConteudoEducacional("Introdução ao Kotlin", 90)
    val conteudo2 = ConteudoEducacional("Programação Orientada a Objetos em Kotlin")

    val formacao = Formacao("Formação Kotlin Avançado", Nivel.INTERMEDIARIO, listOf(conteudo1, conteudo2))

    val aluno1 = Usuario("João", "joao@example.com")
    val aluno2 = Usuario("Maria", "maria@example.com")

    formacao.matricular(aluno1)
    formacao.matricular(aluno2)

    println(formacao)

    formacao.listarInscritos()

    formacao.desmatricular(aluno1)
    formacao.listarInscritos()
}
