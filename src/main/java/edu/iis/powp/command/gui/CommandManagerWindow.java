package edu.iis.powp.command.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.*;

import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.observer.Subscriber;
import edu.iis.powp.window.WindowComponent;

public class CommandManagerWindow extends JFrame implements WindowComponent {

	private PlotterCommandManager commandManager;

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

	public CommandManagerWindow(PlotterCommandManager commandManager) {
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
//

//
//		JButton btnClearObservers = new JButton("Delete observers");
//		btnClearObservers.addActionListener((ActionEvent e) -> this.deleteObservers());
//		c.fill = GridBagConstraints.BOTH;
//		c.weightx = 1;
//		c.gridx = 0;
//		c.weighty = 1;
//		content.add(btnClearObservers, c);

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

		setPositionButton = new JButton("Set Position - Mode");
		c.insets = new Insets(3,3,3,3);
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 0;
		c.weighty = 1;
		sidePanel.add(setPositionButton, c);

		drawToButton = new JButton("Draw To - Mode");
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 1;
		c.weighty = 1;
		sidePanel.add(drawToButton, c);

		clearCommandButton = new JButton("Clear command");
		clearCommandButton.addActionListener((ActionEvent e) -> this.clearCommand());
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 3;
		c.weighty = 1;
		sidePanel.add(clearCommandButton, c);

		commandNameLabel.setHorizontalAlignment(JLabel.CENTER);
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 4;
		c.weighty = 1;
		sidePanel.add(commandNameLabel, c);

		commandNameField = new JTextField();
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 5;
		c.weighty = 1;
		sidePanel.add(commandNameField, c);

		saveCommandButton = new JButton("Save Command");
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 6;
		c.weighty = 1;
		sidePanel.add(saveCommandButton, c);

		commandListLabel.setHorizontalAlignment(JLabel.CENTER);
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 7;
		c.weighty = 1;
		sidePanel.add(commandListLabel, c);

		listModel = new DefaultListModel();
		commandList = new JList(listModel);
		listModel.addElement(new String("PLACEHOLDER"));
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 8;
		c.weighty = 1;
		sidePanel.add(saveCommandButton, c);

		useCommandButton = new JButton("Use Command");
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 9;
		c.weighty = 1;
		sidePanel.add(useCommandButton, c);
	}

	private void clearCommand() {
//		commandManager.clearCurrentCommand();
//		updateCurrentCommandField();
	}

	public void updateCurrentCommandField() {
//		currentCommandField.setText(commandManager.getCurrentCommandString());
	}

	public void deleteObservers() {
//		commandManager.getChangePublisher().clearObservers();
//		this.updateObserverListField();
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
//		updateObserverListField();
		if (this.isVisible()) {
			this.setVisible(false);
		} else {
			this.setVisible(true);
		}
	}

}
