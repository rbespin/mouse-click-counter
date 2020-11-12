package com.mouseclickcounter;

import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.client.input.MouseAdapter;

public class MouseClickCounterListener extends MouseAdapter
{
	private int leftClickCounter;
	private int rightClickCounter;
	private int middleClickCounter;
	private int totalClickCounter;
	private final Client client;

	MouseClickCounterListener(Client client)
	{
		this.leftClickCounter = 0;
		this.rightClickCounter = 0;
		this.middleClickCounter = 0;
		this.totalClickCounter = 0;
		this.client = client;
	}

	@Override
	public MouseEvent mousePressed(MouseEvent event)
	{
		if(client.getGameState() == GameState.LOGGED_IN)
		{

			if(SwingUtilities.isLeftMouseButton(event))
			{
				this.leftClickCounter++;
				this.totalClickCounter++;
			}

			else if(SwingUtilities.isRightMouseButton(event))
			{
				this.rightClickCounter++;
				this.totalClickCounter++;
			}

			else if(SwingUtilities.isMiddleMouseButton(event))
			{
				this.middleClickCounter++;
				this.totalClickCounter++;
			}

		}
		return event;
	}

	public int getLeftClickCounter() { return this.leftClickCounter; }

	public int getRightClickCounter() { return this.rightClickCounter; }

	public int getMiddleClickCounter() { return this.middleClickCounter; }

	public int getTotalClickCounter() { return this.totalClickCounter; }

}
