package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Alert
import javafx.scene.input.MouseEvent
import projet.echecmartien.modele.Jeu
import projet.echecmartien.vue.VueJeu

class ControleurAfficherRegles(vue : VueJeu, modele : Jeu) : EventHandler<ActionEvent> {
    val vue : VueJeu
    val modele : Jeu

    init {
        this.vue=vue
        this.modele=modele
    }

    override fun handle(p0: ActionEvent?) {
        val dialog = Alert(Alert.AlertType.INFORMATION)
        dialog.title = "INFORMATION"
        dialog.headerText = "Les règles"
        dialog.contentText = "RÈGLES DU JEU :" +
                "\nPREPARATION" +
                "\nDisposez les 18 pions comme sur la figure ci-contre." +
                "\nUn joueur identifie ses pièces par leur position à un instant donné." +
                "\nLe damier est divisé en 2 zones, une pour chaque joueur. Toute pièce dans la zone d'un joueur est la sienne." +
                "\n\nDEROULEMENT DU JEU" +
                "\nChaque joueur, à son tour de jeu, déplace une de ses pièces." +
                "\nLes grands pions se déplacent verticalement, horizontalement et diagonalement de n cases (comme la dame aux Eches traditionnel)." +
                "\nLes pions moyens se déplacent verticalement, horizontalement et diagonalement de 1 ou 2 cases." +
                "\nLes petits pions se déplacent diagonalement de 1 case." +
                "\nA son tour de jeu un joueur peut déplacer n'importe quel pion de son camp, soit à l'intérieur de sa zone soit vers la zone adverse." +
                "\nException: Il est interdit de renvoyer dans la zone adverse un pion qui vient d'arriver dans sa zone. Mais on peut déplacer ce même pion à l'intérieur de sa zone" +
                "\nOn capture un pion adverse en prenant sa place (donc fatalement en prenant un pion de sa zone et en allant dans la zone adverse). Le pion capturé est retiré du damier." +
                "\nLe saut par dessus un ou n pions adverses ou non n'est pas autorisé." +
                "\n\nFIN DE LA PARTIE" +
                "\nUne fois la partie finie (plus de pions à capturer car ils sont tous capturés ou plus aucunes prises n'est possibles), on compte 3 points par grand pion capturés, 2 par moyen et 1 par petit." +
                "\nLe gagant est évidement le joueur qui à le plus de points."
        dialog.showAndWait()
    }

}