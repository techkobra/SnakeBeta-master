package com.moonstub.training.app.snakealpha;

import com.moonstub.training.app.snakealpha.Screens.LoadingScreen;
import com.moonstub.training.app.snakealpha.framework.GameActivity;
import com.moonstub.training.app.snakealpha.framework.GameScreen;

public class Game extends GameActivity {

    protected GameScreen initializeScreen() {
        return new LoadingScreen(this);
    }

}
