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

		JPanel sidePanel = new JPanel(new GridLayout(8,1,2,2));
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 1;
		c.weighty = 1;
		content.add(sidePanel, c);

		initializeSidePanel(sidePanel);
	}

	private void initializeSidePanel(JPanel sidePanel) {
		setPositionButton = new JButton("Set Position - Mode");
		sidePanel.add(setPositionButton);

		drawToButton = new JButton("Draw To - Mode");
		sidePanel.add(drawToButton);

		clearCommandButton = new JButton("Clear command");
		clearCommandButton.addActionListener((ActionEvent e) -> this.clearCommand());
		sidePanel.add(clearCommandButton);

		commandNameLabel.setHorizontalAlignment(JLabel.CENTER);
		sidePanel.add(commandNameLabel);

		commandNameField = new JTextField();
		sidePanel.add(commandNameField);

		saveCommandButton = new JButton("Save Command");
		sidePanel.add(saveCommandButton);

		commandListLabel.setHorizontalAlignment(JLabel.CENTER);
		sidePanel.add(commandListLabel);

		JPanel commandListPanel = new JPanel(new GridLayout(1,1));
		listModel = new DefaultListModel();
		listModel.addElement(new String("PLACEHOLDER"));
		listModel.addElement(new String("PLACEHOLDER2"));
		JList commandList = new JList(listModel);
		commandListPanel.add(commandList);


		//JScrollPane scrollPane = new JScrollPane(commandListPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBar(new JScrollBar(Adjustable.VERTICAL));
 		scrollPane.setViewportView(scrollPane);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		sidePanel.add(scrollPane);

		useCommandButton = new JButton("Use Command");
		sidePanel.add(useCommandButton);
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
