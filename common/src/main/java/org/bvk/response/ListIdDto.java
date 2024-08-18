package org.bvk.response;

import java.util.ArrayList;
import java.util.List;

public class ListIdDto {

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    private List<Long> idList = new ArrayList<>();
}
