package com.github.projectsandstone.simpleplugin;

import com.google.inject.Inject;

import com.github.projectsandstone.api.Game;
import com.github.projectsandstone.api.Sandstone;
import com.github.projectsandstone.api.event.Listener;
import com.github.projectsandstone.api.event.init.InitializationEvent;
import com.github.projectsandstone.api.event.init.PostInitializationEvent;
import com.github.projectsandstone.api.event.init.PreInitializationEvent;
import com.github.projectsandstone.api.logging.Logger;
import com.github.projectsandstone.api.plugin.Plugin;
import com.github.projectsandstone.api.plugin.PluginContainer;
import com.github.projectsandstone.api.plugin.PluginDefinition;
import com.github.projectsandstone.api.util.version.Schemes;

import java.time.Duration;

/**
 * Created by jonathan on 25/08/16.
 */
@Plugin(id = "com.github.projectsandstone.SimplePlugin", name = "SimplePlugin", version = "1.0")
public class SimplePlugin {

    private final Game game;
    private final Logger logger;
    private final PluginContainer pluginContainer;
    private final PluginDefinition pluginDefinition;

    @Inject
    public SimplePlugin(Game game, Logger logger, PluginDefinition pluginDefinition, PluginContainer pluginContainer) {
        this.game = game;
        this.logger = logger;
        this.pluginDefinition = pluginDefinition;
        this.pluginContainer = pluginContainer;
        pluginDefinition.applyVersion(version -> version.changeScheme(Schemes.getSemVerScheme()));
    }

    @Listener
    public void preInit(PreInitializationEvent event) {
        logger.info("Simple Plugin version: "+pluginContainer.getVersion());
        logger.info("Pre Initialization");
    }

    @Listener
    public void init(InitializationEvent event) {
        logger.info("Initialization");
    }

    @Listener
    public void postInit(PostInitializationEvent event) {
        logger.info("Post Initialization");

        game.getScheduler().createAndSubmit(this, "Task #1", Duration.ofSeconds(5), Duration.ZERO, false, () -> {

            logger.info("Scheduled print");

            pluginDefinition.getVersion();
            throw new IllegalStateException("Test");
        });
    }
}
