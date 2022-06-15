package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Alert
import javafx.scene.control.ButtonBar
import projet.echecmartien.modele.Jeu
import projet.echecmartien.modele.Joueur
import projet.echecmartien.vue.VueCompteurPoints
import projet.echecmartien.vue.VueJeu
import projet.echecmartien.vue.VuePlateau

class ControleurBoutonRecommencer(vue : VueJeu, modele : Jeu) : EventHandler<ActionEvent> {
    val vue : VueJeu
    val modele : Jeu

    init {
        this.vue=vue
        this.modele=modele
    }

    override fun handle(p0: ActionEvent?) {
        val dialog = Alert(Alert.AlertType.CONFIRMATION)
        dialog.title = "CONFIRMATION"
        dialog.headerText = "Voulez vous vraiment recommencer la partie"
        dialog.contentText = "Tout progression non-sauvegarder sera perdue"
        val res = dialog.showAndWait()
        if (res.get().buttonData== ButtonBar.ButtonData.OK_DONE) {
            modele.initialiserPartie(Joueur(vue.gauche.nomJoueur1), Joueur(vue.gauche.nomJoueur2), 10)
            val plateau = VuePlateau(vue.plateau.bot)
            val gauche = VueCompteurPoints(vue.gauche.nomJoueur1, vue.gauche.nomJoueur2, 1280.0, 640.0, 80.0, 80.0)
            plateau.clic(ControleurClicCase(modele, plateau, gauche))
            vue.center = plateau
            vue.left = gauche
        }
    }

}