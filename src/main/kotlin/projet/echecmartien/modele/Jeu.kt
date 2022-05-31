package projet.echecmartien.modele

import projet.echecmartien.exeptions.DeplacementExeption


class Jeu : InterfaceJeu{
    private var nombreCoupsSansPrise : Int = 0
    private var nombreCoupsSansPriseMax : Int = 0
    private var coordOrigine : Coordonnee? = null
    private var coordDest : Coordonnee? = null
    private var joueurs : Array<Joueur> = arrayOf()
    private var joueurCourant : Joueur? = null
    private var plateau : Plateau = Plateau()
    private var pionArriveDeZone : Pion? = null

    /**
     * getter
     * @return la coordonnée origine du déplacement
     */
    fun getCoordOrigineDeplacement(): Coordonnee?{
       return coordOrigine
    }
    /**
     * getter
     * @return la coordonnée destination du déplacement
     */
    fun getCoordDestinationDeplacement(): Coordonnee?{
        return coordDest
    }


    /**
     * setter
     * @param origine la coordonnée origine du déplacement
     */
    fun setCoordOrigineDeplacement(origine: Coordonnee){
       coordOrigine=origine
    }


    /**
     * setter
     * @param destination la coordonnée destination du déplacement
     */
    fun setCoordDestinationDeplacement(destination: Coordonnee){
        coordDest=destination
    }


    /** retourne le joueur courant
     * @return le joueur courant
     */
    fun getJoueurCourant(): Joueur? {
       return joueurCourant
    }


/**
     * affectation des joueurs aux cases
     * @param joueur1 premier joueur
     * @paral joueur2 second joueur
     */
    private fun initialiserJoueur(joueur1: Joueur, joueur2: Joueur) {
        for (i in 0 until plateau.getTailleVerticale()){
            for (j in 0 until plateau.getTailleHorizontale()){
                if (i<4){
                    plateau.getCases()[j][i].setJoueur(joueur2)
                }
                else{
                    plateau.getCases()[j][i].setJoueur(joueur1)
                }
            }
        }
    }

    /**
     * permet de savoir si la partie est finie ou non
     * @return true si la partie est finie, false sinon
     */
    fun arretPartie(): Boolean {
        var cptj1 = 0
        var cptj2 = 0
        for (i in 0 until plateau.getTailleVerticale()){
            for (j in 0 until plateau.getTailleHorizontale()){
                if (i<4 && plateau.getCases()[i][j].getPion()!=null){
                    cptj2++
                }
                if (i>=4 && plateau.getCases()[i][j].getPion()!=null) {
                    cptj1++
                }
            }
        }
        if (cptj1==0 || cptj2==0)
            return true
       return nombreCoupsSansPriseMax==nombreCoupsSansPrise
    }

    /**
     * modifie le joueur courant
     *
     */
    fun changeJoueurCourant() {
        if (joueurCourant==joueurs[0]){
            joueurCourant=joueurs[1]
        }
        else{
            joueurCourant=joueurs[0]
        }
    }

    override fun initialiserPartie(joueur1: Joueur, joueur2: Joueur, nombreCoupsSansPriseMax: Int) {
        initialiserJoueur(joueur1,joueur2)
        joueurs= arrayOf(joueur1,joueur2)
        joueurCourant=joueur1
        plateau.initialiser()
        this.nombreCoupsSansPriseMax=nombreCoupsSansPriseMax
    }


    override fun joueurVainqueur(): Joueur? {
        if (arretPartie()){
            if (joueurs[0].calculerScore()>joueurs[1].calculerScore())
                return joueurs[0]
            if (joueurs[0].calculerScore()<joueurs[1].calculerScore())
                return joueurs[1]
        }
        return null
    }

    override fun deplacementPossible(coordOrigineX: Int, coordOrigineY: Int): Boolean {
        if (coordOrigineX<0 || coordOrigineX >= plateau.getTailleHorizontale() || coordOrigineY<0 || coordOrigineY>= plateau.getTailleVerticale())
            return false
        val c = plateau.getCases()[coordOrigineX][coordOrigineY]
        if (c.getJoueur()!=joueurCourant)
            return false
        if (c.getPion() != null){ // test de tous les Pions
            if (coordOrigineX+1 < plateau.getTailleHorizontale() && coordOrigineY+1 < plateau.getTailleVerticale()){
                if (plateau.getCases()[coordOrigineX+1][coordOrigineY+1].estLibre())
                    return true
                if (plateau.getCases()[coordOrigineX+1][coordOrigineY+1].getJoueur()!=joueurCourant && c.getPion()!=pionArriveDeZone)
                    return true
            }
            if (coordOrigineX+1 < plateau.getTailleHorizontale() && coordOrigineY-1>=0){
                if (plateau.getCases()[coordOrigineX+1][coordOrigineY-1].estLibre())
                    return true
                if (plateau.getCases()[coordOrigineX+1][coordOrigineY-1].getJoueur()!=joueurCourant && c.getPion()!=pionArriveDeZone)
                    return true
            }
            if (coordOrigineX-1 >= 0 && coordOrigineY+1 < plateau.getTailleVerticale()){
                if (plateau.getCases()[coordOrigineX-1][coordOrigineY+1].estLibre())
                    return true
                if (plateau.getCases()[coordOrigineX-1][coordOrigineY+1].getJoueur()!=joueurCourant && c.getPion()!=pionArriveDeZone)
                    return true
            }
            if (coordOrigineX-1 >=0 && coordOrigineY-1 >= 0){
                if (plateau.getCases()[coordOrigineX-1][coordOrigineY-1].estLibre())
                    return true
                if (plateau.getCases()[coordOrigineX-1][coordOrigineY-1].getJoueur()!=joueurCourant && c.getPion()!=pionArriveDeZone)
                    return true
            }
        }

        if (c.getPion() is GrandPion || c.getPion() is MoyenPion){ //test du Moyen et Grand Pion
            if (coordOrigineX+1 < plateau.getTailleHorizontale()){
                if (plateau.getCases()[coordOrigineX+1][coordOrigineY].estLibre())
                    return true
                if (plateau.getCases()[coordOrigineX+1][coordOrigineY].getJoueur()!=joueurCourant && c.getPion()!=pionArriveDeZone)
                    return true
            }
            if (coordOrigineX-1 >=0){
                if (plateau.getCases()[coordOrigineX-1][coordOrigineY].estLibre())
                    return true
                if (plateau.getCases()[coordOrigineX-1][coordOrigineY].getJoueur()!=joueurCourant && c.getPion()!=pionArriveDeZone)
                    return true
            }
            if (coordOrigineY+1 < plateau.getTailleVerticale()){
                if (plateau.getCases()[coordOrigineX][coordOrigineY+1].estLibre())
                    return true
                if (plateau.getCases()[coordOrigineX][coordOrigineY+1].getJoueur()!=joueurCourant && c.getPion()!=pionArriveDeZone)
                    return true
            }
            if (coordOrigineY-1 >=0){
                if (plateau.getCases()[coordOrigineX][coordOrigineY-1].estLibre())
                    return true
                if (plateau.getCases()[coordOrigineX][coordOrigineY-1].getJoueur()!=joueurCourant && c.getPion()!=pionArriveDeZone)
                    return true
            }
        }
        return false
    }

    override fun deplacementPossible(
        coordOrigineX: Int,
        coordOrigineY: Int,
        coordDestinationX: Int,
        coordDestinationY: Int,
        joueur: Joueur?
    ): Boolean {
        if (!deplacementPossible(coordOrigineX,coordOrigineY))
            return false
        if (joueur!=joueurCourant)
            return false
        val d = Deplacement(Coordonnee(coordDestinationX,coordDestinationY),
            Coordonnee(coordDestinationX,coordDestinationY)
        )
        val co = plateau.getCases()[coordOrigineX][coordOrigineY]
        val cd = plateau.getCases()[coordDestinationX][coordDestinationY]
        if (co.getJoueur()!=cd.getJoueur() && co.getPion()==pionArriveDeZone)
            return false
        val chemin : List<Coordonnee>
        try {
            chemin = co.getPion()!!.getDeplacement(d)
        }
        catch (e : DeplacementExeption){
            return false
        }
        for (i in 0 until chemin.size-1){
            if (!plateau.getCases()[chemin[i].getX()][chemin[i].getY()].estLibre())
                return false
        }
        if (cd.getJoueur()==joueurCourant && !cd.estLibre())
            return false
        return true
    }


    override fun deplacer(coordOrigineX: Int, coordOrigineY: Int, coordDestinationX: Int, coordDestinationY: Int) {
        val co = plateau.getCases()[coordOrigineX][coordOrigineY]
        val cd = plateau.getCases()[coordDestinationX][coordDestinationY]
        if (deplacementPossible(coordOrigineX,coordOrigineY,coordDestinationX,coordDestinationY,joueurCourant)){
            nombreCoupsSansPrise += 1
            if (!cd.estLibre() && cd.getJoueur()!=joueurCourant) {
                joueurCourant!!.ajouterPionCaptures(cd.getPion()!!)
                nombreCoupsSansPrise = 0
            }
            if (cd.getJoueur() != co.getJoueur()){
                pionArriveDeZone=co.getPion()
            }
            plateau.getCases()[coordDestinationX][coordDestinationY].setPion(co.getPion())
            plateau.getCases()[coordOrigineX][coordOrigineY].setPion(null)
        }
    }

    override fun toString(): String {
        return plateau.toString()
    }




}