# Shep

Shep will help you keep track of your tasks.

It is based on the duke task found [here](https://github.com/nus-cs2103-AY2425S2/ip)

## Features
Shep is:

- text based
- **easy** to **learn**
- ~~Fast~~ *Super Fast* to use

## Setup

1. Download it [here]().
2. Double click it.
3. Add your tasks.
4. Let it manage your tasks for you.

## Interact

By writing into the cli like: `todo` and `mark`

## Output should look like

- [x] Download Shep
- [ ] Let Shep manage tasks for you

### If you are a Java programmer, you can use it to practice Java too. Here's the main method:
```Java
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
```

> [!NOTE]
> This is super epic and fun. :+1:!
