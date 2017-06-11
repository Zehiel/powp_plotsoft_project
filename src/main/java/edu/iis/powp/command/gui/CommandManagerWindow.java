package edu.iis.powp.command.gui;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

import edu.iis.powp.command.manager.IPlotterCommandManager;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.window.WindowComponent;

public class CommandManagerWindow extends JFrame implements WindowComponent {

	private IPlotterCommandManager commandManager;

	private final JLabel commandNameLabel = new JLabel("Command Name");
	private final JLabel commandListLabel = new JLabel("Command List");
	private JTextArea currentCommandField;
	private JTextField commandNameField;
	private JButton setPositionButton, drawToButton, clearCommandButton, saveCommandButton, useCommandButton;
	private JList commandList;
	private JTextArea observerListField;

	private DefaultListModel listModel;
	private String observerListString;

	/**
	 * 
	 */
	private static final long serialVersionUID = 9204679248304669948L;

	public CommandManagerWindow(IPlotterCommandManager commandManager) {
		this.commandManager = commandManager;
		initializeUI();
	}

	private void initializeUI() {
		this.setTitle("Command Manager");
		this.setSize(400, 400);
		Container content = this.getContentPane();
		content.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();




//		observerListField = new JTextArea("");
//		observerListField.setEditable(false);
//		c.fill = GridBagConstraints.BOTH;
//		c.weightx = 1;
//		c.gridx = 0;
//		c.weighty = 1;
//		content.add(observerListField, c);
//		updateObserverListField();
//
//		currentCommandField = new JTextArea("");
//		currentCommandField.setEditable(false);
//		c.fill = GridBagConstraints.BOTH;
//		c.weightx = 1;
//		c.gridx = 0;
//		c.weighty = 1;
//		content.add(currentCommandField, c);
//		updateCurrentCommandField();


		JPanel drawPanel = new JPanel();
		drawPanel.setBackground(Color.WHITE);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 3;
		c.gridx = 0;
		c.weighty = 1;
		content.add(drawPanel,c);

		JPanel sidePanel = new JPanel(new GridBagLayout());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 1;
		c.weighty = 1;
		content.add(sidePanel, c);

		initializeSidePanel(sidePanel);
	}

	private void initializeSidePanel(JPanel sidePanel) {
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 1;

		setPositionButton = new JButton("Set Position - Mode");
		c.gridy = 1;
		sidePanel.add(setPositionButton, c);

		drawToButton = new JButton("Draw To - Mode");
		c.gridy = 2;
		sidePanel.add(drawToButton, c);

		clearCommandButton = new JButton("Clear command");
		c.gridy = 3;
		clearCommandButton.addActionListener((ActionEvent e) -> this.clearCommand());
		sidePanel.add(clearCommandButton, c);

		commandNameLabel.setHorizontalAlignment(JLabel.CENTER);
		c.gridy = 4;
		sidePanel.add(commandNameLabel, c);

		commandNameField = new JTextField();
		c.gridy = 5;
		sidePanel.add(commandNameField, c);

		saveCommandButton = new JButton("Save Command");
		c.gridy = 6;
		sidePanel.add(saveCommandButton, c);

		commandListLabel.setHorizontalAlignment(JLabel.CENTER);
		c.gridy = 7;
		sidePanel.add(commandListLabel, c);

		JPanel commandListPanel = new JPanel(new GridLayout(1,1));
		listModel = new DefaultListModel();
		commandList = new JList(listModel);
		commandListPanel.add(commandList);
		listModel.addElement(new String("PLACEHOLDER"));
		listModel.addElement(new String("PLACEHOLDER2"));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(commandListPanel);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		c.gridy = 8;
		c.gridheight = 4;
		sidePanel.add(scrollPane, c);

		useCommandButton = new JButton("Use Command");
		c.gridy = 12;
		c.gridheight = 1;
		sidePanel.add(useCommandButton, c);
	}

	private void clearCommand() {
//		commandManager.clearCurrentCommand();
//		updateCurrentCommandField();
	}

	public void updateCurrentCommandField() {
//		currentCommandField.setText(commandManager.getCurrentCommandString());
	}


	private void updateObserverListField() {
//		observerListString = "";
//		List<Subscriber> commandChangeSubscribers = commandManager.getChangePublisher().getSubscribers();
//		for (Subscriber observer : commandChangeSubscribers) {
//			observerListString += observer.toString() + System.lineSeparator();
//		}
//		if (commandChangeSubscribers.isEmpty())
//			observerListString = "No observers loaded";
//
//		observerListField.setText(observerListString);
	}

	@Override
	public void HideIfVisibleAndShowIfHidden() {
		if (this.isVisible()) {
			this.setVisible(false);
		} else {
			this.setVisible(true);
		}
	}

}
