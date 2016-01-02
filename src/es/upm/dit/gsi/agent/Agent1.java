package es.upm.dit.gsi.agent;

import java.util.List;

import edu.wpi.cetask.*;
import edu.wpi.disco.*;
import edu.wpi.disco.Agenda.Plugin;
import edu.wpi.disco.lang.*;

/**
 * This class illustrates the appropriate methods to use to embed Disco 
 * as a component of a larger software system.  
 * <p>
 * Important note re threads:  This example is thread-safe because it only
 * uses methods documented as thread-safe in {@link Interaction} and {@link Actor}.
 * (Note newInstance() methods for built-in utterance types are all thread-safe.)
 * <p>
 * Important note re turn-taking: Disco does not include a built-in algorithm for
 * turn-taking, as this is a complex topic and typically depends heavily on the
 * application context, e.g., spoken versus text versus graphical interfaces.
 * (The Disco debugging console provides a simple "automatic" mode in which each
 * user turn consists of zero or more non-utterance actions terminated by an 
 * utterance---this can be turned off using the 'next false' command---see
 * console 'help'). 
 * <p>
 * Invoke the main method below to run this example.
 */
public class Agent1 {

   public  void main (String[] args) { new Agent1(); }
   
   private final Interaction interaction = new Interaction(new MyAgent("agent"), new User("user"));
   private final Disco disco = interaction.getDisco(); 
   private final boolean 
         guess = interaction.getProperty("interaction@guess", true),
         retry = interaction.getProperty("interaction@retry", true);
   
   public void lorena() {
         
      // note the try block below creating ConsoleWindow is optional and creates a Disco
      // console window that runs in parallel with the embedding system. This is convenient
      // for debugging, such as using the 'history' or 'status' commands and using
      // 'eval' to check or set application state.
    //  try (ConsoleWindow window = new ConsoleWindow(interaction, 600, 500, 14)) {
         
       //  window.setVisible(true);

         // the code below is running on the *main* thread, but think of this as an
         // example of the appropriate thread in the embedding application

         // using example task model from Disco source release
        //interaction.load("models/Library.xml");//(CAMBIAAR)

         // typically the embedding system is calling Disco inside of a loop,
         // but here we just have straight-line code to illustrate some
         // typical calls

         // user utterance, e.g., from speech recognition or GUI
       

         // example of generating menu of possible user utterances, e.g., for GUI
         // or to restrict grammar for speech recognition

         // print out formatted choices on system console
    

         // if optional Disco console window used, go to window now and try typing in 
         // commands, such as 'history'
      
         // next line is here only to keep optional Disco console window open in 
         // this demo until you type 'quit' or close it
         try { interaction.join(); } catch (InterruptedException e) {}
      }
//}
   
public void interact(){
   user(Propose.Should.newInstance(disco, true, newInstance("Borrow")),
           // null argument allows plan recognition to determine contributes
           null); 

     // agent response
     agent();}

   public boolean agent () {      
      // see simple model for agent turn at Agent.respond()
      return interaction.getSystem().respond(interaction, false, guess, retry);
   }
   
   public void user (Task task, Plan contributes) {
      interaction.occurred(true, task, contributes);
   }
   
   private Task newInstance (String task) { 
      return interaction.getTaskClass(task).newInstance();
   }
   
  public class MyAgent extends Agent {
      
     public MyAgent (String name) { super(name); }
      
      @Override
      public void say (Interaction interaction, Utterance utterance) {
         // here is where you would put natural language generation
         // and/or pass utterance string to TTS or GUI
         // for now we just call Disco's default formatting and print
         // out result on system console
         System.out.println("AGENT: "+interaction.format(utterance));
      }
   }
}