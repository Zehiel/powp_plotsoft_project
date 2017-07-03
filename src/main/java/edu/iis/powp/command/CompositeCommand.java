package edu.iis.powp.command;

import edu.iis.client.plottermagic.IPlotter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by grusz on 06.06.2017.
 */
public class CompositeCommand implements IPlotterCommand {

    private List<IPlotterCommand> childCommands = new ArrayList<>();

    public CompositeCommand() {
        this.childCommands = new ArrayList<IPlotterCommand>();
    }

    public CompositeCommand(List<IPlotterCommand> commands) {
        this.childCommands = commands;
    }

    public CompositeCommand(IPlotterCommand command) {
        this.childCommands = new ArrayList<IPlotterCommand>();
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
            iPlotterCommand.execute(plotter);
        }
    }
}
