package com.dma.tutorial.javaii;

public class NonStaticClass {

    private int myGlobalInt;

    public NonStaticClass()
    {
        // do init here, at least one thing should happen or there is no point making a constructor
        myGlobalInt = 0;
    }

    public int Run()
    {
        if (DangerousCountdownToZero())
            System.out.println("That time it worked, but...");

        // The point of all this is how to organize your data clearly within your program.
        // This won't make much sense without more context, but you know enough to begin applying
        // your knowledge of the language to solving a real problem.

        if (!DangerousCountdownToZero())
            System.out.println("This time it didn't!");
        return 0;
    }

    public boolean DangerousCountdownToZero()
    {
        int myLocalInt;
        for (myLocalInt = 3; myLocalInt > 0; myLocalInt--)
            System.out.println("counting down to 0! " + myLocalInt + "...");
        if (myLocalInt == myGlobalInt)
        {
            myGlobalInt++;
            return true;
        }
        return false;
    }




}
