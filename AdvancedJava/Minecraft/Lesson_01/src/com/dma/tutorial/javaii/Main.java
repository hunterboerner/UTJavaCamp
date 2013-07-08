package com.dma.tutorial.javaii;

import java.util.ArrayList;


public class Main {

    static int myGlobalInt = 0;

    public static void main(String[] args)
    {
        // Set the debugger to the first boolean initialization and debug this to begin the lesson.
        // Java Basics:
        // Java is a type-safe, statically typed language.
        // Types: Types store data.
        // Bool, char, int (uint), float, double, class instances (explain how a much bigger thing can be a type too)
        // Types are important for telling the computer how much memory to reserve. You can tell the
        // computer to save you some memory for something you want to work with by declaring it:
        // I haz a bool
        boolean myBool;
        // The computer says ok, I'll set aside a bit of memory to store that Boolean. In this case the memory is reserved, but unused.
        // You have to initialize a variable by giving it a value:
        myBool = true;
        // You can change a variable after it's been initialized:
        myBool = false;

        // You can ask in the debugger if you ever lose track, or if you need to keep an eye on types.
        // This is similar for all types and they require larger and larger amounts of memory the further you go in the list.
        // Type-safety: C# won't ever think that one type is another, because
        // Statically typed: you *have* to say what type everything is
        // This is important because these types will be all over the code we will see shortly, and they will need to all make sense.
        // Keywords: These are the words you use to work with the types in different ways. The simplest are the arithmetic symbols:

        int myInt = 1;
        myInt = myInt + 1;
        myInt += 1;
        myInt++;
        ++myInt;

        // Next there are conditionals that we can use to only execute operations in certain situations:
        if (myInt == 5)
            myInt++;

        if (myBool == false)
            System.out.println("good, IIRC I set it false");
        // If you want to do 2 or more things you need to group them in curly braces. This is a good habit to do even if you only put one line at first.
        if (myInt == 6)
        {
            // Reset everything
            myBool = false;
            myInt = 0;
        }
        // The == checks for equality. There are other operators (>=, >, !=, <, <=, !, and more)
        // There are other conditionals that can execute more than once on a single pass, like
        while (myInt != 5)
        {
            myInt++;
            System.out.println("counting up from 1! " + myInt + "...");
        }

        myInt = 0;
        for (int i = 5; i > 0; i--)
            System.out.println("counting down to 1! " + i + "...");
        // Braces apply to these too.
        // There's also Java's list iteration loop for ArrayLists and other collections:
        ArrayList<Integer> myList = new ArrayList<Integer>();
        for (Integer i = 0; i < 10; i++)
        {
            myList.add(i);
            System.out.println("Added " + i + " to myList at index " + i);
        }

        for (Integer i : myList)
        {
            System.out.println("Value at index " + myList.get(i) + " is " + i);
        }

        // There are many more types of containers, see [insert link here] for more
        // There are many reasons for why there are different types, it's best to learn
        // the need for each on a need-to-know basis rather than learning them all at the beginning.

        // After making a few short programs, it becomes quickly obvious that if we just wrote out every step of a program in order,
        // it would end up repeating itself over and over again each time you wanted something similar to happen. There's two extra
        // layers that programs have (and sometimes a third), to manage writing large applications and games without ever, or rarely,
        // having to repeat yourself.

        CountToFive(myInt);
        CountToFive(myInt);

        // Methods can return a value, originally this started to tell the caller that everything went ok
        if (CountdownToZero())
            System.out.println("Counted down to zero without problems");

        // You can have a method return a value without having to use it necessarily...
        CountdownToZero();
        // Besides taking arguments and returning values, methods can also have side effects
        // It's better to avoid side effects if possible, but sometimes it's necessary. Here's an example of when it isn't:

        if (AlmostDangerousCountdownToZero())
            System.out.println("We got lucky this time, but in a non-static context we might not have been");
        // As the above suggests, we are about hitting the limit of what we can do in a static context. To get out, we should call up a non-static class instance.
        // The topmost level of the Application must always be static, but anything below it need not be.

        NonStaticClass myNewClass = new NonStaticClass();
        if (myNewClass.Run() == 0)
        {
            System.out.println("program exited normally");
            SynthClass mySynthClass = new SynthClass();
            if (mySynthClass.test() == 0)
        	 	System.out.println("synthClass existed normally");
            	// Notice below we have to pass a 0
            	//System.exit(0);
        }

    }

    public static void CountToFive(int myInt)
    {
        while (myInt != 5)
        {
            myInt++;
            System.out.println("counting up from 1! " + myInt + "...");
        }
    }

    public static boolean CountdownToZero()
    {
        int myLocalInt;
        for (myLocalInt = 3; myLocalInt > 0; myLocalInt--)
            System.out.println("counting down to 1! " + myLocalInt + "...");
        // Why is myLocalInt actually 0?
        // Use the Expression evaluation window (Alt+F8 on Windows) to watch the values change next time you come back here.
        // Add it to Watches to avoid having to ask every time
        if (myLocalInt == 0)
            return true;
        return false;
    }

    public static boolean AlmostDangerousCountdownToZero()
    {
        int myLocalInt;
        for (myLocalInt = 3; myLocalInt > 0; myLocalInt--)
            System.out.println("counting down to 0! " + myLocalInt + "...");
        if (myLocalInt == myGlobalInt)
            return true;
        return false;
    }
}


