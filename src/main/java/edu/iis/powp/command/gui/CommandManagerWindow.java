package edu.iis.powp.command.gui;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import javax.swing.*;

import edu.iis.powp.appext.FeaturesManager;
import edu.iis.powp.command.*;
import edu.iis.powp.command.manager.IPlotterCommandManager;
import edu.iis.powp.observer.Subscriber;
import edu.iis.powp.window.WindowComponent;
import edu.kis.powp.drawer.shape.ILine;

public class CommandManagerWindow extends JFrame implements WindowComponent {

	private IPlotterCommandManager commandManager;

	private final JLabel commandNameLabel = new JLabel("Command Name");
	private final JLabel commandListLabel = new JLabel("Command List");
	private JTextArea currentCommandField = new JTextArea("");
	private JTextField commandNameField;
	private JButton loadFromFileButton, saveToFileButton, clearCommandButton, saveCommandButton, useCommandButton;
	private JList commandList;
	private JTextArea observerListField;

	private DefaultListModel listModel;
	private HashMap<String, CompositeCommand> commandMap;
	private IPlotterCommand currentCommand;
	private String observerListString;

	private static final long serialVersionUID = 9204679248304669948L;

	public CommandManagerWindow(IPlotterCommandManager commandManager) {
		this.commandManager = commandManager;
		commandMap = new HashMap<>();
		initializeUI();
	}

	private void initializeUI() {
		this.setTitle("Command Manager");
		this.setSize(400, 400);
		Container content = this.getContentPane();
		content.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		JPanel leftPanel = new JPanel(new GridBagLayout());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(leftPanel,c);

		JPanel rightPanel = new JPanel(new GridBagLayout());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 1;
		c.weighty = 1;
		content.add(rightPanel, c);

		initializeLeftPanel(leftPanel);
		initializeRightPanel(rightPanel);
	}

	private void initializeLeftPanel(JPanel leftPanel) {
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 1;

		loadFromFileButton = new JButton("Load from file");
		c.gridy = 1;
		leftPanel.add(loadFromFileButton, c);

//		saveToFileButton = new JButton("Save to file");
//		c.gridy = 2;
//		leftPanel.add(saveCommandButton, c);

//		observerListField = new JTextArea("");
//		observerListField.setEditable(false);
//		c.weightx = 1;
//		c.gridx = 3;
//		//content.add(observerListField, c);
//		updateObserverListField();
//
//		currentCommandField = new JTextArea("");
//		currentCommandField.setEditable(false);
//		c.fill = GridBagConstraints.BOTH;
//		c.weightx = 1;
//		c.gridx = 4;
//		//content.add(currentCommandField, c);
//		updateCurrentCommandField();
	}

	private void initializeRightPanel(JPanel rightPanel) {
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 1;

		clearCommandButton = new JButton("Clear command");
		c.gridy = 1;
		clearCommandButton.addActionListener((ActionEvent e) -> this.clearCommand());
		rightPanel.add(clearCommandButton, c);

		commandNameLabel.setHorizontalAlignment(JLabel.CENTER);
		c.gridy = 2;
		rightPanel.add(commandNameLabel, c);

		commandNameField = new JTextField();
		c.gridy = 3;
		rightPanel.add(commandNameField, c);

		saveCommandButton = new JButton("Save Command");
		c.gridy = 4;
		saveCommandButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveCustomCommand();
			}
		});
		rightPanel.add(saveCommandButton, c);

		commandListLabel.setHorizontalAlignment(JLabel.CENTER);
		c.gridy = 5;
		rightPanel.add(commandListLabel, c);

		JPanel commandListPanel = new JPanel(new GridLayout(1,1));
		listModel = new DefaultListModel();
		commandList = new JList(listModel);
		commandListPanel.add(commandList);
		commandListPanel.setMinimumSize(new Dimension(commandListPanel.getWidth(), 160));
		commandListPanel.setPreferredSize(new Dimension(commandListPanel.getWidth(), 160));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(commandListPanel);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		c.gridy = 6;
		c.gridheight = 4;
		rightPanel.add(scrollPane, c);

		useCommandButton = new JButton("Use Command");
		c.gridy = 10;
		useCommandButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadCustomCommand();
			}
		});
		c.gridheight = 1;
		rightPanel.add(useCommandButton, c);
	}

	private void clearCommand() {
		commandManager.clearCurrentCommand();
		updateCurrentCommandField();
	}

	public void updateCurrentCommandField() {
		currentCommandField.setText(commandManager.getCurrentCommandString());
	}

	public void deleteObservers() {
		commandManager.getChangePublisher().clearObservers();
		this.updateObserverListField();
	}


	private void updateObserverListField() {
		observerListString = "";
		List<Subscriber> commandChangeSubscribers = commandManager.getChangePublisher().getSubscribers();
		for (Subscriber observer : commandChangeSubscribers) {
			observerListString += observer.toString() + System.lineSeparator();
		}
		if (commandChangeSubscribers.isEmpty())
			observerListString = "No observers loaded";

		observerListField.setText(observerListString);
	}

	@Override
	public void HideIfVisibleAndShowIfHidden() {
		if (this.isVisible()) {
			this.setVisible(false);
		} else {
			this.setVisible(true);
		}
	}

	private void saveCustomCommand() {
		String commandName;
		if ((commandName = commandNameField.getText()).isEmpty() || commandMap.containsKey(commandName)) return;
		commandNameField.setText("");

		List<ILine> lineList = FeaturesManager.getLinesList();
		FeaturesManager.drawerController().clearPanel();
		List<IPlotterCommand> commands = new ArrayList<>();
		for(ILine line : lineList){
			commands.add(new SetPositionCommand(line.getStartCoordinateX(),line.getStartCoordinateY()));
			commands.add(new DrawToCommand(line.getEndCoordinateX(),line.getEndCoordinateY()));
		}

		CompositeCommand compositeCommand = new CompositeCommand(commands);
		commandMap.put(commandName, compositeCommand);
		listModel.addElement(commandName);

	}

	private void loadCustomCommand() {
		String commandName;
		if ((commandName = commandList.getSelectedValue().toString()) == null) return;
		currentCommand = commandMap.get(commandName);
		FeaturesManager.getPlotterCommandManager().setCurrentCommand(currentCommand);
	}

}
