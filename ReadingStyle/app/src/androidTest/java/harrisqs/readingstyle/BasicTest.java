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

    public void test在背景下載書店資料() throws Exception
    {

    }

    public void Mtest秀出書店列表() throws Exception
    {
        boolean notSearch = BasicTestSolo.searchText("女書店");
        assertTrue("無法正確秀出書店列表", notSearch);
    }
}
