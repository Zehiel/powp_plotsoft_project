package edu.iis.powp.command.manager;

import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.observer.Publisher;

import java.util.List;

/**
 * Created by user on 2017-06-11.
 */
public abstract class AbstractPlotterCommandManager implements IPlotterCommandManager{

    protected IPlotterCommand currentCommand = null;

    protected Publisher changePublisher = new Publisher();

    @Override
    public synchronized void setCurrentCommand(IPlotterCommand commandList) {
        currentCommand = commandList;
    }

    @Override
    public synchronized IPlotterCommand getCurrentCommand() {
        return currentCommand;
    }

    @Override
    public synchronized void clearCurrentCommand() {
        currentCommand = null;
    }

    @Override
    public Publisher getChangePublisher() {
        return changePublisher;
    }

    public synchronized String getCurrentCommandString() {
        return getCurrentCommand().toString();
    }
}
