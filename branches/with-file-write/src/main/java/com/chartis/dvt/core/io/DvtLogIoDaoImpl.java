package com.chartis.dvt.core.io;

import static com.chartis.dvt.commons.utils.StringUtils.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.chartis.dvt.core.db.model.DvtLog;

/**
 * 
 * @author CHAMINDA.AMARASINGHE
 *
 */
public class DvtLogIoDaoImpl implements DvtLogIoDao{

    private static final String COMMA = ",";
    private BufferedWriter bw;
    
    public DvtLogIoDaoImpl(final String path) throws IOException {
        this(new File(path));
    }

    public DvtLogIoDaoImpl(final File file) throws IOException {
        boolean newFile = !file.exists();
        if(newFile) {
            final File parent = file.getParentFile();
            if (!parent.exists()) {
                parent.mkdir();
            }
            file.createNewFile();
        }
        bw = new BufferedWriter(new FileWriter(file, true));
        if (newFile) {
            writeHeader();
        }
    }

    private void writeHeader() throws IOException{
        bw.write("XML_FILE_NAME,POLICY_NO,CERTIFICATE_NO,RENL_CERT_NO,EFF_DT_SEQ_NO,POL_OFFICE_CD,CLAIM_NO,");
        bw.write("CLM_OFFICE_CD,TABLE_NAME,COL_NAME,COL_VALUE,XML_ELEMENT_VALUE,STATUS_CD,EVAL_CD,");
        bw.write("TRANS_TYPE_DESC,DOMAIN_NAME,LOB_NAME,COL_ORDER,SOURCE_SYSTEM_ID,TIMESTAMP");
        bw.newLine();
    }

    public void save(List<DvtLog> log) {
        throw new UnsupportedOperationException("not yet");
    }

    public void save(final DvtLog log) throws SQLException, IOException {
        bw.write(nullSafeText(log.getXmlFileName()));
        bw.write(COMMA);
        bw.write(nullSafeText(log.getPolicyNo()));
        bw.write(COMMA);
        bw.write(nullSafeText(log.getCertificateNo()));
        bw.write(COMMA);
        bw.write(nullSafeText(log.getRenlCertNo()));
        bw.write(COMMA);
        bw.write(nullSafeText(log.getEffDtSeq_no()));
        bw.write(COMMA);
        bw.write(nullSafeText(log.getPolOfficeCd()));
        bw.write(COMMA);
        bw.write(nullSafeText(log.getClaimNo()));
        bw.write(COMMA);
        bw.write(nullSafeText(log.getClmOfficeCd()));
        bw.write(COMMA);
        bw.write(nullSafeText(log.getTableName()));
        bw.write(COMMA);
        bw.write(nullSafeText(log.getColName()));
        bw.write(COMMA);
        bw.write(nullSafeText(log.getColValue()));
        bw.write(COMMA);
        bw.write(nullSafeText(log.getXmlElementValue()));
        bw.write(COMMA);
        bw.write(nullSafeText(log.getStatusCd()));
        bw.write(COMMA);
        bw.write(nullSafeText(log.getEvalCd()));
        bw.write(COMMA);
        bw.write(nullSafeText(log.getTransTypeDesc()));
        bw.write(COMMA);
        bw.write(nullSafeText(log.getDomainName()));
        bw.write(COMMA);
        bw.write(nullSafeText(log.getLobName()));
        bw.write(COMMA);
        bw.write(nullSafeText(log.getColOrder()));
        bw.write(COMMA);
        bw.write(nullSafeText(log.getSourceSystemId()));
        bw.write(COMMA);
        bw.write(log.getTimestamp().toString());
        bw.newLine();
    }

    public void close() throws IOException {
        bw.close();
    }

    public void flush() throws IOException {
        bw.flush();
    }
}
