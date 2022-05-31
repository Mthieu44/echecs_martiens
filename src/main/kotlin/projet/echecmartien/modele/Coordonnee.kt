package projet.echecmartien.modele

class Coordonnee(x : Int, y : Int) {
    private val x : Int
    private val y : Int

    init {
        this.x=x
        this.y=y
    }

    /**
     *@return la coordonnée en x
     */
    fun getX(): Int{
        return x
    }


    /**
     *@return la coordonnée en y
     */
    fun getY(): Int{
       return y
    }


    override fun toString():String{
       return "($x,$y)"
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Coordonnee)
            return false
        return x==other.x && y==other.y
    }

}