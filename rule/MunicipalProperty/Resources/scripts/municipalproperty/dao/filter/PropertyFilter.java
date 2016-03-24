package municipalproperty.dao.filter;

import municipalproperty.model.constants.KufType;
import staff.model.Organization;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Medet created 23.03.2016.
 */
public class PropertyFilter {

    private List<Organization> balanceHolders;
    private List<KufType> kufTypes;

    public PropertyFilter() {
        balanceHolders = new ArrayList<>();
        kufTypes = new ArrayList<>();
    }

    public void setKufTypes(List<KufType> kufTypes) {
        this.kufTypes = kufTypes;
    }

    public void setKufType(KufType kufType) {
        kufTypes.clear();
        kufTypes.add(kufType);
    }

    public void addKufType(KufType kufType) {
        kufTypes.add(kufType);
    }

    public List<KufType> getKufTypes() {
        return kufTypes;
    }

    public void setBalanceHolders(List<Organization> organizations) {
        balanceHolders = organizations;
    }

    public void setBalanceHolder(Organization organization) {
        balanceHolders.clear();
        balanceHolders.add(organization);
    }

    public void addBalanceHolder(Organization organization) {
        balanceHolders.add(organization);
    }

    public List<Organization> getBalanceHolders() {
        return balanceHolders;
    }
}