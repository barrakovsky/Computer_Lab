/*Name:Bar Rakovsky
* Due Date: 07/17/18
* project 4: Computer Lab
* 
* In this project we had to write a program that will simulate a few functions of administrator person in several computer labs. 
* Since every lab suppose to have the same attributes, I decided to create an object of a computer lab that will hold all the attributes and functions for each computer lab.
* From the main method I created an array of a computer lab. 
* The main method is responsible for the menu we were requested to create. 
* Each case leads to different task: quit, login, log off and search. 
* There is a default case- if the user doesn't choose the valid option from the menu it will ask the user to choose again. 
*/

import java.util.Scanner;


public class ComputerLabMain {

	public static void main(String[] args) {
		
		ComputerLab lab1 = new ComputerLab(5,1);
		ComputerLab lab2 = new ComputerLab(6,2);
		ComputerLab lab3 = new ComputerLab(4,3);
		ComputerLab lab4 = new ComputerLab(3,4);
		
		
		ComputerLab[] labsArray = {lab1, lab2, lab3, lab4};
		
		boolean cont = true; 
		Scanner sc = new Scanner(System.in); 
		
		while(cont){
			
			System.out.println("Main Menu");
			System.out.println("0) Quit\n"
					+ "1) Simulate login\n"
					+ "2) Simulate logoff\n"
					+ "3) Search\n"
					+ "4) Lab Status");
			
			if(sc.hasNextInt()){
				int menuOption = sc.nextInt();
				switch (menuOption){
				case 0:
					System.out.println("Goodbye");
					cont = false; 
					break; 
				case 1: 
					login(sc,labsArray);
					break; 
				case 2: 
					logoff(sc,labsArray);
					break; 
				case 3: 
					search(sc,labsArray);
					break;
				case 4:
					labStatus(labsArray);
					break;
				default: 
					System.out.println("\nPlease choose a valid menu choice 0-4");
					break;
				}
				
			}else{
				System.out.println("Please choose a valid menu choice 0-4");
				sc.next();
			}
			
			
			
		}
		
		sc.close();
	
		

	}
	
	/*This method is responsible to validate the login of user to a specific computer lab and computer - the function of the login is part of the computer lab object.
	 * 1-to start the needs to enter a valid user ID with 5 digit ID. -This method checks for validity of this condition.
	 * 2-the method checks if the user is already logged in- if this user ID is logged in, the second login is blocked and the user is requested to enter a different student ID. 
	 * 3-the user is being requested to enter a valid lab number- if he failed, he is being requested again to enter a lab number. 
	 * 4- the user is being requested to enter a computer number- if he enter a valid computer number for the lab he chose, the program checks if the requested computer is "Empty". 
	 * 		If the computer is available- if the computer is available, the program logs in the user, if not, the program requests from the user to choose a different computer. 
	 * 
	 * I have 3 different do-while loop that controls all the repetition of the program. 
	 * I made sure to not hard code any of the indexes since if someone will add a computer in one of the labs or even add a lab to the lab array the code will be adjusted accordingly. 
	*/
	public static void login(Scanner scn, ComputerLab[] labsA){
		
		String userID; 
		int index = -1; 
		boolean cont1 = true; 
		boolean cont2 = true;
		boolean cont3 = true; 
		int computerLabIndex;
		int computerIndex;
		String checkIfEmpty;
		
		do{
			
			System.out.println("Enter the 5 digit ID number of the user logging in");
			userID = scn.next();
			
			if(userID.matches("[0-9]+") && userID.length() == 5 ){
				
				for(int i = 0; i < labsA.length; i++ ){
					index = labsA[i].linearSearch(userID);
					if(index != -1){
						System.out.println("The user is already logged on in lab #" + (i+1) + " on computer #" + (index+1)+ ".\n" +
								"please enter a different student ID.\n");
						i = labsA.length;
					}
				}
				
				if(index == -1){
					
					do{
						System.out.println("Enter the lab number the user is logging in from (1-" + labsA.length + ")");
						if(!scn.hasNextInt()){
							System.out.println("You didn't enter a valid chioce, please enter a choice between 1-" + labsA.length + "\n");
							scn.next();
						}else{
							computerLabIndex = scn.nextInt();
							if(computerLabIndex  > labsA.length || computerLabIndex == 0){
								System.out.println("You didn't enter a valid chioce, please enter a choice between 1-" + labsA.length + "\n");
								
							}else{
								
								do{
									System.out.println("Enter computer station number the user is logging in to (1-" + 
											labsA[computerLabIndex-1].getNumComputers() + ")");
									
									if(!scn.hasNextInt()){
										System.out.println("You didn't enter a valid chioce, please enter a choice between 1-" + labsA[computerLabIndex-1].getNumComputers() + "\n");
										scn.next();
									}else{
										computerIndex = scn.nextInt();
										if(computerIndex > labsA[computerLabIndex-1].getNumComputers() || computerIndex == 0){
											System.out.println("You didn't enter a valid chioce, please enter a choice between 1-" + labsA[computerLabIndex-1].getNumComputers() + "\n");
											
										}else{
											checkIfEmpty = labsA[computerLabIndex-1].getStudentId(computerIndex);
											
											if(checkIfEmpty != "Empty"){
												System.out.println("computer #" + (computerLabIndex-1) + " is already taken by student ID " + checkIfEmpty +
														"\nPlease choose another computer station\n");
											}else{
												labsA[computerLabIndex-1].simulateLogin(computerIndex, userID);
												cont1 = false;
												cont2 = false;
												cont3 = false;
												
											}
											
								
											
											
										}
									}
									
								}while(cont3);
								
								
							}
						}
						
					}while(cont2);
					
					
					
				}
				
				
				
			}else{
				System.out.println("You didn't enter a valid student ID, please try again.\n");
			}
			
		}while(cont1);
			
		
	}
	
	
	/*This method is responsible to validate the log off of the user- the function of the log off is part of the computer lab object.
	 * The method validate if the student ID that was entered by the user is logged in, and if the student is logged in - than it allows to log off the student id. 
	 */
	public static void logoff(Scanner scn, ComputerLab[] labsA){
		
		String userID; 
		int index = -1;
		
		System.out.println("Enter the 5 digit ID number of the user to find:");
		userID = scn.next();
		
		
		for (int i = 0; i < labsA.length; i++){
			 index = labsA[i].simulateLogoff(userID);
			
			if(index != -1){
				System.out.println("User " + userID + " is logged off.\n");
				i = labsA.length;
			}
		}
		
		if(index == -1){
			System.out.println("Can't logoff user ID " + userID + " since this user is not logeed on\n");
		}
		
		
		
		
	}
	
	
	/*This method is responsible take the user entry and loop through the lab array of labs. 
	 * This method present the outcome. 
	 * This method uses the search method in the ComputerLab class. 
	 */	
	public static void search(Scanner scn, ComputerLab[] labsA){
		
		String userID;
		int index = -1;
	
		
		System.out.println("Enter the 5 digit ID number of the user to find:");
		userID = scn.next();
		
		for( int i = 0; i < labsA.length; i++){
			
			index = labsA[i].linearSearch(userID); 
			
			if(index != -1){
				
				System.out.println("The user is logged on in lab #" + (i+1) + " on computer #" + (index+1) + "\n");
				i = labsA.length;
				
			}
			
		}
		
		if(index == -1){
			System.out.println("The system couldn't locate user ID #" + userID + "\n");
		}
		
		
		
		
		
	}
	
	public static void  labStatus(ComputerLab[] labsArray){
		
		System.out.println("LAB STATUS");
		System.out.println("Lab # Computer Stations");
		
		for(int i=0; i <labsArray.length ; i++){
			System.out.println(labsArray[i].toString());
		}
		
		System.out.println();
	}

}
