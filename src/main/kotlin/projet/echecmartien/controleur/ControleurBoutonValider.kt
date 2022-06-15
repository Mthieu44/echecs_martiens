package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.stage.Stage
import projet.echecmartien.modele.Jeu
import projet.echecmartien.modele.Joueur
import projet.echecmartien.vue.VueAccueil
import projet.echecmartien.vue.VueJeu

class ControleurBoutonValider(primary: Stage, vue: VueAccueil, modele: Jeu) : EventHandler<ActionEvent> {
    val vue: VueAccueil
    val modele: Jeu
    val primary: Stage

    init {
        this.vue = vue
        this.modele = modele
        this.primary = primary
    }

    override fun handle(p0: ActionEvent?) {
        if (vue.textFieldPseudoj1.text.isBlank() || (vue.textFieldPseudoj2.text.isBlank() && !vue.checkBoxIA.isSelected) || vue.textFieldPseudoj1.text == vue.textFieldPseudoj2.text) {
            val dialog = Alert(Alert.AlertType.INFORMATION)
            dialog.title = "ERREUR"
            dialog.headerText = "CHAMPS NON VALIDE"
            dialog.contentText =
                "Merci de remplir correctement les pseudos des joueurs, les pseudos doivent être renseignés et différents"
            dialog.showAndWait()
            return
        }
        val j1 = Joueur(vue.textFieldPseudoj1.text)
        val j2 = if (vue.checkBoxIA.isSelected)
            Joueur("[BOT]")
        else
            Joueur(vue.textFieldPseudoj2.text)
        val root = VueJeu(j1.getPseudo(), j2.getPseudo())
        val scene = Scene(root, 820.0, 650.0)
        root.plateau.clic(ControleurClicCase(modele, root.plateau, root.gauche))
        root.droite.fixeBoutonListener(root.droite.boutonAfficherRegles, ControleurAfficherRegles(root, modele))
        root.droite.fixeBoutonListener(root.droite.boutonRecommencer, ControleurBoutonRecommencer(root, modele))
        root.droite.fixeBoutonListener(root.droite.boutonRetourAccueil, ControleurBoutonRetourAccueil(primary, root, modele))
        root.droite.fixeBoutonListener(root.droite.boutonSauvegarder, ControleurBoutonSauvegarder(root, modele))
        root.droite.fixeBoutonListener(root.droite.boutonCharger, ControleurBoutonCharger(root, modele))
        modele.initialiserPartie(j1, j2, 10)
        primary.title = "Echec Martien"
        primary.scene = scene
        primary.show()
    }
}