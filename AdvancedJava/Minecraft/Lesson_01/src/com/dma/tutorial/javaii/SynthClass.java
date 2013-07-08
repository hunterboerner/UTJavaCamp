package com.dma.tutorial.javaii;

import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.UnitOscillator;

public class SynthClass {
	Synthesizer synth;
	UnitOscillator osc;
	LineOut lineOut;

	int test()
	{
	// Create an instance of the synthesizer.
	synth = JSyn.createSynthesizer();
	
	// Start synthesizer using default stereo output at 44100 Hz.
	synth.start();

	// Add a tone generator.
	synth.add( osc = new SineOscillator() );
	// Add a stereo audio output unit.
	synth.add( lineOut = new LineOut() );
	
	// Connect the oscillator to both channels of the output.
	osc.output.connect( 0, lineOut.input, 0 );
	osc.output.connect( 0, lineOut.input, 1 );

	// Set the frequency and amplitude for the sine wave.
	osc.frequency.set( 345.0 );
	osc.amplitude.set( 0.3 );

	// We only need to start the LineOut. It will pull data from the
	// oscillator.
	for (int i=0; i < 9; i++)
	{
		//This only works in debug mode!
		osc.frequency.set( 345.0+i*50);
		lineOut.start();
		try
		{
			double time = synth.getCurrentTime();
			// Sleep for a few seconds.
			synth.sleepUntil( time + 4.0 );
		} catch( InterruptedException e )
		{
			e.printStackTrace();
		}
		lineOut.stop();
	}
	
	//Try a growing S curve! You need to use another variable to flip the sign and add a bit each time
	int j = 0;
	for (int i=0; i < 9; i++)
	{
		
		j=-j;
		if (j>0)
			j++;
		if (j<=0)
			j--;
		//This only works in debug mode!
		osc.frequency.set( 345.0+j*25);
		lineOut.start();
		try
		{
			double time = synth.getCurrentTime();
			// Sleep for a few seconds.
			synth.sleepUntil( time + 4.0 );
		} catch( InterruptedException e )
		{
			e.printStackTrace();
		}
		lineOut.stop();
	}
	// Sleep while the sound is generated in the background.
	/*
	try
	{
		double time = synth.getCurrentTime();
		// Sleep for a few seconds.
		synth.sleepUntil( time + 4.0 );
	} catch( InterruptedException e )
	{
		e.printStackTrace();
	}
	*/
	System.out.println( "Stop playing. -------------------" );
	// Stop everything.
	synth.stop();
	return 0;
	}
	
}
