package com.okapi.colorun.screens;

import static com.okapi.colorun.Assets.GAME_OVER;
import static com.okapi.colorun.Assets.PLAY_BUTTON;
import static com.okapi.colorun.Assets.SCORE_TABLE;
import static com.okapi.colorun.ColoRunner.HEIGHT;
import static com.okapi.colorun.ColoRunner.WIDTH;

public enum Positions {
    PLAY_POS (
            WIDTH / 2 - PLAY_BUTTON.getWidth() - 5,
            HEIGHT / 2 - SCORE_TABLE.getHeight()
    ),
    SCORES_POS (
            WIDTH / 2 + 5,
            HEIGHT / 2 - SCORE_TABLE.getHeight()
    ),
    GAME_OVER_POS (
            (WIDTH - GAME_OVER.getWidth()) / 2,
            (HEIGHT + SCORE_TABLE.getHeight() + GAME_OVER.getHeight() + 10) / 2
    ),
    SCORE_TABLE_POS (
            (WIDTH - SCORE_TABLE.getWidth()) / 2,
            (HEIGHT - SCORE_TABLE.getHeight() + 30) / 2
    ),
    NEW_POS (
            (WIDTH + 48) / 2,
            HEIGHT / 2
    ),
    SCORE_LABEL_POS (
            (WIDTH + PLAY_BUTTON.getWidth() + 10) / 2,
            (HEIGHT + 30) / 2
    ),
    HIGH_SCORE_LABEL_POS (
            (WIDTH + PLAY_BUTTON.getWidth() + 10) / 2,
            (HEIGHT - PLAY_BUTTON.getHeight()) / 2
    );

    public final float X, Y;

    Positions(final float x, final float y) {
        X = x; Y = y;
    }

}
