package edu.iis.powp.command.manager;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.observer.Publisher;

import java.util.Iterator;
import java.util.List;

/**
 * Driver Manager with support of passing not only list, but compound command in once
 */
public class TweakedPlotterCommandManager extends AbstractPlotterCommandManager
{
	/**
     * Set current command.
     *
     * @param commandList Set the command as current.
     */
    public synchronized void setCurrentCommand(IPlotterCommand commandList)
    {
    	this.currentCommand = commandList;
    	changePublisher.notifyObservers();
    }

    /**
     * Set current command.
     *
     * @param commandList list of commands representing a compound command.
     * @param name name of the command.
     */
    public synchronized void setCurrentCommand(List<IPlotterCommand> commandList, String name)
    {
    	setCurrentCommand(new ICompoundCommand() {

    		List<IPlotterCommand> plotterCommands = commandList;

			@Override
			public void execute(IPlotter plotter) {
				plotterCommands.forEach((c) -> c.execute(plotter));
			}

			@Override
			public Iterator<IPlotterCommand> iterator() {
				return plotterCommands.iterator();
			}

			@Override
			public void setCommandName(String name) {};

			@Override
			public String toString() {
				return name;
			}
		});

    }

	/**
	 * Set current command
	 *
	 * @param iCompoundCommand, set of commands packed into one compound command.
	 * @param name name of command.
	 *
     */

	public synchronized void setCurrentCommand(ICompoundCommand iCompoundCommand, String name)
	{
		iCompoundCommand.setCommandName(name);
		setCurrentCommand(iCompoundCommand);
	}

    /**
     * Return current command.
     *
     * @return Current command.
     */
    public synchronized String getCurrentCommandString() {
		if(getCurrentCommand() == null) {
			return "No command loaded";
		} else return getCurrentCommand().toString();
    }

}
