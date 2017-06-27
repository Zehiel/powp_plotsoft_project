package edu.iis.powp.command.manager;

import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.observer.Publisher;

import java.util.List;

/**
 * Created by user on 2017-06-11.
 */
public interface IPlotterCommandManager {

    public void setCurrentCommand(IPlotterCommand commandList);
    public IPlotterCommand getCurrentCommand();
    public void clearCurrentCommand();
    public Publisher getChangePublisher();
    public String getCurrentCommandString();
}