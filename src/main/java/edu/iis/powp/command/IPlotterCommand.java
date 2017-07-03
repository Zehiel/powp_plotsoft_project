package edu.iis.powp.command;

import edu.iis.client.plottermagic.IPlotter;

import java.io.Serializable;

/**
 * PlotterCommand interface.
 */
public interface IPlotterCommand extends Serializable {

    /**
     * Execute command on plotter.
     * 
     * @param plotter plotter.
     */
	public void execute(IPlotter plotter);
	
}
