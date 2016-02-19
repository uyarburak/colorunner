package com.okapi.colorun.screens;

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.graphics.Color;
        import com.badlogic.gdx.graphics.OrthographicCamera;
        import com.badlogic.gdx.graphics.g2d.BitmapFont;
        import com.badlogic.gdx.graphics.g2d.SpriteBatch;
        import com.badlogic.gdx.scenes.scene2d.Stage;
        import com.badlogic.gdx.scenes.scene2d.ui.Label;
        import com.badlogic.gdx.scenes.scene2d.ui.Table;
        import com.badlogic.gdx.utils.Disposable;
        import com.badlogic.gdx.utils.viewport.FitViewport;
        import com.badlogic.gdx.utils.viewport.Viewport;
        import com.okapi.colorun.AssetLoader;
        import com.okapi.colorun.ColoRunnerDemo;

/**
 * Created by burak on 18.02.2016.
 */
public class Hud implements Disposable {

    private Viewport viewport;
    public Stage stage;

    private BitmapFont font;

    private static Integer score;
    private static Integer hiscore;

    private static Label scoreLabel;
    private static Label hiscoreLabel;
    private static Label colorLabel;
    public Hud(SpriteBatch sb){

        viewport = new FitViewport(ColoRunnerDemo.WIDTH, ColoRunnerDemo.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        font = AssetLoader.font;
        font.getData().setScale(.25f, .25f);

        score = 0;
        hiscore = AssetLoader.getHighScore();

        //define a table used to organize our hud's labels
        Table table = new Table();
        //Top-Align table
        table.top();
        //make the table fill the entire stage
        table.setFillParent(true);

        //define our labels using the String, and a Label style consisting of a font and color
        scoreLabel = new Label(String.format("%3d", score), new Label.LabelStyle(font, Color.WHITE));
        colorLabel = new Label(String.format("%6s", "COLOR"), new Label.LabelStyle(font, Color.WHITE));
        hiscoreLabel = new Label(String.format("%3d", hiscore), new Label.LabelStyle(font, Color.WHITE));

        //add our labels to our table, padding the top, and giving them all equal width with expandX
        table.add(new Label("RECORD", new Label.LabelStyle(font, Color.WHITE))).expandX().padTop(10);
        table.add(colorLabel).expandX().padTop(10);
        table.add(new Label("SCORE", new Label.LabelStyle(font, Color.WHITE))).expandX().padTop(10);

        //add a second row to our table
        table.row().row();
        table.add(hiscoreLabel).expandX();
        table.add();
        table.add(scoreLabel).expandX();

        //add our table to the stage
        stage.addActor(table);

    }

    public static int getScore(){

        return score;
    }
    public static void addScore(int value){

        score += value;
        scoreLabel.setText(String.format("%3d", score));
        if(score > hiscore) {
            hiscoreLabel.setText(String.format("%3d", score));
        }
    }
    public static void changeColor(String color){

        colorLabel.setText(String.format("%6s", color));
        if(color.contains("RED")){
            colorLabel.setColor(Color.RED);
        }
        else if(color.contains("BLUE")){
            colorLabel.setColor(Color.BLUE);
        }
        else if(color.contains("YELLOW")){
            colorLabel.setColor(Color.YELLOW);
        }
        else if(color.contains("GREEN")){
            colorLabel.setColor(Color.GREEN);
        }
    }

    @Override
    public void dispose() {

        stage.dispose(); }

}
