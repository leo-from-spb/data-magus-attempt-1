package lb.datamagus.ui

import javafx.application.Application
import javafx.stage.Stage
import sun.launcher.resources.launcher
import javafx.scene.layout.BorderPane
import javafx.scene.Scene
import javafx.scene.layout.StackPane
import javafx.scene.Parent


class DataMagusApplication: Application()
{

    public override fun start(primaryStage: Stage?)
    {
        primaryStage!!
        primaryStage.setTitle("DataMagus")

        val rootPane = BorderPane()
        val scene = Scene(rootPane:Parent, 600.0, 600.0)
        primaryStage.setScene(scene)
        primaryStage.show()
    }

}
