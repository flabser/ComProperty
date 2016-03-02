/*
 Не допускать разбухания функции.
 Если нужны условия для какого та диалога, вынести в саму функцию диалога вызывающего эту функцию.
 Не писать условия в кнопке, типа если id == '?' то делать то-то; Вынасите в вызывающую функцию.

 @param fields
    {
        'целевое поле':
            [
                '* название поля модели от куда брать значение',
                'название поля модели от куда брать значение для текста, иначе значение первого * [опционально], назначение для [data-input]'
            ]
    }
    пример
    {
        balanceholderid: ['id', 'name'],
        balanceholderbin: ['bin']
    }
*/
nbApp.defaultChoiceDialog = function(el, url, dataType, fields, templateId) {
    var form = nb.getForm(el);
    var dlg = nb.dialog.show({
        targetForm: form.name,
        fields: fields,
        title: el.title,
        href: url,
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
    var url = 'Provider?id=get-organizations';
    return this.defaultChoiceDialog(el, url, 'json', {
        balanceholderid: ['id', 'name'],
        balanceholderbin: ['bin']
    });
};

nbApp.choiceCountries = function(el) {
    var url = 'Provider?id=get-countries';
    return this.defaultChoiceDialog(el, url, 'json', {
        countryid: ['id', 'name']
    });
};

nbApp.choiceRegion = function(el) {
    var url = 'Provider?id=get-regions';
    return this.defaultChoiceDialog(el, url, 'json', {
        regionid: ['id', 'name']
    });
};

nbApp.choiceDistrict = function(el) {
    var form = nb.getForm(el);
    var regionId = form.regionid.value;
    var url = 'Provider?id=get-district&regionid=' + regionId;
    return this.defaultChoiceDialog(el, url, 'json', {
        districtid: ['id', 'name']
    });
};

nbApp.choiceCity = function(el) {
    var url = 'Provider?id=get-city';
    return this.defaultChoiceDialog(el, url, 'json', {
        cityid: ['id', 'name']
    });
};

nbApp.choiceStreet = function(el) {
    var url = 'Provider?id=get-street';
    return this.defaultChoiceDialog(el, url, 'json', {
        streetid: ['id', 'name']
    });
};
