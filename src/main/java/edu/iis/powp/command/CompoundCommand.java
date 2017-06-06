package edu.iis.powp.command;

import edu.iis.client.plottermagic.IPlotter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by user on 2017-06-06.
 */
public class CompoundCommand implements ICompoundCommand {
    private List<IPlotterCommand> commands;

    public CompoundCommand(List<IPlotterCommand> commands) {
        this.commands = commands;
    }

    public CompoundCommand(IPlotterCommand command) {
        this.commands = new ArrayList<IPlotterCommand>();
        commands.add(command);
    }


    @Override
    public Iterator<IPlotterCommand> iterator() {
        return commands.iterator();
    }

    @Override
    public void execute(IPlotter plotter) {
        for (IPlotterCommand subCommand : commands) {
            subCommand.execute(plotter);
        }
    }
}
