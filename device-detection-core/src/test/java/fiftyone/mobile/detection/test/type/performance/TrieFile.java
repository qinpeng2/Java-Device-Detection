/*
 * This Source Code Form is copyright of 51Degrees Mobile Experts Limited.
 * Copyright © 2015 51Degrees Mobile Experts Limited, 5 Charlotte Close,
 * Caversham, Reading, Berkshire, United Kingdom RG4 7BY
 *
 * This Source Code Form is the subject of the following patent
 * applications, owned by 51Degrees Mobile Experts Limited of 5 Charlotte
 * Close, Caversham, Reading, Berkshire, United Kingdom RG4 7BY:
 * European Patent Application No. 13192291.6; and
 * United States Patent Application Nos. 14/085,223 and 14/085,301.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0.
 *
 * If a copy of the MPL was not distributed with this file, You can obtain
 * one at http://mozilla.org/MPL/2.0/.
 *
 * This Source Code Form is "Incompatible With Secondary Licenses", as
 * defined by the Mozilla Public License, v. 2.0.
 */

package fiftyone.mobile.detection.test.type.performance;

import fiftyone.mobile.detection.factories.TrieFactory;
import java.io.IOException;
import java.util.Calendar;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public abstract class TrieFile extends TrieBase {

    public TrieFile(String dataSet) {
        super(dataSet);
    }
    
    /* Actual tests */
    
    @Test
    public void randomUserAgentMulti() {
        userAgentMulti(fiftyone.mobile.detection.test.common.UserAgentGenerator.getRandomUserAgents());
    }
    
    @Test
    public void randomUserAgentsMultiAll() {
        userAgentmultiAll(fiftyone.mobile.detection.test.common.UserAgentGenerator.getRandomUserAgents());
    }
    
    @Test
    public void randomUserAgentsSingle() {
        userAgentsSingle(fiftyone.mobile.detection.test.common.UserAgentGenerator.getRandomUserAgents());
    }
    
    @Test
    public void randomUserAgentsSingleAll() {
        userAgentsSingleAll(fiftyone.mobile.detection.test.common.UserAgentGenerator.getRandomUserAgents());
    }
    
    @Test
    public void uniqueUserAgentsMulti() {
        userAgentMulti(fiftyone.mobile.detection.test.common.UserAgentGenerator.getUniqueUserAgents());
    }
    
    @Test
    public void uniqueUserAgentsMultiAll() {
        userAgentmultiAll(fiftyone.mobile.detection.test.common.UserAgentGenerator.getUniqueUserAgents());
    }
    
    @Test
    public void uniqueUserAgentsSingle() {
        userAgentsSingle(fiftyone.mobile.detection.test.common.UserAgentGenerator.getUniqueUserAgents());
    }
    
    @Test
    public void uniqueUserAgentsSingleAll() {
        userAgentsSingleAll(fiftyone.mobile.detection.test.common.UserAgentGenerator.getUniqueUserAgents());
    }
    
    @Test
    public void badUserAgentsMulti() {
        userAgentMulti(fiftyone.mobile.detection.test.common.UserAgentGenerator.getBadUserAgents());
    }
    
    @Test
    public void badUserAgentsMultiAll() {
        userAgentmultiAll(fiftyone.mobile.detection.test.common.UserAgentGenerator.getBadUserAgents());
    }
    
    @Test
    public void badUserAgentsSingle() {
        userAgentsSingle(fiftyone.mobile.detection.test.common.UserAgentGenerator.getBadUserAgents());
    }
    
    @Test
    public void badUserAgentsSingleAll() {
        userAgentsSingleAll(fiftyone.mobile.detection.test.common.UserAgentGenerator.getBadUserAgents());
    }
    
    public void setUp() {
        System.out.println();
        System.out.printf("Setup test with file '%s'\r\n", dataFile);
    }
    
    @Before
    public void createDataSet() {
        assumeFileExists(super.dataFile);
        long startTime = Calendar.getInstance().getTimeInMillis();
        try {
            provider = TrieFactory.create(super.dataFile);
        } catch (IOException ex) {
            fail(ex.getMessage());
        }
        setUpTime = (int)(Calendar.getInstance().getTimeInMillis() - 
                startTime);
        setUp();
    }
    
}
