package projet.echecmartien.modele

import projet.echecmartien.exeptions.DeplacementExeption


open class GrandPion : Pion() {

    override fun getScore(): Int {
        return 3
    }

    override fun getDeplacement(deplacement: Deplacement): List<Coordonnee> {
        if (deplacement.estVertical())
            return deplacement.getCheminVertical()
        if (deplacement.estHorizontal())
            return deplacement.getCheminHorizontal()
        if (deplacement.estDiagonal())
            return deplacement.getCheminDiagonal()
        throw DeplacementExeption("Déplacement illégal")
    }

}