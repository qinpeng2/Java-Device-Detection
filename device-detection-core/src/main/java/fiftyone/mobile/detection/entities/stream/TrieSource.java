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
package fiftyone.mobile.detection.entities.stream;

import fiftyone.mobile.detection.WrappedIOException;
import fiftyone.mobile.detection.readers.TrieReader;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Encapsulates either a byte array or a file containing the uncompressed data
 * structures used by the data set. <p> Must be disposed to ensure that the
 * readers are closed and the file free for other uses. Does not need to be
 * disposed if a byte array is used.
 */
public class TrieSource implements Closeable {

    private final FileInputStream fileInputStream;

    public TrieSource(String filename, boolean isTempFile) 
                                                throws FileNotFoundException {
        fileInputStream = new FileInputStream(filename);
    }

    @Override
    public void close() {
        try {
            fileInputStream.close();
        } catch (IOException ex) {
            throw new WrappedIOException(ex.getMessage());
        }
    }

    /**
     * Creates a new reader and stores a reference to it.
     *
     * @return A reader open for read access to the stream
     * @throws java.io.IOException indicates an I/O exception occurred
     */
    public synchronized TrieReader createReader() throws IOException {
        return new TrieReader(fileInputStream.getChannel());
    }
}
