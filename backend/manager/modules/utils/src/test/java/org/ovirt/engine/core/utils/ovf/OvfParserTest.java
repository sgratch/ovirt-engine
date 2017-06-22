package org.ovirt.engine.core.utils.ovf;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

public class OvfParserTest {

    @Test
    public void utcDateStringToLocaDateNoDep() {
        Date date = OvfParser.utcDateStringToLocalDate("1984/06/19 14:25:11");
        assertEquals(456503111000L, date.getTime());
    }
}
