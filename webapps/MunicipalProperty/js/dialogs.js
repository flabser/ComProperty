/*
 Не допускать разбухания функции.
 Если нужны условия для какого та диалога, вынести в саму функцию диалога вызывающего эту функцию.
 Не писать условия в кнопке, типа если id == '?' то делать то-то; Вынасите в вызывающую функцию.

 @param fields
    {
        'название поля модели':
            [
                '* название поля куда прописать значение',
                'поле по которому будет отображен текст [опционально], иначе значение первого *'
            ]
    }
    пример
    {
        id: ['balanceholderid', 'name'],
        bin: ['balanceholderbin']
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
        id: ['balanceholderid', 'name'],
        bin: ['balanceholderbin']
    });
};

nbApp.choiceCountries = function(el) {
    var url = 'Provider?id=get-countries';
    return this.defaultChoiceDialog(el, url, 'json', {
        id: ['countryid', 'name']
    });
};

nbApp.choiceRegion = function(el) {
    var url = 'Provider?id=get-regions';
    return this.defaultChoiceDialog(el, url, 'json', {
        id: ['regionid', 'name']
    });
};

nbApp.choiceDistrict = function(el) {
    var url = 'Provider?id=get-district';
    return this.defaultChoiceDialog(el, url, 'json', {
        id: ['districtid', 'name']
    });
};

nbApp.choiceCity = function(el) {
    var url = 'Provider?id=get-city';
    return this.defaultChoiceDialog(el, url, 'json', {
        id: ['cityid', 'name']
    });
};

nbApp.choiceStreet = function(el) {
    var url = 'Provider?id=get-street';
    return this.defaultChoiceDialog(el, url, 'json', {
        id: ['streetid', 'name']
    });
};
