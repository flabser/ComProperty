nbApp.selectOptions = {
    balanceholder: {
        url: 'p?id=get-organizations',
        fields: ['bin'],
        onSelect: function(e) {
            if (e.target.form && e.target.form.balanceholderbin) {
                e.target.form.balanceholderbin.value = (e.params.data) ? e.params.data.bin : '';
            }
            if(e.target.form.name == "reporttemplate"){
                $("#orgcategory").select2('val', 'All');
            }
        }
    },
    orgcategory: {
        url: 'p?id=get-org-categories',
        onSelect: function(e) {
            if(e.target.form.name == "reporttemplate"){
                $("#balanceholder").select2('val', 'All');
            }
        }
    },
    department: {
        url: 'p?id=get-departments',
        data: ['organization']
    },
    position: {
        url: 'p?id=get-positions'
    },
    propertycode: {
        url: 'p?id=get-property-codes'
    },
    receivingreason: {
        url: 'p?id=get-receiving-reasons'
    },
    district: {
        url: 'p?id=get-districts',
        data: ['region']
    },
    street: {
        url: 'p?id=get-streets',
        data: ['locality'],
        fields: ['streetId'],
        cache: false,
        minimumResultsForSearch: 0,
        templateResult: function(item) {
            if (!item.id || !item.streetId) {
                return item.text;
            }

            var $item = $('<span>' + item.text + '</span><span class=street-id>' + item.streetId + '</span>');
            return $item;
        }
    },
    tags: {
        url: 'p?id=get-tags',
        fields: ['color'],
        templateResult: function(item) {
            if (!item.id || !item.color) {
                return item.text;
            }

            var $item = $('<span style="color:' + item.color + '">' + item.text + '</span>');
            return $item;
        }
    }
};
