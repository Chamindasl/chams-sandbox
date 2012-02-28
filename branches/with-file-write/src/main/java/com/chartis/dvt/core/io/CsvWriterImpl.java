package com.chartis.dvt.core.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvWriterImpl implements CsvWriter{

    private BufferedWriter bufferedWriter;
    
    public CsvWriterImpl(final String path) throws IOException {
        this(new File(path));
    }

    public CsvWriterImpl(final File file) throws IOException {
        bufferedWriter = new BufferedWriter(new FileWriter(file));
    }

    public void writeHeader(){
        
    }
}
