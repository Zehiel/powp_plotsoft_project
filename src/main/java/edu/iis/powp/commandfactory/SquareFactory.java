package edu.iis.powp.commandfactory;

import java.util.ArrayList;
import java.util.List;

import edu.iis.powp.command.DrawToCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.SetPositionCommand;
import edu.iis.powp.command.CompoundCommand;

public class SquareFactory {

    public CompoundCommand getCompoundCommand () {
        List<IPlotterCommand> commandList = new ArrayList<>();
        commandList.add(new SetPositionCommand(-120, -120));
        commandList.add(new DrawToCommand(-120, 120));
        commandList.add(new DrawToCommand(120, 120));
        commandList.add(new DrawToCommand(120, -120));
        commandList.add(new DrawToCommand(-120, -120));
        return new CompoundCommand(commandList);
    }
}