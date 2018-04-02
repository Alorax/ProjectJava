// Main.java
// Authors: Miruna Serian and Anima Sutradhar.
// Program last updated: 5th March 2018.

/* This program helps teams allocate the credit for a project fairly so that all
   parties are satisfied with the outcome.                                          */


//------------------------------------------------------------------------------------
//    This is the Main class where the project is run.
//------------------------------------------------------------------------------------

public class Main
{
    public static void main(String[] args)
    {
        Menu theMenu = new Menu();
        theMenu.readDataFromFile();
        theMenu.displayMenu();

    }
}
