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
	private JTextArea currentCommandField;
	private JTextPane commandNameField;
	private JButton setPositionButton, drawToButton;
	private JTextArea observerListField;

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
//		JButton btnClearCommand = new JButton("Clear command");
//		btnClearCommand.addActionListener((ActionEvent e) -> this.clearCommand());
//		c.fill = GridBagConstraints.BOTH;
//		c.weightx = 1;
//		c.gridx = 0;
//		c.weighty = 1;
//		content.add(btnClearCommand, c);
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

		JPanel sidePanel = new JPanel();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 1;
		c.weighty = 1;
		content.add(sidePanel, c);
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
