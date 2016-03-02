nbApp.defaultChoiceDialog = function(el, url, fields, callback) {
    var form = nb.getForm(el);
    var dlg = nb.dialog.show({
        targetForm: form.name,
        fields: fields,
        title: el.title,
        href: url,
        dataType: 'json',
        buttons: {
            ok: {
                text: nb.getText('select'),
                click: function() {
                    dlg[0].dialogOptions.onExecute();
                    callback && callback();
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

nbApp.choiceBalanceHolder = function(el, callback) {
    var form = nb.getForm(el);
    var url = 'Provider?id=get-organizations&_fn=' + form.name;
    return this.defaultChoiceDialog(el, url, {
        id: ['balanceholder', 'name'],
        bin: ['balanceholderbin']
    }, callback);
};

nbApp.choiceReaders = function(el, callback) {
    var form = nb.getForm(el);
    var url = 'Provider?id=get-employees&_fn=' + form.name;
    return this.defaultChoiceDialog(el, url, {
        id: ['reader', 'name']
    }, callback);
};
