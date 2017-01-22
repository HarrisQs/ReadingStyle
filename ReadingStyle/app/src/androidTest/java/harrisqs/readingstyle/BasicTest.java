package harrisqs.readingstyle;
/**
 * Created by HarrisQs on 2017/1/20.
 */
import com.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;

public class BasicTest extends ActivityInstrumentationTestCase2<MainActivity>
{
    private Solo BasicTestSolo;
    public BasicTest()
    {
        super(MainActivity.class);
    }
    public void setUp() throws Exception
    {
        BasicTestSolo = new Solo(getInstrumentation(), getActivity());
    }
    @Override
    public void tearDown() throws Exception
    {
        BasicTestSolo.finishOpenedActivities();
    }
    public void testBasicRun() throws Exception
    {

    }
}
