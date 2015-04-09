//author Medet
//set utf-8 құ

var windowUnload = false;

var apps = {
	dbid: "BGZ",
	staticpage: "type=static"
};

//хранилище базы
var dbcache = {
	gloss: {
		//для глоссариев
	},
	doctype: {
		888: 'department',
		889: 'employer',
		890: '',
		891: 'organization',
		892: 'role',
		893: 'person',
		894: 'glossary',
		895: 'userprofile',
		896: 'document',
		897: 'task',
		898: 'execution',
		899: 'project',
		900: 'group'
	}
};


/*
 * localStorage
 */
var nbLocalStorage = {
		isLS: 'localStorage' in window && window['localStorage'] !== null,
		set: function(name, value, exp){
			if( this.isLS ){
				try {
					localStorage.setItem(name, JSON.stringify(value));
				} catch(e) {
					if( e == QUOTA_EXCEEDED_ERR ){ alert('QUOTA_EXCEEDED'); }
					return false;
				}
			} else {
				if(typeof(exp)==="undefined"){
					exp = "1";
				}

				$.cookie(name, value, {
					path: "",
					expires: exp
				});
			}

			return true;
		},
		get: function(name){
			var rv = null;
			if( this.isLS ){
				try {
					 rv = JSON.parse(localStorage.getItem(name));
				} catch(e) {}
			} else {
				rv = $.cookie(name);
			}

			return (rv === null) ? "" : rv;
		},
		remove: function(name){
			if( this.isLS ){
				localStorage.removeItem(name);
			} else {
				$.cookie(name, "", {
					path: "",
					expires: "-1"
				});
			}
		},
		removeAllLS: function(){
			if( this.isLS ){
				localStorage.clear();
			}
		}
};


/*
 * blockWaiting
 */
var blockWaiting = {
	addEl: [],
	block: function() {

		var myDiv = document.createElement("DIV");
		divhtml = '<div class="blockWindow" id="blockWindow">';
		divhtml += '<img style="position:fixed; top:50%; left:50%; z-index:999;" src="/SharedResources/img/classic/loading-large.gif" alt=""/></div>';
		myDiv.innerHTML = divhtml;

		this.addEl.push(myDiv);
		document.body.appendChild(myDiv);
	},
	show: function(msg, noLoader) {

		var myDiv = document.createElement("DIV");
		divhtml = '<div class="blockWindow" id="blockWindow"></div>';
		divhtml += '<div align="center" style="z-index:999; width:35%; background-color:#FFF; border:1px solid #A6C9E2; position:fixed; top:43%; left:35%; display:block;">';
		divhtml += '<b style="height:1px; font-size:1px; display:block; overflow:hidden; border:1px solid #A6C9E2; border-width:0 1px;"></b>';
		divhtml += '<div style="display:block; padding:10px;">'+ msg +'</div>';
		if(!noLoader){
			divhtml += '<img style="margin:5px;" src="/SharedResources/img/classic/loading-large.gif" alt=""/>';
		}
		divhtml += '<b style="height:1px; font-size:1px; display:block; overflow:hidden; border:1px solid #A6C9E2; border-width:0 1px;"></b>';
		divhtml += '</div>';
		myDiv.innerHTML = divhtml;

		this.addEl.push(myDiv);
		document.body.appendChild(myDiv);
	},
	surface: function(msg) {

		var myDiv = document.createElement("DIV");
		divhtml = '<div id="blockWaitingSurface" style="z-index:999; border:none; position:fixed; top:5px; right:20px;">';
		divhtml += '<img style="vertical-align:bottom;" src="/SharedResources/img/classic/loader-mini.gif" alt=""/>';
		divhtml += '<span style="vertical-align:middle;padding-left:3px;color:#6790B3;">'+msg+'</span></div>';
		myDiv.innerHTML = divhtml;

		this.addEl.push(myDiv);
		document.body.appendChild(myDiv);
	},
	destroy: function(){
		while( this.addEl.length > 0 ){
			try {
				blockEl = this.addEl.shift();
				blockEl.parentNode.removeChild(blockEl);
			}catch(e){}
		}
	}
};

var surfaceMessage = {
	_show: function(msg, showmls, tcolor, bgcolor, bordercolor){

		var msgDiv = document.createElement("DIV");
		msgDiv.innerHTML = "<div class='surfaceMessage' style='position:fixed;top:3px;left:45%;padding:5px;background-color:#"+bgcolor+";border:1px solid #"+bordercolor+";color:#"+tcolor+";z-index:1000;'>"+msg+"</div>";
		document.body.appendChild(msgDiv);

		msgDiv.onclick = function(){ this.parentNode.removeChild(this); };

		try {
			setTimeout(function(){$(msgDiv).remove();}, showmls);
		}catch(e){}
	},
	show: function(msg, showmls){
		showmls = (typeof showmls === "number" && (showmls>0)) ? showmls : 4000;
		this._show(msg, showmls, "000", "E8F2FE", "CEE5FF");
	},
	error: function(msg, showmls){
		showmls = (typeof showmls === "number" && (showmls>0)) ? showmls : 4000;
		this._show(msg, showmls, "FFF", "D42F1F", "B41708");
	}
};

var globalMessage = {
	uiBlocked: false,
	msg: [],
	ping: (false && window.location.href.indexOf(apps.staticpage)==-1 && !windowUnload) && setTimeout(function(){globalMessage.getMsg();}, 3000) && setInterval(function(){globalMessage.getMsg();}, 20000),
	unping: function(){clearInterval(this.ping);},
	getMsg: function(){
		return;

		var msgbgcolor = "3D618A";
		var msgbrcolor = "3D618A";
		var tcolor = "FFF";

		if(windowUnload){
			return;
		}

		jQuery.ajax({
			url: "Provider?type=handler&id=global-message",
			success: function(data){
				if( (window.location.href.indexOf(apps.staticpage)==-1) && ($(data).text().match("AuthorizationPage")) ){
					window.location.href = "Logout";
					return;
				}

				if(globalMessage.uiBlocked){
					blockWaiting.destroy();
				}

				globalMessage.msg = [];
				$(data).find("globalmessage").each(function(){
					if($(this).attr("important")=="1"){
						msgbgcolor = "D42F1F";
						msgbrcolor = "B41708";
					} else {
						msgbgcolor = "3D618A";
						msgbrcolor = "3D618A";
					}
					globalMessage.msg.push("<div style='background-color:#"+msgbgcolor+";padding:1px;" +
							"border:1px solid #"+msgbrcolor+";color:#"+tcolor+";'>"+$(this).text()+"</div>");
				});
				if(globalMessage.msg.length){
					globalMessage.show(globalMessage.msg.join(""), 0);
				} else {
					$(".globalMessage").remove();
				}
			},
			error: function(xhr, ajaxOptions, thrownError){
				if(!windowUnload){
					globalMessage.show("error globalMessage", 0, "FFF", "D42F1F", "B41708");
				}
			},
			statusCode: {
				0: function() {
					if(!windowUnload){
						globalMessage.uiBlocked = true;
						globalMessage.show("Connection failed", 0, "FFF", "D42F1F", "B41708");
						blockWaiting.destroy();
						//blockWaiting.show("Server is offline", true);
						blockWaiting.block();
					}else{
						globalMessage.uiBlocked = false;
					}
				}
			}
		});
	},
	show: function(msg, showmls, tcolor, bgcolor, bordercolor){

		$(".globalMessage").remove();
		var boxcolorstyle = "";
		if((typeof bgcolor==="string") && (typeof bordercolor==="string")){
			boxcolorstyle = "background-color:#"+ bgcolor +";border:1px solid #" +bordercolor +";";
		}
		var msgDiv = document.createElement("DIV");
		msgDiv.setAttribute("class", "globalMessage");
		msgDiv.innerHTML = "<div title='Click to hide' class='globalMessage'" +
				" style='color:#FFF;cursor:pointer;position:fixed;right:20px;bottom:0;" + boxcolorstyle +
				"max-height:150px;max-width:45%;z-index:1000;overflow:auto;'>" +
				"<div style='color:#CCC;font-weight:bold;font-size:10px;" +
				"line-height:7px;float:right;margin-left:3px;margin-right:1px;'> </div>"+msg+"</div>";
		document.body.appendChild(msgDiv);

		msgDiv.onclick = function(){ this.parentNode.removeChild(this); };

		try {
			if(showmls>0){
				setTimeout(function(){$(msgDiv).remove();}, showmls);
			}
		}catch(e){}
	}
};

var checkSettings = {
		ping: (false && window.location.href.indexOf(apps.staticpage)==-1 && !windowUnload) && setTimeout(function(){checkSettings.getMsg();}, 3000),
		getMsg: function(){

			if(windowUnload){
				return;
			}

			if(!$(".frame").length){
				return;
			}

			jQuery.ajax({
				url: "Provider?type=handler&id=check-settings",
				success: function(data){
					if( (window.location.href.indexOf(apps.staticpage)==-1) && ($(data).text().match("AuthorizationPage")) ){
						window.location.href = "Logout";
						return;
					}

					var msg = "";

					$(data).find("message").each(function(){
						msg = $(this).text();
					});

					if(msg.length){
						msg = msg.replace(/\n/, "<br/>");
						surfaceMessage.error(msg, 10000);
					}
				}
			});
		}
};

var tokenizer = {
	replace: function(s, fromS, toS) {
		var re = new RegExp(fromS, 'gim');
		newstr = s.replace(re, toS);
		return newstr;
	},
	striptags: function(s) {
		newstr = this.replace(this.replace(s, '<', '&lt;'), '>', '&gt;');
		return newstr;
	},
	retag: function(s) {
		newstr = this.replace(this.replace(s, '&lt;', '<'), '&gt;', '>');
		return newstr;
	},
	trim: function(s) {
		return s.replace(/(^\s+)|(\s+$)/g, "");
	}
};

function getUrlParam(name) {

	name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
	var regexS = "[\\?&]" + name + "=([^&#]*)";
	var regex = new RegExp(regexS);
	var results = regex.exec(window.location.href);
	//alert(name+"\n"+regexS+"\n"+regex+"\n"+results);
	return (results === null) ? null : results[1];
}

function flashEntry(el, c, color){

	var cc = (typeof(c)==="number") ? c : 2;
	var cl = (typeof(color)==="undefined") ? "#ffff99" : color;

	try {
		while(cc>0){
			el.animate({backgroundColor: cl}, 500);
			el.animate({backgroundColor: "white"}, 1000);
			cc--;
		}
	}catch(e){
		alert(e);
	}
}


function setCookie(c_name, value, expiredays){
	var _cook = c_name + "=" + escape(value) + ";path=;";

	if( typeof expiredays === "number" ){
		var exdate = new Date();
		exdate.setDate(exdate.getDate() + expiredays);
		_cook += ";expires=" + exdate.toUTCString();
	}

	document.cookie = _cook;
}

function getCookie(c_name){
	if( document.cookie.length > 0 ){
		c_start = document.cookie.indexOf(c_name + "=");

		if( c_start != -1 ){
			c_start = c_start + c_name.length+1;
			c_end = document.cookie.indexOf(";",c_start);
			if (c_end==-1){
				c_end = document.cookie.length;
			}

			return unescape(document.cookie.substring(c_start,c_end));
		}
	}

	return "";
}


//дни между датами
function dayMidDate(sd, ed){
	return (sd-ed)/(1000*60*60*24);
}


function logout(){
	setCookie("auth", "", -1);
	setCookie("user", "", -1);
	nbLocalStorage.removeAllLS();
}


function delDocument(docids, typedel, dbid){

	if( typeof typedel !== "string" ){
		typedel = "0";
	}

	if( typeof dbid !== "string" ){
		dbid = apps.dbid;
	}

	jQuery.ajax({
		type: "GET",
		url: "Provider?type=delete&typedel="+ typedel +"&ck="+ docids + "&dbid="+ dbid +"&nocache="+Math.random() * 300,
		success: function(data){
			return data;
		}
	});
}

//setWindowTitle
function setWindowTitle(wtitle, docsCount){
	if( typeof wtitle === "string" ){
		if(wtitle.length){
			try {
				if(docsCount!="0"){
					docsCount = "документов: " + docsCount;
					$("#js-view-count").html(docsCount);
				} else {
					docsCount = "";
				}

				$("#js-view-title").html(wtitle);

				if( top.document.title.indexOf(wtitle) === -1 ){
					top.document.title = wtitle + ". " + docsCount + " - " + top.document.title;
				}
			} catch(e) {}
		}
	}
}

//getGlossaryVal
function getGlossaryVal(selection, iquery){
	try {
		var gloss = [];

		function setgloss(c, v){ gloss[c] = v; }

		function replaceGlossCode(){
			glossval = dbcache.gloss[iquery];

			$(selection).each(function(){
				var el = $(this);
				code = el.innerHTML;
				el.html(glossval[code]);
			});
		}

		if( $(selection).size() > 0 ){
			if( ! dbcache.gloss[iquery] ){
				$.ajax({
					url: 'Provider?type=query&id=' + iquery + '&code=',
					success: function(data){
						$(data).find("query > entry").each(function(){
							gcode = this.lastChild.firstChild.data;
							gval = this.firstChild.firstChild.data;
							setgloss(gcode, gval);
						});

						dbcache.gloss[iquery] = gloss;
						replaceGlossCode();
					}
				});
			} else {
				replaceGlossCode();
			}
		}
	} catch(e) {alert(e);}
}


/*
 * addNewGlossary
 */
function addNewGlossary(formid, dlgtitle){
	try {
		$(":focus").blur();
	}catch(e){}

	var gdialog;
	var gdialogid;

	gdialogid = "dialogNewGlossary"+formid;
	if($("#"+gdialogid).length){
		window.status = "new glossary form " + formid + " already is open";
		return;
	}

	blockWaiting.block();

	$.ajax({
		url: 'Provider?type=glossary&id='+formid+'&key=',
		success: function(data){
			data = data.split("<form")[1].split("</form>")[0];

			gdialogid = "dialogNewGlossary"+formid;
			$("#"+gdialogid).dialog("destroy");

			$(document.body).append("<div id='"+gdialogid+"'></div>");
			$("#"+gdialogid).html("<form style=\"font-size:12px;\" "+data+"</form>");
			blockWaiting.destroy();
			gdialog = $("#"+gdialogid);
			openDialogForm();
		}
	});

	function openDialogForm(){
		/*gdialog.each(function(){
			window.status = $(this).attr("name");
		});*/

		gdialog.dialog({
			title: dlgtitle,
			modal: true,
			height: 550,
			width: 850,
			buttons: {
				'Отмена': function(){
					gdialog.dialog('close');
					$(gdialog).remove();
				},
				'Сохранить': function(){
					if( saveGlossary() ){
						gdialog.dialog('close');
						$(gdialog).remove();
					}
				}
			}
		});
	}

	function saveGlossary(){
		try {
			alertmessage = validationFields(gdialogid);
			$("#dialog-message").remove();
			if( alertmessage.length>0 ){
				dialogMsg(alertmessage);
				return false;
			}

			$("#"+gdialogid+" .number").numericCompile();
			var formData = $("#"+gdialogid+" form").serialize();
			blockWaiting.block();
			//
			$.ajax({
				   type: "POST",
				   url: 'Provider',
				   data: formData,
				   success: function(xml){
						blockWaiting.destroy();

					   $(xml).find('response').each(function(){
						   var st=$(this).attr('status');
						   if (st === "error" || st === "undefined"){
							   msgtext = $(xml).find('message').text();
							   var myDiv = document.createElement("DIV");
							   divhtml = "<div id='dialog-message' title='Уведомление'>";
							   if( msgtext.length ){
								   gdialog.dialog('close');
								   $(gdialog).remove();
								   showSaveResult("Документ успешно сохранен");
								} else {
									divhtml+="<br/><p><span style='height:40px'><font style='font-size:13px'>"+msgtext+"</font></p></span>";
								}
							   divhtml += "</div>";
							   myDiv.innerHTML = divhtml;
							   document.body.appendChild(myDiv);
							   $("#dialog").dialog("destroy");
							   $("#dialog-message").dialog({
								   modal: true,
								   height:180,
								   buttons: {
									   Ok: function(){
										   $(this).dialog('close');
										   $(gdialog).remove();
									   }
								   }
							   });
						   } else {
							   gdialog.dialog('close');
							   $(gdialog).remove();
							   showSaveResult("Документ успешно сохранен");
						   }
					   });
				   },
				   error:function (xhr, ajaxOptions, thrownError){
					   	blockWaiting.destroy();

						if (xhr.status == 400){
							$("body").append("<div id='errordata'>"+xhr.responseText+"</div>");
							$("li[type='square'] > a").attr("href","javascript:backtocontent()");
						}
					}
			});
		}catch(e){
			return false;
		}

		return true;
	}

	function showSaveResult(msg){
		divhtml = "<div id='saveresult' style='position:fixed;top:3px;left:45%;padding:5px;background-color:#E8F2FE;border:1px solid #CEE5FF;'>"+msg+"</div>";
		$("body").append(divhtml);

		try {
			setTimeout(function(){$('#saveresult').remove();}, 3000);
		}catch(e){}
	}
}


/*
 * quickkeyevent
 */
function quickkeyevent(){

	var keyrxp = /\((.*?)\)/;

	try{
		if(nbLocalStorage.get("qkehelp")=="hide"){
			$("#qkehelp").addClass("hidden");
		}else{
			$("#qkehelp").removeClass("hidden");
		}

		$("#qkehelpclose").bind("click", function(){
			$("#qkehelp").addClass("hidden");

			nbLocalStorage.set("qkehelp", "hide", "7");
		});
	}catch(e){}

	$(".quickkey").each(function(){
		var el = $(this);
		var eltitle = el.attr("title");
		var elfkey = keyrxp.exec(eltitle);
		var elkey = ( elfkey!==null ) ? elfkey[1] : "";

		if( elkey.length ){
			var elpos = el.position();
			var eltop = elpos.top + 9;
			var elleft = elpos.left + 7;

			el.before("<span class=\"quickkeyhint\" style=\"z-index:3;position:absolute;top:"+eltop+"px;left:"+elleft+"px;display:none;background-color:#FFFFE1;border:1px solid #22221E;padding:2px;font-size:11px;font-weight:normal;\">"+elkey+"</span>");

			//$(document).unbind('keydown', $(this));
			$(document).bind('keydown', elkey, function(e){
				e.stopPropagation();
				e.preventDefault();

				try {
					if( e.data.combi == elkey ){
						switch (elkey){
						case "Ctrl+s":
							$("#saveclose").click();
							break;
						case "Ctrl+n":
							var newdocurl = $("a.newdoc").attr("href");
							if( typeof(newdocurl)!="undefined" ){
								window.location.href = newdocurl;
							}
							break;
						default:
							window.status = eltitle;
							el.click();
							break;
						}
					}
				}catch(er){
					alert(er);
				}

				return false;
			});
		}
	});

	try {
		$quickkeyhint = $(".quickkeyhint");
		$quickkeyhint.click(function(){
			$(this).hide();
		});

		$(document).keydown(function(e){
			if( e.keyCode == 17 ){
				$quickkeyhint.show();
			} else {
				var paginationMoveKey = {39:"right"};
				if(e.ctrlKey && (e.keyCode in paginationMoveKey) && ($("#doctype").length > 0)){
					e.stopPropagation();
					e.preventDefault();
					window.status = "goto next document";

					getNextDoc();
				}
			}
		}).keyup(function(e){
			if( e.keyCode == 17 ){
				$quickkeyhint.hide();
			}
		});
	}catch(e){}
}


/* Start jQuery
---------------- */
$(document).ready(function(){

	$('.st').expander();

	$(".dropdownmenu").live("click", function(){
		dropdown = $(this).children(".dropdown");
		dropdown.css("visibility", "visible");

		$(this).bind("mouseout", function(){

			setTimeout(function(){
				dropdown.css("visibility", "");
			}, 500);

			$(this).unbind("mouseout");
		});
	});

	$(window).unload(function(){
		windowUnload = true;
		blockWaiting.destroy();

		if( $("#key").length && $("#doctype").length ){
			var docid = "doc-"+$("#doctype").val()+"-"+$("#key").val();
			nbLocalStorage.set("closeddoc", docid, "1");
		}
	});

	(function(){

		var controlkey = {13:"enter", 27:"esc", 37:"left", 38:"up", 39:"right", 40:"down"};
		var keycode = 0;
		var currindex = -1;
		var currcatindex = -1;
		var entryCount = 0;
		var _el;
		var currcat;
		var hasExpEntry = false;
		var isItem = false;
		var _picklist;

		function activeCategory(i){
			if( currcat ){
				$("#picklist .categorytitle").removeClass("active-category");
				currcat.removeClass("active-category");
			}
			currcat = $(".expandedentry").eq(i);
			currcat.addClass("active-category");
			$(".expandedentry.active-category").parent(".categorytitle").addClass("active-category");

			jQuery("#view").scrollTo(currcat, 0, {offset:-(jQuery("#view").height()/2)});
		}

		function activeListElement(i){
			if( _el ){
				_el.parent(".list").removeClass("list-active");
			}
			_el = $("[name='chbox']").eq(i);
			_el.attr("checked", true);
			$("[name='chbox']:checked").parent(".list").addClass("list-active");

			jQuery("#contentpane, #view").scrollTo($(".list-active"), 0, {offset:-70});
		}

		$("[name='chbox']").live('click', function(){
			currindex = -1;
			if( _el ){
				_el.parent(".list").removeClass("list-active");
			}
		});

		$(document).bind("keydown", function(e){

			keycode = e.keyCode;
			//if( !(keycode in controlkey) | e.shiftKey | e.ctrlKey ) return true;
			if( !(keycode in controlkey) | e.shiftKey ){
				return true;
			}
			//window.status = "key:"+controlkey[keycode];
			if( $("#picklist").length === 0 ){
				return true;
			}
			_picklist = $("#picklist");

			e.preventDefault();
			e.stopPropagation();

			if( keycode == 27 ){
				pickListClose();
				return false;
			}

			ctrl = e.ctrlKey;
			hasExpEntry = _picklist.find(".category").size();

			if(ctrl){
				entryCount = _picklist.find(".expandedentry").size();
			} else {
				entryCount = _picklist.find("[name='chbox']").size();
			}

			//window.status = "key:"+keycode+" / count:"+entryCount+" / ctrl:"+ctrl;

			if( entryCount>0 ){
				entryCount = (entryCount==1) ? entryCount : entryCount-1;

				switch( keycode ){
				case 38:
					if(ctrl){
						currcatindex = ( currcatindex<0 ) ? entryCount : currcatindex-1;
						activeCategory(currcatindex);
					} else {
						currindex = ( currindex<0 ) ? entryCount : currindex-1;
						activeListElement(currindex);
					}
					break;
				case 40:
					if(ctrl){
						currcatindex = ( entryCount<=currcatindex ) ? 0 : currcatindex+1;
						activeCategory(currcatindex);
					} else {
						currindex = ( entryCount<=currindex ) ? 0 : currindex+1;
						activeListElement(currindex);
					}
					break;
				case 13:
					if(ctrl){
						currcat.click();
					} else {
						$("[name='chbox']:checked").dblclick();
					}
					break;
				}
			}
		});
	})();
});


/*
 * Свертывает и развертывает секции в формах и оутлайне
 * 
 * для работы нужна следуюшая разметка
 * <div class="formkit" id="id набора полей">
		<div class="st"> //событие вешается на class st
			<div class="title">заголовок блока</div>
			<div class="pick ui-icon expand|collapse"></div> //рисунок состояния
		</div>
		<div class="section">скрываемая секция</div>
	</div>
	...
	похожее
 */
jQuery.fn.expander = function(saveMode){
	var $thisSlctr = this.selector;
	var bSaveMode = (saveMode !== false);

	return this.each(function(){
		$expander = jQuery(this);

		if(bSaveMode){
			$expander.each(function(){
				$pid = $(this).parent().attr('id');

				if (nbLocalStorage.get("expander-"+$pid)=="collapse") {
					$hidest = $('#'+$pid +' > .section');
					if($hidest.children().find(".active").length===0){
						$('#'+$pid +' '+ $thisSlctr + ' > .pick').first().removeClass('expand').addClass('collapse');
						$(this).addClass('border-collapse');
						$hidest.hide();
					}
				} else {
					nbLocalStorage.remove("expander-"+$pid);
				}
			});
		}

		$expander.bind("mousedown", function(){
			try{
				$pid = $(this).parent().attr('id');
				$hidest = $('#'+$pid +' > .section');
				$pick = $('#'+$pid +' '+ $thisSlctr +' > .pick').first();

				if ($pick.hasClass('expand')) {
					if(bSaveMode){
						nbLocalStorage.set("expander-"+$pid, "collapse", "7");
					}
					$pick.removeClass('expand').addClass('collapse');
					$(this).addClass('border-collapse');
					$hidest.slideUp('fast');
				} else {
					if(bSaveMode){
						nbLocalStorage.remove("expander-"+$pid);
					}
					$pick.removeClass('collapse').addClass('expand');
					$(this).removeClass('border-collapse');
					$hidest.show();
				}
			}catch(e){ alert(e); }
		});
	});
};


/*
 * suggestionQuery
 * 
 * squery - queryType:queryID
 * ml - мультизначимость / пока не реализовано
 * minsbl - минимальное количество символов для запроса
 */
jQuery.fn.suggestionQuery = function(_squery, ml, minsbl){

	var hsfield = jQuery(this);
	var hsfieldname = hsfield.attr('name');
	var squery = _squery.split(':');
	var queryType = squery[0];
	var queryID = squery[1];

	//Check queryType
	switch (queryType) {
	case 'view':
		break;
	case 'query':
		alert('Не реализованный тип запроса: "'+queryType+'". Поле '+hsfieldname);
		return;
		break;
	default:
		alert('not defined query type: "'+queryType+'", field '+hsfieldname);
		return;
		break;
	}

	if( minsbl == undefined ){
		minsbl = 3;
	}
	//-----

	suggestionDiv = document.createElement("DIV");
	$(suggestionDiv).addClass('suggestion');

	sfield = document.createElement("input");
	$(sfield).attr('type', 'text');
	$(sfield).attr('id', hsfieldname+'_sugg');
	$(sfield).attr('value', hsfield.attr('alt'));
	if( hsfield.attr('size') ){
		$(sfield).attr('size', hsfield.attr('size'));
	} else {
		$(sfield).attr('size', 65);
	}
	//$(sfield).css('width', hsfield.attr('size') * 7);
	var sfield = $(sfield);

	dfield = document.createElement("div");
	$(dfield).attr('id', hsfieldname+'_dsp');
	$(dfield).html(hsfield.attr('alt'));
	var dfield = $(dfield);

	suggestionviewObj = document.createElement("div");
	$(suggestionviewObj).attr('id', hsfieldname+'-suggestion-view');
	$(suggestionviewObj).attr('class', 'suggestion-view');
	var suggestionviewObj = $(suggestionviewObj);

	sfield.appendTo( suggestionDiv );
	dfield.appendTo( suggestionDiv );
	suggestionviewObj.appendTo( suggestionDiv );
	hsfield.after( $(suggestionDiv) );
	//----------------------------------------

	suggactive( hsfield.val()==='' );

	dfield.click(function(){ suggactive(true); });
	sfield.blur(function(){
		suggactive( hsfield.val()==='' );
	});

	function activeListElement(i){
		if( _el ){
			_el.parent(".suggestion-entry").removeClass("suggestion-entry-active");
		}
		_el = $("[name='suggitem"+queryID+"']").eq(i);
		_el.parent(".suggestion-entry").addClass("suggestion-entry-active");

		try {
			container.scrollTop( _el.offset().top - container.offset().top );
		} catch(e){}
	}

	function selectListElement(i){
		$(".suggestion-entry").removeClass("suggestion-entry-active");
		_el = $("[name='suggitem"+queryID+"']").eq(i);
		tvalues = _el.attr("title").replace(/(<b>)|(<\/b>)/g, '');
		insertSuggItem(_el.val(), tvalues);
		_el.attr('checked', 'checked');
		suggactive( hsfield.val()==='' );
	}

	var entryCount = 0;
	var currindex = -1;
	var _el;
	var container;
	var to;

	sfield.bind("keydown", function(e){
		if( entryCount>0 ){
			if( e.keyCode==13 || e.keyCode==37 || e.keyCode==38 || e.keyCode==39 || e.keyCode==40 ){
				container = $(".suggestion-box:visible");

				switch (e.keyCode){
				case 38:
					currindex = ( currindex<0 ) ? entryCount : currindex-1;
					activeListElement(currindex);
					break;
				case 40:
					currindex = ( entryCount<=currindex ) ? 0 : currindex+1;
					activeListElement(currindex);
					break;
				case 13:
					selectListElement(currindex);

					try {
						$(":focus").blur();
					}catch(e){}
					break;
				}
			}
		}
	}).bind("keyup", function(e){
		if( e.keyCode==13 || e.keyCode==37 || e.keyCode==38 || e.keyCode==39 || e.keyCode==40 ){
			return false;
		}

		value = $(this).val().replace(/(^\s+)|(\s+$)/g, '');

		try {
			clearTimeout(to);
		}catch(e){}

		to = setTimeout(function() { getSuggestionQuery(value); }, 400);

		suggestionviewObj.html("");
	});

	$('#'+hsfieldname+'-suggestion-view .suggestion-entry').live('mousedown', function(){
		inpf = $(this).children('[name="suggitem'+queryID+'"]');
		_el = inpf;
		activeListElement(_el.index());
		tvalues = inpf.attr("title").replace(/(<b>)|(<\/b>)/g, '');
		insertSuggItem(inpf.val(), tvalues);
		inpf.attr('checked', 'checked');
		suggactive( false );
	});

	function insertSuggItem(did, value){
		$(".suggestion-entry").removeClass("suggestion-entry-active");
		hsfield.val(did);
		sfield.val(value);
		dfield.html(value);
	}

	function getSuggestionQuery(value){

		if( value.length < minsbl ){
			window.status = "Установлено минимум "+minsbl+" символа";
			return;
		}

		$.ajax({
			   type: "get",
			   url: 'Provider?type='+queryType+'&id='+queryID+'&keyword='+value,
			   beforeSend: function(){
				   sfield.addClass('suggestion-load');
			   },
			   success: function(rdata){

				   switch (queryType) {
				   	case 'view':
				   		entry = $(rdata).find("[name='suggitem"+queryID+"']");
				   		entryCount = entry.size();
						if( entryCount>0 ){
							entryCount = (entryCount==1) ? entryCount : entryCount-1;
							rdata = rdata.replace(new RegExp(value, 'gim'), '<b>'+value+'</b>');
							suggestionviewObj.html(rdata);
						}
				   		break;
				   	case 'query':
				   		alert('Не реализованный тип запроса: '+queryType);
				   		return;
				   		break;
				   }
			   },
			   complete: function(){
				   sfield.removeClass('suggestion-load');
			   }
		});
	}

	function suggactive( b ){

		if( b ){
			suggestionviewObj.show();
			dfield.hide();
			sfield.show().focus().select();
		} else {
			suggestionviewObj.hide();
			sfield.hide();
			dfield.show();
		}
	}
};
