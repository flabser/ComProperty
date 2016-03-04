/*
 @param fields
    {
        'целевое поле':
            [
                '* название поля модели от куда брать значение',
                'название поля модели от куда брать значение для текста [data-input], иначе значение первого * [опционально]'
            ]
    }
    @example
    {
        balanceholderid: ['id', 'name'],
        balanceholderbin: ['bin']
    }
*/
nbApp.defaultChoiceDialog = function(el, url, fields, isMulti, callback) {
    var form = nb.getForm(el);
    var dlg = nb.dialog.show({
        targetForm: form.name,
        fields: fields,
        isMulti: isMulti,
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
    var url = 'Provider?id=get-organizations&_fn=' + nb.getForm(el).name;
    return this.defaultChoiceDialog(el, url, {
        balanceholder: ['id', 'name'],
        balanceholderbin: ['bin']
    }, false, callback);
};

nbApp.choiceReaders = function(el, callback) {
    var url = 'Provider?id=get-employees&_fn=' + nb.getForm(el).name;
    return this.defaultChoiceDialog(el, url, {
        reader: ['id', 'name']
    }, true, callback);
};
