package com.mouseclickcounter;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.input.MouseManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
	name = "Mouse Click Counter"
)
public class MouseClickCounterPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private MouseClickCounterConfig config;

	@Inject
	private MouseManager mouseManager;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private MouseClickCounterOverlay overlay;

	private MouseClickCounterListener mouseListener;

	@Override
	protected void startUp() throws Exception
	{
		mouseListener = new MouseClickCounterListener(client);
		mouseManager.registerMouseListener(mouseListener);
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		mouseManager.unregisterMouseListener(mouseListener);
		mouseListener = null;
		overlayManager.remove(overlay);
	}

	@Provides
	MouseClickCounterConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(MouseClickCounterConfig.class);
	}

	public int getLeftClickCounter() { return mouseListener.getLeftClickCounter(); }

	public int getRightClickCounter() { return mouseListener.getRightClickCounter(); }

	public int getMiddleClickCounter() { return mouseListener.getMiddleClickCounter(); }

	public int getTotalClickCounter() { return mouseListener.getTotalClickCounter(); }


}
