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

    private static int offsetX = -10, offsetY = 10;

    public CommandCounter(JPanel jPanel) {
        panel = jPanel;
        labels = new ArrayList<>();
    }

    @Override
    public void visit(IEditablePlotterCommand cmd) {
        selectAppropiateOffset(cmd);
        JLabel label = new JLabel("" + (labels.size() + 1));
        label.setLocation(cmd.getX() + panel.getWidth()/2 + offsetX, cmd.getY() + panel.getHeight()/2 + offsetY);
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

    private void selectAppropiateOffset(IEditablePlotterCommand cmd) {
        if (cmd.getX() > 0) {
            offsetX = 10;
        } else offsetX = -10;

        if (cmd.getY() > 0) {
            offsetY = 10;
        } else offsetY = -10;
    }

}
