package reference.page.action;

import kz.flabs.localization.LanguageType;
import kz.lof.dataengine.jpa.ViewPage;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;
import reference.dao.CountryDAO;
import reference.model.Country;

/**
 * @author Kayra created 17-02-2016
 */

public class GetCountriesAction extends _DoPage {

    @Override
    public void doGET(_Session ses, _WebFormData formData, LanguageType lang) {
        String keyword = formData.getValueSilently("keyword");
        int pageNum = formData.getNumberValueSilently("page", 1);
        int pageSize = ses.pageSize;

        CountryDAO dao = new CountryDAO(ses);
        ViewPage<Country> vp = dao.findAllByKeyword(keyword, pageNum, pageSize);
        addContent(new _POJOListWrapper(vp.getResult(), vp.getMaxPage(), vp.getCount(), vp.getPageNum(), lang));
    }
}
