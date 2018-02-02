import java.util.Scanner;

public class MainMenu
{
    public static void main(String[] args)
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Welcome to Split-it");
        System.out.print("\n\n\tAbout (A)");
        System.out.print("\n\tCreate Project (C)");
        System.out.print("\n\tEnter Votes (V)");
        System.out.print("\n\tShow Project (S)");
        System.out.print("\n\tQuit (Q)");
        System.out.print("\n\n\tPlease choose an option: ");
        char option = reader.next().charAt(0);

    }
}