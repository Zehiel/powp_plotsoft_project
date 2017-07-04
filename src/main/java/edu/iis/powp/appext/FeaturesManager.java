package edu.iis.powp.appext;

import edu.iis.powp.app.Application;
import edu.iis.powp.app.DriverManager;
import edu.iis.powp.command.manager.IPlotterCommandManager;
import edu.iis.powp.command.manager.LoggerCommandChangeObserver;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.command.visitor.CommandCounter;
import edu.iis.powp.events.predefine.SelectClearPanelOptionListener;
import edu.iis.powp.events.predefine.SelectShowHideMarkingsListener;
import edu.kis.powp.drawer.panel.DrawPanelController;
import edu.kis.powp.drawer.shape.ILine;
import edu.kis.powp.drawer.shape.LineFactory;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class FeaturesManager {

	private static boolean areFeaturesAdded = false;

	private static IPlotterCommandManager commandManager;
	private static DriverManager driverManager;
	private static DrawPanelController drawerController;
	private static int startX, startY, endX, endY;
	private static List<ILine> linesList = new ArrayList<>();
	private static JPanel drawerPanel;
	private static CommandCounter commandCounter;

	/**
	 * Startup configuration.
	 */
	public synchronized static void expandApplication(Application application) {
		if (!areFeaturesAdded) {
			areFeaturesAdded = true;

			driverManager = application.getDriverManager();
			setupCommandManager(new PlotterCommandManager());

			setupDrawerPlugin(application);
		}
	}

	public synchronized static void expandApplication(Application application, IPlotterCommandManager commandManager) {
		if (!areFeaturesAdded) {
			areFeaturesAdded = true;

			driverManager = application.getDriverManager();
			setupCommandManager(commandManager);

			setupDrawerPlugin(application);
		}
	}

	private static void setupCommandManager(IPlotterCommandManager newCommandManager) {
		commandManager = newCommandManager;

		LoggerCommandChangeObserver loggerObserver = new LoggerCommandChangeObserver();
		commandManager.getChangePublisher().addSubscriber(loggerObserver);
	}

	/**
	 * Setup Drawer Plugin and add to context.
	 * 
	 * @param application
	 *            Application context.
	 */
	private static void setupDrawerPlugin(Application application) {
		SelectClearPanelOptionListener selectClearPanelOptionListener = new SelectClearPanelOptionListener();

		SelectShowHideMarkingsListener selectShowHideMarkingsListener = new SelectShowHideMarkingsListener();

		JPanel drawerPanel = application.getFreePanel();
		drawerController = new DrawPanelController();
		commandCounter = new CommandCounter(drawerPanel);

		application.addComponentMenu(DrawPanelController.class, "Draw Panel", 0);
		application.addComponentMenuElement(DrawPanelController.class, "Clear Panel", selectClearPanelOptionListener);
		application.addComponentMenuElement(DrawPanelController.class, "Show/hide markings", selectShowHideMarkingsListener);

		drawerController.initialize(drawerPanel);


		drawerPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				startX = e.getX()-270;
				startY = e.getY()-230;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				endX = e.getX()-270;
				endY = e.getY()-230;
                ILine line = LineFactory.getBasicLine();
                line.setStartCoordinates(startX,startY);
                line.setEndCoordinates(endX,endY);
                linesList.add(line);
				drawerController.drawLine(line);
			}
		});


	}

	/**
	 * Get controller of application drawing panel.
	 * 
	 * @return drawPanelController.
	 */
	public static DrawPanelController drawerController() {
		return drawerController;
	}

	/**
	 * Get manager of application driver.
	 * 
	 * @return driverManager.
	 */
	public static DriverManager getDriverManager() {
		return driverManager;
	}

	public static void setPlotterCommandManager(IPlotterCommandManager iPlotterCommandManager) {
		FeaturesManager.commandManager = iPlotterCommandManager;
	}

	/**
	 * Get manager of application plotter command.
	 * 
	 * @return plotterCommandManager.
	 */
	public static IPlotterCommandManager getPlotterCommandManager() {
		return commandManager;
	}

	public static List<ILine> getLinesList(){
		List<ILine> lines = new ArrayList<>(linesList);
		linesList.clear();
		return lines;
	}

	public static CommandCounter getCommandCounter() {
		return commandCounter;
	}
}
