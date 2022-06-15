package projet.echecmartien.vue

import javafx.geometry.Insets
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.scene.paint.Paint
import javafx.scene.shape.Circle
import javafx.scene.text.Text
import javafx.scene.text.TextAlignment
import projet.echecmartien.modele.GrandPion

class VueCompteurPoints(
    val nomJoueur1: String,
    val nomJoueur2: String,
    tailleFenetreX: Double,
    tailleFenetreY: Double,
    private val bx: Double,
    private val by: Double,
    private val compteJoueur1: Int = 0,
    private val compteJoueur2: Int = 0,
    private val compteGrandPionJoueur1: Int = 0,
    private val compteMoyenPionJoueur1: Int = 0,
    private val comptePetitPionJoueur1: Int = 0,
    private val compteGrandPionJoueur2: Int = 0,
    private val compteMoyenPionJoueur2: Int = 0,
    private val comptePetitPionJoueur2: Int = 0
) : VBox() {
    val texteAQuiDeJouer : Text

    init {
        this.setPrefSize((3/8)*tailleFenetreX, tailleFenetreY)
        //this.style = "-fx-background-color: #00000050"

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

        val fontSizeCompteur = 30.0
        val constX = 6.5
        val constY = -3.0

        val compte11 =
            Text(bx / 2.0 - fontSizeCompteur / 2.0 + constX, tailleGrandPion + fontSizeCompteur / 2.0 + constY, "$compteGrandPionJoueur1")
        val compte12 =
            Text(bx / 2.0 - fontSizeCompteur / 2.0 + constX, tailleMoyenPion + fontSizeCompteur / 2.0 + constY, "$compteMoyenPionJoueur1")
        val compte13 =
            Text(bx / 2.0 - fontSizeCompteur / 2.0 + constX, taillePetitPion + fontSizeCompteur / 2.0 + constY, "$comptePetitPionJoueur1")
        val compte21 =
            Text(bx / 2.0 - fontSizeCompteur / 2.0 + constX, tailleGrandPion + fontSizeCompteur / 2.0 + constY, "$compteGrandPionJoueur2")
        val compte22 =
            Text(bx / 2.0 - fontSizeCompteur / 2.0 + constX, tailleMoyenPion + fontSizeCompteur / 2.0 + constY, "$compteMoyenPionJoueur2")
        val compte23 =
            Text(bx / 2.0 - fontSizeCompteur / 2.0 + constX, taillePetitPion + fontSizeCompteur / 2.0 + constY, "$comptePetitPionJoueur2")

        val styleCompteur = "-fx-font-family: Lobster; -fx-font-size: ${fontSizeCompteur}px;"
        this.stylesheets.add("https://fonts.googleapis.com/css2?family=Lobster&display=swap")
        compte11.style = styleCompteur
        compte12.style = styleCompteur
        compte13.style = styleCompteur
        compte21.style = styleCompteur
        compte22.style = styleCompteur
        compte23.style = styleCompteur
        compte11.fill = Paint.valueOf("#f6e8fa")
        compte12.fill = Paint.valueOf("#e3d5c1")
        compte13.fill = Paint.valueOf("#c9af87")
        compte21.fill = Paint.valueOf("#f6e8fa")
        compte22.fill = Paint.valueOf("#e3d5c1")
        compte23.fill = Paint.valueOf("#c9af87")

        val pane11 = Pane()
        val pane12 = Pane()
        val pane13 = Pane()
        val pane21 = Pane()
        val pane22 = Pane()
        val pane23 = Pane()

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
        partieJoueur1.setPrefSize(200.0, 300.0)
        val partieJoueur2 = VBox()
        partieJoueur2.padding = Insets(0.0, 80.0, 0.0, 30.0)
        partieJoueur2.setPrefSize(250.0, 300.0)
        partieJoueur2.minWidth = 250.0


        val paddingJoueurX = 10.0
        val paddingJoueurY = 20.0
        val fontSizeNomJoueur = 17.0

        val texteNomJoueur2 = Text(paddingJoueurX + 5, (2 / 3) * paddingJoueurY + 1.9 * fontSizeNomJoueur, nomJoueur2)
        val zoneNomJoueur2 = Pane()
        zoneNomJoueur2.children.add(texteNomJoueur2)
        val texteNomJoueur1 = Text(paddingJoueurX + 5, (2 / 3) * paddingJoueurY + 1.9 * fontSizeNomJoueur, nomJoueur1)
        val zoneNomJoueur1 = Pane()
        zoneNomJoueur1.children.add(texteNomJoueur1)

        val styleNomJoueur = "-fx-font-family: Lobster; -fx-font-size: ${fontSizeNomJoueur}px;"
        texteNomJoueur2.style = styleNomJoueur
        texteNomJoueur1.style = styleNomJoueur


        partieJoueur2.children.add(zoneNomJoueur2)
        zoneNomJoueur2.padding = Insets(0.0, 0.0, paddingJoueurY, paddingJoueurX)
        //zoneNomJoueur2.style = "-fx-background-color: #00000030;"
        partieJoueur1.children.add(zoneNomJoueur1)
        zoneNomJoueur1.padding = Insets(0.0, 0.0, paddingJoueurY, paddingJoueurX)
        //zoneNomJoueur1.style = "-fx-background-color: #00000030;"

        val jetonsEtCompteurJoueur1 = BorderPane()
        jetonsEtCompteurJoueur1.left = jetonsJoueur1
        jetonsEtCompteurJoueur1.center = Text("= $compteJoueur1")
        jetonsEtCompteurJoueur1.center.style = styleCompteur
        partieJoueur1.children.add(jetonsEtCompteurJoueur1)
        val jetonsEtCompteurJoueur2 = BorderPane()
        jetonsEtCompteurJoueur2.left = jetonsJoueur2
        jetonsEtCompteurJoueur2.center = Text("= $compteJoueur2")
        jetonsEtCompteurJoueur2.center.style = styleCompteur
        partieJoueur2.children.add(jetonsEtCompteurJoueur2)

        val paneAQuiDeJouer = Pane()
        texteAQuiDeJouer = Text(15.0, 15.0, "C'est au tour de\n$nomJoueur1\nde jouer !")
        texteAQuiDeJouer.style = styleNomJoueur
        texteAQuiDeJouer.textAlignment = TextAlignment.CENTER
        paneAQuiDeJouer.children.add(texteAQuiDeJouer)
        paneAQuiDeJouer.padding = Insets(20.0, 20.0, 20.0, 20.0)
        //paneAQuiDeJouer.style = "-fx-background-color: #FF000030;"

        this.children.add(partieJoueur2)
        this.children.add(paneAQuiDeJouer)
        this.children.add(partieJoueur1)
    }
}