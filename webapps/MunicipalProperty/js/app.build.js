/*!

 handlebars v4.0.5

Copyright (C) 2011-2015 by Yehuda Katz

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.

@license
*/
(function webpackUniversalModuleDefinition(root, factory) {
	if(typeof exports === 'object' && typeof module === 'object')
		module.exports = factory();
	else if(typeof define === 'function' && define.amd)
		define([], factory);
	else if(typeof exports === 'object')
		exports["Handlebars"] = factory();
	else
		root["Handlebars"] = factory();
})(this, function() {
return /******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};

/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {

/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId])
/******/ 			return installedModules[moduleId].exports;

/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			exports: {},
/******/ 			id: moduleId,
/******/ 			loaded: false
/******/ 		};

/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);

/******/ 		// Flag the module as loaded
/******/ 		module.loaded = true;

/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}


/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;

/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;

/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";

/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(0);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	var _interopRequireWildcard = __webpack_require__(1)['default'];

	var _interopRequireDefault = __webpack_require__(2)['default'];

	exports.__esModule = true;

	var _handlebarsBase = __webpack_require__(3);

	var base = _interopRequireWildcard(_handlebarsBase);

	// Each of these augment the Handlebars object. No need to setup here.
	// (This is done to easily share code between commonjs and browse envs)

	var _handlebarsSafeString = __webpack_require__(17);

	var _handlebarsSafeString2 = _interopRequireDefault(_handlebarsSafeString);

	var _handlebarsException = __webpack_require__(5);

	var _handlebarsException2 = _interopRequireDefault(_handlebarsException);

	var _handlebarsUtils = __webpack_require__(4);

	var Utils = _interopRequireWildcard(_handlebarsUtils);

	var _handlebarsRuntime = __webpack_require__(18);

	var runtime = _interopRequireWildcard(_handlebarsRuntime);

	var _handlebarsNoConflict = __webpack_require__(19);

	var _handlebarsNoConflict2 = _interopRequireDefault(_handlebarsNoConflict);

	// For compatibility and usage outside of module systems, make the Handlebars object a namespace
	function create() {
	  var hb = new base.HandlebarsEnvironment();

	  Utils.extend(hb, base);
	  hb.SafeString = _handlebarsSafeString2['default'];
	  hb.Exception = _handlebarsException2['default'];
	  hb.Utils = Utils;
	  hb.escapeExpression = Utils.escapeExpression;

	  hb.VM = runtime;
	  hb.template = function (spec) {
	    return runtime.template(spec, hb);
	  };

	  return hb;
	}

	var inst = create();
	inst.create = create;

	_handlebarsNoConflict2['default'](inst);

	inst['default'] = inst;

	exports['default'] = inst;
	module.exports = exports['default'];

/***/ },
/* 1 */
/***/ function(module, exports) {

	"use strict";

	exports["default"] = function (obj) {
	  if (obj && obj.__esModule) {
	    return obj;
	  } else {
	    var newObj = {};

	    if (obj != null) {
	      for (var key in obj) {
	        if (Object.prototype.hasOwnProperty.call(obj, key)) newObj[key] = obj[key];
	      }
	    }

	    newObj["default"] = obj;
	    return newObj;
	  }
	};

	exports.__esModule = true;

/***/ },
/* 2 */
/***/ function(module, exports) {

	"use strict";

	exports["default"] = function (obj) {
	  return obj && obj.__esModule ? obj : {
	    "default": obj
	  };
	};

	exports.__esModule = true;

/***/ },
/* 3 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	var _interopRequireDefault = __webpack_require__(2)['default'];

	exports.__esModule = true;
	exports.HandlebarsEnvironment = HandlebarsEnvironment;

	var _utils = __webpack_require__(4);

	var _exception = __webpack_require__(5);

	var _exception2 = _interopRequireDefault(_exception);

	var _helpers = __webpack_require__(6);

	var _decorators = __webpack_require__(14);

	var _logger = __webpack_require__(16);

	var _logger2 = _interopRequireDefault(_logger);

	var VERSION = '4.0.5';
	exports.VERSION = VERSION;
	var COMPILER_REVISION = 7;

	exports.COMPILER_REVISION = COMPILER_REVISION;
	var REVISION_CHANGES = {
	  1: '<= 1.0.rc.2', // 1.0.rc.2 is actually rev2 but doesn't report it
	  2: '== 1.0.0-rc.3',
	  3: '== 1.0.0-rc.4',
	  4: '== 1.x.x',
	  5: '== 2.0.0-alpha.x',
	  6: '>= 2.0.0-beta.1',
	  7: '>= 4.0.0'
	};

	exports.REVISION_CHANGES = REVISION_CHANGES;
	var objectType = '[object Object]';

	function HandlebarsEnvironment(helpers, partials, decorators) {
	  this.helpers = helpers || {};
	  this.partials = partials || {};
	  this.decorators = decorators || {};

	  _helpers.registerDefaultHelpers(this);
	  _decorators.registerDefaultDecorators(this);
	}

	HandlebarsEnvironment.prototype = {
	  constructor: HandlebarsEnvironment,

	  logger: _logger2['default'],
	  log: _logger2['default'].log,

	  registerHelper: function registerHelper(name, fn) {
	    if (_utils.toString.call(name) === objectType) {
	      if (fn) {
	        throw new _exception2['default']('Arg not supported with multiple helpers');
	      }
	      _utils.extend(this.helpers, name);
	    } else {
	      this.helpers[name] = fn;
	    }
	  },
	  unregisterHelper: function unregisterHelper(name) {
	    delete this.helpers[name];
	  },

	  registerPartial: function registerPartial(name, partial) {
	    if (_utils.toString.call(name) === objectType) {
	      _utils.extend(this.partials, name);
	    } else {
	      if (typeof partial === 'undefined') {
	        throw new _exception2['default']('Attempting to register a partial called "' + name + '" as undefined');
	      }
	      this.partials[name] = partial;
	    }
	  },
	  unregisterPartial: function unregisterPartial(name) {
	    delete this.partials[name];
	  },

	  registerDecorator: function registerDecorator(name, fn) {
	    if (_utils.toString.call(name) === objectType) {
	      if (fn) {
	        throw new _exception2['default']('Arg not supported with multiple decorators');
	      }
	      _utils.extend(this.decorators, name);
	    } else {
	      this.decorators[name] = fn;
	    }
	  },
	  unregisterDecorator: function unregisterDecorator(name) {
	    delete this.decorators[name];
	  }
	};

	var log = _logger2['default'].log;

	exports.log = log;
	exports.createFrame = _utils.createFrame;
	exports.logger = _logger2['default'];

/***/ },
/* 4 */
/***/ function(module, exports) {

	'use strict';

	exports.__esModule = true;
	exports.extend = extend;
	exports.indexOf = indexOf;
	exports.escapeExpression = escapeExpression;
	exports.isEmpty = isEmpty;
	exports.createFrame = createFrame;
	exports.blockParams = blockParams;
	exports.appendContextPath = appendContextPath;
	var escape = {
	  '&': '&amp;',
	  '<': '&lt;',
	  '>': '&gt;',
	  '"': '&quot;',
	  "'": '&#x27;',
	  '`': '&#x60;',
	  '=': '&#x3D;'
	};

	var badChars = /[&<>"'`=]/g,
	    possible = /[&<>"'`=]/;

	function escapeChar(chr) {
	  return escape[chr];
	}

	function extend(obj /* , ...source */) {
	  for (var i = 1; i < arguments.length; i++) {
	    for (var key in arguments[i]) {
	      if (Object.prototype.hasOwnProperty.call(arguments[i], key)) {
	        obj[key] = arguments[i][key];
	      }
	    }
	  }

	  return obj;
	}

	var toString = Object.prototype.toString;

	exports.toString = toString;
	// Sourced from lodash
	// https://github.com/bestiejs/lodash/blob/master/LICENSE.txt
	/* eslint-disable func-style */
	var isFunction = function isFunction(value) {
	  return typeof value === 'function';
	};
	// fallback for older versions of Chrome and Safari
	/* istanbul ignore next */
	if (isFunction(/x/)) {
	  exports.isFunction = isFunction = function (value) {
	    return typeof value === 'function' && toString.call(value) === '[object Function]';
	  };
	}
	exports.isFunction = isFunction;

	/* eslint-enable func-style */

	/* istanbul ignore next */
	var isArray = Array.isArray || function (value) {
	  return value && typeof value === 'object' ? toString.call(value) === '[object Array]' : false;
	};

	exports.isArray = isArray;
	// Older IE versions do not directly support indexOf so we must implement our own, sadly.

	function indexOf(array, value) {
	  for (var i = 0, len = array.length; i < len; i++) {
	    if (array[i] === value) {
	      return i;
	    }
	  }
	  return -1;
	}

	function escapeExpression(string) {
	  if (typeof string !== 'string') {
	    // don't escape SafeStrings, since they're already safe
	    if (string && string.toHTML) {
	      return string.toHTML();
	    } else if (string == null) {
	      return '';
	    } else if (!string) {
	      return string + '';
	    }

	    // Force a string conversion as this will be done by the append regardless and
	    // the regex test will do this transparently behind the scenes, causing issues if
	    // an object's to string has escaped characters in it.
	    string = '' + string;
	  }

	  if (!possible.test(string)) {
	    return string;
	  }
	  return string.replace(badChars, escapeChar);
	}

	function isEmpty(value) {
	  if (!value && value !== 0) {
	    return true;
	  } else if (isArray(value) && value.length === 0) {
	    return true;
	  } else {
	    return false;
	  }
	}

	function createFrame(object) {
	  var frame = extend({}, object);
	  frame._parent = object;
	  return frame;
	}

	function blockParams(params, ids) {
	  params.path = ids;
	  return params;
	}

	function appendContextPath(contextPath, id) {
	  return (contextPath ? contextPath + '.' : '') + id;
	}

/***/ },
/* 5 */
/***/ function(module, exports) {

	'use strict';

	exports.__esModule = true;

	var errorProps = ['description', 'fileName', 'lineNumber', 'message', 'name', 'number', 'stack'];

	function Exception(message, node) {
	  var loc = node && node.loc,
	      line = undefined,
	      column = undefined;
	  if (loc) {
	    line = loc.start.line;
	    column = loc.start.column;

	    message += ' - ' + line + ':' + column;
	  }

	  var tmp = Error.prototype.constructor.call(this, message);

	  // Unfortunately errors are not enumerable in Chrome (at least), so `for prop in tmp` doesn't work.
	  for (var idx = 0; idx < errorProps.length; idx++) {
	    this[errorProps[idx]] = tmp[errorProps[idx]];
	  }

	  /* istanbul ignore else */
	  if (Error.captureStackTrace) {
	    Error.captureStackTrace(this, Exception);
	  }

	  if (loc) {
	    this.lineNumber = line;
	    this.column = column;
	  }
	}

	Exception.prototype = new Error();

	exports['default'] = Exception;
	module.exports = exports['default'];

/***/ },
/* 6 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	var _interopRequireDefault = __webpack_require__(2)['default'];

	exports.__esModule = true;
	exports.registerDefaultHelpers = registerDefaultHelpers;

	var _helpersBlockHelperMissing = __webpack_require__(7);

	var _helpersBlockHelperMissing2 = _interopRequireDefault(_helpersBlockHelperMissing);

	var _helpersEach = __webpack_require__(8);

	var _helpersEach2 = _interopRequireDefault(_helpersEach);

	var _helpersHelperMissing = __webpack_require__(9);

	var _helpersHelperMissing2 = _interopRequireDefault(_helpersHelperMissing);

	var _helpersIf = __webpack_require__(10);

	var _helpersIf2 = _interopRequireDefault(_helpersIf);

	var _helpersLog = __webpack_require__(11);

	var _helpersLog2 = _interopRequireDefault(_helpersLog);

	var _helpersLookup = __webpack_require__(12);

	var _helpersLookup2 = _interopRequireDefault(_helpersLookup);

	var _helpersWith = __webpack_require__(13);

	var _helpersWith2 = _interopRequireDefault(_helpersWith);

	function registerDefaultHelpers(instance) {
	  _helpersBlockHelperMissing2['default'](instance);
	  _helpersEach2['default'](instance);
	  _helpersHelperMissing2['default'](instance);
	  _helpersIf2['default'](instance);
	  _helpersLog2['default'](instance);
	  _helpersLookup2['default'](instance);
	  _helpersWith2['default'](instance);
	}

/***/ },
/* 7 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	exports.__esModule = true;

	var _utils = __webpack_require__(4);

	exports['default'] = function (instance) {
	  instance.registerHelper('blockHelperMissing', function (context, options) {
	    var inverse = options.inverse,
	        fn = options.fn;

	    if (context === true) {
	      return fn(this);
	    } else if (context === false || context == null) {
	      return inverse(this);
	    } else if (_utils.isArray(context)) {
	      if (context.length > 0) {
	        if (options.ids) {
	          options.ids = [options.name];
	        }

	        return instance.helpers.each(context, options);
	      } else {
	        return inverse(this);
	      }
	    } else {
	      if (options.data && options.ids) {
	        var data = _utils.createFrame(options.data);
	        data.contextPath = _utils.appendContextPath(options.data.contextPath, options.name);
	        options = { data: data };
	      }

	      return fn(context, options);
	    }
	  });
	};

	module.exports = exports['default'];

/***/ },
/* 8 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	var _interopRequireDefault = __webpack_require__(2)['default'];

	exports.__esModule = true;

	var _utils = __webpack_require__(4);

	var _exception = __webpack_require__(5);

	var _exception2 = _interopRequireDefault(_exception);

	exports['default'] = function (instance) {
	  instance.registerHelper('each', function (context, options) {
	    if (!options) {
	      throw new _exception2['default']('Must pass iterator to #each');
	    }

	    var fn = options.fn,
	        inverse = options.inverse,
	        i = 0,
	        ret = '',
	        data = undefined,
	        contextPath = undefined;

	    if (options.data && options.ids) {
	      contextPath = _utils.appendContextPath(options.data.contextPath, options.ids[0]) + '.';
	    }

	    if (_utils.isFunction(context)) {
	      context = context.call(this);
	    }

	    if (options.data) {
	      data = _utils.createFrame(options.data);
	    }

	    function execIteration(field, index, last) {
	      if (data) {
	        data.key = field;
	        data.index = index;
	        data.first = index === 0;
	        data.last = !!last;

	        if (contextPath) {
	          data.contextPath = contextPath + field;
	        }
	      }

	      ret = ret + fn(context[field], {
	        data: data,
	        blockParams: _utils.blockParams([context[field], field], [contextPath + field, null])
	      });
	    }

	    if (context && typeof context === 'object') {
	      if (_utils.isArray(context)) {
	        for (var j = context.length; i < j; i++) {
	          if (i in context) {
	            execIteration(i, i, i === context.length - 1);
	          }
	        }
	      } else {
	        var priorKey = undefined;

	        for (var key in context) {
	          if (context.hasOwnProperty(key)) {
	            // We're running the iterations one step out of sync so we can detect
	            // the last iteration without have to scan the object twice and create
	            // an itermediate keys array.
	            if (priorKey !== undefined) {
	              execIteration(priorKey, i - 1);
	            }
	            priorKey = key;
	            i++;
	          }
	        }
	        if (priorKey !== undefined) {
	          execIteration(priorKey, i - 1, true);
	        }
	      }
	    }

	    if (i === 0) {
	      ret = inverse(this);
	    }

	    return ret;
	  });
	};

	module.exports = exports['default'];

/***/ },
/* 9 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	var _interopRequireDefault = __webpack_require__(2)['default'];

	exports.__esModule = true;

	var _exception = __webpack_require__(5);

	var _exception2 = _interopRequireDefault(_exception);

	exports['default'] = function (instance) {
	  instance.registerHelper('helperMissing', function () /* [args, ]options */{
	    if (arguments.length === 1) {
	      // A missing field in a {{foo}} construct.
	      return undefined;
	    } else {
	      // Someone is actually trying to call something, blow up.
	      throw new _exception2['default']('Missing helper: "' + arguments[arguments.length - 1].name + '"');
	    }
	  });
	};

	module.exports = exports['default'];

/***/ },
/* 10 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	exports.__esModule = true;

	var _utils = __webpack_require__(4);

	exports['default'] = function (instance) {
	  instance.registerHelper('if', function (conditional, options) {
	    if (_utils.isFunction(conditional)) {
	      conditional = conditional.call(this);
	    }

	    // Default behavior is to render the positive path if the value is truthy and not empty.
	    // The `includeZero` option may be set to treat the condtional as purely not empty based on the
	    // behavior of isEmpty. Effectively this determines if 0 is handled by the positive path or negative.
	    if (!options.hash.includeZero && !conditional || _utils.isEmpty(conditional)) {
	      return options.inverse(this);
	    } else {
	      return options.fn(this);
	    }
	  });

	  instance.registerHelper('unless', function (conditional, options) {
	    return instance.helpers['if'].call(this, conditional, { fn: options.inverse, inverse: options.fn, hash: options.hash });
	  });
	};

	module.exports = exports['default'];

/***/ },
/* 11 */
/***/ function(module, exports) {

	'use strict';

	exports.__esModule = true;

	exports['default'] = function (instance) {
	  instance.registerHelper('log', function () /* message, options */{
	    var args = [undefined],
	        options = arguments[arguments.length - 1];
	    for (var i = 0; i < arguments.length - 1; i++) {
	      args.push(arguments[i]);
	    }

	    var level = 1;
	    if (options.hash.level != null) {
	      level = options.hash.level;
	    } else if (options.data && options.data.level != null) {
	      level = options.data.level;
	    }
	    args[0] = level;

	    instance.log.apply(instance, args);
	  });
	};

	module.exports = exports['default'];

/***/ },
/* 12 */
/***/ function(module, exports) {

	'use strict';

	exports.__esModule = true;

	exports['default'] = function (instance) {
	  instance.registerHelper('lookup', function (obj, field) {
	    return obj && obj[field];
	  });
	};

	module.exports = exports['default'];

/***/ },
/* 13 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	exports.__esModule = true;

	var _utils = __webpack_require__(4);

	exports['default'] = function (instance) {
	  instance.registerHelper('with', function (context, options) {
	    if (_utils.isFunction(context)) {
	      context = context.call(this);
	    }

	    var fn = options.fn;

	    if (!_utils.isEmpty(context)) {
	      var data = options.data;
	      if (options.data && options.ids) {
	        data = _utils.createFrame(options.data);
	        data.contextPath = _utils.appendContextPath(options.data.contextPath, options.ids[0]);
	      }

	      return fn(context, {
	        data: data,
	        blockParams: _utils.blockParams([context], [data && data.contextPath])
	      });
	    } else {
	      return options.inverse(this);
	    }
	  });
	};

	module.exports = exports['default'];

/***/ },
/* 14 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	var _interopRequireDefault = __webpack_require__(2)['default'];

	exports.__esModule = true;
	exports.registerDefaultDecorators = registerDefaultDecorators;

	var _decoratorsInline = __webpack_require__(15);

	var _decoratorsInline2 = _interopRequireDefault(_decoratorsInline);

	function registerDefaultDecorators(instance) {
	  _decoratorsInline2['default'](instance);
	}

/***/ },
/* 15 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	exports.__esModule = true;

	var _utils = __webpack_require__(4);

	exports['default'] = function (instance) {
	  instance.registerDecorator('inline', function (fn, props, container, options) {
	    var ret = fn;
	    if (!props.partials) {
	      props.partials = {};
	      ret = function (context, options) {
	        // Create a new partials stack frame prior to exec.
	        var original = container.partials;
	        container.partials = _utils.extend({}, original, props.partials);
	        var ret = fn(context, options);
	        container.partials = original;
	        return ret;
	      };
	    }

	    props.partials[options.args[0]] = options.fn;

	    return ret;
	  });
	};

	module.exports = exports['default'];

/***/ },
/* 16 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	exports.__esModule = true;

	var _utils = __webpack_require__(4);

	var logger = {
	  methodMap: ['debug', 'info', 'warn', 'error'],
	  level: 'info',

	  // Maps a given level value to the `methodMap` indexes above.
	  lookupLevel: function lookupLevel(level) {
	    if (typeof level === 'string') {
	      var levelMap = _utils.indexOf(logger.methodMap, level.toLowerCase());
	      if (levelMap >= 0) {
	        level = levelMap;
	      } else {
	        level = parseInt(level, 10);
	      }
	    }

	    return level;
	  },

	  // Can be overridden in the host environment
	  log: function log(level) {
	    level = logger.lookupLevel(level);

	    if (typeof console !== 'undefined' && logger.lookupLevel(logger.level) <= level) {
	      var method = logger.methodMap[level];
	      if (!console[method]) {
	        // eslint-disable-line no-console
	        method = 'log';
	      }

	      for (var _len = arguments.length, message = Array(_len > 1 ? _len - 1 : 0), _key = 1; _key < _len; _key++) {
	        message[_key - 1] = arguments[_key];
	      }

	      console[method].apply(console, message); // eslint-disable-line no-console
	    }
	  }
	};

	exports['default'] = logger;
	module.exports = exports['default'];

/***/ },
/* 17 */
/***/ function(module, exports) {

	// Build out our basic SafeString type
	'use strict';

	exports.__esModule = true;
	function SafeString(string) {
	  this.string = string;
	}

	SafeString.prototype.toString = SafeString.prototype.toHTML = function () {
	  return '' + this.string;
	};

	exports['default'] = SafeString;
	module.exports = exports['default'];

/***/ },
/* 18 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	var _interopRequireWildcard = __webpack_require__(1)['default'];

	var _interopRequireDefault = __webpack_require__(2)['default'];

	exports.__esModule = true;
	exports.checkRevision = checkRevision;
	exports.template = template;
	exports.wrapProgram = wrapProgram;
	exports.resolvePartial = resolvePartial;
	exports.invokePartial = invokePartial;
	exports.noop = noop;

	var _utils = __webpack_require__(4);

	var Utils = _interopRequireWildcard(_utils);

	var _exception = __webpack_require__(5);

	var _exception2 = _interopRequireDefault(_exception);

	var _base = __webpack_require__(3);

	function checkRevision(compilerInfo) {
	  var compilerRevision = compilerInfo && compilerInfo[0] || 1,
	      currentRevision = _base.COMPILER_REVISION;

	  if (compilerRevision !== currentRevision) {
	    if (compilerRevision < currentRevision) {
	      var runtimeVersions = _base.REVISION_CHANGES[currentRevision],
	          compilerVersions = _base.REVISION_CHANGES[compilerRevision];
	      throw new _exception2['default']('Template was precompiled with an older version of Handlebars than the current runtime. ' + 'Please update your precompiler to a newer version (' + runtimeVersions + ') or downgrade your runtime to an older version (' + compilerVersions + ').');
	    } else {
	      // Use the embedded version info since the runtime doesn't know about this revision yet
	      throw new _exception2['default']('Template was precompiled with a newer version of Handlebars than the current runtime. ' + 'Please update your runtime to a newer version (' + compilerInfo[1] + ').');
	    }
	  }
	}

	function template(templateSpec, env) {
	  /* istanbul ignore next */
	  if (!env) {
	    throw new _exception2['default']('No environment passed to template');
	  }
	  if (!templateSpec || !templateSpec.main) {
	    throw new _exception2['default']('Unknown template object: ' + typeof templateSpec);
	  }

	  templateSpec.main.decorator = templateSpec.main_d;

	  // Note: Using env.VM references rather than local var references throughout this section to allow
	  // for external users to override these as psuedo-supported APIs.
	  env.VM.checkRevision(templateSpec.compiler);

	  function invokePartialWrapper(partial, context, options) {
	    if (options.hash) {
	      context = Utils.extend({}, context, options.hash);
	      if (options.ids) {
	        options.ids[0] = true;
	      }
	    }

	    partial = env.VM.resolvePartial.call(this, partial, context, options);
	    var result = env.VM.invokePartial.call(this, partial, context, options);

	    if (result == null && env.compile) {
	      options.partials[options.name] = env.compile(partial, templateSpec.compilerOptions, env);
	      result = options.partials[options.name](context, options);
	    }
	    if (result != null) {
	      if (options.indent) {
	        var lines = result.split('\n');
	        for (var i = 0, l = lines.length; i < l; i++) {
	          if (!lines[i] && i + 1 === l) {
	            break;
	          }

	          lines[i] = options.indent + lines[i];
	        }
	        result = lines.join('\n');
	      }
	      return result;
	    } else {
	      throw new _exception2['default']('The partial ' + options.name + ' could not be compiled when running in runtime-only mode');
	    }
	  }

	  // Just add water
	  var container = {
	    strict: function strict(obj, name) {
	      if (!(name in obj)) {
	        throw new _exception2['default']('"' + name + '" not defined in ' + obj);
	      }
	      return obj[name];
	    },
	    lookup: function lookup(depths, name) {
	      var len = depths.length;
	      for (var i = 0; i < len; i++) {
	        if (depths[i] && depths[i][name] != null) {
	          return depths[i][name];
	        }
	      }
	    },
	    lambda: function lambda(current, context) {
	      return typeof current === 'function' ? current.call(context) : current;
	    },

	    escapeExpression: Utils.escapeExpression,
	    invokePartial: invokePartialWrapper,

	    fn: function fn(i) {
	      var ret = templateSpec[i];
	      ret.decorator = templateSpec[i + '_d'];
	      return ret;
	    },

	    programs: [],
	    program: function program(i, data, declaredBlockParams, blockParams, depths) {
	      var programWrapper = this.programs[i],
	          fn = this.fn(i);
	      if (data || depths || blockParams || declaredBlockParams) {
	        programWrapper = wrapProgram(this, i, fn, data, declaredBlockParams, blockParams, depths);
	      } else if (!programWrapper) {
	        programWrapper = this.programs[i] = wrapProgram(this, i, fn);
	      }
	      return programWrapper;
	    },

	    data: function data(value, depth) {
	      while (value && depth--) {
	        value = value._parent;
	      }
	      return value;
	    },
	    merge: function merge(param, common) {
	      var obj = param || common;

	      if (param && common && param !== common) {
	        obj = Utils.extend({}, common, param);
	      }

	      return obj;
	    },

	    noop: env.VM.noop,
	    compilerInfo: templateSpec.compiler
	  };

	  function ret(context) {
	    var options = arguments.length <= 1 || arguments[1] === undefined ? {} : arguments[1];

	    var data = options.data;

	    ret._setup(options);
	    if (!options.partial && templateSpec.useData) {
	      data = initData(context, data);
	    }
	    var depths = undefined,
	        blockParams = templateSpec.useBlockParams ? [] : undefined;
	    if (templateSpec.useDepths) {
	      if (options.depths) {
	        depths = context !== options.depths[0] ? [context].concat(options.depths) : options.depths;
	      } else {
	        depths = [context];
	      }
	    }

	    function main(context /*, options*/) {
	      return '' + templateSpec.main(container, context, container.helpers, container.partials, data, blockParams, depths);
	    }
	    main = executeDecorators(templateSpec.main, main, container, options.depths || [], data, blockParams);
	    return main(context, options);
	  }
	  ret.isTop = true;

	  ret._setup = function (options) {
	    if (!options.partial) {
	      container.helpers = container.merge(options.helpers, env.helpers);

	      if (templateSpec.usePartial) {
	        container.partials = container.merge(options.partials, env.partials);
	      }
	      if (templateSpec.usePartial || templateSpec.useDecorators) {
	        container.decorators = container.merge(options.decorators, env.decorators);
	      }
	    } else {
	      container.helpers = options.helpers;
	      container.partials = options.partials;
	      container.decorators = options.decorators;
	    }
	  };

	  ret._child = function (i, data, blockParams, depths) {
	    if (templateSpec.useBlockParams && !blockParams) {
	      throw new _exception2['default']('must pass block params');
	    }
	    if (templateSpec.useDepths && !depths) {
	      throw new _exception2['default']('must pass parent depths');
	    }

	    return wrapProgram(container, i, templateSpec[i], data, 0, blockParams, depths);
	  };
	  return ret;
	}

	function wrapProgram(container, i, fn, data, declaredBlockParams, blockParams, depths) {
	  function prog(context) {
	    var options = arguments.length <= 1 || arguments[1] === undefined ? {} : arguments[1];

	    var currentDepths = depths;
	    if (depths && context !== depths[0]) {
	      currentDepths = [context].concat(depths);
	    }

	    return fn(container, context, container.helpers, container.partials, options.data || data, blockParams && [options.blockParams].concat(blockParams), currentDepths);
	  }

	  prog = executeDecorators(fn, prog, container, depths, data, blockParams);

	  prog.program = i;
	  prog.depth = depths ? depths.length : 0;
	  prog.blockParams = declaredBlockParams || 0;
	  return prog;
	}

	function resolvePartial(partial, context, options) {
	  if (!partial) {
	    if (options.name === '@partial-block') {
	      partial = options.data['partial-block'];
	    } else {
	      partial = options.partials[options.name];
	    }
	  } else if (!partial.call && !options.name) {
	    // This is a dynamic partial that returned a string
	    options.name = partial;
	    partial = options.partials[partial];
	  }
	  return partial;
	}

	function invokePartial(partial, context, options) {
	  options.partial = true;
	  if (options.ids) {
	    options.data.contextPath = options.ids[0] || options.data.contextPath;
	  }

	  var partialBlock = undefined;
	  if (options.fn && options.fn !== noop) {
	    options.data = _base.createFrame(options.data);
	    partialBlock = options.data['partial-block'] = options.fn;

	    if (partialBlock.partials) {
	      options.partials = Utils.extend({}, options.partials, partialBlock.partials);
	    }
	  }

	  if (partial === undefined && partialBlock) {
	    partial = partialBlock;
	  }

	  if (partial === undefined) {
	    throw new _exception2['default']('The partial ' + options.name + ' could not be found');
	  } else if (partial instanceof Function) {
	    return partial(context, options);
	  }
	}

	function noop() {
	  return '';
	}

	function initData(context, data) {
	  if (!data || !('root' in data)) {
	    data = data ? _base.createFrame(data) : {};
	    data.root = context;
	  }
	  return data;
	}

	function executeDecorators(fn, prog, container, depths, data, blockParams) {
	  if (fn.decorator) {
	    var props = {};
	    prog = fn.decorator(prog, props, container, depths && depths[0], data, blockParams, depths);
	    Utils.extend(prog, props);
	  }
	  return prog;
	}

/***/ },
/* 19 */
/***/ function(module, exports) {

	/* WEBPACK VAR INJECTION */(function(global) {/* global window */
	'use strict';

	exports.__esModule = true;

	exports['default'] = function (Handlebars) {
	  /* istanbul ignore next */
	  var root = typeof global !== 'undefined' ? global : window,
	      $Handlebars = root.Handlebars;
	  /* istanbul ignore next */
	  Handlebars.noConflict = function () {
	    if (root.Handlebars === Handlebars) {
	      root.Handlebars = $Handlebars;
	    }
	    return Handlebars;
	  };
	};

	module.exports = exports['default'];
	/* WEBPACK VAR INJECTION */}.call(exports, (function() { return this; }())))

/***/ }
/******/ ])
});
;
/* Kazakh (UTF-8) initialisation for the jQuery UI date picker plugin. */
/* Written by Dmitriy Karasyov (dmitriy.karasyov@gmail.com). */
( function( factory ) {
	if ( typeof define === "function" && define.amd ) {

		// AMD. Register as an anonymous module.
		define( [ "../widgets/datepicker" ], factory );
	} else {

		// Browser globals
		factory( jQuery.datepicker );
	}
}( function( datepicker ) {

datepicker.regional.kk = {
	closeText: "Жабу",
	prevText: "&#x3C;Алдыңғы",
	nextText: "Келесі&#x3E;",
	currentText: "Бүгін",
	monthNames: [ "Қаңтар","Ақпан","Наурыз","Сәуір","Мамыр","Маусым",
	"Шілде","Тамыз","Қыркүйек","Қазан","Қараша","Желтоқсан" ],
	monthNamesShort: [ "Қаң","Ақп","Нау","Сәу","Мам","Мау",
	"Шіл","Там","Қыр","Қаз","Қар","Жел" ],
	dayNames: [ "Жексенбі","Дүйсенбі","Сейсенбі","Сәрсенбі","Бейсенбі","Жұма","Сенбі" ],
	dayNamesShort: [ "жкс","дсн","ссн","срс","бсн","жма","снб" ],
	dayNamesMin: [ "Жк","Дс","Сс","Ср","Бс","Жм","Сн" ],
	weekHeader: "Не",
	dateFormat: "dd.mm.yy",
	firstDay: 1,
	isRTL: false,
	showMonthAfterYear: false,
	yearSuffix: "" };
datepicker.setDefaults( datepicker.regional.kk );

return datepicker.regional.kk;

} ) );

/* Russian (UTF-8) initialisation for the jQuery UI date picker plugin. */
/* Written by Andrew Stromnov (stromnov@gmail.com). */
( function( factory ) {
	if ( typeof define === "function" && define.amd ) {

		// AMD. Register as an anonymous module.
		define( [ "../widgets/datepicker" ], factory );
	} else {

		// Browser globals
		factory( jQuery.datepicker );
	}
}( function( datepicker ) {

datepicker.regional.ru = {
	closeText: "Закрыть",
	prevText: "&#x3C;Пред",
	nextText: "След&#x3E;",
	currentText: "Сегодня",
	monthNames: [ "Январь","Февраль","Март","Апрель","Май","Июнь",
	"Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь" ],
	monthNamesShort: [ "Янв","Фев","Мар","Апр","Май","Июн",
	"Июл","Авг","Сен","Окт","Ноя","Дек" ],
	dayNames: [ "воскресенье","понедельник","вторник","среда","четверг","пятница","суббота" ],
	dayNamesShort: [ "вск","пнд","втр","срд","чтв","птн","сбт" ],
	dayNamesMin: [ "Вс","Пн","Вт","Ср","Чт","Пт","Сб" ],
	weekHeader: "Нед",
	dateFormat: "dd.mm.yy",
	firstDay: 1,
	isRTL: false,
	showMonthAfterYear: false,
	yearSuffix: "" };
datepicker.setDefaults( datepicker.regional.ru );

return datepicker.regional.ru;

} ) );

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
        dateFormat: 'yy-mm-dd'
    },
    translations: {
        yes: 'Да',
        no: 'Нет',
        ok: 'Ok',
        cancel: 'Отмена',
        select: 'Выбрать',
        dialog_select_value: 'Вы не сделали выбор'
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
 * @param templateId - template function name defined in nb.tpl
 * @param data - data for template
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
        opt.width = opt.width || 360;
        opt.height = opt.height || 210;
        opt.buttons = opt.buttons || {
            'Ok': function() {
                $(this).dialog('close');
            }
        };

        return this.show(opt);
    },
    warn: function(opt) {
        opt.className = 'dialog-warn';
        opt.width = opt.width || 360;
        opt.height = opt.height || 210;
        opt.buttons = opt.buttons || {
            'Ok': function() {
                $(this).dialog('close');
            }
        };

        return this.show(opt);
    },
    error: function(opt) {
        opt.className = 'dialog-error';
        opt.width = opt.width || 360;
        opt.height = opt.height || 210;
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
        if ($dlgw.css('display') === 'none') {
            return;
        }
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
            fch += containerNode.children[i].clientHeight + containerNode.children[i].offsetTop;
        }
        //
        if (wh < 600 && fch > 300) {
            top = window.scrollY + 1;
            height = wh - 3;
        } else {
            if (wh > 800 && fch > 600) {
                height = wh / 1.2;
            } else {
                height = (fch > 60 ? fch : 60) + barHeight;
            }
            top = (wh - height) / 2;
            if (top < 0) {
                top = window.scrollY + 1;
                height = wh - 3;
            } else {
                top = top + window.scrollY;
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
        // console.log('bh:' + barHeight, 'wh:' + wh, 'fch:' + fch, 'top:' + top, 'dlgH:' + $dlgw[0].clientHeight, 'ch:' + containerNode.clientHeight);
        //
        $(containerNode).css({
            height: ($dlgw[0].clientHeight - barHeight) + 'px',
            maxHeight: 'none'
        });
    },
    load: function(url, $container, options) {
        return $.ajax({
            url: url,
            dataType: options.dataType || 'html',
            beforeSend: function() {
                $container.addClass('loading');
            },
            success: function(response, status, xhr) {
                if (status === 'error') {
                    $container.html('<div class="alert alert-danger">' + status + '</div>');
                } else {
                    if (options.dataType === 'json') {
                        $container.html(nb.template.call(options, options.templateId, response));
                    } else {
                        $container.html(response);
                    }

                    if (options.onLoad !== null) {
                        options.onLoad(response, status, xhr);
                    }
                    if (options.filter !== false) {
                        new nb.dialog.Filter($container, options.dialogFilterListItem, 13);
                    }
                }

                try {
                    window.dispatchEvent(new Event('resize'));
                } catch (e) {}

                if (nb.debug === true) {
                    console.log('nb.dialog : load callback', xhr);
                }
            },
            error: function(response, status, xhr) {
                if (status === 'error') {
                    $container.html('<div class="alert alert-danger">' + status + '</div>');
                }

                try {
                    window.dispatchEvent(new Event('resize'));
                } catch (e) {}

                if (nb.debug === true) {
                    console.log('nb.dialog : load error', xhr);
                }
            },
            complete: function() {
                $container.removeClass('loading');
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

        options.width = options.width || 500;
        options.position = { top: window.scrollY };
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
            $dlgContainer = $('<div data-role="nb-dialog" id="' + options.id + '" class="nb-dialog-container loading ' + options.className + '"></div>');
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

/**
 * notify
 */
nb.notify = function(options) {

    var $nwrap = $('#nb-notify-wrapper');
    if (!$nwrap.length) {
        $nwrap = $('<div id=nb-notify-wrapper class=nb-notify></div>').appendTo('body');
    }
    var $el = $('<div class=nb-notify-entry-' + (options.type || 'info') + '>' + options.message + '</div>').appendTo($nwrap);

    return {
        show: function(timeout, callback) {
            $el.css('display', 'block');
            if (timeout && timeout > 0) {
                this.remove(timeout, callback);
                return;
            }
            return this;
        },
        hide: function() {
            $el.css('display', 'none');
            return this;
        },
        set: function(options) {
            for (var key in options) {
                if (key === 'text') {
                    $el.text(options[key]);
                } else if (key === 'type') {
                    $el.attr('class', 'nb-notify-entry-' + options[key]);
                }
            }
            return this;
        },
        remove: function(timeout, callback) {
            if ($el === null) {
                return;
            }

            if (timeout && timeout > 0) {
                setTimeout(function() {
                    $el.remove();
                    $el = null;
                    if (typeof(callback) === 'function') {
                        callback();
                    }
                }, timeout);
            } else {
                $el.remove();
                $el = null;
                if (typeof(callback) === 'function') {
                    callback();
                }
            }
        }
    };
};

/**
 * doDelete
 */
nb.xhrDelete = function(data) {
    return $.ajax({
        type: 'DELETE',
        dataType: 'json',
        url: location.href + '&' + data
    });
};

nb.tpl = {};

nb.tpl.defaultDialogListTemplate = function(data) {
    return nb.templates['dialog-list']({
        dialogId: this.id,
        fields: this.fields,
        isMulti: this.isMulti === true,
        models: data.objects[0]
    });
};

Handlebars.registerHelper('mapValue', function(context, options) {
    return context[options];
});

/**
 * defaultDialogListTemplate
 */
nb.tpl.defaultDialogListTemplate2 = function(data) {

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

this["nb"] = this["nb"] || {};
this["nb"]["templates"] = this["nb"]["templates"] || {};
this["nb"]["templates"]["dialog-list"] = Handlebars.template({"1":function(container,depth0,helpers,partials,data,blockParams,depths) {
    var stack1, alias1=depth0 != null ? depth0 : {};

  return "    <li class=nb-dialog-list-it>\r\n        <label data-role=\"dialog-list-item\">\r\n"
    + ((stack1 = helpers["if"].call(alias1,(depths[1] != null ? depths[1].isMulti : depths[1]),{"name":"if","hash":{},"fn":container.program(2, data, 0, blockParams, depths),"inverse":container.program(4, data, 0, blockParams, depths),"data":data,"blockParams":blockParams})) != null ? stack1 : "")
    + "            <span>"
    + container.escapeExpression(container.lambda(((stack1 = blockParams[0][0]) != null ? stack1.name : stack1), depth0))
    + "</span>\r\n"
    + ((stack1 = helpers.each.call(alias1,(depths[1] != null ? depths[1].fields : depths[1]),{"name":"each","hash":{},"fn":container.program(6, data, 1, blockParams, depths),"inverse":container.noop,"data":data,"blockParams":blockParams})) != null ? stack1 : "")
    + "        </label>\r\n    </li>\r\n";
},"2":function(container,depth0,helpers,partials,data,blockParams,depths) {
    var stack1, alias1=container.lambda, alias2=container.escapeExpression;

  return "                <input data-type=\"select\" type=\"checkbox\" name=\"select_"
    + alias2(alias1((depths[1] != null ? depths[1].dialogId : depths[1]), depth0))
    + "\" value=\""
    + alias2(alias1(((stack1 = blockParams[1][0]) != null ? stack1.id : stack1), depth0))
    + "\"/>\r\n";
},"4":function(container,depth0,helpers,partials,data,blockParams,depths) {
    var stack1, alias1=container.lambda, alias2=container.escapeExpression;

  return "                <input data-type=\"select\" type=\"radio\" name=\"select_"
    + alias2(alias1((depths[1] != null ? depths[1].dialogId : depths[1]), depth0))
    + "\" value=\""
    + alias2(alias1(((stack1 = blockParams[1][0]) != null ? stack1.id : stack1), depth0))
    + "\"/>\r\n";
},"6":function(container,depth0,helpers,partials,data,blockParams) {
    var stack1;

  return ((stack1 = helpers["if"].call(depth0 != null ? depth0 : {},((stack1 = blockParams[0][0]) != null ? stack1["1"] : stack1),{"name":"if","hash":{},"fn":container.program(7, data, 0, blockParams),"inverse":container.program(9, data, 0, blockParams),"data":data,"blockParams":blockParams})) != null ? stack1 : "");
},"7":function(container,depth0,helpers,partials,data,blockParams) {
    var stack1, alias1=container.lambda, alias2=container.escapeExpression, alias3=depth0 != null ? depth0 : {}, alias4=helpers.helperMissing;

  return "                    <input data-id=\""
    + alias2(alias1(((stack1 = blockParams[2][0]) != null ? stack1.id : stack1), depth0))
    + "\" name=\""
    + alias2(alias1(((stack1 = blockParams[1][0]) != null ? stack1["0"] : stack1), depth0))
    + "\" value=\""
    + alias2((helpers.mapValue || (depth0 && depth0.mapValue) || alias4).call(alias3,blockParams[2][0],((stack1 = blockParams[1][0]) != null ? stack1["0"] : stack1),{"name":"mapValue","hash":{},"data":data,"blockParams":blockParams}))
    + "\" data-text=\""
    + alias2((helpers.mapValue || (depth0 && depth0.mapValue) || alias4).call(alias3,blockParams[2][0],((stack1 = blockParams[1][0]) != null ? stack1["1"] : stack1),{"name":"mapValue","hash":{},"data":data,"blockParams":blockParams}))
    + "\" type=\"hidden\"/>\r\n";
},"9":function(container,depth0,helpers,partials,data,blockParams) {
    var stack1, alias1=container.lambda, alias2=container.escapeExpression;

  return "                    <input data-id=\""
    + alias2(alias1(((stack1 = blockParams[2][0]) != null ? stack1.id : stack1), depth0))
    + "\" name=\""
    + alias2(alias1(((stack1 = blockParams[1][0]) != null ? stack1["0"] : stack1), depth0))
    + "\" value=\""
    + alias2((helpers.mapValue || (depth0 && depth0.mapValue) || helpers.helperMissing).call(depth0 != null ? depth0 : {},blockParams[2][0],((stack1 = blockParams[1][0]) != null ? stack1["0"] : stack1),{"name":"mapValue","hash":{},"data":data,"blockParams":blockParams}))
    + "\" type=\"hidden\"/>\r\n";
},"compiler":[7,">= 4.0.0"],"main":function(container,depth0,helpers,partials,data,blockParams,depths) {
    var stack1;

  return "<ul class=nb-dialog-list>\r\n"
    + ((stack1 = helpers.each.call(depth0 != null ? depth0 : {},(depth0 != null ? depth0.models : depth0),{"name":"each","hash":{},"fn":container.program(1, data, 1, blockParams, depths),"inverse":container.noop,"data":data,"blockParams":blockParams})) != null ? stack1 : "")
    + "</ul>\r\n";
},"useData":true,"useDepths":true,"useBlockParams":true});
/*
 Не допускать разбухания функции.
 Если нужны условия для какого та диалога, вынести в саму функцию диалога вызывающего эту функцию.
 Не писать условия в кнопке, типа если id == '?' то делать то-то; Вынасите в вызывающую функцию.

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
nbApp.defaultChoiceDialog = function(el, url, dataType, fields, templateId) {
    var form = nb.getForm(el);
    var dlg = nb.dialog.show({
        targetForm: form.name,
        fields: fields,
        title: el.title,
        href: url,
        dataType: dataType || 'html',
        templateId: templateId,
        buttons: {
            ok: {
                text: nb.getText('select'),
                click: function() {
                    dlg[0].dialogOptions.onExecute();
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

nbApp.choiceBalanceHolder = function(el) {
    var url = 'Provider?id=get-organizations';
    return this.defaultChoiceDialog(el, url, 'json', {
        balanceholderid: ['id', 'name'],
        balanceholderbin: ['bin']
    });
};

nbApp.choiceCountries = function(el) {
    var url = 'Provider?id=get-countries';
    return this.defaultChoiceDialog(el, url, 'json', {
        countryid: ['id', 'name']
    });
};

nbApp.choiceRegion = function(el) {
    var url = 'Provider?id=get-regions';
    return this.defaultChoiceDialog(el, url, 'json', {
        regionid: ['id', 'name']
    });
};

nbApp.choiceDistrict = function(el) {
    var regionId = nb.getForm(el).regionid.value;
    var url = 'Provider?id=get-district&regionid=' + regionId;
    return this.defaultChoiceDialog(el, url, 'json', {
        districtid: ['id', 'name']
    });
};

nbApp.choiceLocality = function(el) {
    var districtId = nb.getForm(el).districtid.value;
    var url = 'Provider?id=get-locality&districtid=' + districtId;
    return this.defaultChoiceDialog(el, url, 'json', {
        localityid: ['id', 'name']
    });
};

nbApp.choiceStreet = function(el) {
    var localityId = nb.getForm(el).localityid.value;
    var url = 'Provider?id=get-street&localityid=' + localityId;
    return this.defaultChoiceDialog(el, url, 'json', {
        streetid: ['id', 'name']
    });
};

$(function() {
    $.datepicker.setDefaults($.datepicker.regional['ru']);
    $('[type=date]').datepicker({ dateFormat: nb.options.dateFormat });

    $('[data-action=save_and_close]').click(function(event) {
        event.preventDefault();
        nb.submitForm(nb.getForm(this));
    });

    $('[data-action=delete_document]').click(function(event) {
        event.preventDefault();

        var docids = nb.getSelectedEntityIDs('docid');
        if (!docids.length) {
            return;
        }

        nb.xhrDelete('docid=' + docids.join('&docid=')).then(function() {
            location.reload();
        });
    });

    $('[data-action=delete_document]').attr('disabled', true);
    $(':checkbox').bind('change', function() {
        var countChecked = $('[name=docid]:checked').length;
        $('[data-action=delete_document]').attr('disabled', countChecked === 0);
    });

    $('[name=docid]:checked').attr('checked', false);

    $('[data-toggle-theme]').click(function() {
        var themeName = $(this).data('toggle-theme');
        if ($('body').hasClass('theme1')) {
            $('body').removeClass(themeName);
            localStorage.setItem('theme', '');
        } else {
            $('body').addClass(themeName);
            localStorage.setItem('theme', themeName);
        }
    });

    var theme = localStorage.getItem('theme');
    if (theme) {
        $('body').addClass(theme);
    }
});
