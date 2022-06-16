package projet.echecmartien.vue

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Cursor
import javafx.scene.control.*
import javafx.scene.effect.DropShadow
import javafx.scene.effect.Effect
import javafx.scene.input.MouseEvent
import javafx.scene.input.RotateEvent
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import javafx.scene.text.TextAlignment

class VueAccueil : BorderPane() {
    val borderPaneHaut: BorderPane
    val vboxJoueur1: VBox
    val vboxJoueur2: VBox
    val vboxValide: VBox
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
        vboxValide = VBox()
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
                    "\nOn capture un pion adverse en prenant sa place (donc fatalement en prenant un pion de sa zone et en allant dans la zone adverse). Le pion capturé est retiré du damier." +
                    "\nLe saut par dessus un ou n pions adverses ou non n'est pas autorisé." +
                    "\n\nFIN DE LA PARTIE" +
                    "\nUne fois la partie finie (plus de pions à capturer car ils sont tous capturés ou plus aucunes prises n'est possibles), on compte 3 points par grand pion capturés, 2 par moyen et 1 par petit." +
                    "\nLe gagant est évidement le joueur qui à le plus de points."
        )
        this.stylesheets.add("https://fonts.googleapis.com/css2?family=Oxygen:wght@300&display=swap")
        regles.isDisable = true
        regles.isWrapText = true
        regles.style =
            "-fx-color : #000000; -fx-background-color: #faf5e6; -fx-font-family: 'Oxygen', sans-serif; -fx-font-size: 13.5px; -fx-font-weight: 700;"
        regles.padding = Insets(20.0)
        regles.textAlignment = TextAlignment.CENTER
        borderPaneHaut.padding = Insets(10.0)
        vboxJoueur1.spacing = 5.0
        vboxJoueur1.padding = Insets(50.0, 40.0, 15.0, 40.0)
        vboxJoueur2.spacing = 5.0
        vboxJoueur2.padding = Insets(20.0, 40.0, 15.0, 40.0)
        vboxValide.alignment = Pos.BOTTOM_CENTER



        this.top = borderPaneHaut
        this.center = regles
        borderPaneHaut.left = vboxJoueur1
        vboxValide.children.add(boutonValide)
        borderPaneHaut.center = vboxValide
        borderPaneHaut.right = vboxJoueur2
        vboxJoueur1.children.addAll(labelPseudoj1, textFieldPseudoj1)
        vboxJoueur2.children.addAll(checkBoxIA, labelPseudoj2, textFieldPseudoj2)


        boutonValide.padding = Insets(10.0, 17.0, 10.0, 17.0)
        boutonValide.style =
            "-fx-background-color: #dbfff3; -fx-border-width: 3px; -fx-border-color: #36e0a8; -fx-background-radius: 8px; -fx-border-radius: 8px;"
        boutonValide.setOnMouseEntered {
            boutonValide.cursor = Cursor.cursor("HAND");
            boutonValide.style =
                "-fx-background-radius: 8px; -fx-border-radius: 8px; -fx-background-color: #bbf2e0; -fx-border-color: #36e0a8; -fx-border-width: 3px;"
        }
        boutonValide.setOnMouseExited {
            boutonValide.style =
                "-fx-background-color: #dbfff3; -fx-border-width: 3px; -fx-border-color: #36e0a8; -fx-background-radius: 8px; -fx-border-radius: 8px;"
        }
        boutonValide.addEventHandler(MouseEvent.MOUSE_PRESSED) {
            boutonValide.effect = DropShadow(20.0, Color.web("#36e0a8"));
        }
        boutonValide.addEventHandler(MouseEvent.MOUSE_RELEASED) {
            boutonValide.effect = DropShadow(0.0, Color.rgb(255, 255, 255))
        }


        textFieldPseudoj1.padding = Insets(10.0, 12.0, 10.0, 12.0)
        textFieldPseudoj2.padding = Insets(10.0, 12.0, 10.0, 12.0)

        textFieldPseudoj1.style =
            "-fx-background-color: #ecf5d0; -fx-border-width: 3px; -fx-border-color: #acd136; -fx-background-radius: 8px; -fx-border-radius: 8px;"
        textFieldPseudoj2.style =
            "-fx-background-color: #fcebf1; -fx-border-width: 3px; -fx-border-color: #db4678; -fx-background-radius: 8px; -fx-border-radius: 8px;"

        this.style = "-fx-background-color: linear-gradient(from 50% 0% to 50% 100%, #2192e610, #dec08860);"

    }

    fun checkBox(controleur: EventHandler<MouseEvent>) {
        this.checkBoxIA.onMouseClicked = controleur
    }

    fun valider(controleur: EventHandler<ActionEvent>) {
        this.boutonValide.onAction = controleur
    }
}