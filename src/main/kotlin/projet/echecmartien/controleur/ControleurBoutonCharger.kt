package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import projet.echecmartien.modele.Jeu
import projet.echecmartien.vue.VueJeu

class ControleurBoutonCharger(vue : VueJeu, modele : Jeu) : EventHandler<ActionEvent> {
    val vue : VueJeu
    val modele : Jeu

    init {
        this.vue=vue
        this.modele=modele
    }

    override fun handle(p0: ActionEvent?) {
        TODO("Not yet implemented")
    }

}