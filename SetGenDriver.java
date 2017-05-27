package general;

import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Driver for the set generator; this will initialize the GUI and create the
 * loop for the program
 * 
 * @author Netrift and Andrew Massey
 */

@SuppressWarnings("restriction")
public class SetGenDriver extends Application {

	static int size, cont = 0;

	public static void main(String[] args) {

		
		//Terminal test
		/**Scanner scan = new Scanner(System.in);
		do {

			System.out.println("Enter set size.");
			size = scan.nextInt();

			System.out.println(SetGenMethods.setGen(size));
			System.out.println("Generate another set?");
			System.out.println("0 = yes, 1 = no");
			cont = scan.nextInt();
		} while (cont < 1);
		scan.close();*/
		launch(args);
		setController.writeSongDatabase();

	}

	@Override
	/**
	 * start - creates the GUI and presents it to the user
	 */
	public void start(Stage primaryStage) {

		Parent root = null;
		setController.readSongDatabase();
		try {
			root = FXMLLoader.load(getClass().getResource("./setGeneratorFXMLEcl.fxml"));
		} catch (IOException e) {
			System.out.println(e);
			System.exit(1);
		} // try

		primaryStage.setTitle("Netrift's Set Generator 2.0");
		primaryStage.setScene(new Scene(root, 618, 400));
		primaryStage.setResizable(true);
		primaryStage.show();
		
		

	} // start

}