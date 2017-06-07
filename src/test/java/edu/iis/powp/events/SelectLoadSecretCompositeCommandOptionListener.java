package edu.iis.powp.events;

/**
 * Created by grusz on 06.06.2017.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import edu.iis.powp.appext.FeaturesManager;
import edu.iis.powp.command.*;
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

        CompositeCommand secondLetterS = new CompositeCommand();
        letterS.add(new SetPositionCommand(170, -50));
        letterS.add(new DrawToCommand(120, -50));
        letterS.add(new DrawToCommand(120, 0));
        letterS.add(new DrawToCommand(170, 0));
        letterS.add(new DrawToCommand(170, 50));
        letterS.add(new DrawToCommand(120, 50));


        commands.add(firstLetterI);
        commands.add(secondLetterI);
        commands.add(letterS);

        CompoundCommand superCommand = new CompoundCommand(commands);

//        List<IPlotterCommand> superCommands = new ArrayList<>();
//        superCommands.add(superCommand);
//        superCommands.add(secondLetterS);

        PlotterCommandManager manager = FeaturesManager.getPlotterCommandManager();
        manager.setCurrentCommand(superCommand, "TopSecretCommand");
    }
}