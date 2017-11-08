package com.webcheckers.appl;

import org.junit.Before;
import org.junit.Test;
import spark.Request;
import spark.Session;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by wor3835 on 11/5/2017.
 */
public class MessageTest {
    private Message CuT;

    private Request request;
    private Session session;

    @Before
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        CuT = new Message("test", MasterEnum.MessageType.info);
    }

    @Test
    /**
     * tests an error message
     */
    public void testError() {
        Message m = new Message("error", MasterEnum.MessageType.error);
        assertEquals("error", m.getText());
    }

    @Test
    /**
     * tests getting message type
     */
    public void getType() {
        assertEquals(MasterEnum.MessageType.info, CuT.getType());
    }

    @Test
    public void emptyMessage() {
        Message empty = new Message("", MasterEnum.MessageType.info);
        assertEquals("", empty.getText());
    }

}
