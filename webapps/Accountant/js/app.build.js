/**
 * @author Medet
 */

'use strict';

var nb = {
    APP_NAME: location.hostname,
    LANG_ID: 'RUS',
    debug: false,
    translations: {
        yes: 'Да',
        no: 'Нет',
        ok: 'Ok',
        cancel: 'Отмена',
        select: 'Выбрать',
        dialog_select_value: 'Вы не сделали выбор'
    },
    xhr: {}
};

var nbApp = { /* local application namespace */ };

/**
 * ajax
 */
nb.ajax = function(options) {
    return $.ajax(options);
};

/**
 * getText
 */
nb.getText = function(stringKey, defaultText) {
    if (this.translations[stringKey]) {
        return this.translations[stringKey];
    } else {
        return (defaultText !== undefined) ? defaultText : stringKey;
    }
};

/**
 * getForm
 */
nb.getForm = function(el) {
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
 * uiBlock
 */
nb.uiBlock = function() {
    var $el = $('#nb-block-ui');
    if ($el.length === 0) {
        $el = $('<div id="nb-block-ui" style="background:rgba(0,0,0,0.1);cursor:wait;position:fixed;top:0;left:0;bottom:0;right:0;z-index:999;"/>');
        $el.appendTo('body');
    }

    $el.css('display', 'block');
};

/**
 * uiUnblock
 */
nb.uiUnblock = function() {
    $('#nb-block-ui').css('display', 'none');
};

/**
 * template
 */
nb.template = function(templateId, data) {
    if (nb.tpl[templateId]) {
        return nb.tpl[templateId].call(this, data);
    }
    //
    throw new Error('nb.error > Template not found: ' + templateId + ', data: ' + data);
};

// init
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

    // toggle=panel
    $(document).on('click', '[data-toggle=panel]', function() {
        var $panel = $(this).parents('.panel');
        $panel.toggleClass('open');
    });

    // toggle=side-nav
    $(document).on('click', '[data-toggle=side-nav]', function(event) {
        event.preventDefault();
        $('body').toggleClass('side-nav-toggle');
    });

    if ($('#content-overlay').length) {
        $('#content-overlay').mousedown(function(event) {
            event.preventDefault();
            $('body').removeClass('side-nav-toggle  search-open');
            if ($('.navbar-search input.q').length) {
                $('.navbar-search input.q')[0].blur();
            }
        });

        $('#content-overlay')[0].addEventListener('touchstart', function(event) {
            event.preventDefault();
            $('body').removeClass('side-nav-toggle');
        }, false);
    }

    //
    $('.navbar-search input.q').on('focus', function() {
        $('body').addClass('search-open');
    });
    $('.navbar-search input.q').on('blur', function() {
        $('body').removeClass('search-open');
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
    }, 250);
});

/**
 * dialog
 */
nb.dialog = {
    _props: {
        title: nb.APP_NAME
    },
    info: function(opt) {
        opt.className = 'dialog-info';
        opt.width = opt.width || '360';
        opt.height = opt.height || '210';
        opt.buttons = opt.buttons || {
            'Ok': function() {
                $(this).dialog('close');
            }
        };

        return this.show(opt);
    },
    warn: function(opt) {
        opt.className = 'dialog-warn';
        opt.width = opt.width || '360';
        opt.height = opt.height || '210';
        opt.buttons = opt.buttons || {
            'Ok': function() {
                $(this).dialog('close');
            }
        };

        return this.show(opt);
    },
    error: function(opt) {
        opt.className = 'dialog-error';
        opt.width = opt.width || '360';
        opt.height = opt.height || '210';
        opt.buttons = opt.buttons || {
            'Ok': function() {
                $(this).dialog('close');
            }
        };

        return this.show(opt);
    },
    execute: function(dlgInnerNode) {
        var $dlgw = $(dlgInnerNode).parents('[role=dialog]');
        var $dlgWgt = $('[data-role=nb-dialog]', $dlgw);

        $dlgWgt[0].dialogOptions.onExecute(arguments);
    },
    resize: function($dialog) {
        var $dlgw = $($dialog[0]).parents('[role=dialog]');
        //
        var titleBarHeight = $('.ui-dialog-titlebar', $dlgw[0]).outerHeight();
        var actionBarHeight = $('.ui-dialog-buttonpane', $dlgw[0]).outerHeight()
        var searchBarHeight = $('.dialog-filter', $dlgw[0]).outerHeight()
        var barHeight = titleBarHeight + actionBarHeight + searchBarHeight;
        //
        var wh = window.innerHeight;
        var top, height;
        var containerNode = $('.nb-dialog-container', $dlgw[0]).get(0);
        var fch = containerNode.offsetTop;
        for (var i = 0; i < containerNode.children.length; i++) {
            fch += containerNode.children[i].clientHeight;
        }
        //
        if (wh < 500 && fch > 300) {
            top = window.scrollY + 1;
            height = wh - 2;
        } else {
            if (false && containerNode.clientHeight < fch) {
                height = wh - barHeight;
            } else {
                height = fch + 20 + barHeight;
            }
            top = (wh - height) / 2;
            if (top < 0) {
                top = window.scrollY;
                height = wh;
            } else {
                top = top + window.scrollY;
            }
            //
            if (fch > 500 && wh < 800) {
                top = window.scrollY + 1;
                height = wh - 2;
            }
        }
        //
        var left = (window.innerWidth - $dlgw.outerWidth()) / 2;
        //
        $dlgw.css({
            height: height + 'px',
            top: top + 'px',
            left: left + 'px'
        });
        //
        $(containerNode).css({
            height: ($dlgw[0].clientHeight - barHeight) + 'px'
        });
    },
    load: function(url, $container, options) {
        return $.ajax({
            url: url,
            dataType: options.dataType || 'html',
            success: function(response, status, xhr) {
                if (status === 'error') {
                    $container.html('<div class="alert alert-danger">' + status + '</div>');
                } else {
                    if (options.dataType === 'json') {
                        $container.html(nb.template.call(options, options.templateId, response));
                    } else {
                        $container.html(response);
                    }

                    try {
                        if (options.onLoad !== null) {
                            options.onLoad(response, status, xhr);
                        }
                    } catch (e) {
                        console.log('nb.dialog', e);
                    }

                    try {
                        if (options.filter !== false) {
                            new nb.dialog.Filter($container, options.dialogFilterListItem, 13);
                        }
                    } catch (e) {
                        console.log('nb.dialog', e);
                    }
                }
                //
                try {
                    window.dispatchEvent(new Event('resize'));
                } catch (e) {}
                //
                if (nb.debug === true) {
                    console.log('nb.dialog : load callback', xhr);
                }
            },
            error: function(response, status, xhr) {
                if (status === 'error') {
                    $container.html('<div class="alert alert-danger">' + status + '</div>');
                }
                //
                try {
                    window.dispatchEvent(new Event('resize'));
                } catch (e) {}
                //
                if (nb.debug === true) {
                    console.log('nb.dialog : load error', xhr);
                }
            }
        });
    },
    show: function(options) {
        var self = this;
        var $dialog;

        options.id = options.id || null;
        options.title = options.title || this._props.title;
        options.href = options.href || null;
        options.className = options.className || '';
        options.message = options.message || null;
        options.filter = options.filter;
        options.dialogFilterListItem = options.dialogFilterListItem || 'li';
        options.buttons = options.buttons || null;
        options.dialogClass = 'nb-dialog ' + (options.dialogClass ? options.dialogClass : '');
        options.errorMessage = options.errorMessage || nb.getText('dialog_select_value');
        options.templateId = options.templateId || 'defaultDialogListTemplate';

        options.onLoad = options.onLoad || null;
        // onExecute
        options.onExecute = options.onExecute || function() {
            if (nb.setFormValues($dialog)) {
                $dialog.dialog('close');
            }
        };

        options.autoOpen = true;
        if (options.modal === false) {
            options.modal = false;
        } else {
            options.modal = true;
        }

        options.width = options.width || '360';
        options.position = {
            top: window.scrollY
        };
        options.resizable = false;
        options.draggable = false;

        if (!options.id && options.href) {
            options.id = 'dlg_' + options.href.replace(/[^a-z0-9]/gi, '');

            $dialog = $('#' + options.id);
            if ($dialog[0]) {
                if ($dialog.dialog('isOpen') === true) {
                    return;
                } else {
                    $dialog.dialog('open');
                    self.resize($dialog);
                    return;
                }
            }
        } else if (options.id) {
            $dialog = $('#' + options.id);
            if ($dialog[0]) {
                if ($dialog.dialog('isOpen') === true) {
                    return;
                } else {
                    $dialog.dialog('open');
                    self.resize($dialog);
                    return;
                }
            }
        }

        var $dlgContainer;

        if (options.href) {
            $dlgContainer = $('<div data-role="nb-dialog" id="' + options.id + '" class="nb-dialog-container ' + options.className + '"><div class="loading-state"></div></div>');
        } else {
            if (options.id) {
                $dlgContainer = $('<div data-role="nb-dialog" id="' + options.id + '" class="nb-dialog-container ' + options.className + '">' + options.message + '</div>');
            } else {
                $dlgContainer = $('<div data-role="nb-dialog" class="nb-dialog-container ' + options.className + '">' + options.message + '</div>');
            }
        }

        $dlgContainer[0].dialogOptions = options;

        if (options.href) {
            self.load(options.href, $dlgContainer, options);

            $dialog = $dlgContainer.dialog(options);

            $dlgContainer.on('click', 'a', function(e) {
                e.preventDefault();
                self.load(this.href, $dlgContainer, options);
            });
        } else {
            $dialog = $dlgContainer.dialog(options);
        }

        if (!options.id) {
            options.close = options.close || function() {
                $dialog.dialog('destroy');
                $dialog.remove();
                $($dialog.resizeEvent).off();
            };
        }

        var doTimeout;
        $dialog.resizeEvent = $(window).on('resize', function() {
            clearTimeout(doTimeout);
            doTimeout = setTimeout(function() {
                self.resize($dialog);
            }, 100);
        });

        return $dialog;
    }
};

/**
 * nb.dialog.Filter
 */
nb.dialog.Filter = function(_containerNode, _filterNode, _initCount, _triggerLen) {

    var $inputEl = null;
    var initCount = _initCount || 13;
    var triggerLen = _triggerLen || 2;
    var timeout = 300;
    var to = null;
    var enabledViewSearch = false;
    var filterNode = _filterNode || '.item';
    var $containerNode = _containerNode;
    var $dlgw = $containerNode.parents('[role=dialog]');
    var $collection;

    init();

    function init() {
        if ($('.dialog-filter input[data-role=search]', $dlgw).length !== 0) {
            return;
        }

        $collection = $(filterNode, $containerNode[0]);

        var isHierarchical = $('.toggle-response', $containerNode[0]).length > 0;
        if ($collection.length < initCount) {
            if (!isHierarchical) {
                return;
            }
        }

        if ($('.dialog-filter', $dlgw).length === 0) {
            $containerNode.before('<div class=dialog-filter></div>');
        }
        $('.dialog-filter', $dlgw).append('<input type=text name=keyword data-role=search placeholder="' + nb.getText('filter', 'Фильтр') + '" />');
        $inputEl = $('.dialog-filter input[data-role=search]', $dlgw);
        $inputEl.on('keyup', function(e) {
            try {
                clearTimeout(to);
                if (e.keyCode === 13) {
                    return;
                }
            } catch (ex) {
                console.log(ex);
            }

            to = setTimeout(function() {
                $collection = $(filterNode, $containerNode[0]);
                filter(e.target.value);
            }, timeout);
        });
    }

    function filter(value) {
        try {
            if (value.length >= triggerLen) {
                var hiddenCount = 0;
                var re = new RegExp(value, 'gim');
                $collection.attr('style', '');

                $collection.each(function(index, node) {
                    if (!re.test(node.textContent) && node.textContent.indexOf(value) == -1) {
                        if ($(':checked', node).length === 0) {
                            node.style.display = 'none';
                            hiddenCount++;
                        }
                    }
                });

                if ($collection.length > hiddenCount) {
                    $inputEl.attr('title', 'By keyword [' + value + '] filtered ' + ($collection.length - hiddenCount));
                } else {
                    $inputEl.attr('title', nb.getText('filter_no_results', 'Не найдено'));
                }
            } else {
                $collection.attr('style', '');
                $inputEl.attr('title', '');
            }
        } catch (e) {
            console.log(e);
        }
    }
};

/**
 * windowOpen
 */
nb.windowOpen = function(url, id, callbacks) {
    var features, width = 800,
        height = 600;
    var top = (window.innerHeight - height) / 2,
        left = (window.innerWidth - width) / 2;
    if (top < 0) top = 0;
    if (left < 0) left = 0;
    features = 'top=' + top + ',left=' + left;
    features += ',height=' + height + ',width=' + width + ',resizable=yes,scrollbars=yes,status=no';

    var wid = 'window-' + (id || url.hashCode());

    var w = window.open('', wid, features);
    if ('about:blank' === w.document.URL || w.document.URL === '') {
        w = window.open(url, wid, features);

        if (callbacks && callbacks.onclose) {
            var timer = setInterval(function() {
                if (w.closed) {
                    clearInterval(timer);
                    callbacks.onclose();
                }
            }, 1000);
        }
    }
    w.focus();
};

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

        if ($('.js-no-selected-value', $dlgw[0]).length === 0) {
            (function() {
                var $_html = $('<div class="alert alert-danger js-no-selected-value" style="border-radius:2px;bottom:80px;left:4%;right:4%;position:absolute;">' + dialogOptions.errorMessage + '</div>');
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
        var isMulti = dataList.length > 1;
        var multiFieldMap = {};
        //
        dataList.each(function() {
            var dataId = this.value;
            //
            var field, targetFieldName;
            var $val;
            var $targetFieldNode;
            var value;
            var text;
            //
            for (field in fields) {
                targetFieldName = fields[field];
                //
                $val = $('[data-id=' + dataId + '][name=' + field + ']', $dlgw);
                $targetFieldNode = $('[name=' + targetFieldName + ']', form);
                value = $val.val();
                text = $val.data('text');
                if (!text) text = value;
                //
                if (isMulti) {
                    if (multiFieldMap[field] !== true) {
                        $targetFieldNode.remove();
                    }
                    $targetFieldNode = $('<input type="hidden" name="' + targetFieldName + '" />');
                    $targetFieldNode.appendTo(form);
                } else {
                    var $hf = $('[type=hidden][name=' + targetFieldName + ']', form);
                    if ($hf.length) {
                        $hf.remove();
                        $targetFieldNode = $('<input type="hidden" name="' + targetFieldName + '" />');
                        $targetFieldNode.appendTo(form);
                    }
                }
                //
                if ($targetFieldNode.length === 0) {
                    $targetFieldNode = $('<input type="hidden" name="' + targetFieldName + '" />');
                    $targetFieldNode.appendTo(form);
                }
                //
                $targetFieldNode.val(value);
                //
                if (isMulti) {
                    if (multiFieldMap[field] !== true) {
                        multiFieldMap[field] = true;
                        $('[data-input=' + targetFieldName.replace('id', '') + ']', form).html('<li>' + text + '</li>');
                    } else {
                        $('[data-input=' + targetFieldName.replace('id', '') + ']', form).append('<li>' + text + '</li>');
                    }
                } else {
                    $('[data-input=' + targetFieldName.replace('id', '') + ']', form).html(text);
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

/**
 * notify
 */
nb.notify = function(opt) {

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

/**
 * doDelete
 */
nb.xhr.doDelete = function(data) {
    return $.ajax({
        type: 'DELETE',
        dataType: 'json',
        url: location.href + '&' + data
    });
};

nb.tpl = {};

/**
 * defaultDialogListTemplate
 */
nb.tpl.defaultDialogListTemplate = function(data) {

    var models = data.objects[0];
    if (!models.length) {
        return 'empty list';
    }

    var dialogId = this.id;
    var m, index;
    var html = [];
    html.push('<ul class=nb-dialog-list>');
    for (index in models) {
        m = models[index];
        html.push('<li class=nb-dialog-list-it>');
        html.push(' <label ondblclick="nb.dialog.execute(this)">');
        html.push('  <input data-type="select" type="radio" name="select_' + dialogId + '" value="' + m.id + '"/>');
        html.push('  <span>' + m.name + '</span>');
        html.push('  <input data-id="' + m.id + '" name="id" value="' + m.id + '" data-text="' + m.name + '" type="hidden"/>');
        html.push(' </label>');
        html.push('</li>');
    }
    html.push('</ul>');

    return html.join('');
};

nb.getSelectedEntityIDs = function(checkboxName) {
    var $checked = $('input[name=' + (checkboxName || 'docid') + ']:checked');
    if ($checked.length === 0) {
        return [];
    }

    var result = [];
    $checked.each(function() {
        result.push(this.value);
    });

    return result;
};

nb.setSearchReferToSessionStorage = function() {
    if (location.href.indexOf('id=search') == -1) {
        sessionStorage.setItem('search_refer', location.href);
    }
};


nb.resetSearchFromRefer = function() {
    var refer = sessionStorage.getItem('search_refer');
    if (refer) {
        location.href = refer;
    } else {
        history.back();
    }
};


// init
$(document).ready(function() {
    $('form[name=ft-search]').on('submit', function() {
        nb.setSearchReferToSessionStorage();
        return true;
    });

    $('[data-action=reset_search]').click(function(event) {
        event.preventDefault();
        nb.resetSearchFromRefer();
    });
});

nbApp.defaultChoiceDialog = function(el, id, fields, callback) {
    var form = nb.getForm(el);
    var dlg = nb.dialog.show({
        targetForm: form.name,
        fields: fields,
        title: el.title,
        href: 'Provider?id=' + id,
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
    return this.defaultChoiceDialog(el, 'get-organizations&_fn=' + form.name, {
        docid: 'balanceholder',
        bin: 'balanceholderbin'
    }, callback);
};

nbApp.choiceReaders = function(el, callback) {
    var form = nb.getForm(el);
    return this.defaultChoiceDialog(el, 'get-employees&_fn=' + form.name, {
        docid: 'reader'
    }, callback);
};

function uploadUpdate(fileInput, fsid) {
    var formData = new FormData();
    formData.append('file', fileInput.files[0]);
    formData.append('fsid', fsid);
    var time = new Date().getTime();

    return $.ajax({
        url: 'UploadFile?time=' + time,
        type: 'POST',
        cache: false,
        contentType: false,
        processData: false,
        data: formData,
        dataType: 'json',
        xhr: function() {
            var customXhr = $.ajaxSettings.xhr();
            if (customXhr.upload) {
                customXhr.upload.addEventListener('progress', onProgress, false);
            }
            return customXhr;
        },
        success: function(result) {
            var fileName = result.files[0];
            renderFilePanel(fileName, fsid);
            return result;
        },
        error: function(err) {
            console.log(err);
        },
        complete: function() {
            $('progress').attr({
                value: 0,
                max: 100
            });
            fileInput.form.reset();
            insertParam('fsid', fsid);
        }
    });
}

function onProgress(e) {
    if (e.lengthComputable) {
        $('progress').attr({
            value: e.loaded,
            max: e.total
        });
    }
}

function loadFile(fileId, data, fsid) {
    nb.uiBlock();

    var noty = nb.notify({
        type: 'info',
        message: 'Идет загрузка данных. Пожалуйста подождите...'
    }).show();

    return $.ajax({
        type: 'post',
        dataType: 'json',
        url: 'Provider?type=page&id=load-file-data&fileid=' + fileId + '&fsid=' + fsid,
        data: data,
        success: function(result) {
            return result;
        },
        error: function(err) {
            nb.notify({
                type: 'error',
                message: 'Ошибка загрузки'
            }).show(2000);
            return err;
        },
        complete: function() {
            nb.uiUnblock();
            noty.remove();
        }
    });
}

function delFile(fileId, fsid) {
    return $.ajax({
        type: 'delete',
        dataType: 'json',
        url: 'Provider?type=page&id=delete-attach&fileid=' + fileId + '&fsid=' + fsid
    });
}

function checkFile(fileId, fsid) {
    nb.uiBlock();

    var noty = nb.notify({
        type: 'info',
        message: 'Идет проверка структуры файла. Пожалуйста подождите...'
    }).show();

    return $.ajax({
        type: 'get',
        dataType: 'html',
        url: 'Provider?type=page&id=check-file-structure&fileid=' + fileId + '&fsid=' + fsid,
        success: function(data) {
            return data;
        },
        error: function() {
            return false;
        },
        complete: function() {
            nb.uiUnblock();
            noty.remove(200);
        }
    });
}

function renderFilePanel(fileName, fsid) {
    var $cacheUpdateForm = $('form[name=js-init-update-panel][data-file-name="' + fileName + '"]');
    var initMode = $cacheUpdateForm.length;
    var template;
    var $tpl;

    if (initMode) {
        $tpl = $cacheUpdateForm;
    } else {
        template = $('#tpl_update_file_panel').clone();
        $tpl = $(template.html().trim());
    }

    $tpl.attr('name', 'form' + (new Date().getTime()));

    var t_link = $tpl.find('.js-link').attr('href');
    $tpl.find('.js-link').attr('href', t_link + fileName).html(fileName);

    $tpl.find('.js-check').on('click', function(e) {
        e.stopPropagation();
        e.preventDefault();
        var $btn = $(this);
        $btn.attr('disabled', true);
        //
        checkFile(fileName, fsid).then(function(result) {
            /*$btn.parents('.panel').addClass('open');
            if (result == '') {
                $tpl.find('.js-load').removeAttr('disabled');
                $tpl.find('.js-select-balance-holder').removeAttr('disabled');
                $tpl.find('.js-select-readers').removeAttr('disabled');
            }
            $tpl.find('.js-check-result').html(result);*/
            //
            location.reload();
        }, function(err) {
            // $btn.parents('.panel').addClass('open');
            // $tpl.find('.js-check-result').html(err.statusText);
            //
            location.reload();
        });
    });

    $tpl.find('.js-delete').on('click', function(e) {
        e.stopPropagation();
        e.preventDefault();
        delFile(fileName, fsid).then(function() {
            $tpl.remove();
        });
    });

    $tpl.find('.js-load').on('click', function(e) {
        e.stopPropagation();
        e.preventDefault();
        $(this).attr('disabled', true);
        loadFile(fileName, $tpl.serialize(), fsid).then(function() {
            // $tpl.addClass('upload-success');
            location.reload();
        }, function() {
            location.reload();
        });
    });

    $tpl.find('.js-select-balance-holder').on('click', function(e) {
        e.stopPropagation();
        e.preventDefault();
        $(this).parents('.panel').addClass('open');
        nbApp.choiceBalanceHolder(this, function() {
            toggleLoadButtonState($tpl);
        });
        $tpl.find('.errormsg').remove();
    });

    $tpl.find('.js-select-readers').on('click', function(e) {
        e.stopPropagation();
        e.preventDefault();
        $(this).parents('.panel').addClass('open');
        nbApp.choiceReaders(this, function() {
            toggleLoadButtonState($tpl);
        });
        $tpl.find('.errormsg').remove();
    });

    if (!initMode) {
        $('.js-uploaded-files').append($tpl);
    }
}

function toggleLoadButtonState($form) {
    var b = $form.find('[name=balanceholder]').val();
    var r = $form.find('[name=reader]').val();
    if (b && r) {
        $form.find('.js-load').attr('disabled', false);
    }
}

function insertParam(_key, _value) {
    var key = encodeURI(_key);
    var value = encodeURI(_value);
    var kvp = document.location.search.substr(1).split('&');
    var i = kvp.length;
    var x;

    while (i--) {
        x = kvp[i].split('=');
        if (x[0] == key) {
            x[1] = value;
            kvp[i] = x.join('=');
            break;
        }
    }
    if (i < 0) {
        kvp[kvp.length] = [key, value].join('=');
    }
    history.replaceState(null, null, location.pathname + '?' + kvp.join('&'));
}

function initCachedUpdateForm() {
    if (location.search.indexOf('fsid') !== -1) {
        var fsid = location.search.split('fsid=')[1];
        $('form[name=js-init-update-panel][data-file-name]').each(function() {
            renderFilePanel($(this).data('file-name'), fsid);
        });
    }
}

$(document).ready(function() {
    initCachedUpdateForm();
});
