package projet.echecmartien.modele

import projet.echecmartien.exeptions.DeplacementExeption

class PetitPion : Pion() {

    override fun getScore(): Int {
        return 1
    }

    override fun getDeplacement(deplacement: Deplacement): List<Coordonnee> {
        if (!deplacement.estDiagonal() || deplacement.longueur() != 1)
            throw DeplacementExeption("Déplacement illégal")
        return deplacement.getCheminDiagonal()
    }

}