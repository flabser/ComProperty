nbApp.dialogChoiceBalanceHolder = function(el) {
    var form = nb.getForm(el);
    var dlg = nb.dialog.show({
        targetForm: form.name,
        fieldName: 'balanceholder',
        title: el.title,
        href: 'Provider?id=get-organizations',
        buttons: {
            'ok': {
                text: nb.getText('select'),
                click: function() {
                    dlg[0].dialogOptions.onExecute();
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
};
