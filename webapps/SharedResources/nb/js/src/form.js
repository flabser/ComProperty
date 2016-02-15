/**
 * submitForm
 */
nb.submitForm = function(form) {

    /*if (!nb.validateForm(form)) {
        var dfd = $.Deferred();
        return dfd.reject(false);
    }*/

    var notify = nb.notify({
        message: nb.getText('wait_while_document_save', 'Пожалуйста ждите... идет сохранение документа'),
        type: 'process'
    }).show();

    var xhrArgs = {
        cache: false,
        type: 'POST',
        datatype: 'json',
        url: 'Provider',
        data: $(form).serialize(),
        beforeSend: function() {
            nb.uiBlock();
            $('.required', form).removeClass('required');
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
            var erms = $('<div class=error-massage>' + ers[index].message + '</div>');
            if ($di.length) {
                $di.after(erms);
                $di.parent().addClass('has-error');
            } else {
                $('[name=' + ers[index].field + ']', form).after(erms);
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

    var form = nb.getForm($dlgWgt[0].dialogOptions.targetForm);
    var fieldName = $dlgWgt[0].dialogOptions.fieldName;

    var nodeList; // коллекция выбранных
    var isMulti = false;
    var itemSeparate = '';
    var displaySeparate = '<br/>'; // отобразить мульти значения разделителем

    if (!form) {
        nb.dialog.warn({
            title: 'Error',
            message: 'Error nb.setFormValues > form is not found: ' + form
        });
        return false;
    }

    nodeList = $('[data-type=select]:checked', $dlgWgt[0]);
    if (nodeList.length > 0) {
        isMulti = nodeList.length > 1;
        if (!isMulti) {
            itemSeparate = '';
        }

        return _writeValues(nodeList);
    } else {
        if ($dlgWgt[0].dialogOptions.effect) {
            $dlgw.stop();
            $dlgw.effect($dlgWgt[0].dialogOptions.effect, {
                times: 2
            }, 300);
        }

        if ($('.js-no-selected-value', $dlgw[0]).length === 0) {
            (function() {
                var $_html = $('<div class="alert alert-danger js-no-selected-value" style="border-radius:2px;bottom:80px;left:4%;right:4%;position:absolute;">' + $dlgWgt[0].dialogOptions.errorMessage + '</div>');
                $dlgWgt.after($_html);
                setTimeout(function() {
                    $_html.fadeOut({
                        always: function() {
                            $_html.remove();
                        }
                    });
                }, 800);
            })();
        }

        return false;
    }

    // write values to form
    function _writeValues() {
        if (isMulti) {
            $('[name=' + fieldName + ']', form).remove();
            var htm = [];
            nodeList.each(function(index, node) {
                $('<input type="hidden" name="' + fieldName + '" value="' + node.value + '" />').appendTo(form);
                htm.push('<li>' + $(node).data('text') + '</li>');
            });
            $('[data-input=' + fieldName + ']', form).html(htm.join(''));
        } else {
            var $fieldNode = $('[name=' + fieldName + ']', form);
            if ($fieldNode.length === 0) {
                $fieldNode = $('<input type="hidden" name="' + fieldName + '" />');
                $(form).append($fieldNode[0]);
            }

            $fieldNode.val(nodeList[0].value);
            $('[data-input=' + fieldName.replace('id', '') + ']', form).html('<li>' + nodeList.attr('data-text') + '</li>');
        }

        return true;
    }
};

/**
 * clearFormField
 */
nb.clearFormField = function(fieldName, context) {
    $('[name=' + fieldName + ']', context).val('');
    $('[data-input=' + fieldName + ']', context).html('');
};
