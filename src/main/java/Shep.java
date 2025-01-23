public class Shep {
  public static void main(String[] args) {
    String logo =  "(`-').-> (`-').-> (`-')  _ _  (`-')\n" +
    "( OO)_   (OO )__  ( OO).-/ \\-.(OO )\n" +
    "(_)--\\_) ,--. ,'-'(,------. _.'    \\ \n" +
    "/    _ / |  | |  | |  .---'(_...--''\n" +
    "\\_..`--. |  `-'  |(|  '--. |  |_.' |\n" +
    ".-._)   \\|  .-.  | |  .--' |  .___.'\n" +
    "\\       /|  | |  | |  `---.|  |\n" +
    "`-----' `--' `--' `------'`--'\n";

    System.out.println("Hi I'm\n" + "\n" + logo + "\nDo you need me to do anything?\n");

    // Go into interaction process
    Interaction mainInteraction = new Interaction();
    mainInteraction.start();

    System.out.println("\nHappy to help. Bye bye.");
  }
}
