// Prblem 2 from java ranch - even or odd

import java.io.* ;

public class prob2 
{
	public static void main( String [] argv )
	{
		int value;
		try
		{
			value = Integer.parseInt( argv[0] );
		}
		catch (NumberFormatException e)
		{
			System.out.println("Argument must me an integer!") ;
			return;
		}
		if ( value % 2 == 0 )
		{
			System.out.println("even") ;	
		}
		else
		{
			System.out.println("odd") ;
		}
	}
}
