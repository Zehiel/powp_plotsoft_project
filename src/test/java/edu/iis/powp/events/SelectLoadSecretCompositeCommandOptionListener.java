package edu.iis.powp.events;

/**
 * Created by grusz on 06.06.2017.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import edu.iis.powp.app.Application;
import edu.iis.powp.appext.FeaturesManager;
import edu.iis.powp.command.*;
import edu.iis.powp.command.manager.AbstractPlotterCommandManager;
import edu.iis.powp.command.manager.IPlotterCommandManager;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.command.manager.TweakedPlotterCommandManager;


public class SelectLoadSecretCompositeCommandOptionListener extends AbstractCommandListener implements ActionListener {

    public SelectLoadSecretCompositeCommandOptionListener(Application context) {
        super(context);
    }

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
        letterS.add(new SetPositionCommand(120, -50));
        letterS.add(new DrawToCommand(70, -50));
        letterS.add(new DrawToCommand(70, 0));
        letterS.add(new DrawToCommand(120, 0));
        letterS.add(new DrawToCommand(120, 50));
        letterS.add(new DrawToCommand(70, 50));


        commands.add(firstLetterI);
        commands.add(secondLetterI);
        commands.add(letterS);

        CompoundCommand superCommand = new CompoundCommand(commands);
        superCommand.add(secondLetterS);


        FeaturesManager.setPlotterCommandManager(new TweakedPlotterCommandManager());
        TweakedPlotterCommandManager manager = (TweakedPlotterCommandManager) FeaturesManager.getPlotterCommandManager();
        manager.setCurrentCommand(superCommand, "TopSecretCompoundCommand");
    }
}