package projet.echecmartien.vue

import javafx.geometry.Insets
import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox

class VueAccueil : BorderPane() {
    val borderPaneHaut: BorderPane
    val vboxJoueur1: VBox
    val vboxJoueur2: VBox
    val textFieldPseudoj1: TextField
    val textFieldPseudoj2: TextField
    val labelPseudoj1: Label
    val labelPseudoj2: Label
    val boutonValide: Button
    val checkBoxIA: CheckBox
    val regles: Label

    init {
        borderPaneHaut = BorderPane()
        vboxJoueur1 = VBox()
        vboxJoueur2 = VBox()
        textFieldPseudoj1 = TextField("Joueur 1")
        textFieldPseudoj2 = TextField("Joueur 2")
        labelPseudoj1 = Label("Entrer le pseudo du joueur 1")
        labelPseudoj2 = Label("Entrer le pseudo du joueur 2")
        boutonValide = Button("Valider")
        checkBoxIA = CheckBox("Jouer contre l'IA")
        regles = Label(
            "RÈGLES DU JEU :" +
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
                    "\nOn capture un pion adverse en prenant sa place (donc fatalement en prenant un pion de sa zone et en allant dans la zone adverse). Le pion capturé est retiré du damier.." +
                    "\nLe saut par dessus un ou n pions adverses ou non n'est pas autorisé." +
                    "\n\nFIN DE LA PARTIE" +
                    "\nUne fois la partie finie (plus de pions à capturer car ils sont tous capturés ou plus aucunes prises n'est possibles), on compte 3 points par grand pion capturés, 2 par moyen et 1 par petit." +
                    "\nLe gagant est évidement le joueur qui à le plus de points."
        )

        regles.isDisable = true
        regles.isWrapText = true
        regles.style = "-fx-text-fill : red; -fx-background-color:blue"
        borderPaneHaut.padding = Insets(10.0)
        vboxJoueur1.spacing = 5.0
        vboxJoueur2.spacing = 5.0




        this.top = borderPaneHaut
        this.center = regles
        borderPaneHaut.left = vboxJoueur1
        borderPaneHaut.center = boutonValide
        borderPaneHaut.right = vboxJoueur2
        vboxJoueur1.children.addAll(labelPseudoj1, textFieldPseudoj1)
        vboxJoueur2.children.addAll(checkBoxIA, labelPseudoj2, textFieldPseudoj2)


    }


}