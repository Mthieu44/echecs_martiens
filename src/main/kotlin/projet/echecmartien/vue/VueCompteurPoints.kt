package projet.echecmartien.vue

import javafx.geometry.Insets
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.scene.paint.Paint
import javafx.scene.shape.Circle
import javafx.scene.text.Text

class VueCompteurPoints(
    private val nomJoueur1: String,
    private val nomJoueur2: String,
    tailleFenetreX: Double,
    tailleFenetreY: Double,
    private val bx: Double,
    private val by: Double
) : VBox() {
    init {
        val tailleGrandPion: Double
        val tailleMoyenPion: Double
        val taillePetitPion: Double

        val minTailleBouton: Double = if (bx <= by) bx else by
        tailleGrandPion = (18.0 / 20.0) / 2.0 * minTailleBouton
        tailleMoyenPion = (6.0 / 8.0) / 2.0 * minTailleBouton
        taillePetitPion = (1.0 / 2.0) / 2.0 * minTailleBouton

        val cercle11 = Circle(bx / 2.0, tailleGrandPion, tailleGrandPion, Paint.valueOf("#BDA8A8"))
        val cercle12 = Circle(bx / 2.0, tailleMoyenPion, tailleMoyenPion, Paint.valueOf("#9C8165"))
        val cercle13 = Circle(bx / 2.0, taillePetitPion, taillePetitPion, Paint.valueOf("#6E4404"))
        val cercle21 = Circle(bx / 2.0, tailleGrandPion, tailleGrandPion, Paint.valueOf("#BDA8A8"))
        val cercle22 = Circle(bx / 2.0, tailleMoyenPion, tailleMoyenPion, Paint.valueOf("#9C8165"))
        val cercle23 = Circle(bx / 2.0, taillePetitPion, taillePetitPion, Paint.valueOf("#6E4404"))

        val fontSize = 30.0
        val constX = 6.5
        val constY = -3.0

        val compte11 = Text(bx / 2.0 - fontSize/2.0 + constX, tailleGrandPion + fontSize/2.0 + constY, "0")
        val compte12 = Text(bx / 2.0 - fontSize/2.0 + constX, tailleMoyenPion + fontSize/2.0 + constY, "1")
        val compte13 = Text(bx / 2.0 - fontSize/2.0 + constX, taillePetitPion + fontSize/2.0 + constY, "2")
        val compte21 = Text(bx / 2.0 - fontSize/2.0 + constX, tailleGrandPion + fontSize/2.0 + constY, "3")
        val compte22 = Text(bx / 2.0 - fontSize/2.0 + constX, tailleMoyenPion + fontSize/2.0 + constY, "4")
        val compte23 = Text(bx / 2.0 - fontSize/2.0 + constX, taillePetitPion + fontSize/2.0 + constY, "5")

        val styleCompteur = "-fx-font-family: Lobster; -fx-font-size: ${fontSize}px;"
        this.stylesheets.add("https://fonts.googleapis.com/css2?family=Lobster&display=swap")
        compte11.style = styleCompteur
        compte12.style = styleCompteur
        compte13.style = styleCompteur
        compte21.style = styleCompteur
        compte22.style = styleCompteur
        compte23.style = styleCompteur

        val pane11 = Pane()
        val pane12 = Pane()
        val pane13 = Pane()
        val pane21 = Pane()
        val pane22 = Pane()
        val pane23 = Pane()
        /*
        pane11.style = "-fx-background-color: #00FF00;"
        pane12.style = "-fx-background-color: #FF0000;"
        pane13.style = "-fx-background-color: #0000FF;"
        */
        val espace = 10.0
        pane11.setPrefSize(bx, tailleGrandPion * 2 + espace)
        pane12.setPrefSize(bx, tailleMoyenPion * 2 + espace)
        pane13.setPrefSize(bx, taillePetitPion * 2)
        pane21.setPrefSize(bx, tailleGrandPion * 2 + espace)
        pane22.setPrefSize(bx, tailleMoyenPion * 2 + espace)
        pane23.setPrefSize(bx, taillePetitPion * 2)

        pane11.children.addAll(cercle11, compte11)
        pane12.children.addAll(cercle12, compte12)
        pane13.children.addAll(cercle13, compte13)
        pane21.children.addAll(cercle21, compte21)
        pane22.children.addAll(cercle22, compte22)
        pane23.children.addAll(cercle23, compte23)

        val jetonsJoueur1 = VBox()
        jetonsJoueur1.children.add(pane11)
        jetonsJoueur1.children.add(pane12)
        jetonsJoueur1.children.add(pane13)
        val jetonsJoueur2 = VBox()
        jetonsJoueur2.children.add(pane21)
        jetonsJoueur2.children.add(pane22)
        jetonsJoueur2.children.add(pane23)

        val partieJoueur1 = VBox()
        partieJoueur1.padding = Insets(0.0, 80.0, 0.0, 30.0)
        partieJoueur1.setPrefSize(200.0, 280.0)
        val partieJoueur2 = VBox()
        partieJoueur2.padding = Insets(0.0, 80.0, 0.0, 30.0)
        partieJoueur2.setPrefSize(200.0, 280.0)

        val zoneNomJoueur1 = Text(nomJoueur1)
        partieJoueur1.children.add(zoneNomJoueur1)
        val zoneNomJoueur2 = Text(nomJoueur2)
        partieJoueur2.children.add(zoneNomJoueur2)

        val jetonsEtCompteurJoueur1 = BorderPane()
        jetonsEtCompteurJoueur1.left = jetonsJoueur1
        jetonsEtCompteurJoueur1.center = Text("=23")
        partieJoueur1.children.add(jetonsEtCompteurJoueur1)
        val jetonsEtCompteurJoueur2 = BorderPane()
        jetonsEtCompteurJoueur2.left = jetonsJoueur2
        jetonsEtCompteurJoueur2.center = Text("=22")
        partieJoueur2.children.add(jetonsEtCompteurJoueur2)

        // partieJoueur1.children.addAll(jetonsJoueur1)


        this.children.add(partieJoueur1)
        this.children.add(partieJoueur2)
    }
}