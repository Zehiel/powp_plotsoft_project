package edu.iis.powp.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import edu.iis.powp.appext.FeaturesManager;
import edu.iis.powp.command.DrawToCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.SetPositionCommand;
import edu.iis.powp.command.manager.AbstractPlotterCommandManager;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.app.Application;

public class SelectLoadSecretCommandOptionListener extends AbstractCommandListener implements ActionListener {

	public SelectLoadSecretCommandOptionListener(Application context) {
		super(context);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
	    List<IPlotterCommand> commands = new ArrayList<IPlotterCommand>(); 
		commands.add(new SetPositionCommand(-20, -50));
		commands.add(new DrawToCommand(-20, -50));
		commands.add(new SetPositionCommand(-20, -40));
		commands.add(new DrawToCommand(-20, 50));
		commands.add(new SetPositionCommand(0, -50));
		commands.add(new DrawToCommand(0, -50));
		commands.add(new SetPositionCommand(0, -40));
		commands.add(new DrawToCommand(0, 50));
		commands.add(new SetPositionCommand(70, -50));
		commands.add(new DrawToCommand(20, -50));
		commands.add(new DrawToCommand(20, 0));
		commands.add(new DrawToCommand(70, 0));
		commands.add(new DrawToCommand(70, 50));
		commands.add(new DrawToCommand(20, 50));


	    PlotterCommandManager manager = (PlotterCommandManager)FeaturesManager.getPlotterCommandManager();
	    manager.setCurrentCommand(commands, "TopSecretCommand");
	}
}