package edu.iis.powp.appext;

import edu.iis.powp.app.Application;
import edu.iis.powp.app.DriverManager;
import edu.iis.powp.command.manager.IPlotterCommandManager;
import edu.iis.powp.command.manager.LoggerCommandChangeObserver;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.events.predefine.SelectClearPanelOptionListener;
import edu.kis.powp.drawer.panel.DrawPanelController;

public class FeaturesManager {

	private static boolean areFeaturesAdded = false;

	private static IPlotterCommandManager commandManager;
	private static DriverManager driverManager;
	private static DrawPanelController drawerController;

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

		drawerController = new DrawPanelController();
		application.addComponentMenu(DrawPanelController.class, "Draw Panel", 0);
		application.addComponentMenuElement(DrawPanelController.class, "Clear Panel", selectClearPanelOptionListener);

		drawerController.initialize(application.getFreePanel());
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
}
