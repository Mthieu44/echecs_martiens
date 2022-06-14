package projet.echecmartien.modele

import projet.echecmartien.exeptions.DeplacementExeption


class MoyenPion : GrandPion() {

    override fun getScore(): Int {
        return 2
    }

    override fun getDeplacement(deplacement: Deplacement): List<Coordonnee> {
        if (deplacement.longueur() > 2)
            throw DeplacementExeption("Déplacement illégal")
        return super.getDeplacement(deplacement)
    }

}