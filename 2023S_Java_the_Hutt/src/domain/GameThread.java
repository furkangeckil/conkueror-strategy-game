package domain;

public class GameThread extends Thread{
    private Game game;

    public GameThread(Game game) {
        this.game = game;
    }
    
    @Override
    public void run() {
        game.gameLoop();
    }
// Add the folowing to the GameSetup or the file that will start the game
/* Game game = new Game();
    GameThread gameThread = new GameThread(game);
    gameThread.start(); */
}
