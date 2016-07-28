import java.io.* ;
import java.util.* ;
import java.nio.file.Files ;
import java.nio.file.Paths ;
import java.nio.charset.StandardCharsets ;

public class Csv
{
	private File file ;
	private String line ;
	private List<String> field = new ArrayList<String>(0);
	private List<String> lines;
	private int nfield;
	private String fieldsep;
	private Iterator lineIterator;

	public Csv( File file, String sep )
	{
		try{
			this.lines = Files.readAllLines( Paths.get ( file.getPath() ), StandardCharsets.UTF_8) ;
		}
		catch(IOException e)
		{
			System.out.println("File not found!");
			this.lines = new ArrayList<String>();
		}
		this.lineIterator = this.lines.iterator() ;
		this.fieldsep = sep;
	}

	public String getline()
	{
		if( lineIterator.hasNext() )
		{
			line = (String)lineIterator.next() ;
			split();
		}
		return line ;
	}

	private int split()
	{
		String fld="";
		nfield = 0;

		int i, j;
		j = 0;

		String[] temp;

		if (line.length() == 0)
			return 0;
		i = 0 ;
		do {
			
			if (i < line.length() && line.substring(i,i+1) == "\"")
			{
				temp = advquoted(line, fld, ++i) ;
				j = Integer.parseInt(temp[0]);
				fld = temp[1];
			}
			else
			{
				temp = advplain(line, fld, i) ;
				j = Integer.parseInt(temp[0]);
				fld = temp[1];
			}
			if (nfield >= field.size())
				field.add(fld);
			else
				field.set(nfield, fld);	
			nfield++;
			i = j + 1;
		}
		while(j < line.length());

		return nfield;
	}	

	private String[] advquoted(String s, String fld, int i)
	{
		
		int j = 0;
		
		fld = "";
		for(j = 1; j < s.length(); j++)
		{
			if(s.substring(j,j+1).equals("\"") &&
					!s.substring(++j,j+1).equals("\""))
			{
				int k = s.indexOf(fieldsep, j) ;
				if(k < s.length())
					k = s.length();
				for(k -= j; k-- > 0;){
					fld += s.substring(j,j+1);
					j++;
				}
				break;
			}
		}
		String[] result = new String[2];
		result[0] = String.valueOf(j);
		result[1] = fld;
		return result;
	}

	private String[] advplain(String s, String fld, int i)
	{
		int j = 0;

		j = s.indexOf(fieldsep, i);
		if(j > s.length())
			j = s.length();
		System.out.println(s.length());
		System.out.println(i);
		System.out.println(j);
		fld = s.substring(i, j-i+1);
		String[] result = new String[2];
		result[0] = String.valueOf(j);
		result[1] = fld;
		return result;

	}

	public String getfield(int n){
		if(n < 0 || n >= nfield)
			return "";
		else
			return field.get(n);

	}

	public int getnfield()
	{
		return nfield;
	}

	public static void main(String[] args)
	{
		Csv csv = new Csv(new File(".//test.csv"),",");
		String line = csv.getline();
		while(!line.equals("")){
			System.out.println("line = " + line);
			for (int i = 0; i < csv.getnfield();i++)
				System.out.println("field[" + String.valueOf(i) + "] = " +
						csv.getfield(i));
		}
	}

}

