import org.junit.*;
import static org.junit.Assert.*;

public class gIntTest 
{
	static gInt gi1;
	static gInt gi2;

	@BeforeClass
	public static void setupClass()
	{
		gi1 = new gInt(1,1) ;
		gi2 = new gInt(2,2) ;
	}

	@Test
	public void addTest()
	{
		gi1 = gi1.add(gi2);
		assertEquals(3,gi1.real());
		assertEquals(3,gi1.imag());
	}

	@Test
	public void multTest()
	{
		gi1 = gi1.multiply(gi2);
		assertEquals(12,gi1.real());
		assertEquals(12,gi1.imag());
	}
	
	@Test
	public void magTest()
	{
		assertEquals((float)Math.sqrt(288), gi1.norm(),.01);
	}

}
