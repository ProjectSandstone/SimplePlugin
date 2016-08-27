package com.github.projectsandstone.simpleplugin;

import com.google.inject.Inject;

import com.github.projectsandstone.api.Game;
import com.github.projectsandstone.api.event.Listener;
import com.github.projectsandstone.api.event.init.InitializationEvent;
import com.github.projectsandstone.api.event.init.PostInitializationEvent;
import com.github.projectsandstone.api.event.init.PreInitializationEvent;
import com.github.projectsandstone.api.logging.Logger;
import com.github.projectsandstone.api.plugin.Plugin;

/**
 * Created by jonathan on 25/08/16.
 */
@Plugin(id = "com.github.projectsandstone.SimplePlugin", name = "SimplePlugin", version = "1.0")
public class SimplePlugin {

    private final Game game;
    private final Logger logger;

    @Inject
    public SimplePlugin(Game game, Logger logger) {
        this.game = game;
        this.logger = logger;
    }

    @Listener
    public void preInit(PreInitializationEvent event) {
        logger.info("Pre Initialization");
    }

    @Listener
    public void init(InitializationEvent event) {
        logger.info("Initialization");
    }

    @Listener
    public void postInit(PostInitializationEvent event) {
        logger.info("Post Initialization");
    }
}
