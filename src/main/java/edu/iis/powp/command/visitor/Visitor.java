package edu.iis.powp.command.visitor;

import edu.iis.powp.command.CompoundCommand;
import edu.iis.powp.command.DrawToCommand;
import edu.iis.powp.command.IEditablePlotterCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.SetPositionCommand;

/**
 * Created by kyo on 04.07.2017.
 */

public interface Visitor {
    public void visit(IEditablePlotterCommand cmd);
    public void visit(CompoundCommand compoundCommand);
}

