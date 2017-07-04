package edu.iis.powp.events.predefine;

import edu.iis.powp.appext.FeaturesManager;
import edu.iis.powp.command.DrawToCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.SetPositionCommand;
import edu.iis.powp.command.manager.IPlotterCommandManager;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.command.visitor.CommandCounter;
import edu.iis.powp.command.visitor.Visitable;
import edu.kis.powp.drawer.shape.ILine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by grusz on 08.06.2017.
 */
public class SelectShowHideMarkingsListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        CommandCounter commandCounter = FeaturesManager.getCommandCounter();
        commandCounter.showMarkings();
    }
}
