package general;

import java.util.*;
public class SetGenMethods {
	
	
	
	public final static String[] KICKER= {"Take It All","Trading My Sorrows", "Wake","Lion and the Lamb","This is Amazing Grace"};
	
	public final static String[] PREBODY= {"Forever Reign","From the Inside Out","Stars","Calling on Fire","Always"};
	
	public final static String[] INVITATION= {"Oh Come to the Altar","Come as You Are","Good Good Father","Here's My Heart","I Surrender"};
	
	public final static String[] BODY= {"10,000 Reasons","Resurrecting","Boldly I Approach","Oceans","Wide as the Sky","The Stand",
		"This I Believe (The Creed)","Hungry","L-rd I Need You","Came to My Rescue"};
	
	SetGenMethods(){
		
	}
	
	
	
	public static String setGen(int size){
		Random rand = new Random();
	
		int index;
		if (size < 2){
			index = rand.nextInt(4) +0;
			return "\n"+"Kicker: "+KICKER[index];
		} else if (size < 3){
			index = rand.nextInt(4)+0;
			return "\n"+"Pre-Body: "+PREBODY[index] + setGen(size -1);
		}else if (size < 4){
			index = rand.nextInt(4)+0;
			return "\n"+"Invitation: "+INVITATION[index]+setGen(size-1);
		}else if (size <26){
			index = rand.nextInt(9)+0;
			return "\n"+"Body: "+BODY[index]+setGen(size-1);
		}else return "That number is out of range.";
				
		
	}

}
