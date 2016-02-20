/*
 Не допускать разбухания функции.
 Если нужны условия для какого та диалога, вынести в саму функцию диалога вызывающего эту функцию.
 Не писать условия в кнопке, типа если id == '?' то делать то-то; Вынасите в вызывающую функцию.
*/
nbApp.choiceDialog = function(el, id, fieldName) {
    var form = nb.getForm(el);
    var dlg = nb.dialog.show({
        targetForm: form.name,
        fieldName: fieldName,
        title: el.title,
        href: 'Provider?id=' + id,
        datatype: 'json',
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
    return this.choiceDialog(el, 'get-organizations', 'balanceholderid');
};

nbApp.choiceCountries = function(el) {
    return this.choiceDialog(el, 'get-countries', 'country');
};

nbApp.choiceRegion = function(el) {
    return this.choiceDialog(el, 'get-regions', 'region');
};

nbApp.choiceDistrict = function(el) {
    return this.choiceDialog(el, 'get-district', 'district');
};

nbApp.choiceCity = function(el) {
    return this.choiceDialog(el, 'get-city', 'city');
};

nbApp.choiceStreet = function(el) {
    return this.choiceDialog(el, 'get-street', 'street');
};
