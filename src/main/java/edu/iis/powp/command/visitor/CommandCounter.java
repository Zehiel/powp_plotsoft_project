package edu.iis.powp.command.visitor;

import edu.iis.powp.appext.FeaturesManager;
import edu.iis.powp.command.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by user on 2017-07-04.
 */
public class CommandCounter implements edu.iis.powp.command.visitor.Visitor {
    JPanel panel;
    List<JLabel> labels;

    private final static int offsetX = -20, offsetY = 20;

    public CommandCounter(JPanel jPanel) {
        panel = jPanel;
        labels = new ArrayList<>();
    }

    @Override
    public void visit(IEditablePlotterCommand cmd) {
        JLabel label = new JLabel("" + (labels.size() + 1));
        label.setLocation(100, 100);
        label.setSize(50, 25);
        labels.add(label);
    }

    @Override
    public void visit(CompoundCommand cmd) {
        Iterator<IPlotterCommand> commandIterator = cmd.iterator();
        while (commandIterator.hasNext()) {
            IPlotterCommand command = commandIterator.next();
            if (command instanceof CompoundCommand) {
                CompoundCommand compoundCommand = (CompoundCommand) command;
                compoundCommand.accept(this);
            }

            if (command instanceof IEditablePlotterCommand) {
                IEditablePlotterCommand editableCommand = (IEditablePlotterCommand) command;
                editableCommand.accept(this);
            }
        }
    }

    public void showMarkings() {
        IPlotterCommand currentCommand = FeaturesManager.getPlotterCommandManager().getCurrentCommand();
        if (currentCommand instanceof Visitable) {
            Visitable visitableCommand = (Visitable) currentCommand;
            visitableCommand.accept(this);
            for (JLabel label : labels) {
                panel.add(label);
            }
        }
    }

}
