package projet.echecmartien.controleur

import javafx.event.EventHandler
import javafx.scene.input.MouseEvent
import projet.echecmartien.modele.Jeu
import projet.echecmartien.modele.Joueur
import projet.echecmartien.vue.VueCompteurPoints
import projet.echecmartien.vue.VueJeu
import projet.echecmartien.vue.VuePlateau

class ControleurBoutonRecommencer(vue : VueJeu, modele : Jeu) : EventHandler<MouseEvent> {
    val vue : VueJeu
    val modele : Jeu

    init {
        this.vue=vue
        this.modele=modele
    }

    override fun handle(p0: MouseEvent?) {
        modele.initialiserPartie(Joueur(vue.gauche.nomJoueur1), Joueur(vue.gauche.nomJoueur2), 10)
        vue.center = VuePlateau()
        vue.left = VueCompteurPoints(vue.gauche.nomJoueur1, vue.gauche.nomJoueur2, 1280.0, 640.0, 80.0, 80.0)
        vue.plateau.clic(ControleurClicCase(modele, vue.plateau))
    }

}