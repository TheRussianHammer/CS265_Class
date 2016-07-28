// problem 3 from java ranch - leap year

import java.io.* ;

public class prob3
{
	public static void main( String [] argv )
	{
		int year;
		try
		{
			year = Integer.parseInt( argv[0] );
		}

		catch( NumberFormatException e )
		{
			System.out.println("Argument must be an integer year!") ;
			return;
		}
		if ( year % 100 == 0){
			if (year % 400 == 0){
				System.out.println("leap year!") ;
				return ;
			}
			System.out.println("not a leap year!") ;
			return ;
		}
		if( year % 4 == 0)
		{
			System.out.println("leap year!") ;
			return ;
		}
		System.out.println("not a leap year!") ;
	}
}
