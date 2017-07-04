package edu.iis.powp.command.gui;


import edu.iis.powp.appext.FeaturesManager;
import edu.iis.powp.command.*;
import edu.iis.powp.command.manager.IPlotterCommandManager;
import edu.iis.powp.window.WindowComponent;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class CommandManagerEditorWindow extends JFrame implements WindowComponent {

	private IPlotterCommandManager commandManager;

	private JLabel currentCommandLabel;
	private final JLabel commandListLabel = new JLabel("Subcommand List");
	private JList commandListUI;

	private final JLabel commandXPosition = new JLabel("X position");
	private JTextField commandXPositionField;
	private final JLabel commandYPosition = new JLabel("Y position");
	private JTextField commandYPositionField;
	private JButton setPositionButton = new JButton("Set Position");

	private DefaultListModel listModel;
	private java.util.List<IEditablePlotterCommand> commandList;
	private IPlotterCommand currentCommand;

	private static final long serialVersionUID = 9204679248304669948L;

	public CommandManagerEditorWindow(IPlotterCommandManager commandManager) {
		this.commandManager = commandManager;
		commandList = new ArrayList<>();
		initializeUI();
	}

	private void initializeUI() {
		this.setTitle("Command Editor");
		this.setSize(400, 500);
		Container content = this.getContentPane();
		content.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		JPanel sidePanel = new JPanel(new GridBagLayout());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(sidePanel, c);

		initializeSidePanel(sidePanel);
	}

	private void initializeSidePanel(JPanel sidePanel) {
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 1;

		currentCommandLabel = new JLabel("");
		c.gridy = 1;
		sidePanel.add(currentCommandLabel, c);


		commandListLabel.setHorizontalAlignment(JLabel.CENTER);
		c.gridy = 2;
		sidePanel.add(commandListLabel, c);

		JPanel commandListPanel = new JPanel(new GridLayout(1,1));
		listModel = new DefaultListModel();
		commandListUI = new JList(listModel);
		commandListUI.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int listIndex = commandListUI.getSelectedIndex();
				IEditablePlotterCommand iEditablePlotterCommand = commandList.get(listIndex);
				commandXPositionField.setText("" + iEditablePlotterCommand.getX());
				commandYPositionField.setText("" + iEditablePlotterCommand.getY());
			}
		});
		commandListPanel.add(commandListUI);
		commandListPanel.setMinimumSize(new Dimension(commandListPanel.getWidth(), 160));
		commandListPanel.setPreferredSize(new Dimension(commandListPanel.getWidth(), 160));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(commandListPanel);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		c.gridy = 3;
		c.gridheight = 4;
		sidePanel.add(scrollPane, c);

		c.gridy = 7;
		c.gridheight = 1;
		sidePanel.add(commandXPosition, c);

		commandXPositionField = new JTextField("No subcommand selected");
		c.gridy = 8;
		sidePanel.add(commandXPositionField, c);


		c.gridy = 9;
		sidePanel.add(commandYPosition, c);

		commandYPositionField = new JTextField("No subcommand selected");
		c.gridy = 10;
		sidePanel.add(commandYPositionField, c);

		setPositionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setNewPosition();
			}
		});
		c.gridy = 11;
		sidePanel.add(setPositionButton, c);



	}

	@Override
	public void HideIfVisibleAndShowIfHidden() {
		if (this.isVisible()) {
			this.setVisible(false);
		} else {
			updateView();
			this.setVisible(true);
		}
	}

	private void updateView() {
		currentCommandLabel.setText("Current Command: " + FeaturesManager.getPlotterCommandManager().getCurrentCommandString());

		IPlotterCommand currentCommand = FeaturesManager.getPlotterCommandManager().getCurrentCommand();
		if (currentCommand instanceof CompoundCommand) {
			CompoundCommand compoundCommand = (CompoundCommand) currentCommand;
			listModel.removeAllElements();
			expandCommand(compoundCommand.iterator());
		}
	}

	private void expandCommand(Iterator<IPlotterCommand> iterator) {
		while(iterator.hasNext()) {
			IPlotterCommand command = iterator.next();
			if (command instanceof CompoundCommand) {
				CompoundCommand compoundCommand = (CompoundCommand) command;
				expandCommand(compoundCommand.iterator());
			} else if (command instanceof IEditablePlotterCommand){
				IEditablePlotterCommand iEditablePlotterCommand = (IEditablePlotterCommand) command;
				commandList.add(iEditablePlotterCommand);
				listModel.addElement(iEditablePlotterCommand.toString());
			}
		}
	}

	private void setNewPosition() {
		try {
			int listIndex = commandListUI.getSelectedIndex();
			IEditablePlotterCommand iEditablePlotterCommand = commandList.get(listIndex);
			int newX = Integer.parseInt(commandXPositionField.getText());
			int newY = Integer.parseInt(commandYPositionField.getText());
			iEditablePlotterCommand.setX(newX);
			iEditablePlotterCommand.setY(newY);
		} catch (NumberFormatException nfe) {}
	}

}
