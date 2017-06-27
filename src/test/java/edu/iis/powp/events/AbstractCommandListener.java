package edu.iis.powp.events;

import edu.iis.powp.app.Application;
import edu.iis.powp.appext.FeaturesManager;
import edu.iis.powp.command.IPlotterCommand;


/**
 * Created by user on 2017-06-27.
 */
public abstract class AbstractCommandListener {
    protected Application context;

    public AbstractCommandListener(Application context) {
        this.context = context;
        FeaturesManager.getPlotterCommandManager().getChangePublisher().clearObservers();
    }
}
