/**
 * submitForm
 */
nb.submitForm = function(form) {

    if (!nb.validateForm(form)) {
        var dfd = $.Deferred();
        return dfd.reject(false);
    }

    var notify = nb.notify({
        message: nb.getText('wait_while_document_save', 'Пожалуйста ждите... идет сохранение документа'),
        type: 'process'
    }).show();

    var xhrArgs = {
        cache: false,
        type: 'POST',
        dataType: 'json',
        url: 'Provider',
        data: $(form).serialize(),
        beforeSend: function() {
            nb.uiBlock();
            // clear errors
            $('.required, .has-error', form).removeClass('required has-error');
            $('.error-massage', form).remove();
        },
        success: function(response) {
            notify.set({
                text: response.captions.type,
                type: 'success'
            });

            if (response.redirectURL) {
                window.location.href = response.redirectURL;
            }
            return response;
        },
        error: function(err) {
            var response = err.responseJSON;
            var msg = {
                type: 'error'
            };
            if (response.type === 'VALIDATION_ERROR') {
                msg.text = response.captions.type;
                nb.validateForm(form, response.validation);
            } else if (response.type === 'SERVER_ERROR') {
                msg.text = response.captions.type;
            }
            notify.set(msg);
            return err;
        },
        complete: function() {
            nb.uiUnblock();
            notify.remove(2000);
        }
    };

    return nb.ajax(xhrArgs);
};

/**
 * validateForm
 */
nb.validateForm = function(form, validation) {
    var result = true;

    if (validation && validation.errors) {
        var ers = validation.errors;
        for (var index in ers) {
            if (index == 0) {
                $('[name=' + ers[index].field + ']').focus();
            }
            $('[name=' + ers[index].field + ']', form).attr('required', 'required').addClass('required');
            var $di = $('[data-input=' + ers[index].field + ']', form).addClass('required');
            var $erms = $('<div class=error-massage>' + ers[index].message + '</div>');
            if ($di.length) {
                $di.after($erms);
                $di.parent().addClass('has-error');
            } else {
                $('[name=' + ers[index].field + ']', form).after($erms);
                $('[name=' + ers[index].field + ']', form).parent().addClass('has-error');
            }
        }
    }

    if ($('[required]:invalid', form).length) {
        $('[required]:invalid', form).first().focus();
        result = false;
    }

    return result;
};

/**
 * setFormValues
 */
nb.setFormValues = function(currentNode) {

    var $dlgw = $(currentNode).parents('[role=dialog]');
    var $dlgWgt = $('[data-role=nb-dialog]', $dlgw);
    var dialogOptions = $dlgWgt[0].dialogOptions;

    var form = nb.getForm(dialogOptions.targetForm);
    var fields = dialogOptions.fields;

    if (!form) {
        nb.dialog.warn({
            title: 'Error',
            message: 'Error nb.setFormValues > form is not found: ' + form
        });
        return false;
    }

    var dataList = $('[data-type=select]:checked', $dlgw); // коллекция выбранных
    if (dataList.length) {
        return _writeValues();
    } else {
        if (dialogOptions.effect) {
            $dlgw.stop();
            $dlgw.effect(dialogOptions.effect, {
                times: 2
            }, 300);
        }

        if ($('.no-selected-value', $dlgw[0]).length === 0) {
            (function() {
                var $_html = $('<div class=no-selected-value>' + dialogOptions.errorMessage + '</div>');
                $dlgWgt.after($_html);
                setTimeout(function() {
                    $_html.fadeOut({
                        always: function() {
                            $_html.remove();
                        }
                    });
                }, 500);
            })();
        }

        return false;
    }

    // write values to form
    function _writeValues() {
        var isMulti = dataList.length > 1;
        var multiFieldMap = {};

        dataList.each(function() {
            var dataId = this.value;
            var field;
            var $val;
            var $targetFieldNode;
            var value;
            var text;

            for (field in fields) {
                $val = $('[data-id=' + dataId + '][name=' + fields[field][0] + ']', $dlgw);
                $targetFieldNode = $('[name=' + field + ']', form);
                value = $val.val();
                text = $val.data('text');
                if (!text) text = value;

                if (isMulti) {
                    if (multiFieldMap[field] !== true) {
                        $targetFieldNode.remove();
                    }
                    $targetFieldNode = $('<input type="hidden" name="' + field + '" />');
                    $targetFieldNode.appendTo(form);
                } else {
                    var $hf = $('[type=hidden][name=' + field + ']', form);
                    if ($hf.length > 1) {
                        $hf.remove();
                        $targetFieldNode = $('<input type="hidden" name="' + field + '" />');
                        $targetFieldNode.appendTo(form);
                    }
                }

                if ($targetFieldNode.length === 0) {
                    $targetFieldNode = $('<input type="hidden" name="' + field + '" />');
                    $targetFieldNode.appendTo(form);
                }

                $targetFieldNode.val(value);

                if (isMulti) {
                    if (multiFieldMap[field] !== true) {
                        multiFieldMap[field] = true;
                        $('[data-input=' + field.replace('id', '') + ']', form).html('<li>' + text + '</li>');
                    } else {
                        $('[data-input=' + field.replace('id', '') + ']', form).append('<li>' + text + '</li>');
                    }
                } else {
                    $('[data-input=' + field.replace('id', '') + ']', form).html(text);
                }
            }
        });
        return true;
    }
};

/**
 * clearFormFields
 */
nb.clearFormFields = function(fields, el) {
    var form = nb.getForm(el);
    for (var index in fields) {
        $('[name=' + fields[index] + ']', form).val('');
        $('[data-input=' + fields[index] + ']', form).html('');
    }
};
