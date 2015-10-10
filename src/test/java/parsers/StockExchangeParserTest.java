package parsers;

import org.apache.hadoop.io.Text;
import org.junit.Test;

import com.krishna.hadoop.StockExchangeParser;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class StockExchangeParserTest {

	
	@Test
	 public void testStockrecord()
	 {
		Text  testRecord  = new Text("21-12-1995,5096.53,DJINDUS");
		StockExchangeParser sep = new StockExchangeParser();
		sep.parse(testRecord);
		assertThat(sep.getDateStr(), is( new String("1995-12-21")));
	    assertThat(sep.getExchangeName(),is(new String("DJINDUS")));
	    assertThat(sep.getEodIndex() ,is( new Double(5096.53)));
		
	 }
}
