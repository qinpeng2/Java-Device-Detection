/* *********************************************************************
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
 * ********************************************************************* */
package fiftyone.mobile.detection.factories;

import fiftyone.mobile.detection.Dataset;
import fiftyone.mobile.detection.entities.Profile;
import fiftyone.mobile.detection.readers.BinaryReader;
import java.io.IOException;

/**
 * Creates new instances of a Profile entity.
 * <p>
 * Objects of this class should not be created directly as they are part of the 
 * internal logic.
 */
public abstract class ProfileFactory extends BaseEntityFactory<Profile> {

    /**
     * Size of byte + int + int + int.
     */
    private static final int MIN_LENGTH = 1 + 4 + 4 + 4;
    
    /**
     * Creates a new instance of Profile.
     * 
     * @param dataSet The data set whose profile list the profile is contained 
     * within.
     * @param index The offset to the start of the profile within the profile 
     * data structure.
     * @param reader Binary reader positioned at the start of the Profile.
     * @return A new instance of an Profile.
     */
    @Override
    public Profile create(Dataset dataSet, int index,
            BinaryReader reader) {
        return construct(dataSet, index, reader);
    }

    /**
     * Returns the length of the Profile entity.
     * 
     * @param entity Entity of type.
     * @return Length in bytes of the Profile.
     * @throws java.io.IOException if there was a problem accessing data file.
     */
    @Override
    public int getLength(Profile entity) throws IOException {
        return MIN_LENGTH + 
            (entity.getValueIndexes().length * 4) + 
            (entity.getSignatureIndexes().length * 4);
    }
    
    /**
     * Returns a new Profile object. Implementation differs for memory and 
     * stream.
     * 
     * @param dataSet the data set whose profile list the profile is contained 
     *                within.
     * @param index the offset to the start of the profile within the profile 
     *              data structure.
     * @param reader Binary reader positioned at the start of the Profile.
     * @return A new instance of an Profile.
     */
    protected abstract Profile construct(Dataset dataSet, int index,
            BinaryReader reader);
}
