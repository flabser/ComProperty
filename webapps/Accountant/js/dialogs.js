nbApp.defaultChoiceDialog = function(el, id, fields, callback) {
    var form = nb.getForm(el);
    var dlg = nb.dialog.show({
        targetForm: form.name,
        fields: fields,
        title: el.title,
        href: 'Provider?id=' + id,
        dataType: 'html',
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
    return this.defaultChoiceDialog(el, 'get-organizations&_fn=' + form.name, {
        docid: 'balanceholder',
        bin: 'balanceholderbin'
    }, callback);
};

nbApp.choiceReaders = function(el, callback) {
    var form = nb.getForm(el);
    return this.defaultChoiceDialog(el, 'get-employees&_fn=' + form.name, {
        docid: 'reader'
    }, callback);
};
