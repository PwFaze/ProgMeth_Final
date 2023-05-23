package drawing;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import logic.game.GameLogic;

public class ShowStaminaPane extends StackPane {
    private ProgressBar staminaBar;
    private Label staminaLabel;
    private Timeline timeline;

    public ShowStaminaPane() {
        staminaBar = new ProgressBar();
        staminaBar.setMaxWidth(Double.MAX_VALUE);

        staminaLabel = new Label();
        staminaLabel.setStyle("-fx-font-size: 18px;");

        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(staminaBar, staminaLabel);
        this.setStyle("-fx-background-color: transparent;");
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            int currentStamina = GameLogic.getPlayer().getPlayerStamina();
            updateStamina(currentStamina);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void updateStamina(int currentStamina) {
        staminaBar.setProgress((double) currentStamina / 300);
        staminaLabel.setText("Stamina: " + currentStamina + " / 300");
    }
}
