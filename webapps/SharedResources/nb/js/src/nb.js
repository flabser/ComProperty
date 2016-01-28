/**
 * @author Medet
 */

'use strict';

var nb = {
    APP_NAME: location.hostname,
    LANG_ID: 'RUS',
    debug: true,
    strings: {
        'yes': 'Да',
        'no': 'Нет',
        ok: 'Ok',
        cancel: 'Отмена',
        select: 'Выбрать',
        dialog_select_value: 'Вы не сделали выбор'
    },
    form: {},
    dialog: {},
    utils: {},
    xhr: {}
};

var nbApp = { /* local application namespace */ };

/**
 * ajax
 */
nb.ajax = function(options) {

    var deferred = $.ajax(options);

    // error
    deferred.error(function(xhr) {
        console.error('nb.ajax : error', xhr);

        if (xhr.status == 400) {
            nb.dialog.error({
                title: nb.getText('error_xhr', 'Ошибка запроса'),
                message: xhr.responseText
            });
        }

        return xhr;
    });

    return deferred;
};

/**
 * getText
 */
nb.getText = function(stringKey, defaultText, langId) {
    if (nbStrings[langId || this.LANG_ID][stringKey]) {
        return nbStrings[langId || this.LANG_ID][stringKey];
    } else {
        return (defaultText !== undefined) ? defaultText : stringKey;
    }
};

/**
 * openXML
 */
nb.openXML = function() {
    window.location.href = window.location + '&onlyxml';
};

/**
 * setValues
 */
nb.form.setValues = function(currentNode) {

    var $dlgw = $(currentNode).parents('[role=dialog]');
    var $dlgWgt = $('[data-role=nb-dialog]', $dlgw);

    var form = nb.utils.getForm($dlgWgt[0].dialogOptions.targetForm);
    var fieldName = $dlgWgt[0].dialogOptions.fieldName;

    var nodeList; // коллекция выбранных
    var isMulti = false;
    var itemSeparate = '';
    var displaySeparate = '<br/>'; // отобразить мульти значения разделителем

    if (!form) {
        nb.dialog.warn({
            title: 'Error',
            message: 'Error nb.form.setValues > form is not found: ' + form
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
            $('[data-input=' + fieldName + ']').html(htm.join(''));
        } else {
            var $fieldNode = $('[name=' + fieldName + ']', form);
            if ($fieldNode.length === 0) {
                $fieldNode = $('<input type="hidden" name="' + fieldName + '" />');
                $(form).append($fieldNode[0]);
            }

            $fieldNode.val(nodeList[0].value);
            $('[data-input=' + fieldName + ']').html('<li>' + nodeList.attr('data-text') + '</li>');
        }

        return true;
    }
};

/**
 * clearField
 */
nb.utils.clearField = function(fieldName, context) {
    $('[name=' + fieldName + ']').val('');
    $('[data-input=' + fieldName + ']').html('');
};

/**
 * getForm
 */
nb.utils.getForm = function(el) {
    if (el === null || el === undefined) {
        return el;
    }

    if (typeof(el) === 'string' && (document[el] && document[el].nodeName === 'FORM')) {
        return document[el];
    } else {
        var $pf = $(el).parents('form')
        if ($pf.length) {
            el = $pf.get(0);
        }
    }

    return el.form || el;
};

/**
 * blockUI
 */
nb.utils.blockUI = function() {
    var $el = $('#nb-block-ui');
    if ($el.length === 0) {
        $el = $('<div id="nb-block-ui" style="background:rgba(0,0,0,0.1);cursor:wait;position:fixed;top:0;left:0;bottom:0;right:0;z-index:999;"/>');
        $el.appendTo('body');
    }

    $el.css('display', 'block');
};

/**
 * unblockUI
 */
nb.utils.unblockUI = function() {
    $('#nb-block-ui').css('display', 'none');
};

/**
 * notify
 */
nb.utils.notify = function(opt) {

    var $nwrap = $('#nb-notify-wrapper');
    if (!$nwrap.length) {
        $nwrap = $('<div id=nb-notify-wrapper class=nb-notify></div>').appendTo('body');
    }
    var $el = $('<div class=nb-notify-entry-' + (opt.type || 'info') + '>' + opt.message + '</div>').appendTo($nwrap);

    return {
        show: function(timeout) {
            $el.css('display', 'block');
            if (timeout && timeout > 0) {
                this.remove(timeout);
                return;
            }
            return this;
        },
        hide: function() {
            $el.css('display', 'none');
            return this;
        },
        set: function(opt) {
            for (var key in opt) {
                if (key === 'text') {
                    $el.text(opt[key]);
                } else if (key === 'type') {
                    $el.attr('class', 'nb-notify-entry-' + opt[key]);
                }
            }
            return this;
        },
        remove: function(timeout, callback) {
            if ($el === null) {
                return;
            }

            if (timeout && timeout > 0) {
                var _this = this;
                setTimeout(function() {
                    $el.remove();
                    $el = null;
                    callback && callback();
                }, timeout);
            } else {
                $el.remove();
                $el = null;
                callback && callback();
            }
        }
    };
};

$(document).ready(function() {
    nb.LANG_ID = $.cookie('lang') || 'RUS';

    //
    var oreo = localStorage.getItem('side-tree-toggle');
    var ary = [];
    if (oreo != null) {
        ary = oreo.split(',');
    } else {
        localStorage.setItem('side-tree-toggle', '');
    }
    $('[data-nav]', '.aside').each(function() {
        if (ary.indexOf($(this).data('nav')) != -1) {
            $(this).removeClass('nav-link-collapsed');
        }
    });

    $(':checkbox').bind('click', function() {
        var $checkbox = $(this);

        if (!$checkbox.data('toggle')) {
            return true;
        }

        var name = this.name || $checkbox.data('toggle');
        var $el = $('[name=' + name + ']:checkbox:visible');

        if ($checkbox.is(':checked')) {
            $el.each(function() {
                this.checked = true;
            });
        } else {
            $el.each(function() {
                this.checked = false;
            });
        }
    });

    $('[data-role=side-tree-toggle]').click(function(e) {
        e.preventDefault();
        e.stopPropagation();
        var $parent = $(this).parent();
        $parent.toggleClass('nav-link-collapsed');
        //
        var storageKey = 'side-tree-toggle';
        var navId = $parent.data('nav');
        var oreo = localStorage.getItem(storageKey);
        var ary = oreo.split(',');

        if ($parent.hasClass('nav-link-collapsed')) {
            var index = ary.indexOf(navId);
            if (index > -1) {
                ary.splice(index, 1);
            }
            localStorage.setItem(storageKey, ary.join(','));
        } else {
            ary.push(navId);
            localStorage.setItem(storageKey, ary.join(','));
        }
    });

    //
    $(document).on('click', '[data-toggle=panel]', function() {
        var $panel = $(this).parents('.panel');
        if ($panel.hasClass('open')) {
            $panel.removeClass('open');
        } else {
            $panel.addClass('open');
        }
    });

    //
    $(window).resize(function() {
        if (window.innerWidth <= 800) {
            $('body').addClass('phone');
        } else {
            $('body').removeClass('phone');
        }
    });

    if (window.innerWidth <= 800) {
        $('body').addClass('phone');
    }

    //
    setTimeout(function() {
        $('body').removeClass('no_transition');
    }, 200);
});
