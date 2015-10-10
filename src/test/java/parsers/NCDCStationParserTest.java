package parsers;

import org.apache.hadoop.io.Text;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import com.krishna.hadoop.NCDCStationParser;

public class NCDCStationParserTest {

	 @Test
	 public void testStationrecord()
	 {
		 Text  testRecord = new Text("011130 99999 GLOMFJORD                     NO            +66.800 +013.983 +0039.0 20040406 20091216");
		 NCDCStationParser stParser =new NCDCStationParser();
		 stParser.parse(testRecord);
		 assertThat(stParser.getStationId(),is( new String("011130-99999")));
		 assertThat(stParser.getStationName(),is( new String("GLOMFJORD")));
	 }
	 
}
