import java.io.* ;

public class gInt
{
	private int r = 0;
	private int i = 0;

	public gInt( int r )
	{
		this.r = r ;
	}

	public gInt( int r, int i )
	{
		this.r = r ;
		this.i = i ;
	}

	public int real()
	{
		return r ;
	}

	public int imag()
	{
		return i ;
	}

	public gInt add( gInt rhs )
	{
		gInt result = new gInt( this.real() + rhs.real(),
				this.imag() + rhs.imag() ) ;
		return result ;
	}

	public gInt multiply( gInt rhs ) 
	{
		gInt result = new gInt( this.real() * rhs.real() +
				this.imag() * rhs.imag(), this.real() * rhs.imag() +
				this.imag() * rhs.real() ) ;
		return result ;
	}

	public float norm()
	{
		return (float)Math.sqrt(this.real() * this.real() + 
				this.imag() * this.imag() ) ;
	}
}
