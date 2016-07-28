// problem 8 from java ranch

import java.io.* ;
import java.util.Date ;
public class prob4
{
	public static void main ( String [] argv )
	{
		int value;
		try
		{
			value = Integer.parseInt( argv[0] ) ;
		}
		catch( NumberFormatException e )
		{
			System.out.println("Argument must be an integer!") ;
			return ;
		}

		Date now = new Date() ;
		switch (value) {
			case 0: System.out.println("milliseconds since January 1, 1970: " + 
							  System.currentTimeMillis()) ;
					  break ;
			case 1: System.out.println("seconds since January 1, 1970: "
							  + (System.currentTimeMillis()/1000)) ;
					  break ;
			case 2: System.out.println("days since January 1, 1970: " + 
							  System.currentTimeMillis()/(1000*60*24)) ;
					  break ;
			case 3: System.out.println("The current date is: " + 
							  now.toString()) ;
					  break;
			default: System.out.println("invalid input!");
						break;
		}
	}	
}
