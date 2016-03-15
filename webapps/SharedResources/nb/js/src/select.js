nb.getSelectOptions = function(optionId) {
    var options = nbApp.selectOptions[optionId];
    var cacheDataSource = [];

    var jsonToSelect2ItemsAdapter = function(data) {
        var items = [],
            list = data.objects[0].list;
        for (var k in list) {
            items.push({
                id: list[k].id,
                text: list[k].name
            });
        }

        return {
            meta: data.objects[0].meta,
            items: items
        }
    };

    return {
        ajax: {
            url: options.url,
            dataType: 'json',
            delay: 0,
            data: function(params) {
                var _data = {
                    page: params.page
                };
                if (options.search) {
                    _data.keyword = params.term
                }
                for (var k in options.data) {
                    _data[options.data] = this[0].form[options.data].value;
                }

                return _data;
            },
            processResults: function(data, params) {
                params.page = params.page || 1;
                var _data = data; // jsonToSelect2ItemsAdapter(data);

                return {
                    results: _data.items,
                    pagination: {
                        more: false // params.page < _data.meta.totalPages
                    }
                };
            },
            transport: function(params, success, failure) {
                console.log('transport', arguments);

                //
                var cachedData,
                    key = params.url,
                    checkCache = !params.data.keyword;

                if (checkCache) {
                    cachedData = cacheDataSource[key];
                }

                if (cachedData) {
                    return success(cachedData);
                } else {
                    var $request = $.ajax(params);
                    $request.then(function(data) {
                        var _data = jsonToSelect2ItemsAdapter(data);
                        cacheDataSource[key] = _data;
                        success(_data);
                        return _data;
                    });
                    $request.fail(failure);
                    return $request;
                }
            },
            cache: true
        }
    };
};

$(document).ready(function() {
    $('select[name]').each(function() {
        if (nbApp.selectOptions && nbApp.selectOptions[this.name]) {
            $(this).select2(nb.getSelectOptions(this.name));
        } else {
            if (nb.isMobile()) {
                if (this.multiple) {
                    $(this).select2();
                }
            } else {
                $(this).select2();
            }
        }
    });

    // need dummy input if no select value
    $('select[name]').on('change', function() {
        if ($(this).val()) {
            $('[data-role=dummy-select][name=' + this.name + ']', $(this).parent()).remove();
        } else {
            $('<input type=hidden data-role=dummy-select name=' + this.name + ' value="">').appendTo($(this).parent());
        }
    });
});
