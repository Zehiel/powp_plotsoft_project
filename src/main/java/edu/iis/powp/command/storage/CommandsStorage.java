package edu.iis.powp.command.storage;

import edu.iis.powp.command.CompositeCommand;
import edu.iis.powp.command.CompoundCommand;
import edu.iis.powp.command.IPlotterCommand;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by grusz on 03.07.2017.
 */
public class CommandsStorage implements Serializable {

    private static CommandsStorage instance = null;
    private HashMap<String, CompoundCommand> commands ;

    private CommandsStorage(){
        commands = new HashMap<>();
    }

    public static CommandsStorage getInstance(){
        if(instance == null){
           instance = new CommandsStorage();
        }
        return instance;
    }

    public void saveToFile(){
        File folder = new File( "storage" );
        folder.mkdirs();

        try( ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream( new File( folder , "commands" ) ) ) ) {
            out.writeObject( commands );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile () throws FileNotFoundException {
        try( ObjectInputStream in = new ObjectInputStream( new FileInputStream( new File( "storage" , "commands" ) ) ) ) {
            HashMap<String, CompoundCommand> read = (HashMap<String, CompoundCommand>) in.readObject();
            commands = read;

        } catch( FileNotFoundException e ){
            throw e;
        }catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setCommands(HashMap<String, CompoundCommand> commands) {
        this.commands = commands;
    }

    public HashMap<String, CompoundCommand> getCommands() {
        return commands;
    }
}
