nbApp.dialogChoiceAccessRoles = function(el) {
    var dlg = nb.dialog.show({
        targetForm: el.form.name,
        fieldName: 'balanceholder',
        title: el.title,
        href: 'Provider?id=get-employees',
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
