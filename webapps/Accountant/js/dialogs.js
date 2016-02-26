nbApp.dialogChoiceBalanceHolder = function(el, callback) {
    var form = nb.getForm(el);
    var dlg = nb.dialog.show({
        targetForm: form.name,
        fieldName: 'balanceholder',
        title: el.title,
        href: 'Provider?id=get-organizations&_fn=' + form.name,
        buttons: {
            'ok': {
                text: nb.getText('select'),
                click: function() {
                    dlg[0].dialogOptions.onExecute();
                    callback();
                }
            },
            'cancel': {
                text: nb.getText('cancel'),
                click: function() {
                    dlg.dialog('close');
                }
            }
        }
    });
    return dlg;
};

nbApp.dialogChoiceReaders = function(el, callback) {
    var form = nb.getForm(el);
    var dlg = nb.dialog.show({
        targetForm: form.name,
        fieldName: 'reader',
        title: el.title,
        href: 'Provider?id=get-employees&_fn=' + form.name,
        buttons: {
            'ok': {
                text: nb.getText('select'),
                click: function() {
                    dlg[0].dialogOptions.onExecute();
                    callback();
                }
            },
            'cancel': {
                text: nb.getText('cancel'),
                click: function() {
                    dlg.dialog('close');
                }
            }
        }
    });
    return dlg;
};
