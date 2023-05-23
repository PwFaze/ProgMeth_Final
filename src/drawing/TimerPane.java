package drawing;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class TimerPane extends StackPane {
    private int remainingSeconds;
    private AnimationTimer animationTimer;
    private Label timerLabel;

    public TimerPane() {
        timerLabel = new Label();
        timerLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        this.setMaxSize(100, 100);
        this.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
        this.setAlignment(Pos.CENTER);
        this.getChildren().add(timerLabel);
        this.setStyle("-fx-background-color: transparent;");

        animationTimer = new AnimationTimer() {
            private long lastUpdate = 0;
            private long updateInterval = 1_000_000_000; // 1 second in nanoseconds

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= updateInterval) {
                    lastUpdate = now;
                    remainingSeconds--;
                    if (remainingSeconds <= 0) {
                        stopTimer();
                        System.out.println("Timeout");
                    }
                    updateLabel();
                }
            }
        };
        startTimer(10);

    }

    public void startTimer(int minutes) {
        remainingSeconds = minutes * 60;
        updateLabel();
        animationTimer.start();
    }

    public void stopTimer() {
        animationTimer.stop();
    }

    private void updateLabel() {
        int minutes = remainingSeconds / 60;
        int seconds = remainingSeconds % 60;
        String time = String.format("%02d:%02d", minutes, seconds);
        timerLabel.setText(time);
    }
    
    public int getRemainingSeconds() {
    	return remainingSeconds;
    }
    public AnimationTimer getAnimationTimer() {
    	return animationTimer;
    }
    
}
