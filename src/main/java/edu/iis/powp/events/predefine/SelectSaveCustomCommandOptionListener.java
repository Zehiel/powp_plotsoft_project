package edu.iis.powp.events.predefine;

import edu.iis.powp.appext.FeaturesManager;
import edu.iis.powp.command.DrawToCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.SetPositionCommand;
import edu.iis.powp.command.manager.IPlotterCommandManager;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.kis.powp.drawer.shape.ILine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by grusz on 08.06.2017.
 */
public class SelectSaveCustomCommandOptionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        List<ILine> lineList = FeaturesManager.getLinesList();
        FeaturesManager.drawerController().clearPanel();
        List<IPlotterCommand> commands = new ArrayList<IPlotterCommand>();
        for(ILine line : lineList){
            commands.add(new SetPositionCommand(line.getStartCoordinateX(),line.getStartCoordinateY()));
            commands.add(new DrawToCommand(line.getEndCoordinateX(),line.getEndCoordinateY()));
        }

        PlotterCommandManager manager = (PlotterCommandManager) FeaturesManager.getPlotterCommandManager();
        manager.setCurrentCommand(commands, "Custom command");


    }
}
