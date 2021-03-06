/**
 * @file        GameScreen.java
 * @author      Aaron Mooney 20072163
 * @assignment  Numerical X and O
 * @brief       displays the game itself
 *
 * @notes       
 * 				
 */
package wit.cgd.numxando.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import wit.cgd.numxando.NumXandOMain;
import wit.cgd.numxando.game.WorldController;
import wit.cgd.numxando.game.WorldRenderer;

public class GameScreen extends AbstractGameScreen {

    @SuppressWarnings("unused")
    private static final String TAG = GameScreen.class.getName();

    private WorldController     worldController;
    private WorldRenderer       worldRenderer;

    private boolean             paused;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void render(float deltaTime) {

        Gdx.gl.glClearColor(34 / 255.0f, 156 / 255.0f, 2 / 255.0f, 0xff / 255.0f);
        // Clears the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Render game world to screen
        worldRenderer.render();
        // Do not update game world when paused.

        if (!paused) {
            // Update game world by the time that has passed
            // since last rendered frame.
            worldController.update(deltaTime);
        }
    }

    @Override
    public void resize(int width, int height) {
        worldRenderer.resize(width, height);
    }

    @Override
    public void show() {
        worldController = new WorldController(game);
        worldRenderer = new WorldRenderer(worldController);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void hide() {
        worldRenderer.dispose();
        Gdx.input.setCatchBackKey(false);
    }

    @Override
    public void pause() {
        paused = true;
    }

    @Override
    public void resume() {
        super.resume();
        paused = false;
    }
}