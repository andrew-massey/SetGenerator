package general;

import javafx.event.ActionEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.io.IOException;


@SuppressWarnings("restriction")
public class setController {
	Stage helpStage, addStage, delStage;
	 
	public static int setSize = 0;
	
	public static String set;

	public static String[] KICKER = {};
	public static String[] PREBODY = {};
	public static String[] INVITATION = {};
	public static String[] BODY = {};
	
	public static String setGen(int size) {
		Random rand = new Random();

		int index;
		if (size < 2) {
			index = rand.nextInt(KICKER.length) + 0;
			return "\n" + "Kicker: " + KICKER[index];
		} else if (size < 3) {
			index = rand.nextInt(PREBODY.length) + 0;
			return "\n" + "Pre-Body: " + PREBODY[index] + setGen(size - 1);
		} else if (size < 4) {
			index = rand.nextInt(INVITATION.length) + 0;
			return "\n" + "Invitation: " + INVITATION[index] + setGen(size - 1);
		} else if (size < 26) {
			index = rand.nextInt(BODY.length) + 0;
			return "\n" + "Body: " + BODY[index] + setGen(size - 1);
		}else if(size == 0){
			return "Cool, we get a night off!";
		}  
		
		else
			return "Dude, that's WAY too many songs for one night.";

	}

	@FXML
    private AnchorPane entry;

    @FXML
    private TextArea promptField;

    @FXML
    private ScrollPane setPane;

    @FXML
    private AnchorPane anchorText;

    @FXML
    private TextArea setView;

    @FXML
    private TextField setSizeText;

    @FXML
    private MenuBar menu;

    @FXML
    private Menu fileMenu;

    @FXML
    private MenuItem closeMenu;

    @FXML
    private MenuItem exitMenu;

    @FXML
    private MenuItem addMenu;
    
    @FXML
    private MenuItem deleteMenu;
    
    @FXML
    private MenuItem helpMenu;

    @FXML
    private Button generator;

    @FXML
    void exitProgram(ActionEvent event) {
    	setController.writeSongDatabase();
    	
    	Platform.exit();
    }
	
	@FXML
    void setTheValue(ActionEvent event) {
		try{
			setSize = Integer.parseInt(setSizeText.getText());
			set = setGen(setSize);
			}
		catch(NumberFormatException e){
			String derp = setSizeText.getText();
			
			if(setSizeText.getText().equalsIgnoreCase("")){set = "Cool, we get a night off!"; }
			else{
				set = "Bruh, what do you mean you only want part of a song?";}
			}
		setView.setText(set);
		
    }
	@FXML
	void loadHelp(){
		helpStage = new Stage();
		Parent root = null;

		try {
			root = FXMLLoader.load(getClass().getResource("./helpFX.fxml"));
		} catch (IOException e) {
			System.out.println(e);
			System.exit(1);
		} // try

		helpStage.setTitle("Boi, it's so simple");
		helpStage.setScene(new Scene(root, 600, 400));
		helpStage.setResizable(true);
		helpStage.show();
	}
	
	@FXML
    void loadAdd(ActionEvent event) {
		addStage = new Stage();
		Parent addRoot = null;

		try {
			addRoot = FXMLLoader.load(getClass().getResource("./addSongWin.fxml"));
		} catch (IOException e) {
			System.out.println(e);
			System.exit(1);
		} // try

		addStage.setTitle("");
		addStage.setScene(new Scene(addRoot, 600, 400));
		addStage.setResizable(true);
		addStage.show();
    }

    @FXML
    void loadDelete(ActionEvent event) {
    	delStage = new Stage();
		Parent delRoot = null;

		try {
			delRoot = FXMLLoader.load(getClass().getResource("./removeSongWin.fxml"));
		} catch (IOException e) {
			System.out.println(e);
			System.exit(1);
		} // try

		delStage.setTitle("");
		delStage.setScene(new Scene(delRoot, 600, 400));
		delStage.setResizable(true);
		delStage.show();
    }
    
    @FXML
	public static void writeSongDatabase() {
		//create a blank song list
    	PrintWriter songList = null;
    	
    	try {
    		songList = new PrintWriter("songList.txt");
		} catch (FileNotFoundException e) {
			System.close();
		}
		
		//write the info into the list
		//Kickers
    	for(int i = 0; i< KICKER.length;i++){
    		songList.println("Kicker: "+KICKER[i]);
    	}
    	
   
		
    	//Prebodies
    	for(int i = 0; i< PREBODY.length;i++){
    		songList.println("Prebody: "+PREBODY[i]);
    	}
    	
    	
    	//Bodies
    	for(int i = 0; i< BODY.length;i++){
    		songList.println("Body: "+BODY[i]);
    	}
    	
    	
    	//Invitations
    	for(int i = 0; i< INVITATION.length;i++){
    		songList.println("Invitation: "+INVITATION[i]);
    	}
    	songList.close();
	}
    
    @FXML
	public static void readSongDatabase() {
    	String inputName = "songList.txt";
    	Scanner inStream = null;
    	try {
			inStream = new Scanner(new File(inputName));
		} catch (FileNotFoundException e) {
		}
    	
    	while(inStream.hasNextLine()){
    		String line = inStream.nextLine();
    		int kickCount = 0, bodyCount = 0, preCount = 0, invCount = 0;
    		switch(line.substring(0, line.indexOf(':'))){
    		case "Kicker":
    			String[] kickTemp = new String[KICKER.length];
    			copyArr(KICKER, kickTemp);
    			KICKER = new String[kickTemp.length+1];
    			copyArr(kickTemp,KICKER);
    			KICKER[KICKER.length-1] = line.substring(8);
    			break;
    		case "Body":
    			String[] bodTemp = new String[BODY.length];
    			copyArr(BODY, bodTemp);
    			BODY = new String[bodTemp.length+1];
    			copyArr(bodTemp,BODY);
    			BODY[BODY.length-1] = line.substring(6);
    			break;
    		case "Prebody":
    			String[] preTemp = new String[PREBODY.length];
    			copyArr(PREBODY, preTemp);
    			PREBODY = new String[preTemp.length+1];
    			copyArr(preTemp,PREBODY);
    			PREBODY[PREBODY.length-1] = line.substring(9);
    			break;
    		case "Invitation":
    			String[] invTemp = new String[INVITATION.length];
    			copyArr(INVITATION, invTemp);
    			INVITATION = new String[invTemp.length+1];
    			copyArr(invTemp,INVITATION);
    			INVITATION[INVITATION.length-1] = line.substring(12);
    			break;
    		
    		}
    	}
    }
    /**
     *	copyArr(String[] songType, String[] temp) - copies list from String[] 1 
     *	and sends it to String[] 2  
     *	
     *	@args
     *	songType - usually the array of songs for that type
     *	temp - the array to be copied to
     */
	private static void copyArr(String[] songType, String[] temp) {
		for(int i = 0 ; i<songType.length; i++){
			temp[i] = songType[i];
		}
	}
	
	@FXML
	private TextField SongNameInput;

	@FXML
	private TextField SongTypeInput;

	@FXML
	private Button addButton;

	@FXML
	void addSong(ActionEvent event) {
		String type = SongTypeInput.getText();
		String[] temp = null;
		if(type.equalsIgnoreCase("kicker")){
			temp = new String[KICKER.length];
			copyArr(KICKER,temp);
			KICKER = new String[temp.length+1];
			copyArr(temp,KICKER);
			KICKER[KICKER.length-1] = SongNameInput.getText();
		}
		else if(type.equalsIgnoreCase("prebody")|type.equalsIgnoreCase("pre-body")){
			temp = new String[PREBODY.length];
			copyArr(PREBODY,temp);
			PREBODY = new String[temp.length+1];
			copyArr(temp,PREBODY);
			PREBODY[PREBODY.length-1] = SongNameInput.getText();
		}
		else if(type.equalsIgnoreCase("body")){
			temp = new String[BODY.length];
			copyArr(BODY,temp);
			BODY = new String[temp.length+1];
			copyArr(temp, BODY);
			BODY[BODY.length-1] = SongNameInput.getText();
		}
		else if(type.equalsIgnoreCase("invitation")){
			temp = new String[INVITATION.length];
			copyArr(INVITATION,temp);
			INVITATION = new String[temp.length+1];
			copyArr(temp, INVITATION);
			INVITATION[INVITATION.length-1] = SongNameInput.getText();
		}
		else
		{
		}
		
		addStage = null;
	}
	
	@FXML
    private ScrollPane removeScroller;

    @FXML
    private TextArea removeSongList;

    @FXML
    private TextField removeInput;

    @FXML
    private TextArea removePrompt;

    @FXML
    private Button removeButton;

    @FXML
    void removeSong(ActionEvent event) {
    	String song = removeInput.getText();
    	int x = 0;
    	try{x = Integer.parseInt(song);}
    	catch(Exception e){
    		//Let's assume they typed the song name in
    		for(int i = 0; i<KICKER.length; i++){
    			if(song.equalsIgnoreCase(KICKER[i])){
    				delArr(KICKER, i);
    				return;
    			}
    		}
    		for(int i = 0; i<BODY.length; i++){
    			if(song.equalsIgnoreCase(BODY[i])){
    				delArr(BODY, i);
    				return;
    			}
    		}
    		for(int i = 0; i<PREBODY.length; i++){
    			if(song.equalsIgnoreCase(PREBODY[i])){
    				delArr(PREBODY, i);
    				return;
    			}
    		}
    		for(int i = 0; i<INVITATION.length; i++){
    			if(song.equalsIgnoreCase(INVITATION[i])){
    				delArr(INVITATION, i);
    				return;
    			}
    		}
    	}
    	/**	I'm also gonna assume the printed string in the scroller is gonna be in 
    	*	order and printed from the arrays
    	*/
    	
    	//There'll be multiple if scenarios to reduce the number down to what is needed for removing the song
    	//After that, I'll have a method to "delete" the single item from the array
    	
    	//If statements to determine which array the nuber REALLY belongs to
    	
    	//delArr(arrayChosen, x)
    }

	private void delArr(String[] s, int i) {
		String[] temp = new String[s.length-1];
		for(int x = 0; x<temp.length; x++){
			if(x==i){continue;}
			else{temp[x]=s[x];}
		}
		s = new String[temp.length];
		for(int x = 0;x<s.length;x++){
			s[x] = temp[x];
		}
	}

}
