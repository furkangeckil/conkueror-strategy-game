package domain.game;

public class Game {
    private GameEnum gameEnum = GameEnum.RUNNING;
    
    public void gameLoop() {
        while (gameEnum != GameEnum.STOPPED) {
            if (gameEnum == GameEnum.RUNNING) {
                
            } else if (gameEnum == GameEnum.PAUSED) {
                while (gameEnum == GameEnum.PAUSED) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public GameEnum getGameEnum() {
        return gameEnum;
    }

    public void pauseGame() {
        gameEnum = GameEnum.PAUSED;
    }
    
    public void resumeGame() {
        gameEnum = GameEnum.RUNNING;
    }

    public void endGame() {
        gameEnum = GameEnum.STOPPED;
    }
}
