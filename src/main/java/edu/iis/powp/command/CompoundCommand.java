package edu.iis.powp.command;

import edu.iis.client.plottermagic.IPlotter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by grusz on 06.06.2017.
 */
public class CompoundCommand implements ICompoundCommand {

    private List<? extends IPlotterCommand> childCommands = new ArrayList<>();

    public CompoundCommand() {
        this.childCommands = new ArrayList<IPlotterCommand>();
    }

    public CompoundCommand(List<? extends IPlotterCommand> commands) {

        this.childCommands = commands;
    }

    public CompoundCommand(IPlotterCommand command) {
        this.childCommands = new ArrayList<>();
        childCommands.add(command);
    }

    public void add(IPlotterCommand iPlotterCommand){
        childCommands.add(iPlotterCommand);
    }

    public void remove(IPlotterCommand iPlotterCommand){
        childCommands.remove(iPlotterCommand);
    }

    @Override
    public void execute(IPlotter plotter) {

        for(IPlotterCommand iPlotterCommand : childCommands){
            if (iPlotterCommand instanceof ICompoundCommand) {
                ((ICompoundCommand)iPlotterCommand).execute(plotter);
            } else iPlotterCommand.execute(plotter);
        }
    }

    @Override
    public Iterator<ICompoundCommand> iterator() {
        return childCommands.iterator();
    }
}
