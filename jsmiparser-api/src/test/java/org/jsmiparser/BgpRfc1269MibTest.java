/*
 * Copyright 2012 The OpenNMS Group, Inc..
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jsmiparser;

import org.jsmiparser.smi.SmiNotificationType;
import org.jsmiparser.smi.SmiTrapType;
import org.jsmiparser.smi.SmiVariable;
import org.jsmiparser.smi.SmiConstants;
import org.jsmiparser.smi.SmiMib;
import org.jsmiparser.smi.SmiModule;
import org.jsmiparser.smi.SmiPrimitiveType;
import org.jsmiparser.smi.SmiRow;
import org.jsmiparser.smi.SmiTable;
import org.jsmiparser.smi.SmiTextualConvention;
import org.jsmiparser.smi.SmiType;
import org.jsmiparser.smi.SmiVersion;
import org.jsmiparser.smi.StatusV2;
import org.jsmiparser.smi.SmiIndex;
import org.jsmiparser.smi.SmiOidNode;

import java.net.URISyntaxException;
import java.util.List;

public class BgpRfc1269MibTest extends AbstractMibTestCase {

    public BgpRfc1269MibTest() {
        super(SmiVersion.V1,
                LIBSMI_MIBS_URL + "/ietf/RFC1269-MIB");
    }

    public void testSizes() {
    	assertNotNull(getMib());
    	assertNotNull(getMib().getScalars());
        assertEquals(0, getMib().getScalars().size());
        
        assertNotNull(getMib().getColumns());
        assertEquals(0, getMib().getColumns().size());
        
        // { bgpEstablished, bgpBackwardTransition } from RFC1269-MIB 
        assertEquals(0, getMib().getTrapTypes().size());

        SmiModule bgpMib = getMib().findModule("RFC1269-MIB");
        assertNotNull(bgpMib);
        
        // { bgpEstablished, bgpBackwardTransition } from RFC1269-MIB
        assertEquals(2, bgpMib.getTrapTypes().size());

        assertEquals(3, bgpMib.getScalars().size());
        assertEquals(22+19+3+3+6, bgpMib.getColumns().size());

        assertNotNull(bgpMib.findScalar("bgpLocalAs"));
        assertNull(bgpMib.findColumn("bgpLocalAs"));

        assertNotNull(bgpMib.findColumn("bgpPeerIdentifier"));
        assertNull(bgpMib.findScalar("bgpPeerIdentifier"));

        assertNotNull(bgpMib.findTable("bgpPeerTable"));
        assertNull(bgpMib.findVariable("bgpPeerTable"));

        assertNotNull(bgpMib.findRow("bgpPeerEntry"));
        assertNull(bgpMib.findVariable("bgpPeerEntry"));
    }

    public void testNotificationTypes() {
    	SmiModule bgpMib = getMib().findModule("RFC1269-MIB");
        assertNotNull(bgpMib);
        
        SmiTrapType bgpEstablished = bgpMib.findTrapType("bgpEstablished");
        assertNotNull(bgpEstablished);
        
        SmiTrapType bgpBackwardTransition = bgpMib.findTrapType("bgpBackwardTransition");
        assertNotNull(bgpBackwardTransition);
        
        assertEquals("foo", bgpEstablished.getEnterprise());
        assertEquals(1, bgpEstablished.getSpecificType());
        assertEquals("The BGP Established event is generated when\n"
            + "          the BGP FSM enters the ESTABLISHED state. ", bgpEstablished.getDescription());
        assertNotNull(bgpEstablished.getVariableTokens());
        assertEquals(3, bgpEstablished.getVariableTokens().size());
        assertEquals("bgpPeerRemoteAddr", bgpEstablished.getVariableTokens().get(0).getValue());
        assertEquals("bgpPeerLastError", bgpEstablished.getVariableTokens().get(1).getValue());
        assertEquals("bgpPeerState", bgpEstablished.getVariableTokens().get(2).getValue());
        assertEquals("bgpEstablished", bgpEstablished.getIdToken().getValue());
        
        
    }

}
