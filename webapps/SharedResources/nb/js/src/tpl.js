nb.tpl = {};

/**
 * defaultDialogListTemplate
 */
nb.tpl.defaultDialogListTemplate = function(data) {

    if (!data) {
        return 'data_error';
    }

    var models = data.objects[0];
    if (!models.length) {
        return nb.getText('empty');
    }

    var fields = this.fields;
    var dialogId = this.id;
    var m, index, tfname, fname, ftext, dataText;
    var html = [];

    html.push('<ul class=nb-dialog-list>');
    for (index in models) {
        m = models[index];
        html.push('<li class=nb-dialog-list-it>');
        html.push(' <label ondblclick="nb.dialog.execute(this)">');
        html.push('  <input data-type="select" type="radio" name="select_' + dialogId + '" value="' + m.id + '"/>');
        html.push('  <span>' + m.name + '</span>');

        for (tfname in fields) {
            fname = fields[tfname][0];
            ftext = fields[tfname][1];
            if (ftext) {
                dataText = ' data-text="' + m[ftext] + '"';
            } else {
                dataText = '';
            }
            html.push('<input data-id="' + m.id + '" name="' + fname + '" value="' + m[fname] + '"' + dataText + ' type="hidden"/>');
        }

        html.push(' </label>');
        html.push('</li>');
    }
    html.push('</ul>');

    return html.join('');
};
