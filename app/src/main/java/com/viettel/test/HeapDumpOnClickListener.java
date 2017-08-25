package com.viettel.test;

import android.os.Debug;
import android.view.View;

import java.io.File;
import java.io.IOException;
 
public class HeapDumpOnClickListener implements View.OnClickListener {
    private static final String HPROF_DUMP_BASENAME = "com.viettel.test";
    private final String dataDir;
 
    public HeapDumpOnClickListener(String dataDir) {
        this.dataDir = dataDir;
    }
 
    @Override
    public void onClick(View v) {
        String absPath = new File(dataDir, HPROF_DUMP_BASENAME).getAbsolutePath();
        try {
            // this'll cause a collection
            Debug.dumpHprofData(absPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
