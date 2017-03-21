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
        timeRing.setTime("12", "12", "1992", "12", "00");
        alarm.addAlarmTime(timeRing);

        expect(time.getTime()).
                andReturn("12:00").times(4).
                andReturn("13:00");

        replay(time);

        assertEquals(alarm.shouldRing(), false);
        assertEquals(alarm.shouldRing(), true);
        assertEquals(alarm.shouldRing(), false);
        assertEquals(alarm.shouldRing(), false);
        assertEquals(alarm.shouldRing(), true);

        verify(time);
    }


    @Test
    public void addAlarmTimeTest(){
        TimeImpl timeRing = new TimeImpl();
        timeRing.setTime("12", "12", "1992", "12", "00");

        assertEquals(alarm.shouldRing(), false);

        alarm.addAlarmTime(timeRing);

        assertEquals(alarm.shouldRing(), true);
    }

    @Test
    public void clearTimeSetTest(){
        TimeImpl timeRing = new TimeImpl();
        timeRing.setTime("20", "12", "1992", "12", "00");
        alarm.addAlarmTime(timeRing);

        expect(time.getTime()).andReturn("201219921205").anyTimes();
        replay(time);

        assertEquals(alarm.shouldRing(), true);
        alarm.clearAlarmTime(timeRing);
        assertEquals(alarm.shouldRing(), false);

        verify(time);
    }
}