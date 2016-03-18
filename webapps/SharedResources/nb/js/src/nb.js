/**
 * @author Medet
 */

'use strict';

var nb = {
    APP_NAME: location.hostname,
    LANG_ID: (function() {
        var ck = document.cookie.match('(lang)=(.*?)($|;|,(?! ))');
        if (ck) {
            return ck[2];
        }
        return 'RUS';
    })(),
    debug: false,
    options: {
        dateFormat: 'yy-mm-dd',
        translationsUrl: 'Provider?id=common-captions'
    },
    translations: {
        yes: 'Да',
        no: 'Нет',
        ok: 'Ok',
        cancel: 'Отмена',
        select: 'Выбрать',
        dialog_no_selected_value: 'Вы не сделали выбор'
    },
    tpl: {}
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
 * getTranslations
 */
nb.getTranslations = function() {
    return $.ajax({
        type: 'get',
        dataType: 'json',
        url: nb.options.translationsUrl
    });
};

/**
 * getForm
 */
nb.getForm = function(el) {
    if (!el) {
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
 * isMobile
 */
nb.isMobile = function() {
    return /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent);
};

/**
 * template
 * @param templateId - hbs template
 * @param data - data for template
 */
nb.template = function(templateId, data) {
    return nb.templates[templateId].call(this, data);
};

// Global ajax callback functions
$(document).ajaxError(function(event, jqxhr, settings, thrownError) {
    if (jqxhr.responseJSON) {
        return;
    }

    var msg,
        bodyStIndex = jqxhr.responseText && jqxhr.responseText.indexOf('<body>');
    if (bodyStIndex > -1) {
        msg = jqxhr.responseText.substring(bodyStIndex, jqxhr.responseText.indexOf('</body>'))
    } else {
        msg = jqxhr.responseText;
    }

    if (msg) {
        nb.dialog.error({
            message: msg,
            height: 400,
            width: 600
        });
    } else {
        nb.notify({
            type: 'error',
            message: thrownError
        });
    }
});

// init
$(document).ready(function() {
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

    // getTranslations
    nb.getTranslations().then(function(response) {
        for (var key in response.captions) {
            nb.translations[key] = response.captions[key];
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
            $('body').removeClass('side-nav-toggle search-open');
            if ($('.navbar-search input.q').length) {
                $('.navbar-search input.q')[0].blur();
            }
        });

        $('#content-overlay')[0].addEventListener('touchstart', function(event) {
            event.preventDefault();
            $('body').removeClass('side-nav-toggle search-open');
            if ($('.navbar-search input.q').length) {
                $('.navbar-search input.q')[0].blur();
            }
        }, false);
    }

    //
    $('.navbar-search input.q').on('focus', function() {
        $('body').addClass('search-open');
    });
    $('.navbar-search input.q').on('blur', function() {
        $('body').removeClass('search-open');
    });

    // data-role="dialog-list-item"
    $(document).on('dblclick', '[data-role=dialog-list-item]', function() {
        return nb.dialog.execute(this);
    });

    //
    $(window).resize(function() {
        if (window.innerWidth <= 800 || nb.isMobile()) {
            $('body').addClass('phone');
        } else {
            $('body').removeClass('phone');
        }
    });

    if (nb.isMobile() || window.innerWidth <= 800) {
        $('body').addClass('phone');
    }

    //
    setTimeout(function() {
        $('body').removeClass('no_transition');
    }, 250);
});
