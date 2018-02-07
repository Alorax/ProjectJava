// This program is a "program" with the purpose of helping
// teams allocate the credit for a project fairly so that all parties
// are satisfied with the outcome.

// This is the first deliverable of the Fair Grade Allocator that shows
// the user the Main Menu, and allows them to create a project.

// Author: Anima Sutradhar.
// Last update: Sunday 4th January 2018.

//------------------------------------------------------------------------
// The Main Menu page that will be the first thing the user sees.
//------------------------------------------------------------------------

import java.util.Scanner;
import static java.lang.String.*;

public class MainMenu
{
    //Creating the Main Menu options

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);

        //Main Menu content
        System.out.println("Welcome to Split-it");
        System.out.println("\n\tAbout (A)");
        System.out.println("\tCreate Project (C)");
        System.out.println("\tEnter Votes (V)");
        System.out.println("\tShow Project (S)");
        System.out.println("\tQuit (Q)");
        System.out.print("\n\tPlease choose an option: ");
        char input = scan.next().charAt(0);
        choice = Character.toString(input);

        switch (choice)
        {
            case valueOf('A'): MainMenu.About();
                break;
            case valueOf('C'): MainMenu.CreateProject();
                break;
            case valueOf('V'): MainMenu();
                break;
            case valueOf('S'): MainMenu();
                break;
            case valueOf('Q'): MainMenu.Quit();
                break;
        }
    }

    //------------------------------------------------------------------------
// Directs to the 'About' page for when user chooses option 'A'.
//------------------------------------------------------------------------

    private void About()
    {
        System.out.println("This program is a Fair Grade Allocator, with the purpose ");
        System.out.println("of helping teams allocate the credit for a project fairly ");
        System.out.println("so that all parties are satisfied with the outcome.");
    }

//------------------------------------------------------------------------
// Returns to Main Menu page for when user chooses options 'V' or 'S'.
//------------------------------------------------------------------------


//------------------------------------------------------------------------
// End of application for when user chooses option 'Q'.
//------------------------------------------------------------------------

    private void Quit()
    {
        System.out.print("END OF PROGRAMME");
    }

//------------------------------------------------------------------------
// New Project page for when user chooses option 'C'.
//------------------------------------------------------------------------
    private void CreateProject()
    {
        System.out.println();
    }
}
