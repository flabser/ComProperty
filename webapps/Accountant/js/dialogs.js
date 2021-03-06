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
nbApp.defaultChoiceDialog = function(el, url, fields, isMulti, callback, message) {
    var form = nb.getForm(el);
    var dlg = nb.dialog.show({
        targetForm: form.name,
        fields: fields,
        isMulti: isMulti,
        title: el.title,
        href: url,
        dataType: 'json',
        message: message,
        onExecute: function() {
            if (nb.setFormValues(dlg)) {
                dlg.dialog('close');
            }
            callback && callback()
        },
        buttons: {
            ok: {
                text: nb.getText('ok'),
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

nbApp.defaultConfirmDialog = function(message, callback) {
    var dlg = nb.dialog.warn({
        message: message,
        height: 200,
        buttons: {
            ok: {
                text: nb.getText('ok'),
                click: function() {
                    dlg.dialog('close');
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

nbApp.choicePropertyRecipient = function(el, callback) {
    var url = 'Provider?id=get-organizations&_fn=getrecipient_' + nb.getForm(el).name;
    return this.defaultChoiceDialog(el, url, {
        recipient: ['id', 'name']
    }, false, callback);
};

nbApp.choiceReaders = function(el, callback) {
    var url = 'Provider?id=get-employees&_fn=' + nb.getForm(el).name;
    return this.defaultChoiceDialog(el, url, {
        readers: ['id', 'name']
    }, true, callback);
};

nbApp.confirmWriteOff = function(callback) {
    var message = 'Внимание! Загружаемое имущество будет списано, продолжить загрузку?';
    return this.defaultConfirmDialog(message, callback);
};

nbApp.confirmTransfer = function(callback) {
    var message = 'Внимание! Загружаемое имущество будет передано другому балансодержателю, продолжить?';
    return this.defaultConfirmDialog(message, callback);
};
