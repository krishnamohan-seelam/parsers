package parsers;
import org.apache.hadoop.io.Text;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import com.krishna.hadoop.HttpLogParser;

public class HttpLogParserTest {
	@Test
	public void testRecord()
	{
	Text testRecord = new Text("unicomp6.unicomp.net - - [01/Jul/1995:00:00:14 -0400] \"GET /shuttle/countdown/count.gif HTTP/1.0\" 200 40310");
	HttpLogParser httplogParser = new HttpLogParser();
	httplogParser.parse(testRecord);
	assertThat(httplogParser.getTimestamp(), is(new Long("804571214000")));
	assertThat(httplogParser.getUserIP(),is(new String ("unicomp6.unicomp.net")));
	
	}

}
