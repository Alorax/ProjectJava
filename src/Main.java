/*
------------------------------------------------------------------------------------
    This is the Main class where the Menu is run.
------------------------------------------------------------------------------------
*/

public class Main
{
    public static void main(String[] args)
    {
        Menu theMenu = new Menu();
        theMenu.readDataFromFile();
        theMenu.displayMenu();

    }
}
