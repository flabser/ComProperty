/*
 Не допускать разбухания функции.
 Если нужны условия для какого та диалога, вынести в саму функцию диалога вызывающего эту функцию.
 Не писать условия в кнопке, типа если id == '?' то делать то-то; Вынасите в вызывающую функцию.
*/
nbApp.defaultChoiceDialog = function(el, id, dataType, fields, templateId) {
    var form = nb.getForm(el);
    var dlg = nb.dialog.show({
        targetForm: form.name,
        fields: fields,
        title: el.title,
        href: 'Provider?id=' + id,
        dataType: dataType || 'html',
        templateId: templateId,
        buttons: {
            ok: {
                text: nb.getText('select'),
                click: function() {
                    dlg[0].dialogOptions.onExecute();
                }
            },
            cancel: {
                text: nb.getText('cancel'),
                click: function() {
                    dlg.dialog('close');
                }
            }
        }
    });
    return dlg;
};

nbApp.choiceBalanceHolder = function(el) {
    return this.defaultChoiceDialog(el, 'get-organizations', 'html', {
        docid: 'balanceholderid',
        bin: 'balanceholderbin'
    });
};

nbApp.choiceCountries = function(el) {
    return this.defaultChoiceDialog(el, 'get-countries', 'json', { id: 'countryid' });
};

nbApp.choiceRegion = function(el) {
    return this.defaultChoiceDialog(el, 'get-regions', 'json', { id: 'regionid' });
};

nbApp.choiceDistrict = function(el) {
    return this.defaultChoiceDialog(el, 'get-district', 'json', { id: 'districtid' });
};

nbApp.choiceCity = function(el) {
    return this.defaultChoiceDialog(el, 'get-city', 'json', { id: 'cityid' });
};

nbApp.choiceStreet = function(el) {
    return this.defaultChoiceDialog(el, 'get-street', 'json', { id: 'streetid' });
};
