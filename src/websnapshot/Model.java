/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websnapshot;

import java.util.Set;

/**
 *
 * @author mista
 */
class Model {

    private int invalidCount;
    private Set<String> invalidUrlList;
    private Set<String> validUrlList;

    public Model() {
        super();
    }

    public Model(int invalidCount, Set<String> invalidUrlList, Set<String> validUrlList) {
        super();
        this.invalidCount = invalidCount;
        this.invalidUrlList = invalidUrlList;
        this.validUrlList = validUrlList;
    }

    public int getInvalidCount() {
        return invalidCount;
    }

    public void setInvalidCount(int invalidCount) {
        this.invalidCount = invalidCount;
    }

    public Set<String> getInvalidUrlList() {
        return invalidUrlList;
    }

    public void setInvalidUrlList(Set<String> invalidUrlList) {
        this.invalidUrlList = invalidUrlList;
    }

    public Set<String> getValidUrlList() {
        return validUrlList;
    }

    public void setValidUrlList(Set<String> validUrlList) {
        this.validUrlList = validUrlList;
    }

}
