package reference.page.action;

import kz.flabs.runtimeobj.RuntimeObjUtil;
import kz.lof.dataengine.jpa.ViewPage;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.scripting.event._DoPage;
import reference.dao.LocalityDAO;
import reference.model.Locality;
import reference.model.Region;
import reference.model.Street;

import java.util.List;

/**
 * @author Kayra created 02-03-2016
 */

public class GetStreetAction extends _DoPage {

    @Override
    public void doGET(_Session ses, _WebFormData formData) {
        String keyword = formData.getValueSilently("keyword");
        int pageNum = formData.getNumberValueSilently("page", 1);
        int pageSize = ses.pageSize;

        LocalityDAO lDao = new LocalityDAO(ses);
        Locality city = lDao.findById(formData.getValueSilently("localityid"));
        if (city != null) {
            List<Street> list = city.getStreets();
            long count = list.size();
            int maxPage = RuntimeObjUtil.countMaxPage(count, pageSize);
            if (pageNum == 0) {
                pageNum = maxPage;
            }
            ViewPage<Region> vp = new ViewPage(list, count, maxPage, pageNum);
            addContent(new _POJOListWrapper(vp.getResult(), vp.getMaxPage(), vp.getCount(), vp.getPageNum(), ses));
        } else {
            setValidation(getLocalizedWord("locality_has_not_found", ses.getLang()));
        }
    }
}
