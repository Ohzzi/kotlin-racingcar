package domain

class Car(
    val name: Name
) {
    var position: Position = Position()
        private set

    constructor(name: String) : this(Name(name))

    fun move(generator: NumberGenerator) {
        if (generator.generate() >= MOVING_STANDARD) {
            position = position.increase()
        }
    }

    fun isLocatedAt(position: Position): Boolean = this.position == position

    companion object {
        private const val MOVING_STANDARD: Int = 4
    }
}
