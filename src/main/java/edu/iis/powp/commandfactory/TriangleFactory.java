package edu.iis.powp.commandfactory;

import java.util.ArrayList;
import java.util.List;

import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.SetPositionCommand;
import edu.iis.powp.command.CompoundCommand;

public class TriangleFactory {

    public CompoundCommand getCompoundCommand () {
        List<IPlotterCommand> commandList = new ArrayList<>();
        commandList.add(new SetPositionCommand(-120, -120));
        commandList.add(new SetPositionCommand(0, -120));
        commandList.add(new SetPositionCommand(0, 0));
        commandList.add(new SetPositionCommand(-120, -120));
        return new CompoundCommand(commandList);
    }
}