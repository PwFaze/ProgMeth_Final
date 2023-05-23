package drawing;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import logic.game.GameLogic;

public class ShowMoneyPane extends StackPane {
    private Timeline timeline;
    private Label moneyLabel;

    public ShowMoneyPane() {
        moneyLabel = new Label();
        moneyLabel.setStyle("-fx-font-size: 18px;");

        this.setAlignment(Pos.CENTER);
        this.getChildren().add(moneyLabel);
        this.setStyle("-fx-background-color: transparent;");
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            int money = (int) GameLogic.getPlayer().getPlayerMoney();
            moneyLabel.setText("Money: $" + money);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
   
}
