package edu.iis.powp.command.storage;

import edu.iis.powp.command.IPlotterCommand;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by grusz on 03.07.2017.
 */
public class CommandsStorage implements Serializable {

    private static CommandsStorage instance = null;
    private List<IPlotterCommand> commands ;

    private CommandsStorage(){
        commands = new ArrayList<>();
    }

    public static CommandsStorage getInstance(){
        if(instance == null){
           instance = new CommandsStorage();
        }
        return instance;
    }
    
}
