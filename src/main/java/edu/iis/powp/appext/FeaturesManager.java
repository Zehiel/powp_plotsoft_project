package edu.iis.powp.appext;

import edu.iis.powp.app.Application;
import edu.iis.powp.app.DriverManager;
import edu.iis.powp.command.manager.LoggerCommandChangeObserver;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.events.predefine.SelectClearPanelOptionListener;
import edu.iis.powp.events.predefine.SelectSaveCustomCommandOptionListener;
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

	private static PlotterCommandManager commandManager;
	private static DriverManager driverManager;
	private static DrawPanelController drawerController;
	private static int startX, startY, endX, endY;
	public static List<ILine> linesList = new ArrayList<>();

	/**
	 * Startup configuration.
	 */
	public synchronized static void expandApplication(Application application) {
		if (!areFeaturesAdded) {
			areFeaturesAdded = true;

			driverManager = application.getDriverManager();
			setupCommandManager();

			setupDrawerPlugin(application);
		}
	}

	private static void setupCommandManager() {
		commandManager = new PlotterCommandManager();

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

		SelectSaveCustomCommandOptionListener selectSaveCustomCommandOptionListener = new SelectSaveCustomCommandOptionListener();

		drawerController = new DrawPanelController();
		application.addComponentMenu(DrawPanelController.class, "Draw Panel", 0);
		application.addComponentMenuElement(DrawPanelController.class, "Clear Panel", selectClearPanelOptionListener);
		application.addComponentMenuElement(DrawPanelController.class, "Save custom command", selectSaveCustomCommandOptionListener);
		JPanel drawerPanel = application.getFreePanel();
		drawerController.initialize(drawerPanel);


		drawerPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				startX = e.getX()-270;
				startY = e.getY()-230;
				System.out.println("Start:"+startX+startY);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				endX = e.getX()-270;
				endY = e.getY()-230;
                System.out.println("Finish:"+endX+endY);
                ILine line = LineFactory.getBasicLine();
                line.setStartCoordinates(startX,startY);
                line.setEndCoordinates(endX,endY);
                linesList.add(line);
				drawerController.drawLine(line);
			}
		});

//		drawerPanel.addMouseMotionListener(new MouseMotionAdapter() {
//			@Override
//			public void mouseDragged(MouseEvent e) {
//				super.mouseDragged(e);
//			}
//		});

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

	/**
	 * Get manager of application plotter command.
	 * 
	 * @return plotterCommandManager.
	 */
	public static PlotterCommandManager getPlotterCommandManager() {
		return commandManager;
	}

	public static List<ILine> getLinesList(){
		List<ILine> lines = new ArrayList<>(linesList);
		System.out.println("Bitch" + lines);
		linesList.clear();
		return lines;
	}
}
