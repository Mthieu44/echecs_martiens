package projet.echecmartien.controleur

import javafx.event.EventHandler
import javafx.scene.input.MouseEvent
import projet.echecmartien.modele.*
import projet.echecmartien.vue.VueCompteurPoints
import projet.echecmartien.vue.VuePlateau
import kotlin.random.Random

class ControleurClicCase(modele: Jeu, vueP: VuePlateau, vueG : VueCompteurPoints) : EventHandler<MouseEvent> {
    val modele: Jeu
    val vueDuPlateau : VuePlateau
    val vueDeGauche : VueCompteurPoints

    init {
        this.vueDeGauche = vueG
        this.modele = modele
        this.vueDuPlateau = vueP
    }

    var stop = false

    override fun handle(event: MouseEvent) {
        val x = (event.x / vueDuPlateau.bx).toInt()
        val y: Int
        if (event.y > 4 * vueDuPlateau.by) {
            y = ((event.y - vueDuPlateau.pane.minHeight) / vueDuPlateau.by).toInt()
        } else {
            y = (event.y / vueDuPlateau.by).toInt()
        }
        if (x<0 || x>3 || y<0 || y>7 || stop) {
            return
        }

        if (modele.getCoordOrigineDeplacement() == Coordonnee(x, y)) {
            modele.setCoordOrigineDeplacement(null)
            enleveCouleur()
            return
        }
        if (modele.getCoordOrigineDeplacement() == null || (modele.getPlateau().getCases()[x][y].getJoueur() == modele.getJoueurCourant() && !modele.getPlateau().getCases()[x][y].estLibre())) {
            modele.setCoordOrigineDeplacement(Coordonnee(x, y))
            deplPossible(x, y)
            return
        }
        if (modele.getCoordOrigineDeplacement() != Coordonnee(x, y)) {
            val xO = modele.getCoordOrigineDeplacement()!!.getX()
            val yO = modele.getCoordOrigineDeplacement()!!.getY()
            if (modele.deplacementPossible(xO, yO, x, y, modele.getJoueurCourant())) {
                vueDuPlateau.tableauCase[y][x].placerPion(modele.getPlateau().getCases()[xO][yO].getPion().toString())
                vueDuPlateau.tableauCase[yO][xO].retirerPion()
                modele.deplacer(xO, yO, x, y)
                score()
                vueDeGauche.texteAQuiDeJouer.text = "C'est au tour de\n@${modele.getJoueurCourant()!!.getPseudo()}\nde jouer !"
                if (modele.arretPartie()){
                    vueDeGauche.texteAQuiDeJouer.text = texteDeFin()
                    stop = true
                }
                if (vueDuPlateau.bot) {
                    tourDuBot()
                    score()
                    vueDeGauche.texteAQuiDeJouer.text = "C'est au tour de\n@${modele.getJoueurCourant()!!.getPseudo()}\nde jouer !"
                    if (modele.arretPartie()){
                        vueDeGauche.texteAQuiDeJouer.text = texteDeFin()
                        stop = true
                    }
                }

            }
        }
        modele.setCoordOrigineDeplacement(null)
        enleveCouleur()
    }

    private fun texteDeFin():String{
        val vainqueur = modele.joueurVainqueur()
        val affichage : String
        if (vainqueur==null) {
            affichage = "Il y a égalite, chaque\njoueur à ${modele.getJoueurCourant()!!.calculerScore()} points !"
        }
        else {
            val j1 : Joueur = modele.getJoueurCourant()!!
            modele.changeJoueurCourant()
            val j2 : Joueur = modele.getJoueurCourant()!!
            if (j1.calculerScore() > j2.calculerScore()){
                affichage = "@$j1\na gagné contre \n@$j2\nà ${j1.calculerScore()}-${j2.calculerScore()} !"
            }
            else{
                affichage = "@$j2\na gagné contre \n@$j1\nà ${j2.calculerScore()}-${j1.calculerScore()} !"
            }
        }
        return affichage
    }

    private fun comptePionCapture(joueur: Joueur, typePion: Pion) : Int{
        var c = 0
        for (p in joueur.getPionsCaptures()){
            if (p == typePion){
                c += 1
            }
        }
        return c
    }

    private fun deplPossible(x: Int, y: Int) {
        for (i in 0 until 4) {
            for (j in 0 until 8) {
                vueDuPlateau.tableauCase[j][i].changeCouleur("None")
                if (modele.deplacementPossible(x, y, i, j, modele.getJoueurCourant())) {
                    if (modele.getPlateau().getCases()[i][j].estLibre()) {
                        vueDuPlateau.tableauCase[j][i].changeCouleur("#A5E3F6")
                    } else {
                        vueDuPlateau.tableauCase[j][i].changeCouleur("#FF6961")
                    }
                }
            }
        }
        if (!modele.getPlateau().getCases()[x][y].estLibre() && modele.getPlateau().getCases()[x][y].getJoueur() == modele.getJoueurCourant())
            vueDuPlateau.tableauCase[y][x].changeCouleur("#B0F2B6")
    }

    private fun enleveCouleur() {
        for (i in 0 until 4) {
            for (j in 0 until 8) {
                vueDuPlateau.tableauCase[j][i].changeCouleur("None")
            }
        }
    }

    private fun score() {
        vueDeGauche.compteJoueur1 = modele.getJoueurs()[0].calculerScore()
        vueDeGauche.compteJoueur2 = modele.getJoueurs()[1].calculerScore()
        vueDeGauche.compteGrandPionJoueur1 = comptePionCapture(modele.getJoueurs()[0], GrandPion())
        vueDeGauche.compteMoyenPionJoueur1 = comptePionCapture(modele.getJoueurs()[0], MoyenPion())
        vueDeGauche.comptePetitPionJoueur1 = comptePionCapture(modele.getJoueurs()[0], PetitPion())
        vueDeGauche.compteGrandPionJoueur2 = comptePionCapture(modele.getJoueurs()[1], GrandPion())
        vueDeGauche.compteMoyenPionJoueur2 = comptePionCapture(modele.getJoueurs()[1], MoyenPion())
        vueDeGauche.comptePetitPionJoueur2 = comptePionCapture(modele.getJoueurs()[1], PetitPion())
        vueDeGauche.updateScore()
    }

    private fun tourDuBot(){
        var ox = -1
        var oy = -1
        var dx = -1
        var dy = -1
        var gain = 0
        var lost = 1
        var g : Int
        var l : Int
        var defaultX = -1
        var defaultY = -1

        for (i in 0 until 4){
            for (j in 0 until 4){
                if (!modele.getPlateau().getCases()[i][j].estLibre() && modele.deplacementPossible(i, j)){
                    if (defaultY == -1){
                        defaultX = i
                        defaultY = j
                    }
                    if (modele.getPlateau().getCases()[i][j].getPion()!!.getScore() < modele.getPlateau().getCases()[defaultX][defaultY].getPion()!!.getScore()){
                        defaultX = i
                        defaultY = j
                    }
                    for (x in 0 until 4){
                        for (y in 4 until 8){
                            if (modele.deplacementPossible(i, j, x, y, modele.getJoueurCourant())){
                                if (modele.getPlateau().getCases()[x][y].estLibre()){
                                    g = 0
                                }else{
                                    g = modele.getPlateau().getCases()[x][y].getPion()!!.getScore()
                                }
                                l = modele.getPlateau().getCases()[i][j].getPion()!!.getScore()
                                if (g/l > gain/lost || (g/l == gain/lost && g > gain)){
                                    gain = g
                                    lost = l
                                    ox = i
                                    oy = j
                                    dx = x
                                    dy = y
                                }
                            }
                        }
                    }
                }
            }
        }
        if (ox == -1){
            ox = defaultX
            oy = defaultY
            if (modele.deplacementPossible(ox, oy, ox+1, oy+1, modele.getJoueurCourant())){
                dx = ox + 1
                dy = oy + 1
            }else if (modele.deplacementPossible(ox, oy, ox-1, oy+1, modele.getJoueurCourant())){
                dx = ox - 1
                dy = oy + 1
            }else if (modele.deplacementPossible(ox, oy, ox+1, oy-1, modele.getJoueurCourant())){
                dx = ox + 1
                dy = oy - 1
            }else if (modele.deplacementPossible(ox, oy, ox-1, oy-1, modele.getJoueurCourant())){
                dx = ox - 1
                dy = oy - 1
            }else if (modele.deplacementPossible(ox, oy, ox, oy+1, modele.getJoueurCourant())){
                dx = ox
                dy = oy + 1
            }else if (modele.deplacementPossible(ox, oy, ox+1, oy, modele.getJoueurCourant())){
                dx = ox + 1
                dy = oy
            }else if (modele.deplacementPossible(ox, oy, ox-1, oy, modele.getJoueurCourant())){
                dx = ox - 1
                dy = oy
            }else if (modele.deplacementPossible(ox, oy, ox, oy-1, modele.getJoueurCourant())){
                dx = ox
                dy = oy - 1
            }else{
                println("Y'a un problème dans la partie, mais on sait pas où")
            }
        }
        vueDuPlateau.tableauCase[dy][dx].placerPion(modele.getPlateau().getCases()[ox][oy].getPion().toString())
        vueDuPlateau.tableauCase[oy][ox].retirerPion()
        modele.deplacer(ox, oy, dx, dy)
    }
}

/*
* Gain  / Perte / Ratio-Diff
* 3     / 1     / 3 - 2
* 3     / 2     / 1.5 - 1
* 3     / 3     / 1 - 0
* 2     / 1     / 2 - 1
* 2     / 2     / 1 - 0
* 2     / 3     / 0.66 - -1
* 1     / 1     / 1 - 0
* 1     / 2     / 0.5 - -1
* 1     / 3     / 0.33 - -2
*
* 3/1
* 2/1
* 3/2
* 3/3 - 2/2 - 1/1
* 2/3
* 1/2
* 1/3
* */