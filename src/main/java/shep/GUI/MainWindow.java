package shep.GUI;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import shep.ui.Interaction;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Interaction shep;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/kurtJawa.jpg"));
    private Image shepImage = new Image(this.getClass().getResourceAsStream("/images/jimmy.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Interaction instance */
    public void setInteraction(Interaction d) {
        String logo =  "(`-').-> (`-').-> (`-')  _ _  (`-')\n"
                + "( OO)_   (OO )__  ( OO).-/ \\-.(OO )\n"
                + "(_)--\\_) ,--. ,'-'(,------. _.'    \\ \n"
                + "/    _ / |  | |  | |  .---'(_...--''\n"
                + "\\_..`--. |  `-'  |(|  '--. |  |_.' |\n"
                + ".-._)   \\|  .-.  | |  .--' |  .___.'\n"
                + "\\       /|  | |  | |  `---.|  |\n"
                + "`-----' `--' `--' `------'`--'\n";

        String welcomeMessage = ("Hi I'm\n" + "\n" + logo + "\nDo you need me to do anything?\n");

        dialogContainer.getChildren().addAll(DialogBox.getShepDialog(welcomeMessage, shepImage));

        this.shep = new Interaction();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Shep's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();

        // let shep Interaction logic handle it
        String response = shep.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getShepDialog(response, shepImage)
        );
        userInput.clear();

        if (response.equals("bye")) {
            Platform.exit();
        }
    }
}