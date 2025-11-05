package ge.tbc.testautomation.api.model;

import java.util.List;

public class QueryModel {
    private List<String> ccyFrom;
    private List<String> ccyTo;
    private String startDate;
    private String endDate;

    public List<String> getCcyFrom() {
        return ccyFrom;
    }

    public void setCcyFrom(List<String> ccyFrom) {
        this.ccyFrom = ccyFrom;
    }

    public List<String> getCcyTo() {
        return ccyTo;
    }

    public void setCcyTo(List<String> ccyTo) {
        this.ccyTo = ccyTo;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}