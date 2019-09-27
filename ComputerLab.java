
 class ComputerLab {
	 
	 //number of computer in the lab
	 private int numComputers; 
	 
	 //the array of the computers
	 private String[] computerString; 
	 
	 //number of lab
	 private int labID;
	 
	 
	//constructor
	ComputerLab(int computerCount, int idLab){
		
		this.numComputers = computerCount;
		
		setBasicComputerString(computerCount);
		
		this.labID = idLab; 
		
		
		
	}
	
	//provides the number of computers
	protected int getNumComputers(){
		return numComputers; 
	}
	
	//sets the number of computers in the lab(not applicable for this project)
	protected void setNumComputers(int computerNum){
		this.numComputers = computerNum;
	}
	
	
	//this method sets the start condition for the lab- all computers are not in use- empty. 
	//This method is private since i don't want anything from the outside to reach this functionality. 
	private void setBasicComputerString(int numOfComputer){
		
		computerString = new String[numOfComputer];
		
		for(int i = 0; i < numOfComputer ; i++){
			computerString[i] = "Empty";
		}
		
	}
	
	//Logs in a user
	protected void simulateLogin(int computerNumber, String studentId){
		
		if(computerString[computerNumber-1] == "Empty"){
			computerString[computerNumber-1] = studentId;
			System.out.println("User ID " + studentId + " is logged on to lab #" + labID + " on computer #" + computerNumber + "\n");
		}
				
			
	}
	
	//get the student ID in a specific computer in the lab
	protected String getStudentId(int computerNumber){
		
		
		return computerString[computerNumber-1]; 
	}
	
	
	//Logs off a user
	protected int simulateLogoff(String studentId){
		
		int index = linearSearch(studentId);
		
		 if(index != -1){
			 computerString[index] = "Empty";
			 return index;
		 }
		 
		 return index;
				
	}
	
	
	//Search function for the lab. 
	//I chose to use linear search since i can't promise that the array of the computers will be organized sequentially to perform a binary search. 
	protected int linearSearch(String studentIdP){
		
		String studentID = studentIdP;
		
		for (int i = 0; i < computerString.length; i++){
			if (studentID.equals(computerString[i])){
				return i; 
			}
		}
		
		return -1; 
	}
	
	
	
	//This method override the regular toString() function. This helps to print the array in the main function. 
	public String toString(){
		String str;
		String str1 = "";
		
		for(int i = 0; i < computerString.length; i++){
			 str = ( i+1 + ": " + computerString[i]+ " ");
			 str1 +=  str; 
		}
		
		return str1; 
		
	}
	

}
