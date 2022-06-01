package signature

fun main() {


    print("Enter name and surname: ")
    val name = readln()
    print("Enter person's status: ")
    val status = readln()

    val frame = Frame(name, status)
    frame.print()

}
