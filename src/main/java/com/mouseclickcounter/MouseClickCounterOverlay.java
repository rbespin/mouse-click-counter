package com.mouseclickcounter;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import javax.inject.Inject;
import net.runelite.api.MenuAction;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayMenuEntry;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;
import static net.runelite.client.ui.overlay.OverlayManager.OPTION_CONFIGURE;

public class MouseClickCounterOverlay extends Overlay
{
	private final PanelComponent panelComponent = new PanelComponent();
	private final MouseClickCounterConfig config;
	private final MouseClickCounterPlugin plugin;
	private int size;
	private ArrayList<Integer> previousSelections;

	@Inject
	private MouseClickCounterOverlay(MouseClickCounterConfig config,
		MouseClickCounterPlugin plugin)
	{
		setPosition(OverlayPosition.ABOVE_CHATBOX_RIGHT);
		this.config = config;
		this.size = 0;
		this.plugin = plugin;
		this.previousSelections = new ArrayList<>();
		getMenuEntries().add(
			new OverlayMenuEntry(MenuAction.RUNELITE_OVERLAY_CONFIG,
				OPTION_CONFIGURE, "Mouse click counter overlay"));
	}

	private int getSize(Graphics2D graphics)
	{
		int configSize = 0;
		ArrayList<Integer> currentSelections = new ArrayList<>();
		if(config.showTotalClick())
		{
			String totalString = "Clicks: " +
				this.plugin.getTotalClickCounter();
			int totalSize = graphics.getFontMetrics().stringWidth(totalString);
			configSize = Math.max(configSize, totalSize);
			currentSelections.add(0);
		}

		if(config.showLeftClick())
		{
			String leftString = "Left: " + this.plugin.getLeftClickCounter();
			int leftSize = graphics.getFontMetrics().stringWidth(leftString);
			configSize = Math.max(configSize, leftSize);
			currentSelections.add(1);
		}

		if(config.showRightClick())
		{
			String rightString = "Right: " +
				this.plugin.getRightClickCounter();
			int rightSize = graphics.getFontMetrics().stringWidth(rightString);
			configSize = Math.max(configSize, rightSize);
			currentSelections.add(2);
		}

		if(config.showMiddleClick())
		{
			String middleString = "Middle: " +
				this.plugin.getMiddleClickCounter();
			int middleSize = graphics.getFontMetrics().stringWidth(middleString);
			configSize = Math.max(configSize, middleSize);
			currentSelections.add(3);
		}

		Collections.sort(currentSelections);
		if(!currentSelections.equals(previousSelections))
		{
			this.size = configSize;
			previousSelections = currentSelections;
		}

		else
		{
			if (this.size + 5 < configSize)
			{
				this.size = configSize;
			}

		}

		return this.size;
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		panelComponent.getChildren().clear();
		panelComponent.setPreferredSize(new Dimension(getSize(graphics)+15,0));
		if(!config.showHide())
		{
			if(config.showTotalClick())
			{
				panelComponent.getChildren().add(LineComponent.builder()
					.left("Clicks: ")
					.right(""+this.plugin.getTotalClickCounter())
					.build());
			}

			if(config.showLeftClick())
			{
				panelComponent.getChildren().add(LineComponent.builder()
					.left("Left: ")
					.right(""+this.plugin.getLeftClickCounter())
					.build());
			}

			if(config.showRightClick())
			{
				panelComponent.getChildren().add(LineComponent.builder()
					.left("Right: ")
					.right(""+this.plugin.getRightClickCounter())
					.build());
			}

			if(config.showMiddleClick())
			{
				panelComponent.getChildren().add(LineComponent.builder()
					.left("Middle: ")
					.right(""+this.plugin.getMiddleClickCounter())
					.build());
			}

		}

		return panelComponent.render(graphics);
	}
}
