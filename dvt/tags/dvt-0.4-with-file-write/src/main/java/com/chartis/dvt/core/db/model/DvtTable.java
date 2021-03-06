package com.chartis.dvt.core.db.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author CHAMINDA.AMARASINGHE
 *
 */
public class DvtTable {

    private String lineOfBusiness;
    private String name;
    private List<DvtColumn> dvtColumns;

    public String getLineOfBusiness() {
        return lineOfBusiness;
    }

    public void setLineOfBusiness(final String lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<DvtColumn> getDvtColumns() {
        return dvtColumns;
    }

    public void setDvtColumns(final List<DvtColumn> dvtColumns) {
        this.dvtColumns = dvtColumns;
    }

    public void addDvtColumn(final DvtColumn dvtColumn) {
        if (dvtColumns == null) {
            dvtColumns = new ArrayList<DvtColumn>();
        }
        dvtColumns.add(dvtColumn);
    }

    @Override
    public boolean equals(final Object that) {
        if (that instanceof DvtTable) {
            final DvtTable thatTable = (DvtTable) that;
            if (this.lineOfBusiness.equals(thatTable.lineOfBusiness) && this.name.equals(thatTable.name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.lineOfBusiness != null ? this.lineOfBusiness.hashCode() : 0);
        hash = 67 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }
    
}
