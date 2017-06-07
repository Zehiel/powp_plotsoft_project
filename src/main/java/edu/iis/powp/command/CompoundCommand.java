package edu.iis.powp.command;

import edu.iis.client.plottermagic.IPlotter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by grusz on 06.06.2017.
 */
public class CompoundCommand implements ICompoundCommand {

    private List<IPlotterCommand> childCommands;
    private String commandName = "Undefined Command Name";

    public CompoundCommand() {
        this.childCommands = new ArrayList<>();
    }

    public CompoundCommand(List<IPlotterCommand> commands) {
        this.childCommands = commands;
    }

    public void add(IPlotterCommand iPlotterCommand){
        childCommands.add(iPlotterCommand);
    }

    public void remove(IPlotterCommand iPlotterCommand){
        childCommands.remove(iPlotterCommand);
    }

    @Override
    public void setCommandName(String name) {
        commandName = name;
    }

    @Override
    public void execute(IPlotter plotter) {

        for(IPlotterCommand iPlotterCommand : childCommands){
            iPlotterCommand.execute(plotter);
        }
    }

    @Override
    public Iterator<IPlotterCommand> iterator() {
        return childCommands.iterator();
    }

    @Override
    public String toString() {
        return commandName;
    }
}
