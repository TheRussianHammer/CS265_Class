// assignment 3 CS265 Alexander Kazantsev

import java.io.* ;
import java.util.* ;
import java.nio.file.Files ;
import java.nio.file.Paths ;
import java.nio.charset.StandardCharsets ;
import java.math.BigInteger ;

public class PhoneDict
{
	private HashMap<Integer,String> keyPad = new HashMap<Integer,String>() ;
	private HashMap<BigInteger,List<String>> hashedWords = new
		HashMap<BigInteger,List<String>>() ;
	private HashMap<String,Integer> letterToInt = new HashMap<String,Integer>() ;

	public PhoneDict(){
		//Generate number to letter map
		keyPad.put(0," ");
		keyPad.put(2,"abc");
		keyPad.put(3,"def");
		keyPad.put(4,"ghi");
		keyPad.put(5,"jkl");
		keyPad.put(6,"mno");
		keyPad.put(7,"pqrs");
		keyPad.put(8,"tuv");
		keyPad.put(9,"wxyz");

		//Generate letter to number hash map
		letterToInt.put(" ",0);
		for(int i = 97; i < 100 ; i++)
			letterToInt.put(String.valueOf((char)i),2);
		for(int i = 100; i < 103 ; i++)
			letterToInt.put(String.valueOf((char)i),3);
		for(int i = 103; i < 106 ; i++)
			letterToInt.put(String.valueOf((char)i),4);
		for(int i = 106; i < 109 ; i++)
			letterToInt.put(String.valueOf((char)i),5);
		for(int i = 109; i < 112 ; i++)
			letterToInt.put(String.valueOf((char)i),6);
		for(int i = 112; i < 116 ; i++)
			letterToInt.put(String.valueOf((char)i),7);
		for(int i = 116; i < 119 ; i++)
			letterToInt.put(String.valueOf((char)i),8);
		for(int i = 119; i < 123 ; i++)
			letterToInt.put(String.valueOf((char)i),9);
	}

	HashMap getHashedWords(){
		return this.hashedWords ;
	}


	/*
		Method resposnible for hasing words in file
		*/
	public int hashWords( String path )
	{
		try
		{
			// Parse each line seperately 
			for (String line : Files.readAllLines( Paths.get( path ),
						StandardCharsets.UTF_8 ) )
			{
				String result = "";
				String word = "";
				// Parse letter of each word
				for (int i = 0; i < line.length(); i++)
				{
					String letter = line.substring( i, i+1 ) ;
					String letterNumber = String.valueOf( letterToInt.get(
								letter.toLowerCase() ) ) ;
					if( null != letter )
					{
						result += letterNumber ;
						word += letter ;
					}
				}

				BigInteger resultValue = new BigInteger( result ) ;

				if( hashedWords.containsKey( resultValue ) )
					hashedWords.get( resultValue ).add(word);

				else
				{
					hashedWords.put( resultValue , 
							new ArrayList<String>() ) ;
					hashedWords.get( resultValue ).add( word ) ;
				}
			}
		}

		catch ( IOException e )
		{
			System.out.println("The File '" + path + "' could not be found");
			return 0;
		}
		return 1;
	}

	/*
		Return ordered list of string numbers representing a possible key 
		*/
	public List<String> parseNumbers(String numbers)
	{
		List<String> possibleKeys = new ArrayList<String>();
		String key = "" ;

		for(int i = 0 ; i < numbers.length() ; i++)
		{
			int number ;
			try
			{
				number = Integer.parseInt( numbers.substring( i, i+1 ) ) ;
			}
			
			// number will be one if input 
			//contains anything besides numeric charactes
			catch ( NumberFormatException nfe )
			{
				number = 1 ;
			}
			//By design, this will "ignore" any leading and trailing zeros
			//as they will not be added to possible keys
			if (number == 0)
			{
				if ( key != ""  )
					possibleKeys.add( key ) ;
				key = "" ;
			}


			else
				key += number ;
		}
		
		if ( key != "" )
			possibleKeys.add( key ) ;
		return possibleKeys;
	}	

	/*
		Returns the constructed sentance from the users' input
		*/
	public String constructSentance( List<String> list )
	{
		String result = "" ;

		for ( String s : list )
		{
			List<String> words = hashedWords.get( new BigInteger ( s ) ) ;
			if ( words != null )
			{
				if ( words.size() > 1 )
				{
					result += "(" ;
					int i;
					for (  i = 0 ; i < words.size()-1 ; i++ )
						result += words.get( i ) + "|" ;
					result += words.get( i ) + ") " ;
				}	
				else
					result += words.get(0) + " " ;
			}

			else
			{
				result += generateInvalid( s ) ;
				result += " " ;
			}

		}

		return result ;
	}

	//Generates invlalid String
	private String generateInvalid( String s )
	{
		String result = "" ;
		for ( int i = 0 ; i < s.length() ; i++ )
			result += "*" ;
		return result ;
	}

	/*
		Easter egg function!
		Generates list of all possible strings from number.
		*/
	private List<String> permute( String numbers , String previous )
	{	
		List<String> result = new ArrayList<String> () ;
		
		if( "".equals( numbers ) )
		{
			//base case
			List<String> finalWord = new ArrayList<String>() ;
			finalWord.add(previous) ;
			return finalWord ;
		}

		//terating over each letter for a key
		for (int i = 0 ; i <
				this.keyPad.get( 
					Integer.parseInt( 
						numbers.substring( 0 , 1 ) 
						) ).length() ; i++ )
		{
			result.addAll( permute( numbers.substring(1) , previous + 
					  	this.keyPad.get( Integer.parseInt( numbers.substring( 0
							, 1 ) ) ).substring(i,i+1) ) ) ; // recursive call
		}

		return result;
	}

	public void testPermute(String n)
	{
		for(String s : permute(n,"") )
			System.out.println( s ) ;
	}

	public static void main(String [] argv)
	{
		BufferedReader br = 
			new BufferedReader(new InputStreamReader(System.in)) ;
		PhoneDict pd = new PhoneDict() ;
		pd.hashWords("words") ;
		Set<BigInteger> keys = pd.getHashedWords().keySet();

		// pd.testPermute("227") ; // generates all possible perms for 227	

		String input;

		try
		{
			while((input=br.readLine())!=null)
			{
				String answer = pd.constructSentance(
						pd.parseNumbers(input)) ; 
				System.out.println(answer);
			}
		}

		catch(IOException ie)
		{
			ie.printStackTrace() ;
			System.out.println( "Something went wrong with your input!" ) ;
		}


	}

}
