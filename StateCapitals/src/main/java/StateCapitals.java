/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 
 * @author Jefferson Agyekum
 * 
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class StateCapitals { 
    private static String DELIMITER= "::";
    private static HashMap<String, String> stateMap = new HashMap<>();
    
    
    
    public static void unMarshallObject(String line){
         // Splits up each line in the document and stores it in the hashMap. 
                String[] parts = line.split(DELIMITER);
                String state = parts[0];
                String capital = parts[1];
                
                stateMap.put(state, capital);
    }
    
    public static void readStatesToFile(){
        
        
        try{
        BufferedReader br = new BufferedReader(new FileReader("src/StateCapitals.txt"));
        Scanner scanner = new Scanner(br);
      
            while(scanner.hasNext()){
                String line = scanner.nextLine();
               
                
                // uses a different method to handle the umarshalling instead of the current method. Passes the current String as a parameter. 
                unMarshallObject(line);
            }
            
           // very important to clock the map after you're finished.  
            scanner.close();
        
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
            
       }
    
    
    public static void startGuessingGame(){
        
        int numberCorrect = 0;
        
        Scanner scanner = new Scanner(System.in);
        
        
        System.out.println("");
        System.out.println("How many times do you want to guess?");
        int numberOfGuesses = Integer.valueOf(scanner.nextLine());
        
        int curr = 0;
        
        Random rand = new Random();
        
        
  
        while(curr < numberOfGuesses){
            
            // converts the states in the map to an Array. 
            String[] states = stateMap.keySet().toArray(new String[0]);
   
            // generates a random number between 1 and the number of states in the map. 
             int num = rand.nextInt(stateMap.size() + 1);
          
            
             String currentState = states[num];
             
             System.out.println("");
             System.out.println("READY TO TEST YOUR KNOWLEDGE? WHAT IS THE CAPITAL OF  " + ("'" + currentState + "'?"));
             String answer = scanner.nextLine();
           
             
             
             if(stateMap.get(currentState).equalsIgnoreCase(answer)){
                 System.out.println("NICE WORLK! " + answer + " IS CORRECT!");
                 numberCorrect++;
             } else {
                 System.out.println("WRONG! " + answer + " IS INCORRECT!");
                 numberCorrect--;
             }
         // removes the state recently asked so that the same question does not repeat. 
          stateMap.remove(currentState);
            curr++;
        }
        
        System.out.println("You got " + numberCorrect);
    }
    
    
    
    public static void main(String[] args){
       readStatesToFile();
       
       System.out.println(stateMap.size() + " STATES & CAPITALS ARE LOADED.");
       System.out.println("====");
       System.out.println("HERE ARE THE STATES : ");
       // prints all the states.    
       for(String state : stateMap.keySet()){
           System.out.print(state + ", ");
       }
       System.out.println("");
      
       startGuessingGame();
       
       
    }
}
