package parsers;
import org.apache.hadoop.io.Text;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import com.krishna.hadoop.WikiPageViewParser;
public class WikiPageViewParserTest {
  
	@Test
	public void testRecord()
	{
		Text testRecord = new Text("\"2015-03-16T00:09:55\"	\"mobile\"	1595");
		WikiPageViewParser wpvParser = new WikiPageViewParser();
		wpvParser.parse(testRecord);
		assertThat(wpvParser.getDateStr(),is(new String("16-03-2015")));
		assertThat(wpvParser.getSiteType(),is(new String("mobile")));
	}
	
}
