package pl.edu.pjwstk.lab3;

import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.easymock.TestSubject;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class AlarmTest {

    @Rule
    public EasyMockRule rule = new EasyMockRule(this);

    @Mock
    Time time;

    @TestSubject
    private Alarm alarm = new AlarmImpl();

    @Test
    public void shouldRingTest(){
        TimeImpl timeRing = new TimeImpl();
        timeRing.setTime("20", "12", "1992", "20", "00");
        alarm.addAlarmTime(timeRing);

        expect(time.getTime())
                .andReturn("201219922000")
                .andReturn("201219922000")
                .andReturn("201219922000")
                .andReturn("191219922000")
                .andReturn("201219922000");
        replay(time);

        assertEquals(true, alarm.shouldRing());
        assertEquals(false, alarm.shouldRing());
        assertEquals(false, alarm.shouldRing());
        assertEquals(false, alarm.shouldRing());
        assertEquals(true, alarm.shouldRing());

        verify(time);
    }


//    @Test
//    public void addAlarmTimeTest(){
//        TimeImpl timeRing = new TimeImpl();
//        timeRing.setTime("20", "12", "1992", "20", "00");
//
//        assertEquals(alarm.shouldRing(), false);
//
//        alarm.addAlarmTime(timeRing);
//
//        assertEquals(alarm.shouldRing(), true);
//        verify(time);
//    }

    @Test
    public void clearAlarmTimeTest(){
        TimeImpl timeRing = new TimeImpl();
        timeRing.setTime("20","12","1992","20","05");
        alarm.addAlarmTime(timeRing);

        expect(time.getTime()).andReturn("201219922005").anyTimes();
        replay(time);

        assertEquals(true, alarm.shouldRing());
        alarm.clearAlarmTime(timeRing);
        assertEquals(false, alarm.shouldRing());

        verify(time);
    }
}