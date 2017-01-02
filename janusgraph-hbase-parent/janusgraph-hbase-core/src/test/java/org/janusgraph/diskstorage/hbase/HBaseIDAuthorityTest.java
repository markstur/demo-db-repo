package org.janusgraph.diskstorage.hbase;

import org.janusgraph.HBaseStorageSetup;
import org.janusgraph.diskstorage.BackendException;
import org.janusgraph.diskstorage.IDAuthorityTest;
import org.janusgraph.diskstorage.configuration.WriteConfiguration;
import org.janusgraph.diskstorage.keycolumnvalue.KeyColumnValueStoreManager;

import org.apache.hadoop.hbase.util.VersionInfo;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;

import java.io.IOException;


public class HBaseIDAuthorityTest extends IDAuthorityTest {

    public HBaseIDAuthorityTest(WriteConfiguration baseConfig) {
        super(baseConfig);
    }

    @BeforeClass
    public static void startHBase() throws IOException {
        HBaseStorageSetup.startHBase();
    }

    @AfterClass
    public static void stopHBase() {
        // Workaround for https://issues.apache.org/jira/browse/HBASE-10312
        if (VersionInfo.getVersion().startsWith("0.96"))
            HBaseStorageSetup.killIfRunning();
    }

    public KeyColumnValueStoreManager openStorageManager() throws BackendException {
        return new HBaseStoreManager(HBaseStorageSetup.getHBaseConfiguration());
    }
}