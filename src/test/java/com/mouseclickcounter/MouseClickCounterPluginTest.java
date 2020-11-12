package com.mouseclickcounter;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class MouseClickCounterPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(com.mouseclickcounter.MouseClickCounterPlugin.class);
		RuneLite.main(args);
	}
}