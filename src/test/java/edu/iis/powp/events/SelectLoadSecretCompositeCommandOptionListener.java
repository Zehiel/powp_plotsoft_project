package edu.iis.powp.events;

/**
 * Created by grusz on 06.06.2017.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import edu.iis.powp.appext.FeaturesManager;
import edu.iis.powp.command.CompositeCommand;
import edu.iis.powp.command.DrawToCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.SetPositionCommand;
import edu.iis.powp.command.manager.PlotterCommandManager;

public class SelectLoadSecretCompositeCommandOptionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e)
    {
        List<IPlotterCommand> commands = new ArrayList<>();

        CompositeCommand firstLetterI = new CompositeCommand();
        firstLetterI.add(new SetPositionCommand(-20, -50));
        firstLetterI.add(new DrawToCommand(-20, -50));
        firstLetterI.add(new SetPositionCommand(-20, -40));
        firstLetterI.add(new DrawToCommand(-20, 50));
        CompositeCommand secondLetterI = new CompositeCommand();
        secondLetterI.add(new SetPositionCommand(0, -50));
        secondLetterI.add(new DrawToCommand(0, -50));
        secondLetterI.add(new SetPositionCommand(0, -40));
        secondLetterI.add(new DrawToCommand(0, 50));
        CompositeCommand letterS = new CompositeCommand();
        letterS.add(new SetPositionCommand(70, -50));
        letterS.add(new DrawToCommand(20, -50));
        letterS.add(new DrawToCommand(20, 0));
        letterS.add(new DrawToCommand(70, 0));
        letterS.add(new DrawToCommand(70, 50));
        letterS.add(new DrawToCommand(20, 50));

        commands.add(firstLetterI);
        commands.add(secondLetterI);
        commands.add(letterS);

        PlotterCommandManager manager = FeaturesManager.getPlotterCommandManager();
        manager.setCurrentCommand(commands, "TopSecretCommand");
    }
}