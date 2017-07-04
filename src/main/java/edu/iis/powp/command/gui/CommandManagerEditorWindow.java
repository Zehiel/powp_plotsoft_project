package edu.iis.powp.command.gui;


import edu.iis.powp.appext.FeaturesManager;
import edu.iis.powp.command.*;
import edu.iis.powp.command.manager.IPlotterCommandManager;
import edu.iis.powp.window.WindowComponent;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class CommandManagerEditorWindow extends JFrame implements WindowComponent {

	private IPlotterCommandManager commandManager;

	private JLabel currentCommandLabel;
	private final JLabel commandListLabel = new JLabel("Subcommand List");
	private JList commandList;

	private final JLabel commandXPosition = new JLabel("X position");
	private JTextField commandXPositionField;
	private final JLabel commandYPosition = new JLabel("Y position");
	private JTextField commandYPositionField;
	private JButton setPositionButton = new JButton("Set Position");

	private DefaultListModel listModel;
	private HashMap<String, IEditablePlotterCommand> commandMap;
	private IPlotterCommand currentCommand;

	private static final long serialVersionUID = 9204679248304669948L;

	public CommandManagerEditorWindow(IPlotterCommandManager commandManager) {
		this.commandManager = commandManager;
		commandMap = new HashMap<>();
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
		commandList = new JList(listModel);
		commandListPanel.add(commandList);
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

		c.gridy = 11;
		sidePanel.add(setPositionButton, c);



	}

	@Override
	public void HideIfVisibleAndShowIfHidden() {
		if (this.isVisible()) {
			this.setVisible(false);
		} else {
			currentCommandLabel.setText("Current Command: " + FeaturesManager.getPlotterCommandManager().getCurrentCommandString());
			this.setVisible(true);
		}
	}

}
