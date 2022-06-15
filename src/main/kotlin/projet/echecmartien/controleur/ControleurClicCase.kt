package projet.echecmartien.controleur

import javafx.event.EventHandler
import javafx.scene.input.MouseEvent
import projet.echecmartien.modele.*
import projet.echecmartien.vue.VueCompteurPoints
import projet.echecmartien.vue.VuePlateau

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
            enleveCouleur(vueDuPlateau)
            return
        }
        if (modele.getCoordOrigineDeplacement() == null || (modele.getPlateau().getCases()[x][y].getJoueur() == modele.getJoueurCourant() && !modele.getPlateau().getCases()[x][y].estLibre())) {
            modele.setCoordOrigineDeplacement(Coordonnee(x, y))
            deplPossible(x, y, modele, vueDuPlateau)
            return
        }
        if (modele.getCoordOrigineDeplacement() != Coordonnee(x, y)) {
            val xO = modele.getCoordOrigineDeplacement()!!.getX()
            val yO = modele.getCoordOrigineDeplacement()!!.getY()
            if (modele.deplacementPossible(xO, yO, x, y, modele.getJoueurCourant())) {
                vueDuPlateau.tableauCase[y][x].placerPion(modele.getPlateau().getCases()[xO][yO].getPion().toString())
                vueDuPlateau.tableauCase[yO][xO].retirerPion()
                modele.deplacer(xO, yO, x, y)
                vueDeGauche.compteJoueur1 = modele.getJoueurs()[0].calculerScore()
                vueDeGauche.compteJoueur2 = modele.getJoueurs()[1].calculerScore()
                vueDeGauche.compteGrandPionJoueur1 = comptePionCapture(modele.getJoueurs()[0], GrandPion())
                vueDeGauche.compteMoyenPionJoueur1 = comptePionCapture(modele.getJoueurs()[0], MoyenPion())
                vueDeGauche.comptePetitPionJoueur1 = comptePionCapture(modele.getJoueurs()[0], PetitPion())
                vueDeGauche.compteGrandPionJoueur2 = comptePionCapture(modele.getJoueurs()[1], GrandPion())
                vueDeGauche.compteMoyenPionJoueur2 = comptePionCapture(modele.getJoueurs()[1], MoyenPion())
                vueDeGauche.comptePetitPionJoueur2 = comptePionCapture(modele.getJoueurs()[1], PetitPion())
                vueDeGauche.updateScore()
                vueDeGauche.texteAQuiDeJouer.text = "C'est au tour de\n@${modele.getJoueurCourant()!!.getPseudo()}\nde jouer !"
                if (modele.arretPartie()){
                    vueDeGauche.texteAQuiDeJouer.text = texteDeFin()
                    stop = true
                }
            }
        }
        modele.setCoordOrigineDeplacement(null)
        enleveCouleur(vueDuPlateau)
        if (vueDuPlateau.bot) {
            println("BOT")
        }
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
}


fun deplPossible(x: Int, y: Int, modele: Jeu, vue: VuePlateau) {
    for (i in 0 until 4) {
        for (j in 0 until 8) {
            vue.tableauCase[j][i].changeCouleur("None")
            if (modele.deplacementPossible(x, y, i, j, modele.getJoueurCourant())) {
                if (modele.getPlateau().getCases()[i][j].estLibre()) {
                    vue.tableauCase[j][i].changeCouleur("#A5E3F6")
                } else {
                    vue.tableauCase[j][i].changeCouleur("#FF6961")
                }
            }
        }
    }
    if (!modele.getPlateau().getCases()[x][y].estLibre() && modele.getPlateau().getCases()[x][y].getJoueur() == modele.getJoueurCourant())
        vue.tableauCase[y][x].changeCouleur("#B0F2B6")
}

fun enleveCouleur(vue: VuePlateau) {
    for (i in 0 until 4) {
        for (j in 0 until 8) {
            vue.tableauCase[j][i].changeCouleur("None")
        }
    }
}
