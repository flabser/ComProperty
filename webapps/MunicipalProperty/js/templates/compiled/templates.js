this["nb"] = this["nb"] || {};
this["nb"]["templates"] = this["nb"]["templates"] || {};
this["nb"]["templates"]["order-view"] = Handlebars.template({"1":function(container,depth0,helpers,partials,data,blockParams) {
    var stack1, alias1=container.escapeExpression, alias2=container.lambda;

  return "            <div class=\"entry-wrap\">\r\n                <div class=\"entry\">\r\n                    <a class=\"entry-link\" href=\""
    + alias1((helpers.unescapeXml || (depth0 && depth0.unescapeXml) || helpers.helperMissing).call(depth0 != null ? depth0 : {},((stack1 = blockParams[0][0]) != null ? stack1.url : stack1),{"name":"unescapeXml","hash":{},"data":data,"blockParams":blockParams}))
    + "\">\r\n                        <div class=\"entry-fields\">\r\n                            <span class=\"span2\">"
    + alias1(alias2(((stack1 = blockParams[0][0]) != null ? stack1.regNumber : stack1), depth0))
    + "</span>\r\n                            <span>"
    + alias1(alias2(((stack1 = blockParams[0][0]) != null ? stack1.description : stack1), depth0))
    + "</span>\r\n                        </div>\r\n                    </a>\r\n                </div>\r\n            </div>\r\n";
},"compiler":[7,">= 4.0.0"],"main":function(container,depth0,helpers,partials,data,blockParams) {
    var stack1;

  return "<div class=\"view property-orders\">\r\n    <div class=\"entries\">\r\n"
    + ((stack1 = helpers.each.call(depth0 != null ? depth0 : {},(depth0 != null ? depth0.list : depth0),{"name":"each","hash":{},"fn":container.program(1, data, 1, blockParams),"inverse":container.noop,"data":data,"blockParams":blockParams})) != null ? stack1 : "")
    + "    </div>\r\n</div>\r\n";
},"useData":true,"useBlockParams":true});