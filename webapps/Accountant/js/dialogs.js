/*
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
nbApp.defaultChoiceDialog = function(el, url, fields, callback) {
    var form = nb.getForm(el);
    var dlg = nb.dialog.show({
        targetForm: form.name,
        fields: fields,
        title: el.title,
        href: url,
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
    var url = 'Provider?id=get-organizations&_fn=' + form.name;
    return this.defaultChoiceDialog(el, url, {
        balanceholder: ['id', 'name'],
        balanceholderbin: ['bin']
    }, callback);
};

nbApp.choiceReaders = function(el, callback) {
    var form = nb.getForm(el);
    var url = 'Provider?id=get-employees&_fn=' + form.name;
    return this.defaultChoiceDialog(el, url, {
        reader: ['id', 'name']
    }, callback);
};
