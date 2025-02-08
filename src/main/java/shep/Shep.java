package shep;

import shep.ui.Interaction;

// don't use this class for now
public class Shep {
    public static void main(String[] args) {
        String logo =  "(`-').-> (`-').-> (`-')  _ _  (`-')\n"
                + "( OO)_   (OO )__  ( OO).-/ \\-.(OO )\n"
                + "(_)--\\_) ,--. ,'-'(,------. _.'    \\ \n"
                + "/    _ / |  | |  | |  .---'(_...--''\n"
                + "\\_..`--. |  `-'  |(|  '--. |  |_.' |\n"
                + ".-._)   \\|  .-.  | |  .--' |  .___.'\n"
                + "\\       /|  | |  | |  `---.|  |\n"
                + "`-----' `--' `--' `------'`--'\n";

        System.out.println("Hi I'm\n" + "\n" + logo + "\nDo you need me to do anything?\n");

        // Go into interaction process
        Interaction mainInteraction = new Interaction();

        System.out.println("\nHappy to help. Bye bye.");
    }

     /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return "Shep heard: " + input;
    }
}
