package com.mouseclickcounter;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("mouseclickcounter")
public interface MouseClickCounterConfig extends Config
{
	@ConfigItem(
		position = 1,
		keyName = "showHide",
		name = "Hide Overlay",
		description = "Toggle the display of any click count"
	)
	default boolean showHide() { return false; }

	@ConfigItem(
		position = 2,
		keyName = "showTotalClick",
		name = "Show total click totals",
		description = "Toggle the display of the total mouse clicks"
	)
	default boolean showTotalClick() { return true; }

	@ConfigItem(
		position = 3,
		keyName = "showLeftClick",
		name = "Show left click totals",
		description = "Toggle the display of the left mouse clicks"
	)
	default boolean showLeftClick() { return false; }

	@ConfigItem(
		position = 4,
		keyName = "showRightClick",
		name = "Show right click totals",
		description = "Toggle the display of the right mouse clicks"
	)
	default boolean showRightClick() { return false; }

	@ConfigItem(
		position = 5,
		keyName = "showMiddleClick",
		name = "Show middle click totals",
		description = "Toggle the display of the middle mouse clicks"
	)
	default boolean showMiddleClick() { return false; }

}

